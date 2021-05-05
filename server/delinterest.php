<?php
    $con = mysqli_connect("localhost", "root", "root", "test");
 
    $Name = $_POST["Name"];
    $Code = $_POST["Code"];
 
    $statement = mysqli_prepare($con, "DELETE FROM interestlist WHERE Code = ? AND Name = ?");
    mysqli_stmt_bind_param($statement, "ss", $Code, $Name);
    mysqli_stmt_execute($statement);
    $response = array();

    echo json_encode($response);
 
?>