<?php
    try {
        $mongo = new MongoDB\Driver\Manager("mongodb://user:user@localhost:27017");
 
        $command = new MongoDB\Driver\Command(['ping' => 1]);
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