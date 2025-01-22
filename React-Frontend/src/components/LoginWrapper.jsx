import React from 'react';
import { LoginCallback } from '@okta/okta-react';
import LandingPage from './LandingPageComponent';

const LoginWrapper = () => {
  return (
    <div>
      <LoginCallback />
      <LandingPage />
    </div>
  );
};

export default LoginWrapper;