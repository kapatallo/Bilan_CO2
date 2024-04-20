import { useState } from 'react';
import './FormQuestions.css'; 

export default function FirstFormQuestions(props) {
    const ButtonsYesNo = () => {
        const [yes,setYes] = useState(false);
        const [no,setNo] = useState(false);

        return (
            
            <div className='question-button-container'> 
                <div className={yes ? 'question-button-clicked': 'question-button'}
                onClick={() => { 
                    setYes(true)
                    setNo(false)
                }}> Oui </div>
                
                <div className={no ? 'question-button-clicked': 'question-button'}
                onClick={() => { 
                    setYes(false)
                    setNo(true)
                }}> Non </div> 
            </div>
        )
    }

    return (
        <div className='question-form'>
            {props.data.questions[props.formId] != null && props.data.questions[props.formId].map( question => 
                <div key={question.questionText} className='question'>
                    <div className='question-text-input-container'>
                        <div> {question.questionText}</div>

                        <div>
                            {question.type === "yesNo" ? 
                                <ButtonsYesNo/>
                            :
                                <input className='question-input'/>
                            }
                        </div>
                    </div>
                    <div className="question-unit"> {question.unit}</div>
                </div>
            )}
        
        </div>
    );
}