import React, { useState } from 'react'
import "./SubmissionButton.css"
import axios from 'axios'
import EndpointProp from '../../../interfaces/EndpointProp'

function SubmissionButton(props : EndpointProp) {

  const [state,setState] = useState("default")

  const placeholder : string = props.placeholder != null ? props.placeholder : "Submit"

  function submit(){
    axios.post(props.endpoint,props.data == null ? props.data : {},{withCredentials:true})
    .then((data : any) => {console.log("success")})
    .catch((error) => {console.log("error")})
  }
  
  return (
    <button id='SubmitButton' className={state} onClick={submit}>{placeholder}</button>
  )
}

export default SubmissionButton