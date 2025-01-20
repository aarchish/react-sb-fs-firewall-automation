import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import { useOktaAuth } from '@okta/okta-react';

// SecureRoute component to handle authentication checks
const SecureRoute = ({ children }) => {
  const { oktaAuth, authState } = useOktaAuth();

  useEffect(() => {
    console.log('SecureRoute rendered');
    console.log('authState:', authState); // Log authState
    console.log('oktaAuth:', oktaAuth); // Log oktaAuth
  }, [authState, oktaAuth]);

  if (!authState) {
    return <div>Loading...</div>;  // Show loading until auth state is determined
  }
  
  if (authState.isPending) {
    return <div>Loading...</div>;  // Show loading until auth state is determined
  }

  if (!authState.isAuthenticated) {
    console.log('User is not authenticated, redirecting to Okta...');
    oktaAuth.signInWithRedirect();
    return null;  // Return null to prevent rendering the protected component
  }

  return children;
};

SecureRoute.propTypes = {
  children: PropTypes.element.isRequired,
};

export default SecureRoute;