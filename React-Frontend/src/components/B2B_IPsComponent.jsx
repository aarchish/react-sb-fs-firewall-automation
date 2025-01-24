import React, { useEffect, useState } from 'react';
import { MaterialReactTable } from 'material-react-table';
import { Container, Typography, Button, Box, CircularProgress } from '@mui/material';
import { getListOfB2B_IPs } from '../services/B2B_IPs_Service';
import * as XLSX from 'xlsx';

const ListB2B_IPsComponent = () => {
  console.log('ListB2B_IPsComponent rendered'); // Log ListB2B_IPsComponent rendering
  
  const [b2b_IPs, setB2B_IPs] = useState([]);
  const [loading, setLoading] = useState(true); // State to manage loading

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getListOfB2B_IPs();
        console.log('Fetched data:', response.data); // Debug: Log fetched data
        setB2B_IPs(response.data);
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
    { accessorFn: row => row.customerObj?.customerName, id: 'customerName', header: 'Customer Name' },
    { accessorKey: 'ipAddress', header: 'IP Address' },
    { accessorFn: row => row.snowReqObj?.snowREQ, id: 'snowREQ', header: 'Snow REQ' },
    { accessorKey: 'inFirewall', header: 'In Firewall', Cell: ({ cell }) => (cell.getValue() ? 'Yes' : 'No') },
    { accessorKey: 'createdAt', header: 'Created At', Cell: ({ cell }) => new Date(cell.getValue()).toLocaleString() },
    { accessorFn: row => row.requestedBy?.name, id: 'name', header: 'Requestor' },
  ];

  const exportToExcel = (selectedRows) => {
    const data = selectedRows.map(row => {
      const { id, customerObj, ipAddress, snowReqObj, inFirewall, createdAt, requestedBy} = row.original;
      return {
        "ID" : id,
        "Customer Name": customerObj?.customerName,
        "IP Address": ipAddress,
        "ServiceNow Request": snowReqObj?.snowREQ,
        "In Firewall": inFirewall ? 'Yes' : 'No',
        "Created At": new Date(createdAt).toLocaleString(),
        "Requestor": requestedBy?.name,
      };
    });
    const worksheet = XLSX.utils.json_to_sheet(data);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'B2B IPs');
    XLSX.writeFile(workbook, 'B2B_IPs.xlsx');
  };

  return (
    <Container maxWidth={false} sx={{ width:'100%'}}>
      <Typography variant="h4" gutterBottom className="text-center">
        List Of B2B IPs
      </Typography>
      { loading ? (
        <Box display="flex" justifyContent="center" alignItems="center" height="50vh">
          <CircularProgress />
        </Box>
      ):(
      <MaterialReactTable
        columns={columns}
        data={b2b_IPs}
        enableColumnFilters
        enableGlobalFilter
        enableSorting
        enablePagination
        enableRowSelection
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
            Export Selected Rows to Excel
          </Button>
        )}
        muiTablePaginationProps={{
          rowsPerPageOptions: [10, 20, 50],
          showFirstButton: true,
          showLastButton: true,
        }}
      />
      )}
    </Container>
  );
};

export default ListB2B_IPsComponent;