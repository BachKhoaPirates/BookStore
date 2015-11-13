<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
    $sql = "select * from Book WHERE Book.BID = (select BID from Order_Book group by(BID) order by(sum(Quantity)) desc limit 10);";
    $result = mysqli_query($con , $sql);
    if(!empty($result)){
        $response['success'] = 1;
        $response['books'] = array();
        while ($row = mysqli_fetch_array($result)) {
            $book = array();
            $book['bid'] = $row['BID'];
            $book['name'] = $row['Name_Book'];
            $book['publisher'] = $row['Publisher'];
            $book['author'] = $row['Author'];
            $book['price'] = $row['Price'];
            $book['quantity'] = $row['Quantity'];

            array_push($response['books'],$book);
        }
        echo json_encode($response);
        mysqli_close($con);
        }
        else{
            $response["success"] = 0;
            echo json_encode($response);
        }
?>