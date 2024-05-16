// App.js
import './style.css';
import Login from './components/Login'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Register from './components/Register';
import CustomerHomePage from './components/Actors/Customer/CustomerHomePage';
import PrivateRoute from './PrivateRoute';
import { AccountDataProvider } from './context/AccountDataContext';

function App() {
  return (
    <AccountDataProvider>
      <BrowserRouter>
        <Routes>
          <Route path='*' element={<Login />} />
          <Route path='/login' element={<Login />} />
          <Route path='register' element={<Register />} />
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
    </AccountDataProvider>
  );
}

export default App;
