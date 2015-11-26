<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['uid']) && isset($_REQUEST['bid']) && isset($_REQUEST['total'])){
        $uid = $_REQUEST['uid'];
        $bid = $_REQUEST['bid'];
        $total = $_REQUEST['total'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        $sql = "SELECT Total FROM Cart WHERE UID = $uid AND BID = $bid";
        $result = mysqli_query($con, $sql);
        $row = mysqli_fetch_array($result);
        if($row[0] != NULL){
            $sql = "UPDATE Cart SET Total = $row[0] + $total WHERE UID = $uid AND BID = $bid";
            $result = mysqli_query($con, $sql);
            if($result){
                $response["success"] = 1;
                echo json_encode($response);
            }
            else{
                    $response["success"] = 0;
                    echo json_encode($response);
            }
        }
        else{
            $sql = "INSERT INTO Cart(UID, BID, Total) VALUES($uid, $bid, $total)";
            $result = mysqli_query($con, $sql);
            if($result){
                $response["success"] = 1;
                echo json_encode($response);
            }
            else{
                    $response["success"] = 0;
                    echo json_encode($response);
            }
        }
        mysqli_close($con);
    }
    else{
        $response["success"] = 0;
        echo json_encode($response);
    }
?>