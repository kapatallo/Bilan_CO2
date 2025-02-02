// ConnectionForm.js
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./RegisterForm.css";
import LogoName from "../../resources/logo/CarbonPrint.svg";
import Logo from "../../resources/logo/Logo.svg";
import axios from "axios";
import bcrypt from "bcryptjs";

export default function RegisterForm() {
  let navigate = useNavigate();

  const [registerData, setRegisterData] = useState({
    id: 0,
    nom: null,
    prenom: null,
    email: null,
    mdp: null,
    ddn: null,
  });

  const handleChange = (event) => {
    setRegisterData({
      ...registerData,
      [event.target.id]: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("https://192.168.75.51/api/bilanco2/utilisateurs", registerData)
      .then((response) => {
        console.log(response);
        navigate("/login");
      })
      .catch(
        (error) => {
          console.log(error);
        },
        {
          headers: {
            mode: "cors",
          },
        }
      );
  };

  return (
    <div className="register-form-container">
      <img src={Logo} alt="CarbonPrint logo" className="logo" />
      <img src={LogoName} alt="CarbonPrint" className="logoName" />

      <form onSubmit={handleSubmit} className="register-form">
        <div className="input-container">
          <label htmlFor="prenom">Prénom:</label>
          <input
            onChange={handleChange}
            type="text"
            id="prenom"
            name="prenom"
            required
          />
        </div>

        <div className="input-container">
          <label htmlFor="nom">Nom:</label>
          <input
            onChange={handleChange}
            type="text"
            id="nom"
            name="nom"
            required
          />
        </div>

        <div className="input-container">
          <label htmlFor="email">Email:</label>
          <input
            onChange={handleChange}
            type="email"
            id="email"
            name="email"
            required
          />
        </div>

        <div className="input-container">
          <label htmlFor="pwd">Mot de passe:</label>
          <input
            onChange={handleChange}
            type="password"
            id="mdp"
            name="motDePasse"
            required
          />
        </div>

        <div className="input-container">
          <label htmlFor="confirmedPwd">Confirmer le mot de passe:</label>
          <input type="password" id="confmdp" name="confirmedPwd" required />
        </div>

        <div className="input-container">
          <label htmlFor="pwd">Date de naissance:</label>
          <input
            onChange={handleChange}
            type="date"
            id="ddn"
            name="date_de_naissance"
            required
          />
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
