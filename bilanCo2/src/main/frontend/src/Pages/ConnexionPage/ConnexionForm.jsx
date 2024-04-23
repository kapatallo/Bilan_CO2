// ConnectionForm.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './ConnexionForm.css'; 
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
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" name="email" required/>
                </div>

                <div className="input-container">
                    <label htmlFor="pwd">Mot de passe:</label>
                    <input type="password" id="pwd" name="pwd" required/>
                </div>

                <div className="input-container">
                    <input type="submit" value="Se connecter" />
                </div>
            </form>
            <div className="login-link">
                <a href="/">S' inscrire</a>
            </div>
        </div>
    );
}
