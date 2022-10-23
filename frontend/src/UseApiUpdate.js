import React, { useState, useEffect } from "react";

const url = "http://192.168.1.104:7777/easybook/v1/api/users";
const UseApiUpdate = () => {
  const [users, setUsers] = useState([]);

  const getUsers = async () => {
    const response = await fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "text/plain",
      },
    });
    const data = await response.json();
    setUsers(data);
    console.log(data);
  };

  useEffect(() => {
    getUsers();
  }, []);

  return (
    <>
      <h1>Hello World</h1>
      <ul>
        {users.map((user) => {
          const { userId, userName, email, address, password, phone } = user;
          return (
            <li key={userId}>
              <h2>{userName}</h2>
              <h4>{email}</h4>
              <h4>{address}</h4>
              <h4>{password}</h4>
              <h4>{phone}</h4>
            </li>
          );
        })}
      </ul>
    </>
  );
};

export default UseApiUpdate;
