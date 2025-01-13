import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { NavLink } from 'react-router-dom';

const HeaderComponent = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ alignContent: 'center', flexGrow: 1 }}>
          Firewall Automation Datalog
        </Typography>
        <Button color="inherit" component={NavLink} to="/tf_ips">
          ThreatFeed IPs
        </Button>
        <Button color="inherit" component={NavLink} to="/b2b_ips">
          B2B IPs
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default HeaderComponent;