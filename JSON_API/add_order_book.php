<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['uid']) && isset($_REQUEST['bid']) && isset($_REQUEST['quantity']) && isset($_REQUEST['payment'])){
        $uid = $_REQUEST['uid'];
        $bid = $_REQUEST['bid'];
        $quantity = $_REQUEST['quantity'];
        $payment = $_REQUEST['payment'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        $sql = "SELECT COUNT(OID) FROM Order_User";
        $result = mysqli_query($con, $sql);
        $result = mysqli_fetch_row($result);
        $count = $result[0];

        // $sql = "SELECT Price FROM Book WHERE BID = $bid";
        // $result = mysqli_query($con, $sql);
        // $result = mysqli_fetch_row($result);
        // $payment = $result[0] * $quantity;

        $sql = "INSERT INTO Order_User(OID, UID, Payment) VALUES($count, $uid, $payment)";
        $result = mysqli_query($con, $sql);
        if($result){
            $sql = "INSERT INTO Order_Book(OID, BID, Quantity) VALUES('$count', '$bid', '$quantity')";
            $result = mysqli_query($con, $sql);
            if($result){
                $sql = "UPDATE Cart SET Buy = 1 WHERE UID = $uid AND BID = $bid";
                $result = mysqli_query($con, $sql);
                $response["success"] = 1;
                echo json_encode($response);
            }
            else{
                $response["success"] = 0;
                echo json_encode($response);
            }
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