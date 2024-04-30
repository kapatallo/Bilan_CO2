import React from 'react';
import NavBar from '../../NavBar/NavBar';
import { useState } from 'react';
import './SettingPage.css'
import axios from 'axios';

export default function SettingPage() {
    const [inputValue, setValue] = useState({
        nom: '',
        prenom: '',
        email: '',
        newPassword: ''
    });

const resetForm = () => {
    console.log("hey")
        setValue({
            nom: '',
            prenom: '',
            email: '',
            mdp: '',
            confmdp: ''
        })
    }

    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setValue(values => ({ ...values, [name]: value }));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.put('https://192.168.75.51/api/bilanco2/utilisateurs', inputValue, {
                headers: {
                    'mode': 'cors'
                }
            })
            console.log('User updated : ', response.setValue);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className='setting-page'>
            <NavBar />
            <div className='settings-container'>
                <p className="header-page">Pages / <span className="header-page-name">Paramètres</span></p>
                <div className='h-settings'>Vos informations</div>
                <form onSubmit={handleSubmit} className='settings-form'>
                    <ul className='settings-container'>
                        <li>
                            <label>
                                <span>Nom :</span>
                            </label>
                            <input className='input-settings' type="text" id="nom" name="nom" value={inputValue.nom} onChange={handleChange} />
                        </li>
                        <li>
                            <label>
                                <span>Prénom :</span>
                            </label>
                            <input className='input-settings' type="text" id='prenom' name="prenom" value={inputValue.prenom} onChange={handleChange} />
                        </li>
                        <li>
                            <label>
                                <span>E-mail :</span>
                            </label>
                            <input className='input-settings' type="email" id='email' name="email" value={inputValue.email} onChange={handleChange} />
                        </li>
                        <li>
                            <label>
                                <span>Mot de passe :</span>
                            </label>
                            <input className='input-settings' type="password" id='mdp' name="mdp" value={inputValue.mdp} onChange={handleChange} />
                        </li>
                        <li>
                            <label>
                                <span>Confirmer le mot de passe :</span>
                            </label>
                            <input className='input-settings' type="password" id='confmdp' name="confmdp" value={inputValue.confmdp} onChange={handleChange} />
                        </li>
                    </ul>
                    <div className="input-container-button">
                        <input type="submit" value="Modifier" />
                        <button type="button" className='annuler-button' onClick={resetForm}>Annuler</button>
                    </div>

                </form>
            </div>
        </div>
    )
}