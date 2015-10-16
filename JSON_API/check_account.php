<?php
	$response = array();
	if(isset($_REQUEST['user']) && isset($_REQUEST['pass'])){
		$user = $_REQUEST['user'];
		$pass = $_REQUEST['pass'];

		require_once __DIR__.'/db_config.php';

		$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
		$sql = "SELECT PASS FROM ACCOUNT WHERE USER = '$user'";
		$result = mysqli_query($con, $sql);
		$row = mysqli_fetch_array($result);
		$data = $row[0];
		if($result && $data == $pass ){
			$response['success'] = 1;
			echo json_encode($response);
		}
		else{
			$response['success'] = 0;
			echo json_encode($response);
		}
		mysqli_close($con);
	}
	else{
		$response['succes'] = 0;
		echo json_encode($response);
	}
?>