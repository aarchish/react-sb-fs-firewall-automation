import React, { useEffect, useState } from 'react';
import { MaterialReactTable } from 'material-react-table';
import { getListOfB2B_IPs } from '../services/B2B_IPs_Service';
import * as XLSX from 'xlsx';

const ListB2B_IPsComponent = () => {
  const [b2b_IPs, setB2B_IPs] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getListOfB2B_IPs();
        console.log('Fetched data:', response.data); // Debug: Log fetched data
        setB2B_IPs(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
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
  ];

  const exportToExcel = (selectedRows) => {
    const data = selectedRows.map(row => row.original);
    const worksheet = XLSX.utils.json_to_sheet(data);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'B2B IPs');
    XLSX.writeFile(workbook, 'B2B_IPs.xlsx');
  };

  return (
    <div className="container">
      <h2 className="text-center">List Of B2B IPs</h2>
      <MaterialReactTable
        columns={columns}
        data={b2b_IPs}
        enableColumnFilters
        enableGlobalFilter
        enableSorting
        enablePagination
        enableRowSelection
        renderTopToolbarCustomActions={({ table }) => (
          <button
            onClick={() => {
              const selectedRows = table.getSelectedRowModel().rows;
              console.log('Selected Rows:', selectedRows);
              // Add your export logic here
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

export default ListB2B_IPsComponent;