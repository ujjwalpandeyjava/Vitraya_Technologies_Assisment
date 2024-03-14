import React from 'react'
import { Link } from 'react-router-dom';

function Home() {
  document.title = "Home";
  return (
    <div>
      <h3>Navbar | Menu</h3>
      <h2>Home stuff....</h2>
      <Link to={"/register"} relative='path'>Register</Link><br/>
      <Link to={"/login"} relative='path'>Login</Link><br/>
    </div>
  )
}

export default Home