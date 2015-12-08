<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['uid'])){
        $uid = $_REQUEST['uid'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT *  FROM Order_User WHERE UID = $uid";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            $response["success"] = 1;
            $response['orders'] = array();
            while ($row = mysqli_fetch_array($result)) {
                $order = array();
                $order['confirm'] = $row['Confirm'];
                $order['payment'] = $row['Payment'];
                $order['date'] = $row['Date_Output'];
                $order['oid'] = $row['OID'];

                array_push($response['orders'],$order);
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
