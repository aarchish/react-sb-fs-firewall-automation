import oktaAuth from '../security/OktaConfig';

export const getAccessToken = async () => {
  try {
    const tokenResponse = await oktaAuth.token.getWithoutPrompt({
      responseType: 'token',
      //scopes: ['openid', 'profile', 'email'],
    });
    console.log('Token response:', JSON.stringify(tokenResponse.tokens, null, 2)); // Log the token response
    const accessToken = tokenResponse.tokens.accessToken.accessToken;
    return accessToken;
  } catch (error) {
    console.error('Error getting access token:', error);
    throw error;
  }
};