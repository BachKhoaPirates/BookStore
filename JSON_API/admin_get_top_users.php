<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['datein'])&& isset($_REQUEST['dateout'])){
        $datein = $_REQUEST['datein'];
        $dateout = $_REQUEST['dateout'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT UID, Name, Address, SUM(Payment) FROM User natural join Order_User WHERE Date_Output >= $datein AND Date_Output <= $dateout GROUP BY UID ORDER BY SUM(Payment)  DESC LIMIT 10";
        $result = mysqli_query($con , $sql);
        if(!empty($result)){
            $response['success'] = 1;
            $response['users'] = array();
            while ($row = mysqli_fetch_array($result)) {
                $user = array();
                $user['uid'] = $row['UID'];
                $user['name'] = $row['Name'];
                $user['add'] = $row['Address'];
                $user['money'] = $row['SUM(Payment)'];

                array_push($response['users'],$user);
            }
            echo json_encode($response);
            mysqli_close($con);
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
?>