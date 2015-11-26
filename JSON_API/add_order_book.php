<?php
    $response = array();
    require_once __DIR__.'/db_config.php';

    if(isset($_REQUEST['uid']) && isset($_REQUEST['payment'])){
        $uid = $_REQUEST['uid'];
        $payment = $_REQUEST['payment'];
        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        //tao OID
        $sql = "SELECT COUNT(OID) FROM Order_User";
        $result = mysqli_query($con, $sql);
        $row = mysqli_fetch_array($result);
        $count = $row[0];

        //
        $sql = "SELECT * FROM Cart WHERE UID = $uid";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            $sql = "INSERT INTO Order_User(OID, UID, Payment) VALUES($count, $uid, $payment)";
            $result = mysqli_query($con, $sql);
            if($result){
                $sql = "SELECT * FROM Cart WHERE UID = $uid";
                $result = mysqli_query($con, $sql);
                if(!empty($result) && (mysqli_num_rows($result) > 0) ){
                    $response["success"] = 1;
                    while ($row = mysqli_fetch_array($result)) {
                        $bid = $row['BID'];
                        $total = $row['Total'];

                        //lay so luong sach con lai kho
                        $sql = "SELECT Quantity FROM Book WHERE BID = $bid";
                        $tmp = mysqli_query($con, $sql);
                        $row = mysqli_fetch_array($tmp);
                        $quantity = $row[0];

                        $sql = "UPDATE Book SET Quantity = $quantity - $total WHERE BID = $bid";
                        $tmp = mysqli_query($con, $sql);
                        if(!$tmp){
                            $response["success"] = 0;
                            //echo json_encode($response);
                            break;
                        }
                        $sql = "INSERT INTO Order_Book(OID, BID, Quantity) VALUES($count, $bid, $total)";
                        mysqli_query($con, $sql);

                    }
                }
                $sql = "DELETE FROM Cart WHERE UID = $uid";
                mysqli_query($con, $sql);
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
    }
    else{
        $response["success"] = 0;
        echo json_encode($response);
    }
