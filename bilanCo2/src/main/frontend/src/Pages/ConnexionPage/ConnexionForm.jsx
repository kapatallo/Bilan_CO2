// ConnectionForm.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './ConnexionForm.css'; 
import LogoName from '../../resources/logo/CarbonPrint.svg';
import Logo from '../../resources/logo/Logo.svg';

export default function ConnexionForm() {
    
    let navigate = useNavigate();

    const [connexionData, setConnexionData] = useState({
        "email":null,
        "mdp":null
    })

    useEffect( () => console.log(connexionData),[connexionData]);
    
    const handleChange = (event) => {
        setConnexionData({
            ...connexionData,
            [event.target.id]: event.target.value
        });
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        // Logique de validation du formulaire
        navigate('/dashboard'); 
    }

    return (
        <div className="connexion-form-container">
            <img src={Logo} alt="CarbonPrint logo" className="logo" />
            <img src={LogoName} alt="CarbonPrint" className="logoName" />
            
            <form onSubmit={handleSubmit} className="connexion-form">

                <div className="input-container">
                    <label htmlFor="email">Email:</label>
                    <input onChange={handleChange} type="email" id="email" name="email" required/>
                </div>

                <div className="input-container">
                    <label htmlFor="pwd">Mot de passe:</label>
                    <input onChange={handleChange} type="password" id="pwd" name="pwd" required/>
                </div>

                <div className="input-container">
                    <input type="submit" value="S'inscrire" />
                </div>
            </form>
            <div className="login-link">
                <a href="/Dashboard">Se connecter</a>
            </div>
        </div>
    );
}
