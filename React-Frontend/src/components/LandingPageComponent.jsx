import React from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { useNavigate } from 'react-router-dom';
import { Container, Typography, Button, Box } from '@mui/material';

const LandingPage = () => {
  const { oktaAuth } = useOktaAuth();
  const navigate = useNavigate();

  const handleLogin = () => {
    oktaAuth.signInWithRedirect();
  };

  return (
    <Container maxWidth="sm">
        <Box textAlign={'center'} mt={5}>
            <Typography variant="h4">Welcome to Firewall Automation</Typography>
            <Typography variant="body1">Please log in with Okta to proceed.</Typography>
            <Button variant="contained" color="primary" onClick={handleLogin}>Login with Okta</Button>
        </Box>
    </Container>
  );
};

export default LandingPage;