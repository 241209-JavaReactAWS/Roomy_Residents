import React from 'react'
import { Link } from 'react-router-dom'

function nav() {
  return (
    <div>
        <ul>
            {/* All Hotells */}
            <li><Link to="/">Hotels</Link></li>
        </ul>    
    </div>
  )
}

export default nav