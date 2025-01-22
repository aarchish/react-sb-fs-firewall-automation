import { OktaAuth } from '@okta/okta-auth-js';

const oktaAuth = new OktaAuth({
  issuer: import.meta.env.VITE_OKTA_ISSUER, // Use environment variable for Okta issuer
  clientId: import.meta.env.VITE_OKTA_CLIENT_ID, // Use environment variable for Client ID
  redirectUri: window.location.origin + `/login/callback`, // Redirect to login callback
  scopes: ['openid', 'profile', 'email'],
  pkce: true,
});

export default oktaAuth;