import axios from "axios";
import { BASE_URL } from "../utils/constants";
// const BASE_URL = 'http://192.168.18.54:9093/'
const PaymentService = {
    TransferAmount: async (paymentData) =>{
        try{
            const resp = axios.post(
                BASE_URL + "api/accounts/transfer", paymentData
            )
            return resp;
        }
        catch(error){
            throw error;
        }
    }
};

export default PaymentService;
