<?php
    $response = array();
    require_once __DIR__.'/db_config.php';
    if(isset($_REQUEST['datein'])&& isset($_REQUEST['dateout'])){
        $datein = $_REQUEST['datein'];
        $dateout = $_REQUEST['dateout'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");
        $sql = "SELECT Distinct * from Order_User as DH,Book JOIN (select BID from Order_Book group by(BID) order by(sum(Quantity_Order)) desc limit 0,1) ip ON Book.BID = ip.BID where DH.Date_Output >= $datein and DH.Date_Output <= $dateout ;";
        $result = mysqli_query($con, $sql);
        if(!empty($result) && (mysqli_num_rows($result) > 0) ){
            $response["success"] = 1;
            $response['books'] = array();
            while ($row = mysqli_fetch_array($result)) {
                $book = array();
                $book['bid'] = $row['BID'];
                $book['name'] = $row['Name_Book'];
                //$book['publisher'] = $row['Publisher'];
                $book['author'] = $row['Author'];
                $book['price'] = $row['Price'];
                //$book['quantity'] = $row['Quantity'];
                //$book['content'] = $row['Content'];
                $book['link'] = IMAGE_URL.$row['BID'].'.jpg';

                array_push($response['books'],$book);
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
