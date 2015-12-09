<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME) or die("Error");
    $sql = "SELECT DISTINCT * FROM Publisher";
    $result = mysqli_query($con, $sql);
    if(!empty($result)){
        $response['success'] = 1;
        $response['publishers'] = array();
        while ($row = mysqli_fetch_array($result)) {
            $publisher  = array();
            $publisher['nid'] = $row['NID'];
            $publisher['publisher'] = $row['Name_Publisher'];
            array_push($response['publishers'], $publisher);
        }
        echo json_encode($response);
    }
    mysqli_close($con);
?>