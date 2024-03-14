import { useState } from 'react';
import apiEndPoints from '../action/api';
import sheet from './Register.module.css';
import { toast } from 'react-toastify';
import { Link, useNavigate } from 'react-router-dom';


function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    const payload = {
      "password": password,
      "email": email
    }

    apiEndPoints.AUTH().login(payload)
      .then(async res => {
        console.warn(res);
        if (res.status === 200) {
          res = await res.data;
          toast("User registered successfully");
          resetFields();
          navigate("/imageToText")
        } else if (res.status === 208) {
          toast("Email already registered");
        }
      }).catch(error => {
        console.error(error.message);
      })
  };
  function resetFields() {
    setEmail("")
    setPassword("")
  }
  return (
    <div id={sheet.register}>
      <form onSubmit={handleSubmit}>
        <h1 align="center">Login</h1>
        <div className={sheet.field}>
          <label htmlFor='email'>Email:</label>
          <input id='email' type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>

        <div className={sheet.field}>
          <label htmlFor='pass'>Password:</label>
          <input id='pass' type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>

        <div className={sheet.field}><input type="submit" value="Login" /></div>

        <Link to={"/register"} relative='path'>Register</Link><br />

      </form>
    </div>
  );
}

export default Login;
