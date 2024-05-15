import React, { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import LoginService from '../Services/LoginService';
import { useAuth } from '../AuthContext'; // Import the AuthContext

const Login = () => {
  const navigate = useNavigate();
  const { login } = useAuth(); // Destructure the login function from AuthContext
  const [loginCred, setLoginCred] = useState({
    "role" :'',
    "username": '',
    "password":''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    
    // If the clicked element is a button (role selection), update the role in the state
    if (e.target.tagName === 'BUTTON') {
      e.preventDefault(); // Prevent default button behavior
      setLoginCred({ ...loginCred, role: value });

    } else {
      // If it's not a button, update the input field values
      setLoginCred({ ...loginCred, [name]: value });
    }
  };

  const handleLogin = async () => {
    if(loginCred.role === '' || loginCred.username==='' || loginCred.password ===''){
      alert("Please fill all the fields!");
    }
    else{
      console.log("login creds:", loginCred);
      try{
        // const response = await LoginService.userLogin(loginCred);
        const response = {data: {customer_id:2}}
        if(response){
          // Store customer ID in local storage upon successful login
          localStorage.setItem('customer_id', response.data.customer_id);
          login(); // Call the login function from AuthContext
          navigate('/customerHomepage');
        }
      }
      catch(error){
        alert(error.response.data)
      }
    }
  };

  return (
    <section className="h-screen flex items-center justify-center bg-gray-100">
      <div className="flex flex-wrap items-center justify-center lg:justify-between w-full max-w-6xl p-6">
        
        {/* Left column container with background */}
        <div className="w-full lg:w-6/12 xl:w-6/12 flex justify-center mb-6 md:mb-0">
          <img
            src="https://tecdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
            className="w-full max-w-sm lg:max-w-none"
            alt="Sample image"
          />
        </div>


        {/* Right column container */}
        <div className="w-full lg:w-5/12 xl:w-5/12 bg-white rounded-lg p-8 shadow-md">
          <form className="space-y-4">
            {/* Bank name title */}
            <div className="text-center lg:text-left mb-6">
              <h1 className="text-2xl font-bold text-gray-800">Banking App</h1>
            </div>

            {/* Login */}
            <div className="flex justify-start">
              <span className="text-gray-700 text-lg font-semibold">Sign in as</span>
            </div>

            {/* Role selection tabs */}
            <div className="flex justify-center gap-2 mb-4">
            <button
              value='ROLE_CUSTOMER'
              className={`py-2 px-4 rounded focus:outline-none ${loginCred.role === 'ROLE_CUSTOMER' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'}`}
              onClick={handleChange}
            >
              Customer
            </button>
            <button
              value='ROLE_EMPLOYEE'
              className={`py-2 px-4 rounded focus:outline-none ${loginCred.role === 'ROLE_EMPLOYEE' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'}`}
              onClick={handleChange}
            >
              Employee
            </button>
            <button
              value='ROLE_ADMIN'
              className={`py-2 px-4 rounded focus:outline-none ${loginCred.role === 'ROLE_ADMIN' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'}`}
              onClick={handleChange}
            >
              Admin
            </button>

            </div>

            {/* Email input */}
            <div className="relative">
              <input
                onChange={handleChange}
                type="text"
                className="block w-full px-4 py-3 rounded border border-gray-300 focus:outline-none focus:border-primary"
                id="email"
                placeholder="Email"
                name="username"
              />
            </div>

            {/* Password input */}
            <div className="relative">
              <input
                onChange={handleChange}
                type="password"
                className="block w-full px-4 py-3 rounded border border-gray-300 focus:outline-none focus:border-primary"
                id="password"
                placeholder="Password"
                name="password"
              />
            </div>

            {/* Remember me checkbox and Forgot password link */}
            <div className="flex items-center justify-between">
              <div className="flex items-center">
                <input
                  type="checkbox"
                  id="remember"
                  className="form-checkbox h-4 w-4 text-primary border-primary focus:ring-primary"
                />
                <label
                  htmlFor="remember"
                  className="ml-2 text-sm text-gray-700"
                >
                  Remember me
                </label>
              </div>
              <a href="#!" className="text-sm text-blue-500 hover:text-blue-700">
                Forgot password?
              </a>
            </div>

            {/* Login button */}
            <div className='flex justify-center'>
              <button
                onClick={handleLogin}
                type="button"
                className="w-32 py-3 text-sm font-semibold bg-blue-500 hover:bg-blue-700 text-white rounded focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
              >
                Login
              </button>
            </div>

            {/* Register link */}
            <div className="text-center">
              <p className="text-sm">
                Don't have an account?{' '}
                <Link className="text-blue-500 hover:text-blue-700" to='/register'>Register</Link>
              </p>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

export default Login;
