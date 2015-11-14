<?php
	$response = array();
	if(isset($_REQUEST['bid'])){
		$bid = $_REQUEST['bid'];
		require_once __DIR__.'/db_config.php';
		$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
		$sql = "SELECT * FROM Book WHERE BID = $bid";
		$result = mysqli_query($con, $sql);
		if(!empty($result)){
			$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

			$response['success'] = 1;
            $response['books'] = array();
            $book = array();
			$book['name'] = $row['Name_Book'];
			$book['publisher'] = $row['Publisher'];
			$book['author'] = $row['Author'];
			$book['price'] = $row['Price'];
			$book['quantity'] = $row['Quantity'];
            $book['content'] = $row['Content'];
            $book['link'] = IMAGE_URL.$bid.'.jpg';

			array_push($response['books'], $book);

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