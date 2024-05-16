import axios from "axios";
import { BASE_URL } from "../utils/constants";
// const BASE_URL = 'http://192.168.18.54:9093/'
const AccountService = {
    AccountDetails: async (customer_id) =>{
        try{
            const resp = axios.get(
                BASE_URL + "api/accounts/customer/" + customer_id
            )
            return resp;
        }
        catch(error){
            throw error;
        }
    }
};

export default AccountService;
