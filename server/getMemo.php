<?php
    try {
        $mongo = new MongoDB\Driver\Manager("mongodb://user:user@localhost:27017/admin");

        $userID = $_POST["name"];
        // $userID = "text";

        $filter = ['name' => $userID];
        $options = [];
        $query = new \MongoDB\Driver\Query($filter, $options);
        $rows = $mongo->executeQuery('user.test', $query);

        $arr= array();
        $i = 0;
        foreach ($rows as $document) {
            $arr[$i++]= $document;
        }

        echo json_encode($arr);
    } catch (MongoDB\Driver\Exception\Exception $e) {
        $filename = basename(__FILE__);
    
        echo "The $filename script has experienced an error.\n"; 
        echo "It failed with the following exception:\n";
        
        echo "Exception:", $e->getMessage(), "\n";
        echo "In file:", $e->getFile(), "\n";
        echo "On line:", $e->getLine(), "\n";       
    }
?>