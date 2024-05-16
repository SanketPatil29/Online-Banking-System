// import axios from "axios";
// import { BASE_URL } from "../utils/constants";

// const LoginService = {
//   userLogin: async (loginCred) => {
//     try {
//       const resp = await axios.post(
//         "http://localhost:8083/auth/login",
//         loginCred
//       );
//       return resp;
//     } catch (error) {
//       throw error;
//     }
//   },
// };

// export default LoginService;

const LoginService = {
  userLogin: async (loginCred) => {
    try {
      const response = await fetch("http://localhost:90933/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          // You can add other headers here if necessary
        },
        body: JSON.stringify(loginCred),
      });

      if (!response.ok) {
        // Handle HTTP errors
        const errorData = await response.json();
        throw new Error(
          `Login failed: ${errorData.message || response.statusText}`
        );
      }

      const data = await response.json();
      return data; // Return the response data
    } catch (error) {
      // Handle network errors or other unexpected errors
      throw new Error(`Login failed: ${error.message}`);
    }
  },
};

export default LoginService;
