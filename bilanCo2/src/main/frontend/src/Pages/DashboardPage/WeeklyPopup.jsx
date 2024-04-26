import React, { useState, useEffect } from "react";
import './WeeklyPopup.css'; 
import FormQuestions from "./FormQuestions";
import FoodSearchComponent from "./FoodSearchComponent";
import DiversSearchComponent from "./DiversSearchComponent";
import { ReactComponent as TransportIcon } from '../../resources/icons/transport.svg';
import { ReactComponent as HouseIcon } from '../../resources/icons/house.svg';
import { ReactComponent as FoodIcon } from '../../resources/icons/food.svg';
import { ReactComponent as CubeIcon } from '../../resources/icons/cube.svg';
import { ReactComponent as ArrowIcon } from '../../resources/icons/arrow.svg';
import { ReactComponent as DoneIcon } from '../../resources/icons/done.svg';


export default function WeeklyPopup({ show, onClose, onUpdateEmissionsMap }) {
  
  const questions = require('./questions_weekly.json');
  const [formPart, setFormPart] = useState({
    "partText":"transport",
    "partId":0
  });

  const [nextStepText, setNextStepText] = useState("Logement");
  const [selectedFoods, setSelectedFoods] = useState([]);
  const [selectedDiversItems, setSelectedDiversItems] = useState([]);
  const [emissionsMap, setEmissionsMap] = useState({
    transport: 0,
    logement: 0,
    alimentation: 0,
    divers: 0
  });

  const [currentEmissions, setCurrentEmissions] = useState(0); // Track current tab's emissions
  const [isQuestionnaireFinished, setIsQuestionnaireFinished] = useState(false);

  useEffect(() => {
    if (isQuestionnaireFinished) {
      onUpdateEmissionsMap(emissionsMap);
    }
  }, [isQuestionnaireFinished, emissionsMap, onUpdateEmissionsMap]);

  if (!show) {
    return null;
  }
  const handleEmissionsCalculated = (emissions) => {
    setCurrentEmissions(emissions);
  };
  
  const handleFoodSelected = (item) => {
    setSelectedFoods(prevItems => {
      const newItems = [...prevItems, item];
      calculateFoodEmissions(newItems);
      return newItems;
    });
  };
  
  const handleFoodDelete = (index) => {
    setSelectedFoods(prevItems => {
      const newItems = prevItems.filter((item, i) => i !== index);
      calculateFoodEmissions(newItems);
      return newItems;
    });
  };
  
  const handleDiversItemSelected = (item) => {
    setSelectedDiversItems(prevItems => {
      const newItems = [...prevItems, item];
      calculateDiversEmissions(newItems);
      return newItems;
    });
  };
  
  const handleDiversItemDelete = (index) => {
    setSelectedDiversItems(prevItems => {
      const newItems = prevItems.filter((_, i) => i !== index);
      calculateDiversEmissions(newItems);
      return newItems;
    });
  };
  
  const calculateFoodEmissions = (items) => {
    const totalEmissions = items.reduce((sum, item) => {
      return sum + (item.quantity * item.Total_poste_non_décomposé);
    }, 0);
    setCurrentEmissions(totalEmissions); 
  };
  
  const calculateDiversEmissions = (items) => {
    const totalEmissions = items.reduce((sum, item) => {
      return sum + (item.quantity * item.Total_poste_non_décomposé);
    }, 0);
    setCurrentEmissions(totalEmissions); 
  };
  
  const handleCompletion = () => {
    setIsQuestionnaireFinished(true);
    setTimeout(() => {
      onClose();
    }, 5000);
  };
  
  
  const NextStepButton = (props) => {
    return <div className="next-step-button"
    onClick={ () => {
      const updatedMap = {
        ...emissionsMap,
        [formPart.partText]: currentEmissions
      };
      setEmissionsMap(updatedMap);
      if (formPart.partText !== "end") {
        switch (formPart.partText) {
          case "transport":
          setNextStepText("Alimentation");
          setFormPart({ "partText": "logement", "partId": 1 });
          break;
          case "logement":
          setNextStepText("Divers");
          setFormPart({ "partText": "alimentation", "partId": 2 });
          break;
          case "alimentation":
          setNextStepText("Terminer");
          setFormPart({ "partText": "divers", "partId": 3 });
          break;
          default:
          handleCompletion();
          break;
        }
      }else{
        handleCompletion();
      }
    }}>
    <div className='next-step-button-text'>
    {props.text} <ArrowIcon className="next-step-button-icon"/>
    </div>
    </div>
  }
  
  
  return (
    <div className="popup-overlay">
    {isQuestionnaireFinished ? (
      <div className="done-popup">
      <button className="close-btn" onClick={onClose}>×</button>
      <DoneIcon className="done-icon"/>
      <p className="done-text">Votre dashboard  a été mis à jour !</p>
      </div>
    ) : (
      <div className="popup">
      <button className="close-btn" onClick={onClose}>×</button>
      <div className='form-part-container'>
      <div className={formPart.partText === "transport" ? 'form-part active' : 'form-part'}>
      <TransportIcon className="icon-bar"/> Transport
      </div>
      <div className={formPart.partText === "logement" ? 'form-part active' : 'form-part'}>
      <HouseIcon className="icon-bar"/> Logement
      </div>
      <div className={formPart.partText === "alimentation" ? 'form-part active' : 'form-part'}>
      <FoodIcon className="icon-bar"/> Alimentation
      </div>
      <div className={formPart.partText === "divers" ? 'form-part active' : 'form-part'}>
      <CubeIcon className="icon-bar"/> Divers
      </div>
      </div>
      <FormQuestions
      data={questions}
      formId={formPart.partId}
      onEmissionsCalculated={handleEmissionsCalculated}
      /> 
      
      {formPart.partText === "alimentation" && (
        <div className="alimentation-section">
        <p className="search-title">Qu'est ce que vous avez mangé cette semaine ?</p>
        <FoodSearchComponent onItemSelected={handleFoodSelected} />
        <div className="selected-items-container">
        {selectedFoods.map((item, index) => (
          <div key={index} className="selected-item-card">
          <div className="item-info">
          <div className="item-text">
          <span>{item.label}</span><span>{item.quantity} kg(s)</span>
          </div>
          <button className="delete-btn" onClick={() => handleFoodDelete(index)}>-</button>
          </div>
          </div>
        ))}
        
        </div>
        </div>
      )}
      {formPart.partText === "divers" && (
        <div className="divers-section">
        <p className="search-title">Veuillez entrer vos divers achats de la semaine :</p>
        <DiversSearchComponent onItemSelected={handleDiversItemSelected} />
        <div className="selected-items-container">
        {selectedDiversItems.map((item, index) => (
          <div key={index} className="selected-item-card">
          <div className="item-info">
          <div className="item-text">
          <span>{item.label}</span><span>{item.quantity} Unt(s)</span>
          </div>
          <button className="delete-btn" onClick={() => handleDiversItemDelete(index)}>-</button>
          </div>
          </div>
        ))}
        </div>
        </div>
      )}
      <div className='wp-next-step-button-container'>
      <NextStepButton text={nextStepText} onClick={() => { /* Handle next step */ }}/>
      </div>
      </div>
    )}
    </div>
  );
}