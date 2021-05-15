<?php
    $con = mysqli_connect("localhost", "root", "root", "test");
 
    $Name = $_POST["Name"];
    $Code = $_POST["Code"];
    $userId= $_POST["userId"];
 
    $statement = mysqli_prepare($con, "DELETE FROM interestlist WHERE Code = ? AND Name = ? AND userID = ?");
    mysqli_stmt_bind_param($statement, "sss", $Code, $Name, $userId);
    mysqli_stmt_execute($statement);
    $response = array();

    echo json_encode($response);
 
?>