import React, { useState, useEffect } from "react";

const url = "http://localhost:7777/easybook/v1/api/users";
const UseApiUpdate = () => {
	const [joke, setJoke] = useState("");

	const getJoke = async () => {
		const response = await fetch(url, {
			method: "GET",
			headers: {
				"Content-Type": "text/plain",
			},
		});
		const data = await response.json();
		setJoke(data + "......");
		console.log(data + "......");
	};

	useEffect(() => {
		getJoke();
	}, []);

	return (
		<>
			<p>{joke}</p>
		</>
	);
};

export default UseApiUpdate;
