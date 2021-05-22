<?php
    header("Content-Type:text/html; charset=UTF-8");
 
    $conn = mysqli_connect("localhost", "root", "root", "test");
 
    mysqli_query($conn, "set names utf8");
    $userId= $_POST["userId"];
 
    $sql= "select * from interestlist where userID = '$userId'";
    $result=mysqli_query($conn, $sql);
    $arr= array(); //빈 배열 생성
    $i = 0;
    if(mysqli_num_rows($result) > 0){
        while($data = mysqli_fetch_assoc($result)){
            $arr[$i++] = $data;
        }
    }
 
    //배열을 json으로 변환하는 함수가 있음.
    // $jsonData=json_encode($arr); //json배열로 만들어짐.
    // echo "$jsonData";

    echo json_encode($arr);
 
    mysqli_close($conn);
?>