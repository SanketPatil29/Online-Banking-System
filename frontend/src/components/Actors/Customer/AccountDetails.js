import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import UserService from '../../../Services/UserService';
import AccountService from '../../../Services/AccountService';
import { useAccountDataContext } from '../../../context/AccountDataContext';

const AccountDetails = () => {
    // Sample account numbers
    const [accountNumbers, setAccountNumbers] = useState([]);
    const [selectedAccount, setSelectedAccount] = useState(null);
    const [transactionDetails, setTransactionDetails] = useState([]);
    const [accountDetails, setAccountDetails] = useState({
        "account_id": '',
        "customer_id": '',
        "type": "",
        "status": "",
        "dateOpened": "",
        "balance": '',
    });
    const customer_id = localStorage.getItem("customer_id")
    const [accountData, setAccountData] = useState([]) 
    const [customerAccounts, setCustomerAccounts] = useState([]);
    const { updateAccObjs } = useAccountDataContext();

    const fetchCustomerDetails = async () => {
        try {
            const response = await AccountService.AccountDetails(customer_id)
            console.log("AccountDetails(AccountDetails.js): ", response.data)
            setAccountData(response.data);
        }
        catch (error) {
            console.log("Error: ", error)
        }
    }

    useEffect(() => {
        fetchCustomerDetails()
    }, [])

    useEffect(() => {
        // Check if accountData is not empty
        if (accountData.length > 0) {
            // Extract account numbers from accountData
            const numbers = accountData.map(account => account.account_id);
            // Set the accountNumbers state with the extracted numbers
            setAccountNumbers(numbers);
        }
        let accObjs = []
        for(let i=0; i<accountData.length;i++){
            const obj = {
                account_id : accountData[i].account_id,
                status : accountData[i].status,
                balance : accountData[i].balance
            }
            accObjs.push(obj)
        }
        updateAccObjs(accObjs);

    }, [accountData])

    useEffect(() => {
        // Find the selected account details and transactions
        if (selectedAccount && accountData.length > 0) {
            console.log("Account Numbers: ",accountNumbers)
            console.log(" Selected Account : ",selectedAccount)
            let selectedAccountData;
            for (let i = 0; i < accountData.length; i++) {
                console.log("acc", accountData[i].account_id === selectedAccount)
                if (accountData[i].account_id === selectedAccount) {
                    selectedAccountData = accountData[i];
                    break;
                }
            }
            console.log("selected Acc data", selectedAccountData)
            if (selectedAccountData) {
                setAccountDetails({
                    account_id: selectedAccountData.account_id,
                    customer_id: selectedAccountData.customer_id,
                    type: selectedAccountData.type,
                    status: selectedAccountData.status,
                    dateOpened: selectedAccountData.dateOpened,
                    balance: selectedAccountData.balance,
                });
                setTransactionDetails(selectedAccountData.transactions);
            }
        }
    }, [selectedAccount, accountData])

    // Function to handle select change
    const handleSelectChange = (e) => {
        setSelectedAccount(parseInt(e.target.value, 10)); // Assuming the value is a string and needs to be converted to an integer
    };

    // Function to format date
    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleString(); // Adjust format as needed
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
                            <option value={0}>Select an account</option>
                            {accountNumbers.map((accountNumber, index) => (
                                <option key={index} value={accountNumber}>{accountNumber}</option>
                            ))}
                        </select>
                    </div>
                    <p className="text-gray-700 text-md font-md mb-1">Customer ID: {accountDetails.customer_id}</p>
                    <p className="text-gray-700 text-md font-md mb-1">Account type: {accountDetails.type}</p>
                    <p className="text-gray-700 text-md font-md mb-1">Status: {accountDetails.status}</p>
                    <p className="text-gray-700 text-md font-md mb-1">Opened Date: {accountDetails.dateOpened}</p>
                    <p className="text-gray-700 text-md font-md mb-1">Available Balance: {accountDetails.balance}</p>
                </div>
            </div>
            {
                transactionDetails.length > 0 && (
                    <div className="bg-white shadow-md rounded-lg px-6 py-4 mb-4">
                        <h4 className="text-xl font-medium mb-4">Transaction Details</h4>
                        <div className="overflow-x-auto">
                            <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                    <tr>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Transaction ID</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Amount</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Type</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Description</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Transaction Date</th>
                                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider">Status</th>
                                    </tr>
                                </thead>
                                <tbody className="bg-white divide-y divide-gray-200">
                                    {transactionDetails.map(transaction => (
                                        <tr key={transaction.transaction_id}>
                                            <td className="px-6 py-4 whitespace-nowrap">{transaction.transaction_id}</td>
                                            <td className="px-6 py-4 whitespace-nowrap">{transaction.amount}</td>
                                            <td className="px-6 py-4 whitespace-nowrap">{transaction.tx_type}</td>
                                            <td className="px-6 py-4 whitespace-nowrap">{transaction.description}</td>
                                            <td className="px-6 py-4 whitespace-nowrap">{formatDate(transaction.transactionDateTime)}</td> {/* Format date here */}
                                            <td className="px-6 py-4 whitespace-nowrap">{transaction.status}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                )
            }
        </div>
    )
}

export default AccountDetails;
