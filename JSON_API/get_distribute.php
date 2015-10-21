<?php
	$response = array();
	require_once __DIR__.'/db_config.php';
	$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME) or die("Error");
	$sql = "SELECT DISTINCT Sort FROM Distribute";
	$result = mysqli_query($con, $sql);
	if(!empty($result)){
		$response['Success'] = 1;
		$response['Distributes'] = array();
		while ($row = mysqli_fetch_array($result)) {
			$Distribute['Distribute'] = $row['Sort'];
			array_push($response['Distributes'], $Distribute);
		}
		echo json_encode($response);
	}
	mysqli_close($con);
?>