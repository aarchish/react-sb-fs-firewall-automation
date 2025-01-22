import axios from "axios";
import { getAccessToken } from "./Okta_Service";

const API_URL = `${import.meta.env.VITE_API_BASE_URL}/b2b_ips`;

export const getListOfB2B_IPs = async () => {
  try {
    const accessToken = await getAccessToken();
    return await axios.get(API_URL, {
      headers: {
        Authorization: `Bearer ${accessToken}`,
        'scope' : 'read write'
      }
    });
  } catch (error) {
    console.error('Error fetching TF IPs:', error);
    throw error;
  }
};

