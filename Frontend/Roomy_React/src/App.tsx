import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import AllHotels from './components/HotelComponent/AllHotels'
import { BrowserRouter, Route, Routes } from 'react-router-dom'

function App() {
  const [count, setCount] = useState(0)
  console.log("App component is rendering");

  return (
    <>
    <BrowserRouter>
          <Routes>
            <Route>
              <AllHotels/>
            </Route>
          </Routes>
        </BrowserRouter>
      
    </>
  )
}

export default App
