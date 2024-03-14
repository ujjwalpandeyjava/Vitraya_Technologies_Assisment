import React, { Fragment } from 'react';
import ReactDOM from 'react-dom/client';
import { Outlet, Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from 'react-router-dom';
import Home from './components/Home.jsx';
import Login from './components/Login.jsx';
import Register from './components/Register.jsx';
import ImageToText from './components/ImageToText.jsx';
import './index.css';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  document.title = "Home";
  return (
    <div>
      <ToastContainer/>
      <Outlet/>
    </div>
  )
}

const routesWithJSX = createBrowserRouter(
  createRoutesFromElements(
    <Route path="" element={<App />} >
      <Route path="" element={<Home />} />
      <Route path="register" element={<Register />} />
      <Route path="login" element={<Login />} />
      <Route path="imageToText" element={<ImageToText />} />
      <Route path='*' element={<h1>Page Not found</h1>} />
    </Route>
  ),
);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Fragment>
    <RouterProvider router={routesWithJSX} />
  </Fragment>
);