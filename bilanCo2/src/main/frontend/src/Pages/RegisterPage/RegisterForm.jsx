// ConnectionForm.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './RegisterForm.css'; 
import LogoName from '../../resources/logo/CarbonPrint.svg';
import Logo from '../../resources/logo/Logo.svg';

export default function RegisterForm() {
    
    let navigate = useNavigate();
    
    const handleSubmit = (event) => {
        event.preventDefault();
        // Logique de validation du formulaire
        navigate('/dashboard'); 
    }

    return (
        <div className="register-form-container">
            <img src={Logo} alt="CarbonPrint logo" className="logo" />
            <img src={LogoName} alt="CarbonPrint" className="logoName" />
            
            <form onSubmit={handleSubmit} className="register-form">
                <div className="input-container">
                    <label htmlFor="prenom">Prénom:</label>
                    <input type="text" id="prenom" name="prenom" required/>
                </div>
                
                <div className="input-container">
                    <label htmlFor="nom">Nom:</label>
                    <input type="text" id="nom" name="nom" required/>
                </div>

                <div className="input-container">
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" name="email" required/>
                </div>

                <div className="input-container">
                    <label htmlFor="pwd">Mot de passe:</label>
                    <input type="password" id="pwd" name="pwd" required/>
                </div>

                <div className="input-container">
                    <label htmlFor="confirmedPwd">Confirmer le mot de passe:</label>
                    <input type="password" id="confirmedPwd" name="confirmedPwd" required/>
                </div>

                <div className="input-container">
                    <input type="submit" value="S'inscrire" />
                </div>
            </form>
            <div className="login-link">
                Oui, j'ai un compte ? <a href="/login">Se connecter</a>
            </div>
        </div>
    );
}
