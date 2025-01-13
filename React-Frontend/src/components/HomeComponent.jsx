import React from "react";
import { NavLink } from 'react-router-dom';
import { Button } from '@mui/material';

const HomeComponent = () => {
    return (
        <>
            <div>
                <h1>Welcome to Firewall Automation Feed DataLog Application</h1>
            </div>
            <div>
                <p>Here you can view the list of B2B Sterling IPs WhiteList and 
                    Threat Feed BlackList IPs along with ServiceNow and Jira Requests associated with them.</p>
            </div>
            <Button variant="outlined" color="contrast" size="medium" component={NavLink} to="/tf_ips"> ThreatFeed IPs </Button>
            <Button variant="outlined" color="contrast" size="medium" border component={NavLink} to="/b2b_ips"> B2B IPs </Button>
        </>
    );
};

export default HomeComponent;