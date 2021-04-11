<?php
    $con = mysqli_connect("localhost", "root", "root", "user");
 
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userPassword = base64_encode(hash('sha512', $userPassword, true));
    $userName = $_POST["userName"];
    $userEmail = $_POST["userEmail"];
 
    $statement = mysqli_prepare($con, "INSERT INTO user VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssss", $userID, $userPassword, $userName, $userEmail);
    mysqli_stmt_execute($statement);
 
 
    $response = array();
    $response["success"] = true;
 
    if ($response["success"]){
        // 성공시 mongodb 연결하여 아이디 추가
        try {
            $mongo = new MongoDB\Driver\Manager("mongodb://user:user@localhost:27017/");
            $bulk = new MongoDB\Driver\BulkWrite;
            $document = ['_id' => new MongoDB\BSON\ObjectID, "title" => "title", "content" => "content", "date" => date()];
            $bulk->insert($document);
            $mongo -> executeBulkWrite('user.test', $bulk);

            // $collection -> inser($document);
     
        //     $command = new MongoDB\Driver\Command(['ping' => 1]);
        // echo "connected";
        } catch (MongoDB\Driver\Exception\Exception $e) {
            $filename = basename(__FILE__);
        
            echo "The $filename script has experienced an error.\n"; 
            echo "It failed with the following exception:\n";
            
            echo "Exception:", $e->getMessage(), "\n";
            echo "In file:", $e->getFile(), "\n";
            echo "On line:", $e->getLine(), "\n";       
        }
    }

    echo json_encode($response);
 
?>