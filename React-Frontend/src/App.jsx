import './App.css';
import React, { useEffect } from 'react';
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import { Security, LoginCallback, useOktaAuth } from '@okta/okta-react';

import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import HomeComponent from './components/HomeComponent';

import ListTF_IPsComponent from './components/TF_IPsComponent';
import ListB2B_IPsComponent from './components/B2B_IPsComponent';

import createOktaAuthClient from './security/OktaConfig';
import SecureRoute from './security/SecureRoute';

const LoginRedirect = () => {
  const { oktaAuth, authState } = useOktaAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!authState) {
      return; // Wait until authState is initialized
    }

    if (!authState.isAuthenticated) {
      oktaAuth.signInWithRedirect();
    } else {
      navigate('/home');
    }
  }, [authState, oktaAuth, navigate]);

  return <div>Loading...</div>;
};

// OktaSecurity component to wrap your app and manage login
function OktaSecurity() {
  const navigate = useNavigate();
  
  const restoreOriginalUri = async (_oktaAuth, originalUri) => {
    console.log('Restoring original URI:', originalUri); // Log restoreOriginalUri
    // Navigate back to the original URI if authenticated, else default to '/'
    navigate(originalUri || '/home');
  };

  console.log('Initializing Security component'); // Log Security initialization

  return (
    <Security 
      oktaAuth={createOktaAuthClient} 
      restoreOriginalUri={restoreOriginalUri}
    >
      <Routes>
        <Route path="/login/callback" element={<LoginCallback />} />
        <Route path="/" element={<LoginRedirect />} />
        <Route path="/home" element={<SecureRoute><HomeComponent /></SecureRoute>}/>
        <Route path="/tf_ips" element={<SecureRoute><ListTF_IPsComponent /></SecureRoute>} />
        <Route path="/b2b_ips" element={<SecureRoute><ListB2B_IPsComponent /></SecureRoute>} />
      </Routes>
    </Security>
  );
}

// App component to render the OktaSecurity component
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <OktaSecurity />
      </BrowserRouter>
    </div>
  );
}

export default App;