import React from 'react'
import { Link } from 'react-router-dom'

function nav() {
  return (
    <div>
        <ul>
            {/* Login User */}
            <li className="link-styles"><Link to="/login">Login Page</Link></li> 
            {/* Logout User */}
            <li className="link-styles"><button /*onClick={logOut}*/>Logout</button></li>
            {/* Register User */}
            <li className="link-styles"><Link to="/register">Register New User Page</Link></li>
            {/* All Hotells */}
            <li className="link-styles"><Link to="/AllHotels">All Hotels</Link></li>
        </ul>    
    </div>
  )
}

export default nav