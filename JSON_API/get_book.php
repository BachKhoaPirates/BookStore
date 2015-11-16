<?php
	$response = array();
	if(isset($_REQUEST['bid'])){
		$bid = $_REQUEST['bid'];
        $like = 0;
		require_once __DIR__.'/db_config.php';
		$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        if(isset($_REQUEST['uid'])){
            $uid = $_REQUEST['uid'];
            $sql = "SELECT BID FROM Favorite WHERE UID = $uid";
            $result = mysqli_query($con, $sql);
            while ($row = mysqli_fetch_array($result)) {
                if($row['BID'] == $bid) {
                    $like = 1;
                    break;
                }
            }
        }
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
            $book['like'] = $like;
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