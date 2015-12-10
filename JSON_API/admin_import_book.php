<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['price_add']) && isset($_REQUEST['bid']) && isset($_REQUEST['quantity_add']) ){
        $bid = $_REQUEST['bid'];
        $price_add = $_REQUEST['price_add'];
        $quantity_add = $_REQUEST['quantity_add'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        //lay so luong sach con lai trong kho
        $sql = "SELECT Quantity_Book, Price_Add FROM Book WHERE BID = $bid";
        $tmp = mysqli_query($con, $sql);
        $row = mysqli_fetch_array($tmp);
        $quantity = $row['Quantity_Book'];

        $date = date('Ymd');


        $sql = "UPDATE Book SET Quantity_Book = $quantity + $quantity_add, Price_Add = $price_add, Date_Add = $date WHERE BID = $bid";
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