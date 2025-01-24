import React, { useEffect, useState } from 'react';
import { MaterialReactTable } from 'material-react-table';
import { Container, Typography, Button, Box, CircularProgress } from '@mui/material';
import { getListOfTF_IPs } from '../services/TF_IPs_Service';
import * as XLSX from 'xlsx';

const ListTF_IPsComponent = () => {
  console.log('ListTF_IPsComponent rendered'); // Log ListTF_IPsComponent rendering
  
  const [tfIPs, setTfIPs] = useState([]);
  const [loading, setLoading] = useState(true); // State to manage loading

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getListOfTF_IPs();
        console.log('Fetched data:', response.data); // Debug: Log fetched data
        setTfIPs(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally { 
        setLoading(false); // Set loading to false when data is fetched
      }
    };

    fetchData();
  }, []);

  const columns = [
    { accessorKey: 'id', header: 'ID' },
    { accessorKey: 'ipAddress', header: 'IP Address' },
    { accessorFn: row => row.jiraObj?.jiraTicket, id: 'jiraTicket', header: 'Jira Ticket' },
    { accessorFn: row => row.snowReqObj?.snowREQ, id: 'snowREQ', header: 'Snow REQ' },
    { accessorKey: 'inFirewall', header: 'In Firewall', Cell: ({ cell }) => (cell.getValue() ? 'Yes' : 'No') },
    { accessorKey: 'createdAt', header: 'Created At', Cell: ({ cell }) => new Date(cell.getValue()).toLocaleString() },
    { accessorFn: row => row.requestedBy?.name, id: 'name', header: 'Requestor' },
  ];

  const exportToExcel = (selectedRows) => {
    const data = selectedRows.map(row => {
      const { id, ipAddress, jiraObj, snowReqObj, inFirewall,  createdAt, requestedBy} = row.original;
      return {
        "ID": id,
        "IP Address": ipAddress,
        "Jira Ticket": jiraObj?.jiraTicket,
        "ServiceNow Request": snowReqObj?.snowREQ,
        "In Firewall": inFirewall ? 'Yes' : 'No',
        "Created At": new Date(createdAt).toLocaleString(),
        "Requestor": requestedBy?.name,
      };
    });
    const worksheet = XLSX.utils.json_to_sheet(data);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'TF IPs');
    XLSX.writeFile(workbook, 'TF_IPs.xlsx');
  };

  return (
    <Container maxWidth={false} sx={{ width:'100%'}}>
      <Typography variant="h4" gutterBottom className="text-center">
        List Of Threat Feed IPs
      </Typography>
        { loading ? (
          <Box display="flex" justifyContent="center" alignItems="center" height="50vh">
              <CircularProgress />
            </Box>
        ):(
          <Box>
            <MaterialReactTable
              columns={columns}
              data={tfIPs}
              enableColumnFilters
              enableGlobalFilter
              enableSorting
              enablePagination
              enableRowSelection
              enableColumnResizing
              muiTableHeadRowProps={{
                sx: {
                  backgroundColor: 'rgba(240, 240, 240, 1)', // Add background color to table header
                }
              }}
              muiTableHeadCellProps={{
                sx: {
                  border: '1px solid rgba(225, 225, 225, 1)', // Add border to table cells
                  borderRadius: '0px', // Remove border radius
                }
              }}
              muiTableBodyCellProps={{
                sx: {
                  border: '1px solid rgba(225, 225, 225, 1)', // Add border to table cells
                  borderRadius: '0px', // Remove border radius
                }
              }}
              renderTopToolbarCustomActions={({ table }) => (
                <Button
                  variant="contained"
                  color="primary"
                  onClick={() => {
                    const selectedRows = table.getSelectedRowModel().rows;
                    console.log('Selected Rows:', selectedRows);
                    exportToExcel(selectedRows);
                  }}
                >
                  Export Selected
                </Button>
              )}
              muiTablePaginationProps={{
                rowsPerPageOptions: [10, 20, 50],
                showFirstButton: true,
                showLastButton: true,
              }}
            />
          </Box>
        )}
    </Container>
  );
};

export default ListTF_IPsComponent;