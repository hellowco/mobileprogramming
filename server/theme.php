<?php
    header("Content-Type:text/html; charset=UTF-8");
 
    $conn = mysqli_connect("localhost", "root", "root", "test");
 
    // mysqli_query($conn, "set names utf8");

    // $date = date('Ymd');
    $date = "20210516";
    
    $sql = "select * from ".$date."_theme";
    $result=mysqli_query($conn, $sql);
    
    $rowCnt= mysqli_num_rows($result);
 
    $arr= array(); //빈 배열 생성
 
    for($i=0;$i<$rowCnt;$i++){
        $row= mysqli_fetch_array($result, MYSQLI_ASSOC);
        //각 각의 row를 $arr에 추가
        $arr[$i]= $row;
        
    }
 
    //배열을 json으로 변환하는 함수가 있음.
    // $jsonData=json_encode($arr); //json배열로 만들어짐.
    // echo "$jsonData";

    echo json_encode($arr);
 
    mysqli_close($conn);
 
 
?>