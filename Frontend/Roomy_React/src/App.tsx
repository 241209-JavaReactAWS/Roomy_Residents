import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import AllHotels from './components/HotelComponent/AllHotels'
import BookingPage from "./components/BookingComponent/BookingPage"
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Nav from './components/Nav/nav'


function App() {
  const [count, setCount] = useState(0)
  console.log("App component is rendering");

  return (
    <>
      <BrowserRouter>
        <Nav></Nav>
          <Routes>
            <Route path='/' element={<AllHotels></AllHotels>}></Route>
          
          </Routes>
        </BrowserRouter>
        <div>
        <BookingPage/>
        </div>
      
    </>
  )
}

export default App
