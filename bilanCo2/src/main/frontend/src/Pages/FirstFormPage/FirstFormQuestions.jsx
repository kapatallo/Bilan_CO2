import { useState } from 'react';
import './FirstFormQuestions.css'; 

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
    const [haveCar,setHaveCar] = useState(false)

    const ButtonsYesNoCar = () => {
        return (
            
            <div className='question-button-container'> 
                <div className={haveCar ? 'question-button-clicked': 'question-button'}
                onClick={() => { 
                    setHaveCar(true)
                }}> Oui </div>
                
                <div className={haveCar ? 'question-button': 'question-button-clicked'}
                onClick={() => { 
                    setHaveCar(false)

                }}> Non </div> 
            </div>
        )
    }

    return (
        <div className='question-form'>
            {props.formId === 0 ?
                haveCar ?
                    props.data.questions[props.formId] != null && props.data.questions[props.formId][1].map( question => 
                        <div key={question.questionText} className='question'>
                            <div className='question-text-input-container'>
                                <div> {question.questionText}</div>

                                <div>
                                    {question.type === "yesNo" ? 
                                        <ButtonsYesNoCar/>
                                    :
                                        <input className='question-input'/>
                                    }
                                </div>
                            </div>
                            <div className="question-unit"> {question.unit}</div>
                        </div>)
                :
                    props.data.questions[props.formId] != null && props.data.questions[props.formId][0].map( question => 
                        <div key={question.questionText} className='question'>
                            <div className='question-text-input-container'>
                                <div> {question.questionText}</div>

                                <div>
                                    {question.type === "yesNo" ? 
                                        <ButtonsYesNoCar/>
                                    :
                                        <input className='question-input'/>
                                    }
                                </div>
                            </div>
                            <div className="question-unit"> {question.unit}</div>
                        </div>)
            :
            props.data.questions[props.formId] != null && props.data.questions[props.formId].map( question => 
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