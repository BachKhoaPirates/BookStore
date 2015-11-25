<?php
    $response = array();
    require_once __DIR__.'/db_config.php';

    $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
    $sql = "SELECT UID, Name, Address, SUM(Payment) FROM User natural join Order_User GROUP BY UID ORDER BY SUM(Payment) DESC";
    $result = mysqli_query($con , $sql);
    if(!empty($result)){
        $response['success'] = 1;
        $response['users'] = array();
        while ($row = mysqli_fetch_array($result)) {
            $user = array();
            $user['uid'] = $row['UID'];
            $user['name'] = $row['Name'];
            $user['add'] = $row['Address'];
            array_push($response['users'],$user);
        }
        echo json_encode($response);
        mysqli_close($con);
    }
    else{
        $response["success"] = 0;
        echo json_encode($response);
    }
?>