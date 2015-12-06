<?php
    $response = array();
    require_once __DIR__.'/db_config.php';

    if(isset($_REQUEST['oid'])){
        $oid = $_REQUEST['oid'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        $sql = "SELECT * FROM Order_Book WHERE OID = $oid";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            $response["success"] = 1;
            while ($row = mysqli_fetch_array($result)) {
                $bid = $row['BID'];
                $total = $row['Quantity_Order'];

                //lay so luong sach con lai kho
                $sql = "SELECT Quantity_Book FROM Book WHERE BID = $bid";
                $tmp = mysqli_query($con, $sql);
                $row = mysqli_fetch_array($tmp);
                $quantity = $row[0];

                $sql = "UPDATE Book SET Quantity_Book = $quantity + $total WHERE BID = $bid";
                mysqli_query($con, $sql);

                $sql = "DELETE FROM Order_Book WHERE BID = $bid";
                mysqli_query($con, $sql);

            }
        }
        $sql = "DELETE FROM Order_User WHERE OID = $oid";
        $result = mysqli_query($con, $sql);

        echo json_encode($response);
        mysqli_close($con);

    }
        else{
        $response["success"] = 0;
        echo json_encode($response);
    }
