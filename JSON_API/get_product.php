<?php
	$response = array();
	if(isset($_REQUEST['bid'])){
		$bid = $_REQUEST['bid'];
		require_once __DIR__.'/db_config.php';
		$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
		$sql = "SELECT * FROM Book WHERE BID = $bid";
		$result = mysqli_query($con, $sql);
		if(!empty($result)){
			$result = mysqli_fetch_array($result, MYSQLI_ASSOC);

			$response['Success'] = 1;
			$Product = array();
			$Product['Name_Book'] = $result['Name_Book'];
			$Product['Publisher'] = $result['Publisher'];
			$Product['Author'] = $result['Author'];
			$Product['Price'] = $result['Price'];
			$Product['Quantity'] = $result['Quantity'];

			$response['Product'] = array();
			array_push($response['Product'], $Product);

			echo json_encode($response);
		}
		else{
			$response["Success"] = 0;
			echo json_encode($response);
		}
		mysqli_close($con);
	}
	else{
		$response['Success'] = 0;
		echo json_encode($response);
	}			
?>