<?php
    $con = mysqli_connect("localhost", "root", "root", "user");
 
    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userEmail = $_POST["userEmail"];
 
    $statement = mysqli_prepare($con, "INSERT INTO user VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssss", $userID, $userPassword, $userName, $userEmail);
    mysqli_stmt_execute($statement);
 
 
    $response = array();
    $response["success"] = true;
 
 
    echo json_encode($response);
 
?>