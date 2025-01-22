// eslint-disable-next-line no-unused-vars
import React from 'react'
import { Box, Typography } from '@mui/material';

const FooterComponent = () => {
  return (
    <Box mt={5} py={3} bgcolor="primary.main" color="white" textAlign="center">
      <Typography variant="body2">
        &copy; {new Date().getFullYear()} Firewall Automation. All rights reserved.
      </Typography>
    </Box>
  );
};

export default FooterComponent