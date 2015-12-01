<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['datein'])&& isset($_REQUEST['dateout'])){
        $datein = $_REQUEST['datein'];
        $dateout = $_REQUEST['dateout'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT * FROM Order_User NATURAL JOIN User WHERE Date_Output >= $datein AND Date_Output <= $dateout";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            $response["success"] = 1;
            $response['boughts'] = array();
            while ($row = mysqli_fetch_array($result)) {
                $bought = array();
                $bought['payment'] = $row['Payment'];
                $bought['date'] = $row['Date_Output'];
                $bought['name'] = $row['Name'];
                $bought['oid'] = $row['OID'];

                array_push($response['boughts'],$bought);
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
