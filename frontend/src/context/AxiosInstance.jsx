import axios from "axios";


const instance = axios.create();


instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("jwtToken");
    console.log(token);
    
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);


instance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
        localStorage.removeItem("jwtToken");
        if (window.location.pathname !== "/login") {
            window.location.href = "/login";
        } 
    }
    return Promise.reject(error);
  }
);

export default instance;
