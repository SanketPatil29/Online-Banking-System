// AuthContext.js
import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const login = () => {
    // Implement your login logic here, e.g., setting isAuthenticated to true
    setIsAuthenticated(true);
  };

  const logout = () => {
    // Implement your logout logic here, e.g., clearing localStorage and setting isAuthenticated to false
    setIsAuthenticated(false);
    localStorage.removeItem('customer_id'); // clear customer_id from localStorage
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
