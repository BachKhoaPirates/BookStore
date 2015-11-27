<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['uid']) && isset($_REQUEST['bid'])){
        $uid = $_REQUEST['uid'];
        $bid = $_REQUEST['bid'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        //update total book in cart
        if(isset($_REQUEST['total'])){
            $total = $_REQUEST['total'];
            $sql = "UPDATE Cart SET Total = $total WHERE UID = $uid AND BID = $bid";
            $result = mysqli_query($con, $sql);
            if($result){
                $response['success'] = 1;
                echo json_encode($response);
            }
            else{
                $response['success'] = 0;
                echo json_encode($response);
            }
        }
        //delete book in cart
        else{
            $sql = "DELETE FROM Cart WHERE UID = $uid AND BID = $bid";
            $result = mysqli_query($con, $sql);
            if($result){
                $response['success'] = 1;
                echo json_encode($response);
            }
            else{
                $response['success'] = 0;
                echo json_encode($response);
            }
        }
        mysqli_close($con);
    }
    else{
        $response['success'] = 0;
        echo json_encode($response);
    }