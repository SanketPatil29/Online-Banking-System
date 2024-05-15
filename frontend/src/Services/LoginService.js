import axios from "axios";
import { BASE_URL } from "../utils/constants";

const LoginService = {
    userLogin: async (loginCred) => {
        try {
          const resp = axios.post(BASE_URL + "login", loginCred);
          return resp;
        } catch (error) {
          throw error;
        }
      },
};

export default LoginService;
