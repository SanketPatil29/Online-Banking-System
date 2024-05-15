import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import {
  UserIcon,
  CreditCardIcon,
  UserCircleIcon,
  PencilAltIcon,
  LogoutIcon,
} from "@heroicons/react/outline";
import AccountDetails from "./AccountDetails";
import Payment from "./Payment";
import Profile from "./Profile";
import Login from "../../Login";
import { useAuth } from "../../../AuthContext";

const CustomerHomePage = () => {
  const [currentPage, setCurrentPage] = useState("accounts");
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);
  const { logout } = useAuth(); // Destructure the login function from AuthContext
  const navigate = useNavigate()
  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };
  const renderPage = () => {
    switch (currentPage) {
      case "accounts":
        return <AccountDetails />;
      case "payments":
        return <Payment />;
        case "profile":
          return <Profile />;
        // case "logout":
        //   return <Login />;
      default:
        return <></>;
    }
  };
  const handleLogout = () =>{
    logout();
    navigate("/login")
  }

  return (
    <div className="font-[sans-serif]">
      <div className="mt-15">
        {isSidebarOpen ? (
          <button
            data-drawer-target="default-sidebar"
            data-drawer-toggle="default-sidebar"
            aria-controls="default-sidebar"
            type="button"
            onClick={toggleSidebar}
            className="inline-flex items-center p-2 mt-2 ms-3 text-sm text-gray-500 rounded-lg hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
          >
            <span className="sr-only">Open sidebar</span>
            <svg
              className="w-6 h-6"
              aria-hidden="true"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                clipRule="evenodd"
                fillRule="evenodd"
                d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"
              ></path>
            </svg>
          </button>
        ) : (
          <button
            data-drawer-target="default-sidebar"
            data-drawer-toggle="default-sidebar"
            aria-controls="default-sidebar"
            type="button"
            onClick={toggleSidebar}
            className="inline-flex items-center p-2 mt-2 ms-3 text-sm text-gray-500 rounded-lg hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
          >
            <span className="sr-only">Open sidebar</span>
            <svg
              className="w-6 h-6"
              aria-hidden="true"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                clipRule="evenodd"
                fillRule="evenodd"
                d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"
              ></path>
            </svg>
          </button>
        )}

        {isSidebarOpen && (
          <aside
            id="default-sidebar"
            className="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0"
            aria-label="Sidebar"
          >
            <div className="h-full px-3 py-4 overflow-y-auto bg-[#e2e3ea] dark:bg-gray-800 mt-[73px]">
              <ul className="space-y-2 font-medium ">
                <li>
                  <button
                    onClick={() => setCurrentPage("accounts")}
                    className={`flex items-center p-2 rounded-lg group no-underline ${
                      currentPage === "accounts"
                        ? "text-white bg-[#6467c0]"
                        : "text-gray-900 "
                    }`}
                  >
                    <span className="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white">
                      <UserIcon className="text-black" />
                    </span>
                    <span className="ms-3">Accounts</span>
                  </button>
                </li>
                <li>
                  <button
                    onClick={() => setCurrentPage("payments")}
                    className={`flex items-center p-2 rounded-lg group no-underline ${
                      currentPage === "payments"
                        ? "text-white bg-[#6467c0]"
                        : "text-gray-900 "
                    }`}
                  >
                    <span className="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white">
                      <CreditCardIcon className="text-black" />
                    </span>
                    <span className="flex-1 ms-3 whitespace-nowrap">
                      Pay
                    </span>
                  </button>
                </li>
                <li>
                  <button
                    onClick={() => setCurrentPage("changePassword")}
                    className={`flex items-center p-2 rounded-lg group no-underline ${
                      currentPage === "changePassword"
                        ? "text-white bg-[#6467c0]"
                        : "text-gray-900"
                    }`}
                  >
                    <span className="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white">
                      <PencilAltIcon className="text-black" />
                    </span>
                    <span className="flex-1 ms-3 whitespace-nowrap">
                      Change Password
                    </span>
                  </button>
                </li>
                <li>
                  <button
                    onClick={() => setCurrentPage("profile")}
                    className={`flex items-center p-2 rounded-lg group no-underline ${
                      currentPage === "profile"
                        ? "text-white bg-[#6467c0]"
                        : "text-gray-900"
                    }`}
                  >
                    <span className="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white">
                      <UserCircleIcon className="text-black" />
                    </span>
                    <span className="flex-1 ms-3 whitespace-nowrap">
                      Profile
                    </span>
                  </button>
                </li>
                <li>
                  <button
                    onClick={handleLogout}
                    className={`flex items-center p-2 rounded-lg group no-underline ${
                      currentPage === "profile"
                        ? "text-white bg-[#6467c0]"
                        : "text-gray-900"
                    }`}
                  >
                    <span className="flex-shrink-0 w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white">
                      <LogoutIcon className="text-black" />
                    </span>
                    <span className="flex-1 ms-3 whitespace-nowrap">
                      logout
                    </span>
                  </button>
                </li>
              </ul>
            </div>
          </aside>
        )}
        <div className={`p-4 ${isSidebarOpen ? "sm:ml-64" : ""}`}>
          {renderPage()}
        </div>
      </div>
    </div>
  );
};

export default CustomerHomePage;
