<?php
    //echo '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />';
	$response = array();
	require_once __DIR__.'/db_config.php';
	$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME) or die("Error");
	$sql = "SELECT DISTINCT Sort FROM Distribute";
	$result = mysqli_query($con, $sql);
	if(!empty($result)){
		$response['success'] = 1;
		$response['distributes'] = array();
		while ($row = mysqli_fetch_array($result)) {
			$Distribute['distribute'] = $row['Sort'];
			array_push($response['distributes'], $Distribute);
		}
		echo json_encode($response);
	}
	mysqli_close($con);
?>