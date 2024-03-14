import { useState } from 'react';
import apiEndPoints from '../action/api';
import sheet from './Register.module.css';
import { Bounce, toast } from 'react-toastify';
import { Link } from 'react-router-dom';


function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    if (password !== confirmPassword) {
      toast.warn('Passwords do not match!', {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
        transition: Bounce,
      });
      return;
    }
    const payload = {
      "password": password,
      "confirmPassword": confirmPassword,
      "email": email
    }

    apiEndPoints.AUTH().register(payload)
      .then(async res => {
        console.warn(res);
        if (res.status === 201) {
          res = await res.data;
          console.warn(res);
          toast("Welcome");
          resetFields();
        } else if (res.status === 208) {
          toast("Email already registered");
        }
      }).catch(error => {
        console.error(error.message);
      })
  };
  function resetFields() {
    setConfirmPassword("")
    setEmail("")
    setPassword("")
  }
  return (
    <div id={sheet.register}>
      <form onSubmit={handleSubmit}>
        <h1 align="center">Register</h1>
        <div className={sheet.field}>
          <label htmlFor='email'>Email:</label>
          <input id='email' type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>

        <div className={sheet.field}>
          <label htmlFor='pass'>Password:</label>
          <input id='pass' type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>

        <div className={sheet.field}>
          <label htmlFor='cPass'>Confirm Password:</label>
          <input id='cPass' type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required />
        </div>

        <div className={sheet.field}><input type="submit" value="Register" /></div>

        <Link to={"/login"} relative='path'>Login</Link><br />

      </form>
    </div>
  );
}

export default Register;
