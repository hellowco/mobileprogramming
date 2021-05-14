<?php
    try {
        $mongo = new MongoDB\Driver\Manager("mongodb://user:user@localhost:27017/admin");

        $id = $_POST["id"];
        $title = $_POST["title"];
        $content = $_POST["content"];
        
        $single_update = new MongoDB\Driver\BulkWrite();
        $single_update->update(
            ['_id' => new MongoDB\BSON\ObjectID($id)],
            ['$set' => ['title' => $title, 'content' => $content]],
            ['multi' => false, 'upsert' => false]
        );
        $result = $mongo->executeBulkWrite("user.test", $single_update);

        $arr= array();
        $i = 0;
        foreach ($result as $document) {
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