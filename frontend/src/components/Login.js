import React from 'react';
import { Link } from "react-router-dom";

const Login = () => {
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
              <h1 className="text-2xl font-bold text-gray-800">Namma Bank</h1>
            </div>

            {/* Login */}
            <div className="flex justify-start">
              <span className="text-gray-700 text-lg font-semibold">Sign in</span>
            </div>

            {/* Email input */}
            <div className="relative">
              <input
                type="text"
                className="block w-full px-4 py-3 rounded border border-gray-300 focus:outline-none focus:border-primary"
                id="email"
                placeholder="Email"
              />
            </div>

            {/* Password input */}
            <div className="relative">
              <input
                type="password"
                className="block w-full px-4 py-3 rounded border border-gray-300 focus:outline-none focus:border-primary"
                id="password"
                placeholder="Password"
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
                {/* <a
                  href="#!"
                  className="text-blue-500 hover:text-blue-700"
                >
                  Register
                </a> */}
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
