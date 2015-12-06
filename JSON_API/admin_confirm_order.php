<?php
    $response = array();
    require_once __DIR__.'/db_config.php';

    if(isset($_REQUEST['oid'])){
        $oid = $_REQUEST['oid'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "UPDATE Order_User SET Confirm = 1 WHERE OID = $oid";
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
    else{
        $response['success'] = 0;
        echo json_encode($response);

    }
