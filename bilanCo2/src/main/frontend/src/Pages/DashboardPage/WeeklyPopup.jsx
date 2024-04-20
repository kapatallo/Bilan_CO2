import { useState } from "react";
import './WeeklyPopup.css'; 
import FormQuestions from "../FirstFormPage/FirstFormQuestions";
export default function WeeklyPopup({ show, onClose }) {
  
  const questions = require('./questions.json');
  const [formPart, setFormPart] = useState({
    "partText":"transport",
    "partId":0
});

    if (!show) {
      return null;
    }
    return (
      <div className="popup-overlay">
        <div className="popup">
          <button className="close-btn" onClick={onClose}>×</button>
          <FormQuestions data={questions} formId={formPart.partId} />
        </div>
      </div>
    );
  }
  