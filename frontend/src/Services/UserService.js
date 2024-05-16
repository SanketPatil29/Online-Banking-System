import axios from "axios";
import { BASE_URL } from "../utils/constants";
// const BASE_URL = 'http://192.168.18.54:8081/'
const UserService = {
    AccountRegistration: async (registrationData) =>{
        try{
            const resp = axios.post(
                BASE_URL + "api/customer",
                registrationData
            )
            return resp;
        }
        catch(error){
            throw error;
        }
    },
    CustomerDetails: async (customer_id) =>{
        try{
            const resp = axios.get(
                BASE_URL + "api/customer/" + customer_id
            )
            return resp;
        }
        catch(error){
            throw error;
        }
    }
};

export default UserService;
