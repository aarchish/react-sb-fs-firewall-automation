import './App.css';
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import { Security, LoginCallback } from '@okta/okta-react';

import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import HomeComponent from './components/HomeComponent';

import ListTF_IPsComponent from './components/TF_IPsComponent';
import ListB2B_IPsComponent from './components/B2B_IPsComponent';

import createOktaAuthClient from './security/OktaConfig';
import SecureRoute from './security/SecureRoute';

// OktaSecurity component to wrap your app and manage login
function OktaSecurity() {
  const navigate = useNavigate();
  
  const restoreOriginalUri = async (_oktaAuth, originalUri) => {
    console.log('Restoring original URI:', originalUri); // Log restoreOriginalUri
    // Navigate back to the original URI if authenticated, else default to '/'
    navigate(originalUri || '/');
  };

  console.log('Initializing Security component'); // Log Security initialization

  return (
    <Security 
      oktaAuth={createOktaAuthClient} 
      restoreOriginalUri={restoreOriginalUri}
    >
      <HeaderComponent />
      <Routes>
        <Route path="/login/callback" element={<LoginCallback />} />
        <Route path="/" element={<SecureRoute><HomeComponent /></SecureRoute>}/>
        <Route path="/tf_ips" element={<SecureRoute><ListTF_IPsComponent /></SecureRoute>} />
        <Route path="/b2b_ips" element={<SecureRoute><ListB2B_IPsComponent /></SecureRoute>} />
      </Routes>
      <FooterComponent />
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