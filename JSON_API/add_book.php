<?php
    $response = array();
    require_once __DIR__.'/db_config.php';

    if(isset($_REQUEST['name']) && isset($_REQUEST['author']) && isset($_REQUEST['price']) && isset($_REQUEST['quantity']) && isset($_REQUEST['content']) && isset($_REQUEST['price_add']) && isset($_REQUEST['image']) ){

        $name = $_REQUEST['name'];
        $author = $_REQUEST['author'];
        $price = $_REQUEST['price'];
        $quantity = $_REQUEST['quantity'];
        $content = $_REQUEST['content'];
        $price_add = $_REQUEST['price_add'];
        $image = $_REQUEST['image'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        //tao BID
        $sql = "SELECT BID from Book order by BID DESC limit 1";
        $result = mysqli_query($con, $sql);
        $row = mysqli_fetch_array($result);
        $bid = $row[0] + 1;

        $sql = "INSERT INTO Book(BID, Name_Book, Author, Price, Quantity_Book, Content, Price_Add) VALUES('$bid', '$name', '$author', '$price', '$quantity', '$content', '$price_add')";
        $result = mysqli_query($con, $sql);
        if($result){

            if(isset($_REQUEST['distribute'])){

                $distribute = $_REQUEST['distribute'];
                //tao PID
                $sql = "SELECT PID from Distribute order by PID DESC limit 1";
                $result = mysqli_query($con, $sql);
                $row = mysqli_fetch_array($result);
                $pid = $row[0] + 1;

                $sql = "INSERT INTO Distribute(PID, Sort) VALUES('$pid', '$distribute')";
                mysqli_query($con, $sql);

            }
            else{
                $pid = $_REQUEST['pid'];
            }
            $sql = "INSERT INTO Genre_Book(PID, BID) VALUES('$pid', '$bid')";
            mysqli_query($con, $sql);

            if(isset($_REQUEST['publisher'])){

                $publisher = $_REQUEST['publisher'];
                //tao NID
                $sql = "SELECT NID from Publisher order by NID DESC limit 1";
                $result = mysqli_query($con, $sql);
                $row = mysqli_fetch_array($result);
                $nid = $row[0] + 1;

                $sql = "INSERT INTO Publisher(NID, Name_Publisher) VALUES('$nid', '$publisher')";
                mysqli_query($con, $sql);

            }
            else{
                $nid = $_REQUEST['nid'];
            }
            $sql = "INSERT INTO Genre_Publisher(NID, BID) VALUES('$nid', '$bid')";
            mysqli_query($con, $sql);
            file_put_contents('image/'.$bid.'.jpg', base64_decode($image));
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
