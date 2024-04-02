import React, { useEffect, useRef }  from 'react';
import { Line } from 'react-chartjs-2';
import 'chart.js/auto';

export default function LineChartComponent({ dataChart }) {
    const chartRef = useRef();

    useEffect(() => {
        if (chartRef.current) {
            const chart = chartRef.current;
            const ctx = chart.canvas.getContext('2d');
            const gradient = ctx.createLinearGradient(0, 0, 0, chart.canvas.offsetHeight);
            gradient.addColorStop(0, 'rgba(45, 96, 255, 0.4)'); 
            gradient.addColorStop(0.8, 'rgba(45, 96, 255, 0)');
            chart.data.datasets[0].backgroundColor = gradient;
            chart.update();
        }
    }, [dataChart]);

    const data = {
        labels: ['Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Jan','Feb'],
        datasets: [
            {
                label: '',
                data: dataChart,
                fill: true,
                backgroundColor: 'rgba(0, 0, 0, 0)',
                borderColor: '#1814F3',
                pointBorderColor: '#303f9f',
                pointBackgroundColor: '#ffffff',
                pointHoverBackgroundColor: '#303f9f',
                pointHoverBorderColor: '#ffffff',
                tension: 0.4,
                pointBorderWidth: 2,
                pointRadius: 4,
                pointHoverRadius: 6
            },
        ],
    };

    const options = {
        plugins: {
            legend: {
                display: false 
            },
            tooltip: {
                enabled: true 
            }
        },
        scales: {
        y: {
            beginAtZero: true,
            ticks: {
                // Définir manuellement les valeurs de tick
                callback: function(value, index, values) {
                    const allowedValues = [0, 200, 400, 600, 800];
                    if (allowedValues.includes(value)) {
                        return value;
                    }
                    return null;
                },
                color: '#9e9e9e', 
                stepSize: 200, 
                min: 0,
                max: 800
            },
            grid: {
                drawBorder: false,
                color: function(context) {
                    if (context.tick.value === 0)
                        return 'rgba(0, 0, 0, 0)'; 
                    return 'rgba(0, 0, 0, 0.1)';
                }
            },
        },
            x: {
                grid: {
                    display: false, // Cacher les lignes de grille de l'axe X
                },
                ticks: {
                    color: '#9e9e9e' // Couleur des étiquettes de l'axe X
                }
            }
        }
    };

    return <Line ref={chartRef} data={data} options={options} style={{ margin:'40px' }}/>;
}
