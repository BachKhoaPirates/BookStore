<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['uid']) && isset($_REQUEST['bid'])){
        $uid = $_REQUEST['uid'];
        $bid = $_REQUEST['bid'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "INSERT INTO Favorite(UID, BID) VALUES('$uid', '$bid')";
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