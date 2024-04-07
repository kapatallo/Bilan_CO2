import './WeeklyPopup.css'; 

export default function WeeklyPopup({ show, onClose }) {
    if (!show) {
      return null;
    }
  
    return (
      <div className="popup-overlay">
        <div className="popup">
          <button className="close-btn" onClick={onClose}>×</button>
            helloooooo
        </div>
      </div>
    );
  }
  