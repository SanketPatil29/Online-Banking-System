import './style.css';
import Login from './components/Login'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Register from './components/Register';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Login/>}></Route>
        <Route path='login' element={<Login/>}></Route>
        <Route path='register' element={<Register/>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
