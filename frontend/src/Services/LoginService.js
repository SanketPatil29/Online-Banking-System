import axios from "axios";
import { BASE_URL } from "../utils/constants";
// const BASE_URL = "http://192.168.18.54:8081/"
const LoginService = {
  // userLogin: async (loginCred) => {
  //   try {
  //     const resp = axios.get(BASE_URL + "api/customer/1");
  //     return resp;
  //   } catch (error) {
  //     throw error;
  //   }
  // },
    userLogin: async (loginCred) => {
        try {
          const resp = axios.post(BASE_URL + "auth/login", loginCred);
          return resp;
        } catch (error) {
          throw error;
        }
      },
};

export default LoginService;
