import React from 'react';
import './LegendItem.css'; 

const LegendItem = ({ color, label, value }) => {
  return (
    <div className="legend-item">
      <span className="legend-color" style={{ backgroundColor: color }}></span>
      <div className="legend-text">
        <span className="legend-label">{label}</span>
        <span className="legend-value">{value}%</span>
      </div>
    </div>
  );
};

export default LegendItem;
