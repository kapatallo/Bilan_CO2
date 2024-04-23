import React from 'react';
import { Doughnut } from 'react-chartjs-2';
import 'chart.js/auto';
import LegendItem from './LegentItem';
import './DoughnutChartComponent.css'; 


export default function DoughnutChartComponent() {
    const data = {
        labels: ['Transport', 'Alimentation', 'Logement', 'Divers'],
        datasets: [
            {
                label: 'Répartition des émissions',
                data: [40, 20, 30, 10], // Ces pourcentages devraient correspondre à vos données
                backgroundColor: [
                    '#DB48FF',
                    '#579AFF',
                    '#8146FF',
                    '#FCDDEC'
                ],
                hoverBackgroundColor: [
                    '#DB48FF',
                    '#579AFF',
                    '#8146FF',
                    '#FCDDEC'
                ],
                borderWidth: 0, 
                cutout: '60%', 
            },
        ],
    };

    const options = {
        plugins: {
            legend: {
                display: false // Désactive la légende intégrée de Chart.js
            },
            tooltip: {
                enabled: true // Active les info-bulles au survol des segments
            }
        },

        elements: {
            arc: {
                borderWidth: 0,
                hoverBorderColor: 'transparent',
                hoverOffset: 20,
        },
        onHover: (event, chartElement) => {
            event.native.target.style.cursor = chartElement[0] ? 'pointer' : 'default';
        }, 
        }
    };


    return (
        <div className='doughnut'>

            <Doughnut className='doughnut-chart' data={data} options={options}/>
            <div className="legends">
            <LegendItem color={"#DB48FF"} label={"Transport"} value={40}/>
            <LegendItem color={"#579AFF"} label={"Alimentation"} value={20}/>
            <LegendItem color={"#8146FF"} label={"Logement"} value={30}/>
            <LegendItem color={"#FCDDEC"} label={"Divers"} value={10}/>
            </div>
            <p className="doughnut-center-texte">CO2</p>
        </div>
    ); 
    
};
