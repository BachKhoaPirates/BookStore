<?php
$response = array();
    if(isset($_REQUEST['image'])){
        $image = $_REQUEST['image'];
        if(file_put_contents('image/test.jpg', base64_decode($image))){
            $response["success"] = 1;
echo json_encode($response);
        }
        else{
           $response["success"] = 0;
echo json_encode($response);
        }

    }
