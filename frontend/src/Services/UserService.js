import axios from "axios";
import { BASE_URL } from "../utils/constants";

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
    }
};

export default UserService;
