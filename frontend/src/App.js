// App.js
import "./style.css";
import Login from "./components/Login";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Register from "./components/Register";
import CustomerHomePage from "./components/Actors/Customer/CustomerHomePage";
import PrivateRoute from "./PrivateRoute";
import { AuthProvider } from "./AuthContext";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="*" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="register" element={<Register />} />
          {/* <Route
              path="register"
              element={
                <PrivateRoute type="register">
                  <Register />
                </PrivateRoute>
              }
            />                  
          */}
          <Route
            path="customerHomepage"
            element={
              <PrivateRoute type="customerHomepage">
                <CustomerHomePage />
              </PrivateRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
