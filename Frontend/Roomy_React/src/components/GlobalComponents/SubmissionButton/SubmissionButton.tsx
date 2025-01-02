import React, { useState } from 'react'
import "./SubmissionButton.css"
import axios from 'axios'
import EndpointProp from '../../../interfaces/EndpointProp'

function SubmissionButton(props : EndpointProp) {

  const [state,setState] = useState("default")

  function submit(){
    axios.post(props.endpoint,props.data == null ? props.data : {},{withCredentials:true})
    .then((data) => {console.log("success")})
    .catch((error) => {console.log("error")})
  }
  
  return (
    <div>
      <button id='SubmitButton' className={state} onClick={submit}>Submit</button>
    </div>
  )
}

export default SubmissionButton