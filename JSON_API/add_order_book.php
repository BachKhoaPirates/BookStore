<?php
    $response = array();
    require_once __DIR__.'/db_config.php';

    if(isset($_REQUEST['uid']) && isset($_REQUEST['payment'])){
        $uid = $_REQUEST['uid'];
        $payment = $_REQUEST['payment'];
        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        $date = date('Ymd');
        //tao OID
        $sql = "SELECT OID from Order_User order by OID DESC limit 1";
        $result = mysqli_query($con, $sql);
        $row = mysqli_fetch_array($result);
        $oid = $row[0] + 1;

        $sql = "SELECT * FROM Cart WHERE UID = $uid";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            mysqli_begin_transaction($con);
            $sql = "INSERT INTO Order_User(OID, UID, Payment, Confirm, Date_Output) VALUES($oid, $uid, $payment, 0, $date)";
            $result = mysqli_query($con, $sql);
            if($result){
                $price_add = 0;
                $sql = "SELECT * FROM Cart WHERE UID = $uid";
                $result = mysqli_query($con, $sql);
                if(!empty($result) && (mysqli_num_rows($result) > 0) ){
                    $response["success"] = 1;
                    while ($row = mysqli_fetch_array($result)) {
                        $bid = $row['BID'];
                        $total = $row['Total'];

                        //lay so luong sach con lai kho
                        $sql = "SELECT Quantity_Book, Price_Add FROM Book WHERE BID = $bid";
                        $tmp = mysqli_query($con, $sql);
                        $row = mysqli_fetch_array($tmp);
                        $quantity = $row['Quantity_Book'];
                        $price_add += $row['Price_Add']*$total;

                        if($quantity > $total){
                            $sql = "UPDATE Book SET Quantity_Book = $quantity - $total WHERE BID = $bid";
                            $tmp = mysqli_query($con, $sql);
                            if(!$tmp){
                                $response["success"] = 0;
                                //echo json_encode($response);
                                break;
                            }
                        }
                        else{
                            $response["success"] = 0;
                            break;
                        }
                        $sql = "INSERT INTO Order_Book(OID, BID, Quantity_Order) VALUES($oid, $bid, $total)";
                        mysqli_query($con, $sql);

                    }
                }
                $sql = "DELETE FROM Cart WHERE UID = $uid";
                mysqli_query($con, $sql);
                $sql = "UPDATE Order_User SET Profit = $payment - $price_add WHERE OID = $oid";
                mysqli_query($con, $sql);

                echo json_encode($response);
                if($response[success]) mysqli_commit($con);
                else{
                    mysqli_rollback($con);
                }
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
    }
    else{
        $response["success"] = 0;
        echo json_encode($response);
    }
