import React,{ useState } from 'react';
import './DashboardPage.css'; 
import NavBar from '../../NavBar/NavBar';
import WelcomeCard from './WelcomeCard';
import InfoCard from "./InfoCard";
import CarbonIcon from "../../resources/icons/carbon.svg";
import FeetIcon from "../../resources/icons/feet.svg";
import EcoIcon from "../../resources/icons/eco.svg";
import LineChartComponent from './LineChartComponent';
import DoughnutChartComponent from './DoughnutChartComponent';
import SemiCircleDoughnutChart from './SemiCircleDoughnutChart';
import SemiCircleDoughnutChartDiff from './SemiCircleDoughnutChartDiff';
import WeeklyPopup from "./WeeklyPopup"


export default function DashboardPage() {

    const [showPopup, setShowPopup] = useState(false);

    const handleOpenPopup = () => {
      setShowPopup(true);
    };
  
    const handleClosePopup = () => {
      setShowPopup(false);
    };

    const username = "Jawad Mecheri";
    const top = 40;
    const carboneEmit= 434 ;
    const co2Km = 2; 
    const choix = 80;
    const data_chart = [100, 400, 300, 800, 400, 200, 400,300];
    const quota = 86;
    const diff = 84;
    const doughnutColor_diff = diff >= 0 ? "#71DDB1" : "#DD7171";

    return (
        
        <div className="dashboard-page">
            <WeeklyPopup show={showPopup} onClose={handleClosePopup} />
            <NavBar />
            <div className="dashboard-content">
                <p className="header-page">Pages / <span className="header-page-name">Dashboard</span></p>
                <h2 className="page-title">Dashboard</h2>
                <WelcomeCard username={username} top={top} onButtonClick={handleOpenPopup} />
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
                
                <div className="emission-stats">
                    <div className="doughnut-container">
                        <h2 className='doughnut-title'>Répartition des émissions</h2>
                        <DoughnutChartComponent />
                    </div>
                    <div className="emission-percentages">
                        <div className="semicircle-DoughnutChart">
                            <SemiCircleDoughnutChart
                                percentage_doughnut={quota}
                                className="semiCircle-qota-percentage"
                                doughnutColor="#8A56AC"

                            />
                            <p className='quota_percentage'>{quota}%</p>
                            <p className='quota_percentage_text'>Vous avez déjà émis {quota}% du quota de carbone prévu pour ce mois-ci.</p>
                        </div>
                        <div className="semicircle-DoughnutChart">
                        <SemiCircleDoughnutChartDiff
                            percentage_doughnut={diff}
                            className="semiCircle-diff-percentage"
                            doughnutColor={doughnutColor_diff} 
                        />
                            <p className='diff-percentage'>{diff}%</p>
                            <p className='diff-text'>
                                {diff > 0 ? `Incrementation` : `Decrementation`}
                            </p>                            
                        <p className='diff_percentage_text'><h1 className="diff_percentage_text_title">Ce mois-ci</h1>{data_chart[7]} kgs le mois passé à la même semaine du mois.</p>
                        </div>  

                    </div>
                </div>

            </div>
            <div className="dashboard-info">
            </div>
        </div>
          )
}