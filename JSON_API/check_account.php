<?php
	$response = array();
	if(isset($_REQUEST['uid']) && isset($_REQUEST['pass'])){
		$uid = $_REQUEST['uid'];
		$pass = $_REQUEST['pass'];
		require_once __DIR__.'/db_config.php';
		$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
		$sql = "SELECT SUM(Payment), UID, Address, Password, Name FROM User NATURAL JOIN Order_User WHERE UID = $uid";
		$result = mysqli_query($con, $sql);
		if(!empty($result)){
			$result = mysqli_fetch_array($result, MYSQLI_ASSOC);
			if($pass == $result["Password"] ){

				$response["success"] = 1;
				$response["name"] = $result["Name"];
                $response["money"] = $result["SUM(Payment)"];
				$response["address"] = $result["Address"];
				echo json_encode($response);
			}
			else{
				$response['success'] = 0;
				echo json_encode($response);
			}
			mysqli_close($con);
		}
	}
	else{
		$response['success'] = 0;
		echo json_encode($response);
	}

?>
