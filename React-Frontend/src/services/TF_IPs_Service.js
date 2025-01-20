import axios from "axios";
import { getAccessToken } from "./Okta_Service";

const API_URL = "http://localhost:8080/api/tf_ips";

export const getListOfTF_IPs = async () => {
  try {
    const accessToken = await getAccessToken();
    return await axios.get(API_URL, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });
  } catch (error) {
    console.error('Error fetching TF IPs:', error);
    throw error;
  }
};

