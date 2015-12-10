<?php
	$response = array();
	if(isset($_REQUEST['uid']) && isset($_REQUEST['pass'])){
		$uid = $_REQUEST['uid'];
		$pass = $_REQUEST['pass'];
		require_once __DIR__.'/db_config.php';
		$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
		$sql = "SELECT UID, Address, Password, Name FROM User WHERE UID = $uid";
		$result = mysqli_query($con, $sql);
		if(!empty($result)){
			$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
			if($pass == $row["Password"] ){

                if($uid==123456){
                    $response['success'] = 2;
                    echo json_encode($response);
                    break;
                }

				$response["success"] = 1;
				$response["name"] = $row["Name"];
				$response["address"] = $row["Address"];

                $sql = "SELECT SUM(Payment) FROM Order_User WHERE UID = $uid";
                $result = mysqli_query($con, $sql);
                $row = mysqli_fetch_array($result);
                if($row[0] != null){
                    $response['money'] = $row[0];
                }
                else{
                    $response['money'] = 0;
                }
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
