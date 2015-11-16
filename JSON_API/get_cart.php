<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['uid'])){
        $uid = $_REQUEST['uid'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT * FROM Cart NATURAL JOIN Book WHERE UID = $uid AND Buy = 0";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            $response["success"] = 1;
            $response['books'] = array();
            while ($row = mysqli_fetch_array($result)) {
                $book = array();
                $book['bid'] = $row['BID'];
                $book['name'] = $row['Name_Book'];
                $book['price'] = $row['Price'];
                //$book['publisher'] = $row['Publisher'];
                $book['author'] = $row['Author'];
                //$book['quantity'] = $row['Quantity'];
                //$book['content'] = $row['Content'];
                $book['link'] = IMAGE_URL.$row['BID'].'.jpg';

                array_push($response['books'],$book);
            }

            echo json_encode($response);
        }
        else{
            $response["success"] = 0;
            echo json_encode($response);
        }
        mysqli_close($con);
    }
    else{
        $response["success"] = 0;
        echo json_encode($response);
    }
?>