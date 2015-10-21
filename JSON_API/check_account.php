<?php
	$response = array();
	if(isset($_REQUEST['user']) && isset($_REQUEST['pass'])){
		$user = $_REQUEST['user'];
		$pass = $_REQUEST['pass'];
		require_once __DIR__.'/db_config.php';
		$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
		$sql = "SELECT * FROM User WHERE UID = $user";
		$result = mysqli_query($con, $sql);
		if(!empty($result)){
			$result = mysqli_fetch_array($result, MYSQLI_ASSOC);
			if($pass == $result["Password"] ){

				$response["Success"] = 1;
				$response["Name"] = $result["Name"];
				$response["Address"] = $result["Address"]; 
				echo json_encode($response);
			}
			else{
				$response['Success'] = 0;
				echo json_encode($response);
			}
			mysqli_close($con);
		}
	}
	else{
		$response['Success'] = 0;
		echo json_encode($response);
	}

?>
