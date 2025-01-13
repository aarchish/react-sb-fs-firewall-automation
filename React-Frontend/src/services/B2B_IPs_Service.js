import axios from "axios";

const API_URL = "http://localhost:8080/api/b2b_ips";

export const getListOfB2B_IPs = async () => {
  return await axios.get(API_URL);
};

