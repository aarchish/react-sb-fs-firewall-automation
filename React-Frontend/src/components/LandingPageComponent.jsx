import React from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { useNavigate } from 'react-router-dom';

const LandingPage = () => {
  const { oktaAuth } = useOktaAuth();
  const navigate = useNavigate();

  const handleLogin = () => {
    oktaAuth.signInWithRedirect();
  };

  return (
    <div>
      <h1>Welcome to Firewall Automation</h1>
      <p>Please log in with Okta to proceed.</p>
      <button onClick={handleLogin}>Login with Okta</button>
    </div>
  );
};

export default LandingPage;