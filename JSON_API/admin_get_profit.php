<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['datein'])&& isset($_REQUEST['dateout'])){
        $datein = $_REQUEST['datein'];
        $dateout = $_REQUEST['dateout'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT SUM(Profit) FROM Order_User WHERE Date_Output >= $datein AND Date_Output <= $dateout";
        $result = mysqli_query($con, $sql);
        $row = mysqli_fetch_array($result);
        if($result && $row[0] != null ){

            $response["success"] = 1;
            $response['profit'] = $row[0];
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
