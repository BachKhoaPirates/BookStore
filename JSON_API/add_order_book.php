<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['uid']) && isset($_REQUEST['bid']) && isset($_REQUEST['quantity'])){
        $user = $_REQUEST['uid'];
        $book = $_REQUEST['bid'];
        $quantity = $_REQUEST['quantity'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        $sql = "SELECT COUNT(OID) FROM Order_Book";
        $result = mysqli_query($con, $sql);
        $result = mysqli_fetch_row($result);
        $count = $result[0];

        $sql = "SELECT Price FROM Book WHERE BID = $book";
        $result = mysqli_query($con, $sql);
        $result = mysqli_fetch_row($result);
        $payment = $result[0] * $quantity;

        $sql = "INSERT INTO Order_Book(OID, UID, BID, Quantity, Payment) VALUES($count, $user, $book, $quantity, $payment)";
        $result = mysqli_query($con, $sql);
        if($result){
            $response["success"] = 1;
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