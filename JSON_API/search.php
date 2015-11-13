<?php
    $response = array();
    if(isset($_REQUEST['key'])){
        $key = '\'%'.$_REQUEST['key'].'%\'';
        require_once __DIR__.'/db_config.php';
        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT * FROM Book WHERE Name_Book LIKE $key limit 10";
        $result = mysqli_query($con, $sql);
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
                $book['content'] = $row['Content'];
                $book['link'] = IMAGE_URL.$row['BID'].'.jpg';

                array_push($response['books'],$book);
            }
            echo json_encode($response);
            mysqli_close($con);
        }
        else{
            $response["success"] = 0;
            echo json_encode($response);
        }
    }
    else{
        $response['success'] = 0;
        echo json_encode($response);
    }
?>