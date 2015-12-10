<?php
    $response = array();
    require_once __DIR__.'/db_config.php';

    if(isset($_REQUEST['name']) && isset($_REQUEST['author']) && isset($_REQUEST['price']) && isset($_REQUEST['bid']) && isset($_REQUEST['content']) ){

        $name = $_REQUEST['name'];
        $author = $_REQUEST['author'];
        $price = $_REQUEST['price'];
        $content = $_REQUEST['content'];

        $bid = $_REQUEST['bid'];

        $con = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME)or die("Error");

        $sql = "UPDATE Book SET Name_Book = $name, Author = $author, Price = $price, Content = $content WHERE BID = $bid";
        $result = mysqli_query($con, $sql);
        if($result){

            if(isset($_REQUEST['distribute'])){

                $distribute = $_REQUEST['distribute'];
                //tao BID
                $sql = "SELECT PID from Distribute order by PID DESC limit 1";
                $result = mysqli_query($con, $sql);
                $row = mysqli_fetch_array($result);
                $pid = $row[0] + 1;

                $sql = "INSERT INTO Distribute(PID, Sort) VALUES($pid, $distribute)";
                mysqli_query($con, $sql);
                $sql = "INSERT INTO Genre_Book(PID, BID) VALUES($pid, $bid)";
                mysqli_query($con, $sql);

            }
            else{
                $pid = $_REQUEST['pid'];
                $sql = "UPDATE Genre_Book SET PID = $pid WHERE BID = $bid AND PID = $pid";
                mysqli_query($con, $sql);
            }


            if(isset($_REQUEST['publisher'])){

                $publisher = $_REQUEST['publisher'];
                //tao BID
                $sql = "SELECT NID from Publisher order by NID DESC limit 1";
                $result = mysqli_query($con, $sql);
                $row = mysqli_fetch_array($result);
                $nid = $row[0] + 1;

                $sql = "INSERT INTO Publisher(NID, Name_Publisher) VALUES($nid, $publisher)";
                mysqli_query($con, $sql);
                $sql = "INSERT INTO Genre_Publisher(NID, BID) VALUES($nid, $bid)";
                mysqli_query($con, $sql);

            }
            else{
                $nid = $_REQUEST['nid'];
                $sql = "UPDATE Genre_Publisher SET NID = $nid WHERE BID = $bid AND NID = $nid";
                mysqli_query($con, $sql);
            }

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
