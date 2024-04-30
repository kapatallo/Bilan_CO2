import React from 'react';
import './WelcomeCard.css'; 
import WelcomeCardIllustration from '../../resources/icons/welcome-card-illustration.svg';

export default function WelcomeCard({ username , top , weeklyQuestionsDone , onButtonClick}) {
    return (
        <div className="welcome-card">
            <div className="welcome-card-text">
                <h1>Salut {username},</h1>
                <p>Tu es dans le top {top}% des personnes qui émet le moins de carbone en France ce mois !</p>
                {!weeklyQuestionsDone && <button className="welcome-card-btn" onClick={onButtonClick}>
                    Lancer mon questionnaire hebdomadaire
                </button>}
            </div>
            <img src={WelcomeCardIllustration} alt="User Illustration" className="user-illustration" /> 
        </div>
    );
}
