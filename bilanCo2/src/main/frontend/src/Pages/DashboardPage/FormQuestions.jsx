import './FormQuestions.css'; 
import React, { useState } from 'react';

export default function FormQuestions({ data, formId, onEmissionsCalculated }) {
    const [inputs, setInputs] = useState({});

    const handleInputChange = (e, questionId) => {
        const value = e.target.value;
        setInputs(prev => ({
            ...prev,
            [questionId]: value
        }));

        // Trigger recalculation every time an input changes
        calculateEmissions(questionId, value);
    };

    const calculateEmissions = (changedQuestionId, changedValue) => {
        // Start with previously calculated total, adjust only the changed item
        let totalEmissions = Object.keys(inputs).reduce((sum, questionId) => {
            if (questionId === changedQuestionId) {
                // Skip the changed item as we'll add its updated value separately
                return sum;
            }
            const question = data.questions[formId].find(q => q.id === questionId);
            return sum + (inputs[questionId] * (question?.co2 || 0));
        }, 0);

        // Add the changed item's updated emissions
        const changedQuestion = data.questions[formId].find(q => q.id === changedQuestionId);
        totalEmissions += (changedValue * (changedQuestion?.co2 || 0));

        onEmissionsCalculated(totalEmissions);
    };

    return (
        <div className='question-form'>
            {data.questions[formId]?.map(question => (
                <div key={question.id} className='question'>
                    <div className='question-text-input-container'>
                        <div className='question-text'> {question.questionText}</div>
                        <input
                            type="number"
                            className='question-input'
                            value={inputs[question.id] || ''}
                            onChange={(e) => handleInputChange(e, question.id)}
                        />
                    </div>
                    <div className="question-unit"> {question.unit}</div>
                </div>
            ))}
        </div>
    );
}
