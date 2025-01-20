import { OktaAuth } from '@okta/okta-auth-js';

const oktaAuth = new OktaAuth({
  issuer: 'https://dev-10075142.okta.com/oauth2/default', // Replace with your Okta domain
  clientId: '0oamr7qxj4cHnKwCq5d7', // Replace with your Client ID
  redirectUri: window.location.origin + '/login/callback',
  scopes: ['openid', 'profile', 'email'],
  pkce: true,
});

export default oktaAuth;