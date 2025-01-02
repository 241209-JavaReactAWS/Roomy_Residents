import React, { useState } from 'react'
import TextInput from '../GlobalComponents/TextInput/TextInput'
import SubmissionButton from '../GlobalComponents/SubmissionButton/SubmissionButton'
import UserInterface from '../../interfaces/UserInterface'

function LoginPage() {

    const [username,setUsername] = useState("")
    const [password,setPassword] = useState("")
    const [firstName,setFirstName] = useState("")
    const [lastName,setLastName] = useState("")
    const [email,setEmail] = useState("")
    const [status,setStatus] = useState("")
    let data : UserInterface = {
        "username" : username,
        "password" : password,
        "email" : email,
        "firstname" : firstName,
        "lastname" : lastName
    }

    return (
        <div className='LoginPage'>
            <div id='LoginGraphic'>
                <img id='HomeImage' src="https://media.istockphoto.com/id/119926339/photo/resort-swimming-pool.jpg?s=612x612&w=0&k=20&c=9QtwJC2boq3GFHaeDsKytF4-CavYKQuy1jBD2IRfYKc="/> 
            </div>
            <div className='loginForm'>
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