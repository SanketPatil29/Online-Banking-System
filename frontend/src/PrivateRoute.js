// PrivateRoute.js
import React from 'react';
import { Route,Navigate,  useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

const PrivateRoute = ({ children, type }) => {
  // const { isAuthenticated } = useAuth();
  const cust_id = localStorage.getItem("customer_id");
  const navigate = useNavigate();

  if (!cust_id){
    alert("Login Required!");
    return <Navigate to="/login"></Navigate>
  }
  else if (
    cust_id &&
    type === "customerHomepage"
  )
    return children;

  else if (
      cust_id &&
      type === "register"
    )
    return children;
};

export default PrivateRoute;
