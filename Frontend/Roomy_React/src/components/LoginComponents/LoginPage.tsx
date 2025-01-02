import React, { useState } from 'react'
import TextInput from '../GlobalComponents/TextInput/TextInput'
import SubmissionButton from '../GlobalComponents/SubmissionButton/SubmissionButton'
import UserInterface from '../../interfaces/UserInterface'
import "./LoginPage.css"

function LoginPage() {

    const [username,setUsername] = useState("")
    const [password,setPassword] = useState("")
    const [firstName,setFirstName] = useState("")
    const [lastName,setLastName] = useState("")
    const [email,setEmail] = useState("")
    const [status,setStatus] = useState(0)
    let data : UserInterface = {
        "username" : username,
        "password" : password,
        "email" : email,
        "firstname" : firstName,
        "lastname" : lastName
    }

    return (
        <div id='LoginPage'>
            <div id='LoginGraphic'>
                <img id='HomeImage' src="https://digital.ihg.com/is/image/ihg/exp-crowne-plaza-tamuning-8691101738-16x5?ts=1735586660671&dpr=off"/> 
            </div>
            <div id='LoginForm'>
                <TextInput id="username_input" for="username" onValueChange={setUsername}></TextInput>
                <TextInput id="password_input" for="password" onValueChange={setPassword}></TextInput>
                <TextInput id="firstName_input" for="firstname" onValueChange={setFirstName}></TextInput>
                <TextInput id="lastName_input" for="lastname" onValueChange={setLastName}></TextInput>
                <TextInput id="email_input" for="email" onValueChange={setEmail}></TextInput>
                <SubmissionButton endpoint='test' statusChanger={setStatus}></SubmissionButton>
            </div>
        </div>
    )
}

export default LoginPage