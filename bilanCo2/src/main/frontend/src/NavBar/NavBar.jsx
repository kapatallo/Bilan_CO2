import React from 'react';
import { NavLink } from 'react-router-dom';
import './NavBar.css'; 

import Logo from "../resources/logo/Logo.svg";
import  LogoName from "../resources/logo/CarbonPrint.svg";
import { ReactComponent as DashboardIcon } from '../resources/icons/home.svg';
import { ReactComponent as ComparisonIcon } from '../resources/icons/comparison.svg';
import { ReactComponent as SettingsIcon } from '../resources/icons/settings.svg';
import { ReactComponent as LogoutIcon } from '../resources/icons/logout.svg';


const NavBar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <img src={Logo} alt="Logo" className="Logo" />
        <img src={LogoName} alt="LogoName" className="LogoName" />
      </div>
      <ul className="nav-links">
        <li>
          <NavLink to="/Dashboard" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
          <DashboardIcon className="icon" />
          Dashboard
          </NavLink>
        </li>
        <li>
        <NavLink to="/comparison" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
          <ComparisonIcon className="icon" />
          Comparison
        </NavLink>

        </li>
        <li>
          <NavLink to="/settings" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
          <SettingsIcon className="icon" />
            Paramètres
          </NavLink>
        </li>
        <li>
          <NavLink to="/logout" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
          <LogoutIcon className="icon" />
            Déconnexion
          </NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default NavBar;