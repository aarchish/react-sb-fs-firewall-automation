import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { NavLink } from 'react-router-dom';
import { useOktaAuth } from '@okta/okta-react';

const HeaderComponent = () => {
  const { oktaAuth, authState } = useOktaAuth();

  const handleLogout = async () => {
    await oktaAuth.signOut();
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Firewall Automation Datalog
        </Typography>
        {authState?.isAuthenticated && (
          <>
            <Button color="inherit" component={NavLink} to="/tf_ips">
              ThreatFeed IPs
            </Button>
            <Button color="inherit" component={NavLink} to="/b2b_ips">
              B2B IPs
            </Button>
            <Button color="inherit" onClick={handleLogout}>
              Logout
            </Button>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
};

export default HeaderComponent;