import React from 'react';
import './DashboardPage.css'; 
import NavBar from '../../NavBar/NavBar';
import WelcomeCard from './WelcomeCard';
import InfoCard from "./InfoCard";
import CarbonIcon from "../../resources/icons/carbon.svg";
import FeetIcon from "../../resources/icons/feet.svg";
import EcoIcon from "../../resources/icons/eco.svg";
import LineChartComponent from './LineChartComponent';
import DoughnutChartComponent from './DoughnutChartComponent';



export default function DashboardPage() {
    const username = "Jawad Mecheri";
    const top = 40;
    const carboneEmit= 434 ;
    const co2Km = 2; 
    const choix = 80;
    const data_chart = [100, 400, 300, 800, 400, 200, 400,300];

    return (
        <div className="dashboard-page">
            <NavBar />
            <div className="dashboard-content">
                <p className="header-page">Pages / <span className="header-page-name">Dashboard</span></p>
                <h2 className="page-title">Dashboard</h2>
                <WelcomeCard username={username} top={top} />
                <div className="dashboard-stats">
                        <InfoCard
                            icon={CarbonIcon}
                            value= {carboneEmit}
                            unit="kgs"
                            label="De carbone émit ce mois-ci"
                        />
                        <InfoCard
                            icon={FeetIcon}
                            value={co2Km}
                            unit="Kgs/100km"
                            label="De CO2 moyennes par kilomètres parcouru"
                        />
                        <InfoCard
                            icon={EcoIcon}
                            value={choix}
                            unit="%"
                            label="De choix éco-friendly"
                        />
                </div>

                <h2 className="emission-history">Historique d’émission</h2>
                <div className="chart-container">
                    <LineChartComponent dataChart={data_chart} />
                </div>
                
                <div className="doughnut-container">
                    <h2 className='doughnut-title'>Répartition des émissions</h2>
                    <DoughnutChartComponent />
                </div>

            </div>
            <div className="dashboard-info">
            </div>
        </div>
          )
}