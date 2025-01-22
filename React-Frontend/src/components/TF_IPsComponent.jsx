import React, { useEffect, useState } from 'react';
import { MaterialReactTable } from 'material-react-table';
import { Container, Typography, Button, Box } from '@mui/material';
import { getListOfTF_IPs } from '../services/TF_IPs_Service';
import * as XLSX from 'xlsx';

const ListTF_IPsComponent = () => {
  console.log('ListTF_IPsComponent rendered'); // Log ListTF_IPsComponent rendering
  
  const [tfIPs, setTfIPs] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getListOfTF_IPs();
        console.log('Fetched data:', response.data); // Debug: Log fetched data
        setTfIPs(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
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
    <Container>
      <Typography variant="h4" gutterBottom className="text-center">
        List Of Threat Feed IPs
      </Typography>
      <MaterialReactTable
        columns={columns}
        data={tfIPs}
        enableColumnFilters
        enableGlobalFilter
        enableSorting
        enablePagination
        enableRowSelection
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
    </Container>
  );
};

export default ListTF_IPsComponent;