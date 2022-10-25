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
    // if (response.status === 200) {
    //   alert("User Fetched");
    // }
  };

  useEffect(() => {
    getUsers();
  }, []);

  return (
    <>
      <h1>Current Users List</h1>
      <ul>
        {users.map((user) => {
          const { id, name, email, address, phone } = user;
          return (
            <li key={id}>
              <h2>{id}</h2>
              <h2>{name}</h2>
              <h4>{email}</h4>
              <h4>{address}</h4>
              <h4>{phone}</h4>
            </li>
          );
        })}
      </ul>
    </>
  );
};

export default UseApiUpdate;
