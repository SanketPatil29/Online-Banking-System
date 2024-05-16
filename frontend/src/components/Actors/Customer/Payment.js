import React, { useContext, useEffect, useState } from "react"
import { useAccountDataContext } from "../../../context/AccountDataContext";
import PaymentService from "../../../Services/PaymentService";

const Payment = () => {
    // Sample account numbers
    const { accObjs } = useAccountDataContext();
    const [selectedAccount, setSelectedAccount] = useState('');
    const [beneficiaryDetails, setBeneficiaryDetails] = useState({
        beneficiaryAccount :'',
        amount : '',
        description:'',
        beneficiaryName:''
    });
    const [accountDetails, setAccountDetails] = useState({
            "account_id": null,
            "status": "",
            "balance": null,
    });
    const [loading, setLoading] = useState(false); // State variable to track loading state

    useEffect(() => {
        if (selectedAccount && accObjs?.length > 0) {
            const selectedAccountData = accObjs.find(account => account.account_id === selectedAccount);
            if (selectedAccountData) {
                setAccountDetails({
                    account_id: selectedAccountData.account_id,
                    status: selectedAccountData.status,
                    balance: selectedAccountData.balance,
                });
            }
        }
    }, [selectedAccount]);

    // Function to handle select change
    const handleSelectChange = (e) => {
        setSelectedAccount(parseInt(e.target.value, 10)); // Assuming the value is a string and needs to be converted to an integer
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setBeneficiaryDetails({ ...beneficiaryDetails, [name]: value });
    };

    const handleSubmit = async() => {
        if(!beneficiaryDetails.amount 
            || !beneficiaryDetails.beneficiaryAccount 
            || !accountDetails.account_id
            || !beneficiaryDetails.description
            || !beneficiaryDetails.beneficiaryName
        ){
            alert("Please fill all the fields!")
            return
        }
        const paymentDetails = {
            "senderAccountId": accountDetails.account_id,
            "recipientAccountId": beneficiaryDetails.beneficiaryAccount,
            "amount": beneficiaryDetails.amount
        }
        setLoading(true)
        try{   
            const response = await PaymentService.TransferAmount(paymentDetails)
            setLoading(false)
            alert(response.data)
        }
        catch(error){
            alert(error.response.data)
        }
    }
    return(
        <div>
            {/* {loading && <div className="loading-spinner">Loading...</div>} */}
            <div className="bg-white shadow-md rounded-lg px-6 py-4 mb-4">
                <h3 className='text-center text-3xl font-semibold mb-4'>Money Transfer</h3>
                <div className="mb-4">
                    <label className="block text-gray-700 text-md font-bold mb-2" htmlFor="account">
                        From Account:
                    </label>
                    <select
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="account"
                        value={selectedAccount}
                        onChange={handleSelectChange}
                    >
                        <option value={null}>Select an account</option>
                        {accObjs?.map((accountNumber, index) => (
                            <option key={index} value={accountNumber.account_id}>{accountNumber.account_id}</option>
                        ))}
                    </select>
                </div>
                <p className="text-gray-700 text-md font-md mb-1">Status: {accountDetails?.status}</p>
                <p className="text-gray-700 text-md font-md mb-1">Available Balance: {accountDetails?.balance}</p>
            </div> 

            <div className="bg-white shadow-md rounded-lg px-6 py-4 mb-4 mt-8">
                <h5 className='text-xl font-semibold mb-4'>Beneficiary Details</h5>
                <div className="mb-4">
                    <label className="text-gray-700 font-medium mb-1" htmlFor="account">
                        Transfer To Account:
                    </label>
                    <input
                        onChange={handleChange}
                        type="text"
                        className="block w-full px-4 py-3 rounded border h-10 border-gray-300 focus:outline-none focus:border-primary"
                        id="beneficiaryAccount"
                        placeholder="Beneficiary Account"
                        name="beneficiaryAccount"
                    />
                     <label htmlFor="beneficiaryName" className=" text-gray-700 font-medium mb-1">Beneficiary Full Name</label>
                     <input
                        onChange={handleChange}
                        type="text"
                        className="block w-1/2 px-4 py-3 rounded border h-10 border-gray-300 focus:outline-none focus:border-primary"
                        id="beneficiaryName"
                        placeholder="Enter Name"
                        name="beneficiaryName"
                    />
                    <label className="text-gray-700 font-medium mt-2 mb-1" htmlFor="account">
                        Enter Amount:
                    </label>
                    <input
                        onChange={handleChange}
                        type="text"
                        className="block w-30 px-4 py-3 h-10 rounded border border-gray-300 focus:outline-none focus:border-primary"
                        id="amount"
                        placeholder="Amount"
                        name="amount"
                    />
                     <label className="text-gray-700 font-medium mt-2 mb-1" htmlFor="account">
                        Description:
                    </label>
                    <input
                        onChange={handleChange}
                        type="text"
                        className="block w-1/3 h-15 px-4 py-3 rounded border border-gray-300 focus:outline-none focus:border-primary"
                        id="description"
                        placeholder="Description"
                        name="description"
                    />
                </div>
                <div className='flex justify-center'>
                    <button
                        onClick={handleSubmit}
                        type="button"
                        className="w-32 py-3 text-md font-semibold bg-blue-500 hover:bg-blue-700 text-white rounded focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                    >
                        Pay
                    </button>
                    </div>
                </div>
        </div>
    )
}
export default Payment