import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from "react-router-dom";

const AccountDetails = ({customerData}) => {
    // Sample account numbers
    const [accountNumbers, setAccountNumbers] = useState([]);
    const [selectedAccount, setSelectedAccount] = useState('');
    const [transactionDetails, setTransactionDetails] = useState([]);
    const [accountDetails, setAccountDetails] = useState({"account_id": '',
            "customer_id": '',
            "type": "",
            "status": "",
            "dateOpened": "",
            "balance": '',
        });

    useEffect(() => {
        for (let i = 0; i < customerData?.length; i++) {
            setAccountNumbers(...accountNumbers, customerData[i]?.account_id);
        }
    }, [])

    useEffect(() => {
        for (let i = 0; i < customerData?.length; i++) {
            if(customerData[i].account_id === selectedAccount){
                setAccountDetails({
                    account_id: customerData[i].account_id,
                    customer_id: customerData[i].customer_id,
                    type: customerData[i].type,
                    status: customerData[i].status,
                    dateOpened: customerData[i].dateOpened,
                    balance: customerData[i].balance,
                });
                setTransactionDetails(customerData[i].transactions);
                break;
            }
        }
    }, [selectedAccount])

    // Function to handle select change
    const handleSelectChange = (e) => {
        setSelectedAccount(e.target.value);
    };

    return (
        <div>
          <div className="max-w-screen-md mx-auto mt-8">
            <div className="bg-white shadow-md rounded-lg px-6 py-4 mb-4">
                <h3 className='text-center text-3xl font-semibold mb-4'>Account Details</h3>
                <div className="mb-4">
                    <label className="block text-gray-700 text-md font-bold mb-2" htmlFor="account">
                        Select Account:
                    </label>
                    <select
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="account"
                        value={selectedAccount}
                        onChange={handleSelectChange}
                    >
                        <option value={accountNumbers[0]}>Select an account</option>
                        {accountNumbers?.map((accountNumber, index) => (
                            <option key={index} value={accountNumber}>{accountNumber}</option>
                        ))}
                    </select>
                </div>
                <p className="text-gray-700 text-md font-md mb-1">Customer ID: {accountDetails?.customer_id}</p>
                <p className="text-gray-700 text-md font-md mb-1">Account type: {accountDetails?.type}</p>
                <p className="text-gray-700 text-md font-md mb-1">Status: {accountDetails?.status}</p>
                <p className="text-gray-700 text-md font-md mb-1">Opened Date: {accountDetails?.dateOpened}</p>
                <p className="text-gray-700 text-md font-md mb-1">Available Balance: {accountDetails?.balance}</p>
            </div> 
        </div>   
            { 
                <div className="bg-white shadow-md rounded-lg px-6 py-4 mb-4">
                    <h4 className="text-xl font-medium mb-4">Transaction Details</h4>
                    <div className="overflow-x-auto">
                        <table className="min-w-full divide-y divide-gray-200">
                            <thead className="bg-gray-50">
                                <tr>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Transaction ID</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Amount</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Description</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Transaction Date</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Status</th>
                                </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray-200">
                                {transactionDetails?.map(transaction => (
                                    <tr key={transaction?.transaction_id}>
                                        <td className="px-6 py-4 whitespace-nowrap">{transaction?.transaction_id}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">{transaction?.amount}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">{transaction?.description}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">{transaction?.transactionDateTime}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">{transaction?.status}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            }
        </div>
    )
}

export default AccountDetails;
