import axios from "axios";

const API_URL = "http://localhost:8080/api/tf_ips";

export const getListOfTF_IPs = async () => {
  return await axios.get(API_URL);
};

