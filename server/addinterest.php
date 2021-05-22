<?php
    $con = mysqli_connect("localhost", "root", "root", "test");
 
    $Name = $_POST["Name"];
    $Code = $_POST["Code"];
    $userId= $_POST["userId"];
 
    $statement = mysqli_query($con, "INSERT INTO interestlist VALUES ('".$Code."','".$Name."','".$userId."')");
    
    echo $statement;

    mysqli_close($con);
?>