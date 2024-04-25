// ConnectionForm.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import FormQuestions from './FirstFormQuestions.jsx';
import { ReactComponent as TransportIcon } from '../../resources/icons/transport.svg';
import { ReactComponent as HouseIcon } from '../../resources/icons/house.svg';
import { ReactComponent as FoodIcon } from '../../resources/icons/food.svg';
import { ReactComponent as CubeIcon } from '../../resources/icons/cube.svg';
import { ReactComponent as ArrowIcon } from '../../resources/icons/arrow.svg';
import { ReactComponent as LeafLogo } from '../../resources/logo/Logo.svg';
import { ReactComponent as CarbonPrintLogo } from '../../resources/logo/CarbonPrint.svg';
import './FirstFormPage.css'; 

export default function FirstFormPage() {
    
    const [formPart, setFormPart] = useState({
        "partText":"transport",
        "partId":0
    });
    const [nextStepText, setNextStepText] = useState("Logement");

    const questions = require('./questions.json');
    
    let navigate = useNavigate();
    useEffect( () => {
        if(formPart.partText === "end") navigate('/Dashboard'); //quitter la page
    }
    ,[formPart])

    const NextStepButton = (props) => {
        return <div className="next-step-button"
                onClick={ () => {
                        if(formPart.partText === "transport") { 
                            setNextStepText("Alimentation") 
                            setFormPart({
                                "partText":"logement",
                                "partId":1
                            })
                            return
                        } 
                        else if(formPart.partText === "logement") { 
                            setNextStepText("Divers") 
                            setFormPart({
                                "partText":"alimentation",
                                "partId":2
                            })
                            return
                        }
                        else if(formPart.partText === "alimentation") { 
                            setNextStepText("Terminer") 
                            setFormPart({
                                "partText":"divers",
                                "partId":3
                            })
                            return
                        }
                        else if(formPart.partText === "divers") { 
                            setFormPart({
                                "partText":"end",
                                "partId":null
                            })
                        }
                    }
                }> 
                    <div className='next-step-button-text'> 
                        {props.text} <ArrowIcon className="next-step-button-icon"/>  
                    </div>
                </div>
    }




    return (
        <>
            <div className="logo">
                <LeafLogo className="logo-element"/>
                <CarbonPrintLogo className="logo-element" />
            </div>
            <h1> Un premier questionnaire pour évaluer vos émissions de carbone... </h1>
            <div className='form'>
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

                <FormQuestions data={questions} formId={formPart.partId} />
                <div className='next-step-button-container'>
                    <NextStepButton text={nextStepText}/>
                </div>
            </div>
                    
            
        </>
    );
}
