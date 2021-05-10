<?php
    try {
        $mongo = new MongoDB\Driver\Manager("mongodb://user:user@localhost:27017/admin");
        $bulk = new MongoDB\Driver\BulkWrite;
        $document = [
            '_id' => new MongoDB\BSON\ObjectID,
            'name' => 'text',
            'title' => 'abc',
            'content' => 'aaaaaa',
            'date' => date("Y-m-d")
        ];
        $bulk->insert($document);
        $mongo->executeBulkWrite('user.test',$bulk);

	echo "connected";
    } catch (MongoDB\Driver\Exception\Exception $e) {
        $filename = basename(__FILE__);
    
        echo "The $filename script has experienced an error.\n"; 
        echo "It failed with the following exception:\n";
        
        echo "Exception:", $e->getMessage(), "\n";
        echo "In file:", $e->getFile(), "\n";
        echo "On line:", $e->getLine(), "\n";       
    }
?>