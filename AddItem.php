<?php
	//Create a connection
	$username = $_GET["username"];
	$password = $_GET["password"];
	$con = mysqli_connect("localhost:3306",username,password,"employees");
	
	if(mysqli_connections_errno()){
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	$firstname = $_GET["firstname"];
	$lastname = $_GET["lastname"];
	$phone = $GET["phone"];
	$email = $_GET["email"];
	$project = $_GET["project"];
	$employer = $_GET["employer"];
	
	
	
	$sql = "INSERT INTO employee (EmployeeFirstName, EmployeeLastName, Phone, Email,ProjectID, idEmployer) VALUES
	($firstname),($lastname),($phone),($email),
	(SELECT idProjects FROM projects WHERE ProjectName =($project)),
	(SELECT idEmployer FROM employer WHERE EmployerName =(&employer)";
	mysqli_close(&con);
	?>