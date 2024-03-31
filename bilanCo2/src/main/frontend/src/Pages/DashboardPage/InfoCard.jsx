import React from 'react';
import './InfoCard.css';

export default function InfoCard({ icon, value, unit, label }) {
  return (
    <div className="info-card">
      <img src={icon} alt={label} className="info-card-icon" />
      <div className="info-card-content">
        <h2 className="info-card-value">{value} <span className="info-card-unit">{unit}</span></h2>
        <p className="info-card-label">{label}</p>
      </div>
    </div>
  );
}
