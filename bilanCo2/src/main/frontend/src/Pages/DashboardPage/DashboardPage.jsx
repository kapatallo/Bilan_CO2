import React,{ useState,useEffect } from 'react';
import axios from 'axios';
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


function getCurrentWeek() {
    const currentTime = new Date()
    const yearStart = new Date('January 1, 2024 00:00:00')

    return Math.floor(((currentTime-yearStart)/86400000) /7)
}

export default function DashboardPage() {

    const [showPopup, setShowPopup] = useState(false);
    const [ weeklyDone, setWeeklyDone]=useState(false)
    const handleOpenPopup = () => {
      setShowPopup(true);
    };
  
    const handleClosePopup = () => {
      setShowPopup(false);
    };

    const handleUpdateEmissionsMap = (emissionsMap) => {
        if (!weeklyDone){
            console.log("Emissions Map Updated:", emissionsMap);
            axios.post('https://192.168.75.51/api/bilanco2/utilisateurs', emissionsMap)
            .then((response) => {
                console.log(response);
            },{
                headers: {
                    'mode': "cors"
                }
            })

        setWeeklyDone(true)
        }
        else{
            // ne pas envoyer de requete 
        }

    };

    // co2 moyen emit par une personne par mois en france
    const averageMonthlyEmission = 741;
    
    const calculateTopPercentile = (emission) => {
        // The closer the emission to 0, the better the ranking
        // We will use a simple linear approximation here for the example
        let topPercentile = ((emission / averageMonthlyEmission) * 100);
        topPercentile = Math.max(0, topPercentile); // Ensure it doesn't go below 0
        topPercentile = Math.min(topPercentile, 100); // Ensure it doesn't exceed 100
        return topPercentile.toFixed(2); // Format to 2 decimal places
    };

    // Function to calculate the quota based on the average of the last four values in data_chart
    const calculateQuota = (emissions, dataPoints) => {
        const lastFourValues = dataPoints.slice(-4);
        const average = lastFourValues.reduce((acc, val) => acc + val, 0) / lastFourValues.length;
        const quotaPercentage = (emissions / average) * 100;
        return quotaPercentage.toFixed(0); // Convert to a percentage and format
    };

    // Function to calculate the difference percentage between the last two values of data_chart
    const calculateDifference = (dataPoints) => {
        if (dataPoints.length < 2) {
            return 0; // Cannot calculate difference with less than two data points
        }
        const lastValue = dataPoints[dataPoints.length - 1];
        const secondLastValue = dataPoints[dataPoints.length - 2];

        if (secondLastValue === 0) {
            return lastValue > 0 ? 100 : 0; // If last is greater than 0 and second last is 0, it's a 100% increase
        }
        
        const diffPercentage = ((lastValue - secondLastValue) / secondLastValue) * 100;
        return diffPercentage.toFixed(0); // Convert to a percentage and format
    };

    const [username, setUsername] = useState("Jawad Mecheri");
    const [carboneEmit, setCarboneEmit] = useState(34);
    const [top, setTop] = useState(calculateTopPercentile(carboneEmit));
    const [co2Km, setCo2Km] = useState(2);
    const [choix, setChoix] = useState(80);
    const [data_chart, setData_chart] = useState([100, 400, 300, 800, 400, 200, 400, 300]);
    const [quota, setQuota] = useState(() => calculateQuota(carboneEmit, data_chart));
    const [diff, setDiff] = useState(() => calculateDifference(data_chart));
    const [weeklyQuestionsDone, setWeeklyQuestionsDone] = useState(false)
    
    const [transportEmit,setTransportEmit] = useState(0);
    const [logementEmit,setLogementEmit] = useState(0);
    const [aliementationEmit,setAliementationEmit] = useState(0);
    const [diversEmit,setDiversEmit] = useState(0);
    
    const [data_repartition, setData_repartition] = useState([10, 40, 30, 20]);
    
    var weekNb = getCurrentWeek();
    useEffect( () => {

        //Semaines stockées dans l'ordre décroissant : 
        // Case 0 : semaine actuelle
        // Case 1 : semaine dernière
        // ...

        var eightWeeksCarboneEmit = [
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                divers: 0
            },
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                divers: 0
            },
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                divers: 0
            },
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                diverse: 0
            },
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                divers: 0
            },
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                divers: 0
            },
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                divers: 0
            },
            {
                transport: 0,
                logement: 0,
                alimentation: 0,
                diverse: 0
            }
              
        ]
        
        eightWeeksCarboneEmit.map( (weeklyCarbonEmit) => {
            const weeklyCarbonEmitValues = Object.keys(weeklyCarbonEmit).map ( (key) => {
                axios.get(`https://192.168.75.51/api/bilanco2/${key}s/search/findByIdent`,
                { params: {
                    annee:2024,
                    semaine:weekNb,
                    id:13
                    }
                })
                .then( (response) => {
                    // en cas de réussite de la requête
                    console.log(response);
                    return response
                })
                .catch( (error) => {
                    // en cas d’échec de la requête
                    console.log(error);
                })
            })
            weeklyCarbonEmit.transport = weeklyCarbonEmitValues[0]
            weeklyCarbonEmit.logement = weeklyCarbonEmitValues[1]
            weeklyCarbonEmit.alimentation = weeklyCarbonEmitValues[2]
            weeklyCarbonEmit.divers = weeklyCarbonEmitValues[3]
            weekNb--;
        })

        //Si tous les champs de la dernière case du tableau (la semaine actuelle) sont à 0n
        //On considère que le questionnaire n'a pas été fait 
        if( eightWeeksCarboneEmit[7].transport === 0 &&
            eightWeeksCarboneEmit[7].logement === 0 &&
            eightWeeksCarboneEmit[7].alimentation === 0 &&
            eightWeeksCarboneEmit[7].divers === 0) {
            setWeeklyQuestionsDone(false)
        }

        setCarboneEmit( eightWeeksCarboneEmit.reduce( (acc, currentValue) => {
                Object.keys(currentValue).forEach ( (key,index) => {
                    acc = acc + currentValue[key]

                    if(index == 0){
                        setTransportEmit( transportEmit + currentValue[key])
                    } else if(index == 1) {
                        setLogementEmit( logementEmit + currentValue[key])
                    } else if(index == 2) {
                        setAliementationEmit( aliementationEmit + currentValue[key])
                    } else if(index == 3) {
                        setDiversEmit( diversEmit + currentValue[key])
                    }
                })
            },0)
        )

        setData_repartition([transportEmit/carboneEmit,
                            logementEmit/carboneEmit,
                            aliementationEmit/carboneEmit,
                            diversEmit/carboneEmit])

            
    }, [])

    const doughnutColor_diff = diff >= 0 ? "#71DDB1" : "#DD7171";

    
    // If 'carboneEmit' changes, update 'top'
    useEffect(() => {
        setTop(calculateTopPercentile(carboneEmit));
    }, [carboneEmit]);

    // Recalculate quota when carboneEmit or data_chart changes
    useEffect(() => {
        setQuota(calculateQuota(carboneEmit, data_chart));
    }, [carboneEmit, data_chart]);
    
        useEffect(() => {
        setDiff(calculateDifference(data_chart));
    }, [data_chart]);

    return (
        
        <div className="dashboard-page">
            <WeeklyPopup show={showPopup} onClose={handleClosePopup} onUpdateEmissionsMap={handleUpdateEmissionsMap} />
            <NavBar />
            <div className="dashboard-content">
                <p className="header-page">Pages / <span className="header-page-name">Dashboard</span></p>
                <h2 className="page-title">Dashboard</h2>
                <WelcomeCard username={username} top={top} questionButton={weeklyQuestionsDone} onButtonClick={handleOpenPopup} />
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
                        <DoughnutChartComponent data={data_repartition} />
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
                        <div className='diff_percentage_text'><h1 className="diff_percentage_text_title">Cette semaine</h1>{data_chart[6]} kgs la semaine passé.</div>
                        </div>  

                    </div>
                </div>

            </div>
            <div className="dashboard-info">
            </div>
        </div>
          )
}