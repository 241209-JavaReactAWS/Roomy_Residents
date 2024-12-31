import React from 'react'
import "./TextInput.css"
import TextInputInterface from '../../../interfaces/TextInputInterface'



function TextInput(props : TextInputInterface) {

  const handleChange = (event: any) => {
    props.onValueChange(event.target.value);
  };

  return (
    <>
    <label htmlFor={props.id}>{props.for}:</label>
    <input id={props.id} type='text'></input>
    </>
  )
}

export default TextInput