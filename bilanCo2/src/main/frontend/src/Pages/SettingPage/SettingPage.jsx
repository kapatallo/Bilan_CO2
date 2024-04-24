import React from 'react';
import NavBar from '../../NavBar/NavBar';
import { useState } from 'react';
import { Form } from 'react-router-dom';
import './SettingPage.css'

export default function SettingPage() {
    const [inputValue, setValue] = useState('');

    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setValue(values => ({ ...values, [name]: value }));
    }
    return (
        <div>
            <h1>Paramètres</h1>
            <h2>Vos informations</h2>
            <form>
                <ul className='settings-container'>
                    <li>
                        <label>
                            <span>Nom :</span>
                        </label>
                        <input type="text" name="name" value={inputValue.name} onChange={handleChange} />
                    </li>
                    <li>
                        <label>
                            <span>Prénom :</span>
                        </label>
                        <input type="text" name="firstnmame" value={inputValue.firstnmame} onChange={handleChange} />
                    </li>
                    <li>
                        <label>
                            <span>E-mail :</span>
                        </label>
                        <input type="email" name="email" value={inputValue.email} onChange={handleChange} />
                    </li>
                    <li>
                        <label>
                            <span>Mot de passe :</span>
                        </label>
                        <input type="password" name="mdp" value={inputValue.mdp} onChange={handleChange} />
                    </li>
                    <li>
                        <label>
                            <span>Confirmer le mot de passe :</span>
                        </label>
                        <input type="password" name="confmdp" value={inputValue.confmdp} onChange={handleChange} />
                    </li>
                </ul>
                <div className="input-container-button">
                    <input type="submit" value="Modifier" />
                    <input type="submit" value="Annuler" />
                </div>

            </form>
        </div>
    )
}