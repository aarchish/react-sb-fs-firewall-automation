import React, { useEffect, useState } from 'react';
import { MaterialReactTable } from 'material-react-table';
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
    <div className="container">
      <h2 className="text-center">List Of Threat Feed IPs</h2>
      <MaterialReactTable
        columns={columns}
        data={tfIPs}
        enableColumnFilters
        enableGlobalFilter
        enableSorting
        enablePagination
        enableRowSelection
        renderTopToolbarCustomActions={({ table }) => (
          <button
            onClick={() => {
              const selectedRows = table.getSelectedRowModel().rows;
              exportToExcel(selectedRows);
            }}
          >
            Export Selected
          </button>
        )}
        muiTablePaginationProps={{
          rowsPerPageOptions: [10, 20, 50],
          showFirstButton: true,
          showLastButton: true,
        }}
      />
    </div>
  );
};

export default ListTF_IPsComponent;