// ConnectionForm.js
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./ConnexionForm.css";
import LogoName from "../../resources/logo/CarbonPrint.svg";
import Logo from "../../resources/logo/Logo.svg";
import bcrypt, { hash } from "bcryptjs";
import axios from "axios";

export default function ConnexionForm() {
  let navigate = useNavigate();

  const [connexionData, setConnexionData] = useState({
    email: null,
    mdp: null,
  });

  const handleChange = (event) => {
    setConnexionData({
      ...connexionData,
      [event.target.id]: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post(`https://192.168.75.51/api/bilanco2/login`, {
        email: connexionData.email,
        mdp: connexionData.pwd,
      })
      .then((response) => {
        const userFromDb = response.data;

        console.log(userFromDb);

        if (userFromDb || userFromDb.length == 0) {
          navigate("/dashboard");
        }
      })
      .catch((error) => {
        alert("Invalid email or password");
        console.error("Invalid email or password");
      });
  };

  return (
    <div className="connexion-form-container">
      <img src={Logo} alt="CarbonPrint logo" className="logo" />
      <img src={LogoName} alt="CarbonPrint" className="logoName" />

      <form onSubmit={handleSubmit} className="connexion-form">
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
            id="pwd"
            name="pwd"
            required
          />
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
