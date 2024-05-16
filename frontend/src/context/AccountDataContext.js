import React, { createContext, useContext, useState } from 'react';

export const AccountDataContext = createContext();

export const useAccountDataContext = () => useContext(AccountDataContext);

export const AccountDataProvider = ({ children }) => {
  const [accObjs, setAccObjs] = useState([]);

  const updateAccObjs = (newAccObjs) => {
    setAccObjs(newAccObjs);
  };

  return (
    <AccountDataContext.Provider value={{ accObjs, updateAccObjs }}>
      {children}
    </AccountDataContext.Provider>
  );
};
