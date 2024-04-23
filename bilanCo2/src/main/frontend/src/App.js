import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import RegisterForm from './Pages/RegisterPage/RegisterForm.jsx';
import DashboardPage from './Pages/DashboardPage/DashboardPage.jsx';
import SettingPage from './Pages/SettingPage/SettingPage.jsx';
import ComparisonPage from './Pages/ComparisonPage/ComparisonPage.jsx';
import FirstFormPage from './Pages/FirstFormPage/FirstFormPage.jsx';
import './App.css';


function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<RegisterForm />} />
          <Route path="/FirstForm"  element={<FirstFormPage />} />
          <Route path="/Dashboard" element={<DashboardPage />} />
          <Route path="/Settings" element={<SettingPage />} />
          <Route path="/Comparison" element={<ComparisonPage />} />
          <Route path="/login" element={<ConnexionForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

