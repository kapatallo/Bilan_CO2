// ConnectionForm.js
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import FormQuestions from "./FirstFormQuestions.jsx";
import { UserResponsesContext } from "./UserResponsesContext.jsx";
import { ReactComponent as TransportIcon } from "../../resources/icons/transport.svg";
import { ReactComponent as HouseIcon } from "../../resources/icons/house.svg";
import { ReactComponent as ArrowIcon } from "../../resources/icons/arrow.svg";
import { ReactComponent as LeafLogo } from "../../resources/logo/Logo.svg";
import { ReactComponent as CarbonPrintLogo } from "../../resources/logo/CarbonPrint.svg";
import "./FirstFormPage.css";
import axios from "axios";

export default function FirstFormPage() {
  const [userResponses, setUserResponses] = useState({});

  const [formPart, setFormPart] = useState({
    partText: "transport",
    partId: 0,
  });
  const [nextStepText, setNextStepText] = useState("Logement");

  const questions = require("./firstQuestions.json");

  let navigate = useNavigate();
  useEffect(() => {
    if (formPart.partText === "end") navigate("/Dashboard"); //quitter la page
  }, [formPart]);

  const NextStepButton = (props) => {
    return (
      <div
        className="next-step-button"
        onClick={() => {
          if (formPart.partText === "transport") {
            setNextStepText("Terminer");
            setFormPart({
              partText: "logement",
              partId: 1,
            });
            return;
          } else if (formPart.partText === "logement") {
            setFormPart({
              partText: "end",
              partId: null,
            });

            let co2 = 0;
            let co2_res = 0;
            const marque = userResponses.marque;
            const model = userResponses.model;
            const carburant = userResponses.carburant;

            const apiCo2Url =
              "https://public.opendatasoft.com/api/explore/v2.1/catalog/datasets/vehicules-commercialises/records?select=AVG(co2_g_km)&where=marque%20%3D%20%27" +
              marque +
              "%27%20and%20modele_dossier%3D%27" +
              model +
              "%27%20and%20carburant%3D%27" +
              carburant +
              "%27&group_by=marque%2C%20modele_dossier%2C%20carburant&limit=1";

            fetch(apiCo2Url)
              .then((response) => response.json())
              .then((data) => {
                if (data.results && data.results.length > 0) {
                  co2_res = Math.round(data.results[0]["AVG(co2_g_km)"]);
                }
              })
              .then(() => {
                if (userResponses.haveCarResponse === "no") {
                  co2 = null;
                } else if (userResponses.haveCarResponse === "yes") {
                  co2 = co2_res;
                }
              })
              .then(() => {
                let surface =
                  userResponses["Quelle est la surface de votre logement ?"];
                let nbPersonnesFoyer =
                  userResponses["Combien de personnes vivent chez vous ?"];
                let tailleAgglo =
                  userResponses[
                    "Taille de l'agglomération (nombre d'habitants) ?"
                  ];
                let appartement = userResponses.yesNoResponse;

                const data = {
                  idui: 0,
                  id: 1, // a recup de la base de données
                  cnit: co2,
                  nbPersFoyer: nbPersonnesFoyer,
                  surfaceLogement: surface,
                  habAgglo: tailleAgglo,
                  possedeAppartement: appartement,
                };

                // verfier d'abord si l'utilisateur est connecté et récupérer son id

                axios
                  .post(
                    "https://192.168.75.51/api/bilanco2/utilisateur/infos/",
                    data
                  )
                  .then((response) => {
                    console.log(response);
                    console.log(data);
                  });
              })
              .then(() => {
                alert("Vos réponses ont été enregistrées. Merci!");
              });
          }
        }}
      >
        <div className="next-step-button-text">
          {props.text} <ArrowIcon className="next-step-button-icon" />
        </div>
      </div>
    );
  };

  return (
    <>
      <UserResponsesContext.Provider
        value={{ userResponses, setUserResponses }}
      >
        <div className="logo">
          <LeafLogo className="logo-element" />
          <CarbonPrintLogo className="logo-element" />
        </div>
        <h1>
          {" "}
          Un premier questionnaire pour évaluer vos émissions de carbone...{" "}
        </h1>
        <div className="form">
          <div className="form-part-container">
            <div
              className={
                formPart.partText === "transport"
                  ? "form-part active"
                  : "form-part"
              }
            >
              <TransportIcon className="icon-bar" /> Transport
            </div>
            <div
              className={
                formPart.partText === "logement"
                  ? "form-part active"
                  : "form-part"
              }
            >
              <HouseIcon className="icon-bar" /> Logement
            </div>
          </div>

          <FormQuestions data={questions} formId={formPart.partId} />
          <div className="next-step-button-container">
            <NextStepButton text={nextStepText} />
          </div>
        </div>
      </UserResponsesContext.Provider>
    </>
  );
}
