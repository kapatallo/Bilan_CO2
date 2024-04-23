import React from 'react';
import { Doughnut } from 'react-chartjs-2';
import 'chart.js/auto';

export default function SemiCircleDoughnutChart({ percentage_doughnut, doughnutColor }) {
  const percentage = percentage_doughnut; 
  const data = {
    datasets: [
      {
        data: [percentage, 100 - percentage],
        backgroundColor: [
          doughnutColor, 
          '#D9E8F5',
        ],
        borderColor: ['transparent', 'transparent'],
        cutout: '70%',
        rotation: 260,
        circumference: 200,
        borderRadius: 5,

      },
    ],
  };

  const options = {
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        enabled: false,
      },
    },
    maintainAspectRatio: false,
    responsive: true,
  };

  return (
<div style={{width:"150px", height:"100px",}}>
      <Doughnut data={data} options={options}  />
</div>
  );
}
