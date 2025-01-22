import React from "react";
import { NavLink } from 'react-router-dom';
import { Container, Typography, Button, Box } from '@mui/material';

const HomeComponent = () => {
    console.log('HomeComponent rendered'); // Log HomeComponent rendering
    
    return (
        <Container>
          <Box textAlign="center" mt={5}>
            <Typography variant="h4" gutterBottom>
              Welcome to Firewall Automation Feed DataLog Application
            </Typography>
            <Typography variant="body1" gutterBottom>
              Here you can view the list of B2B Sterling IPs WhiteList and Threat Feed BlackList IPs along with ServiceNow and Jira Requests associated with them.
            </Typography>
            <Button variant="outlined" color="primary" size="medium" component={NavLink} to="/tf_ips">
              ThreatFeed IPs
            </Button>
            <Button variant="outlined" color="primary" size="medium" component={NavLink} to="/b2b_ips">
              B2B IPs
            </Button>
          </Box>
        </Container>
      );
};

export default HomeComponent;