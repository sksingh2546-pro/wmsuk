<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Excel import</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    


    <div class="container" style="width:500px; margin-top:70px;">
        <div class="jumbotron">
    <h1 class="pb-5">EXCEL IMPORT</h1>
    <div class="file-upload pb-5">
        <div class="file-select">
          <div class="file-select-button" id="fileName">Choose File</div>
          <div class="file-select-name" id="noFile">No file chosen...</div> 
          <input type="file" name="chooseFile" id="chooseFile">
        </div>
      </div>
    <button class="btn btn-success" onclick="uploadSingleFile()">submit</button>
</div>
</div>




    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script type="text/javascript">
<script type="text/javascript" src="paho.js"></script>
'use strict';
var client = new Paho.MQTT.Client("localhost", Number(9001), "clientId");

//set callback handlers
client.onConnectionLost = onConnectionLost;
client.onMessageArrived = onMessageArrived;

//connect the client
client.connect({onSuccess:onConnect});
		        	  function onConnect() {
		        	  	//Once a connection has been made, make a subscription and send a message.
		        	  	console.log("onConnect");
		        	  	client.subscribe("#");
		        	  	}
function onConnectionLost(responseObject) {
	if (responseObject.errorCode !== 0) {
	console.log("onConnectionLost:"+responseObject.errorMessage);
	}
	}

function onMessageArrived(message) {
	console.log(message.payloadString)
	}

    function uploadSingleFile() {
    	
    	 var file=document.getElementById("chooseFile").files;
    	    console.log(file[0]);
				if(file[0]==''){
			alert("Please Select file");
		}
		else{
        var formData = new FormData();
        formData.append("file", file[0]);
        
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/uploadDisptchPlan");

        xhr.onload = function() {
            console.log(xhr.responseText);
            var response = xhr.responseText;
            if(response!= "") {
             message = new Paho.MQTT.Message(JSON.stringify(hash));
	        	  	message.destinationName = "event";
	        	  	client.send(message);
              
                alert("File Uploaded Successfully");
               
            } else {
                singleFileUploadSuccess.style.display = "none";
                singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
            }
        }

        xhr.send(formData);
		}
    	 
    }

</script>
</body>
</html>