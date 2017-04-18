<?php
	//Create a connection
	$username = $_GET["username"];
	$password = $_GET["password"];
	$con = mysqli_connect("localhost:3306",username,password,"employees");
	
	if(mysqli_connections_errno()){
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	else{
	echo "Connection established";
	}
	?>