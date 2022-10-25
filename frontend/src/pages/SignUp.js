import React, { useState } from "react";
const url = "http://localhost:7777/easybook/v1/api/users";
const SignUp = () => {
  const [person, setPerson] = useState({
    name: "",
    email: "",
    password: "",
    re_password: "",
    phone: "",
    address: "",
  });
  const handleChange = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    setPerson({ ...person, [name]: value });
  };
  const setUsers = async () => {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(person),
    });
    console.log(person);
    const data = await response.json();
    console.log(response);
    console.log(data);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    setUsers();
    setPerson({
      name: "",
      email: "",
      password: "",
      re_password: "",
      phone: "",
      address: "",
    });
  };

  return (
    <>
      <h1>Add User</h1>
      <article className="form">
        <form onSubmit={handleSubmit} autoComplete="on">
          <div>
            <label htmlFor="name">Name : </label>
            <input
              type="text"
              id="name"
              name="name"
              value={person.name}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="email">Email : </label>
            <input
              type="email"
              id="email"
              name="email"
              value={person.email}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="password">Password : </label>
            <input
              type="password"
              id="password"
              name="password"
              value={person.password}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="re_password">Re-Enter Password : </label>
            <input
              type="password"
              id="re_password"
              name="re_password"
              value={person.re_password}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="phone">Phone : </label>
            <input
              type="text"
              id="phone"
              name="phone"
              value={person.phone}
              onChange={handleChange}
            />
          </div>
          <div>
            <label htmlFor="address">Address : </label>
            <input
              type="text"
              id="address"
              name="address"
              value={person.address}
              onChange={handleChange}
              required
            />
          </div>
          <button type="submit">Add Person</button>
        </form>
      </article>
    </>
  );
};

export default SignUp;
