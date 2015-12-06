<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['datein'])&& isset($_REQUEST['dateout'])){
        $datein = $_REQUEST['datein'];
        $dateout = $_REQUEST['dateout'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT * FROM Order_User NATURAL JOIN User WHERE Confirm = 0 AND Date_Output >= $datein AND Date_Output <= $dateout";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            $response["success"] = 1;
            $response['orders'] = array();
            while ($row = mysqli_fetch_array($result)) {
                $order = array();
                $order['payment'] = $row['Payment'];
                $order['date'] = $row['Date_Output'];
                $order['name'] = $row['Name'];
                $order['oid'] = $row['OID'];
                $order['profit'] = $row['Profit'];
                $order['address'] = $row['Address'];


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
