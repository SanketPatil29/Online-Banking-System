import React, {useState} from 'react';
import UserService from '../Services/UserService';
import { useNavigate } from 'react-router-dom';

export default function Register() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    type:'',
    username: '',
    password:'',
    role:'ROLE_CUSTOMER',
    firstname: '',
    lastname: '',
    gender: '',
    dob: '',
    mobile: '',
    email: '',
    // branchName: '',
    // ifscCode: '',
    address: '',
    // city: '',
    // state: '',
    // pincode: ''
  });

  const handleSubmit = async(e) => {
    e.preventDefault();
    const {email, mobile } = formData;
    console.log("regiatration data:", formData)
    try{
      const response = await UserService.AccountRegistration(formData);
      if(response){
        alert(`Registered successfully!  Username and password sent to Email: ${email} and Mobile: ${mobile}`);
        navigate("/login")
      }
      else{
        alert(`Unable to register`);
      }
    }
    catch(error){
      console.log("Error", error)
    }

  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  return (
    <div className="min-h-screen p-10 bg-gray-100 flex items-center justify-center">
      <div className="container max-w-screen-lg mx-auto">
        <form className="bg-white rounded shadow-lg p-6 md:p-8"  onSubmit={handleSubmit}>
          <h2 className="font-bold text-xl text-gray-600 mb-6">Registration</h2>
          {/* <div>
              <label htmlFor="creds" className="font-semibold text-black-600 text-lg" >Login Credentials</label>
          </div> */}
          <div className='mb-2'>
              <label htmlFor="type" className="font-medium" >Account Type</label>
              <select  onChange={handleChange}  type="" name="type" id="type" className="ml-2 h-10 border mt-1 rounded px-4 w-48 bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" >
                  <option value=''>Select</option>
                  <option value='SAVING'>Saving</option>
                  <option value='CURRENT'>Current</option> 
              </select>
            </div>
          <div className="grid gap-4 gap-y-2 text-sm grid-cols-1 md:grid-cols-2">
            <div>
                <label htmlFor="username" className="font-medium" >User Name</label>
                <input  onChange={handleChange}  type="text" name="username" id="username" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div>
                <label htmlFor="password" className="font-medium">Password</label>
                <input  onChange={handleChange}  type="text" name="password" id="password" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            
            <div>
                <label htmlFor="firstname" className="font-medium" >First Name</label>
                <input  onChange={handleChange}  type="text" name="firstname" id="firstname" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div>
                <label htmlFor="lastname" className="font-medium">Last Name</label>
                <input  onChange={handleChange}  type="text" name="lastname" id="lastname" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div>
              <label htmlFor="gender" className="font-medium">Gender</label>
              <select  onChange={handleChange}  name="gender" id="gender" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300">
                <option value="">Select</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
              </select>
            </div>
            <div>
              <label htmlFor="dob" className="font-medium">Date of Birth</label>
              <input  onChange={handleChange}  type="date" name="dob" id="dob" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div>
              <label htmlFor="mobile" className="font-medium">Mobile Number</label>
              <input  onChange={handleChange} type="tel" name="mobile" id="mobile" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div>
              <label htmlFor="email" className="font-medium">Email Address</label>
              <input onChange={handleChange} type="email" name="email" id="email" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>

            {/* <div>
              <label htmlFor="branchName" className="font-medium">Branch Name</label>
              <input  onChange={handleChange}  type="text" name="branchName" id="branchName" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div>
              <label htmlFor="ifscCode" className="font-medium">IFSC Code</label>
              <input  onChange={handleChange}  type="text" name="ifscCode" id="ifscCode" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div> */}
            <div className="col-span-2">
              <label htmlFor="address" className="font-medium">Address</label>
              <input  onChange={handleChange}  type="text" name="address" id="address" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            {/* <div className="col-span-2">
              <label htmlFor="city" className="font-medium">City</label>
              <input  onChange={handleChange}  type="text" name="city" id="city" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div className="col-span-2">
              <label htmlFor="pincode" className="font-medium">Pincode</label>
              <input  onChange={handleChange}  type="text" name="pincode" id="pincode" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div>
            <div className="col-span-2">
              <label htmlFor="state" className="font-medium">State</label>
              <input  onChange={handleChange}  type="text" name="state" id="state" className="h-10 border mt-1 rounded px-4 w-full bg-gray-50 focus:outline-none focus:ring focus:border-blue-300" />
            </div> */}
          </div>
          <div className="mt-6">
            <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Register</button>
          </div>
        </form>
      </div>
    </div>
  );
}
