import React, { useContext } from "react";
import { useEffect, useState } from "react";
import { UserResponsesContext } from "./UserResponsesContext.jsx";
import "./FirstFormQuestions.css";

export default function FirstFormQuestions(props) {
  const { userResponses, setUserResponses } = useContext(UserResponsesContext);
  const [yes, setYes] = useState(false);
  const [no, setNo] = useState(false);

  const [haveCar, setHaveCar] = useState(false);

  useEffect(() => {
    setUserResponses({ ...userResponses, haveCarResponse: haveCar });
  }, []);

  const ButtonsYesNo = () => {
    const handleYesClick = () => {
      setYes(true);
      setNo(false);
      setUserResponses({ ...userResponses, yesNoResponse: "yes" });
    };

    const handleNoClick = () => {
      setYes(false);
      setNo(true);
      setUserResponses({ ...userResponses, yesNoResponse: "no" });
    };

    return (
      <div className="question-button-container">
        <div
          className={yes ? "question-button-clicked" : "question-button"}
          onClick={handleYesClick}
        >
          {" "}
          Oui{" "}
        </div>

        <div
          className={no ? "question-button-clicked" : "question-button"}
          onClick={handleNoClick}
        >
          {" "}
          Non{" "}
        </div>
      </div>
    );
  };

  const ButtonsYesNoCar = () => {
    const handleYesClick = () => {
      setHaveCar(true);
      setUserResponses({ ...userResponses, haveCarResponse: "yes" });
    };

    const handleNoClick = () => {
      setHaveCar(false);
      setUserResponses({ ...userResponses, haveCarResponse: "no" });
    };

    return (
      <div className="question-button-container">
        <div
          className={haveCar ? "question-button-clicked" : "question-button"}
          onClick={handleYesClick}
        >
          {" "}
          Oui{" "}
        </div>

        <div
          className={haveCar ? "question-button" : "question-button-clicked"}
          onClick={handleNoClick}
        >
          {" "}
          Non{" "}
        </div>
      </div>
    );
  };

  const [options, setOptions] = useState([]);

  const apiUrlMarque =
    "https://public.opendatasoft.com/api/explore/v2.1/catalog/datasets/vehicules-commercialises/records?select=marque&where=marque%20!%3D%20%22ALFA%20ROMEO%22%20AND%20marque%20!%3D%22ROLLS%20ROYCE%22&group_by=marque&order_by=marque&limit=100";

  useEffect(() => {
    fetch(apiUrlMarque)
      .then((response) => response.json())
      .then((data) => {
        const marques = data.results.map((result) => result.marque);
        setOptions(marques);
        if (marques.length > 0) {
          setSelectedMarque(marques[0]);
          setUserResponses({ ...userResponses, marque: marques[0] });
        }
      })
      .catch((error) => console.error(error));
  }, []);

  const [selectedMarque, setSelectedMarque] = useState("");
  const [modelOptions, setModelOptions] = useState([]);

  const apiUrlModel = `https://public.opendatasoft.com/api/explore/v2.1/catalog/datasets/vehicules-commercialises/records?select=modele_dossier&where=marque%20%3D%20%27${selectedMarque}%27&group_by=modele_dossier&limit=100`;

  useEffect(() => {
    if (selectedMarque) {
      fetch(apiUrlModel)
        .then((response) => response.json())
        .then((data) => {
          const models = data.results.map((result) => result.modele_dossier);
          setModelOptions(models);
          if (models.length > 0) {
            setSelectedModel(models[0]);
            setUserResponses({ ...userResponses, model: models[0] });
          }
        })
        .catch((error) => console.error(error));
    }
  }, [selectedMarque]);

  const [selectedModel, setSelectedModel] = useState("");
  const [carburantOptions, setCarburantOptions] = useState([]);

  const apiUrlCarburant =
    "https://public.opendatasoft.com/api/explore/v2.1/catalog/datasets/vehicules-commercialises/records?select=carburant&where=marque%20%3D%20%27" +
    selectedMarque +
    "%27%20AND%20modele_dossier%20%3D%20%27" +
    selectedModel +
    "%27&group_by=carburant&limit=100";

  useEffect(() => {
    if (selectedModel) {
      fetch(apiUrlCarburant)
        .then((response) => response.json())
        .then((data) => {
          const carburants = data.results.map((result) => result.carburant);
          setCarburantOptions(carburants);
          if (carburants.length > 0) {
            setSelectedCarburant(carburants[0]);
            setUserResponses({ ...userResponses, carburant: carburants[0] });
          }
        })
        .catch((error) => console.error(error));
    }
  }, [selectedModel]);

  const [selectedCarburant, setSelectedCarburant] = useState("");

  const handleMarqueChange = (e) => {
    setSelectedMarque(e.target.value);
    setUserResponses({ ...userResponses, marque: e.target.value });
  };

  const handleModelChange = (e) => {
    setSelectedModel(e.target.value);
    setUserResponses({ ...userResponses, model: e.target.value });
  };

  const handleCarburantChange = (e) => {
    setSelectedCarburant(e.target.value);
    setUserResponses({ ...userResponses, carburant: e.target.value });
  };

  const handleInputChange = (e, question) => {
    setUserResponses({ ...userResponses, [question]: e.target.value });
  };

  return (
    <div className="question-form">
      {props.formId === 0
        ? haveCar
          ? props.data.questions[props.formId] != null &&
            props.data.questions[props.formId][1].map((question) => (
              <div key={question.questionText} className="question">
                <div className="question-text-input-container">
                  <div> {question.questionText}</div>

                  <div>
                    {question.type === "yesNo" ? (
                      <ButtonsYesNoCar />
                    ) : question.type === "dropdown" ? (
                      <select
                        className="question-input"
                        onChange={(e) => setSelectedMarque(e.target.value)}
                      >
                        {options.map((option, index) => (
                          <option key={index} value={option}>
                            {option}
                          </option>
                        ))}
                      </select>
                    ) : (
                      <input
                        className="question-input"
                        type="number"
                        onChange={(e) =>
                          handleInputChange(e, question.questionText)
                        }
                      />
                    )}
                  </div>
                </div>
                <div className="question-unit"> {question.unit}</div>
              </div>
            ))
          : props.data.questions[props.formId] != null &&
            props.data.questions[props.formId][0].map((question) => (
              <div key={question.questionText} className="question">
                <div className="question-text-input-container">
                  <div> {question.questionText}</div>

                  <div>
                    {question.type === "yesNo" ? (
                      <ButtonsYesNoCar />
                    ) : (
                      <input
                        className="question-input"
                        type="number"
                        onChange={(e) =>
                          handleInputChange(e, question.questionText)
                        }
                      />
                    )}
                  </div>
                </div>
                <div className="question-unit"> {question.unit}</div>
              </div>
            ))
        : props.data.questions[props.formId] != null &&
          props.data.questions[props.formId].map((question) => (
            <div key={question.questionText} className="question">
              <div className="question-text-input-container">
                <div> {question.questionText}</div>

                <div>
                  {question.type === "yesNo" ? (
                    <ButtonsYesNo />
                  ) : (
                    <input
                      className="question-input"
                      type="number"
                      onChange={(e) =>
                        handleInputChange(e, question.questionText)
                      }
                    />
                  )}
                </div>
              </div>
              <div className="question-unit"> {question.unit}</div>
            </div>
          ))}

      <div className="question-form">
        {haveCar && props.formId === 0 ? (
          <div>
            <div className="question">
              <div className="question-text-input-container">
                <div>Quel est la marque de votre voiture ?</div>

                <div>
                  <select
                    className="question-input"
                    onChange={(e) => handleMarqueChange(e)}
                  >
                    {options.map((option, index) => (
                      <option key={index} value={option}>
                        {option}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div></div>
        )}

        {haveCar && props.formId === 0 ? (
          <div>
            <div className="question">
              <div className="question-text-input-container">
                <div>Quel est le modèle de votre voiture ?</div>

                <div>
                  <select
                    className="question-input"
                    onChange={(e) => handleModelChange(e)}
                  >
                    {modelOptions.map((option, index) => (
                      <option key={index} value={option}>
                        {option}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div></div>
        )}
        {haveCar && props.formId === 0 ? (
          <div>
            <div className="question">
              <div className="question-text-input-container">
                <div>Carburant : </div>

                <div>
                  <select
                    className="question-input"
                    onChange={(e) => handleCarburantChange(e)}
                  >
                    {carburantOptions.map((option, index) => (
                      <option key={index} value={option}>
                        {option}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div></div>
        )}
      </div>
    </div>
  );
}
