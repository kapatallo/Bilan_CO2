// ConnectionForm.js
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./ConnexionForm.css";
import LogoName from "../../resources/logo/CarbonPrint.svg";
import Logo from "../../resources/logo/Logo.svg";
import bcrypt from "bcryptjs";

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

    // Normalement on doit récupérer mdp de l'utilisateur grace son email
    // hash pour test avec mdp = 12345
    const testmdp =
      "$2a$10$bS9JCm/UT5T5aLiLAWcmNerZF7LjVfpjhBReDsZWzbQcl5J6nUpOK";

    bcrypt.compare(connexionData.pwd, testmdp, function (err, result) {
      if (err) {
        console.error(err);
        return;
      }

      if (result) {
        // Passwords match
        navigate("/dashboard");
      } else {
        // Passwords don't match
        alert("Invalid password");
        console.error("Invalid password");
      }
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
