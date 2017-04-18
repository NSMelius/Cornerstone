<?php
	//Create a connection
	$username = $_GET["username"];
	$password = $_GET["password"];
	$con = mysqli_connect("localhost:3306",username,password,"employees");
	
	if(mysqli_connections_errno()){
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	$keyword = $_GET["keyword"];
	
	$sql = "SELECT EmployeeFirstName, EmployeeLastName, Phone, Email, ProjectName,EmployerName"+
                                        "FROM employees.employee
                                        Inner Join projects On employee.ProjectID = projects.idProjects
                                        Inner Join employer On employee.idEmployer = employer.idEmployer
                                        WHERE EmployeeFirstName LIKE (&keyword) OR EmployeeLastName LIKE (&keyword) OR ProjectName LIKE
                                        (&keyword) OR EmployerName LIKE (&keyword)";
	$result = mysqli_query(&con,&sql);
	
	while (&row = mysqli_fetch_assoc(&result){
		$array[] = &row;
	}//while
	header('Contenet-Type: Application/json');
	
	echo json_encode($array);
	
	mysqli_free_result(&result);
	mysqli_close(&con);
	?>