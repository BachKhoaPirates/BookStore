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

			$response['success'] = 1;
            $book = array();
			$book['name'] = $result['Name_Book'];
			$book['publisher'] = $result['Publisher'];
			$book['author'] = $result['Author'];
			$book['price'] = $result['Price'];
			$book['quantity'] = $result['Quantity'];
            $book['link'] = IMAGE_URL.$bid.'.jpg';

			array_push($response, $book);

			echo json_encode($response);
		}
		else{
			$response["success"] = 0;
			echo json_encode($response);
		}
		mysqli_close($con);
	}
	else{
		$response['success'] = 0;
		echo json_encode($response);
	}
?>