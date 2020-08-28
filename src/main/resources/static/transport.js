/*
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
*/





function getAddress(){
	var XHR = new XMLHttpRequest();
	XHR.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = XHR.responseText;
	         var result=JSON.parse(response);
            	        console.log(result);
	        var address=document.getElementById("transport1")
	        for(var key in result.address){
	        address.innerHTML+='<option value='+result.address[key]+'>';
	        }

	    }
	};
	XHR.open("GET", "/api/getAddress", true);

	XHR.send();
	}
function insertTransport(){

	var driverName=document.getElementById("driverName").value;
	var vehicleNo=document.getElementById("vehicleNo").value;
	var contactNo=document.getElementById("contactNo").value;
	var partyName=document.getElementById("partyName").value;
	var address=document.getElementById("address").value;
	var state=document.getElementById("state").value;
		var truck_bay=document.getElementById("truckbay").value;
	var permit=document.getElementById("permit").value;
	if(driverName==""){
		alert("Please Enter driverName");
	}
	else if(vehicleNo==""){
		alert("Please Enter vehicleNo");
	}
else if(contactNo==""){
		alert("Please Enter contactNo");
	}
else if(partyName==""){
		alert("Please Enter partyName");
	}
else if(address==""){
		alert("Please Enter address");
	}
else if(state==""){
		alert("Please Enter state");
	}
else if(permit==""){
		alert("Please Enter permit");
	}
	
            	else if(truck_bay==""){
                            		alert("Please Enter truck bay");
                            	}

	else{
		 var XHR2 = new XMLHttpRequest();
         var hash={"driver_name":""+driverName+"",
         "contact_no":""+contactNo+"",
         "vehicle_no":""+vehicleNo+"",
         "party_name":""+partyName+"",
         "address":""+address+"",
         "state":""+state+"",
         "pallet_weight":"",
          "total_weight":"",
           "truck_bay_no":""+truck_bay+"",
         "permit_no":""+permit+""


         }
        console.log(hash);
		XHR2.open("POST", "/api/insertTransport");
		XHR2.setRequestHeader("Content-Type", "application/json;charset=UTF-8");


	XHR2.onload = function() {
	          console.log(XHR2.responseText);
	          var response = JSON.parse(XHR2.responseText);
	          if(response['message']=="Successful") {

	             localStorage.setItem("state",document.getElementById("state").value);
	             window.location.href="purchase";
	          }

			  else {

	            alert("unSuccessful");

	          }
	      }


	XHR2.send(JSON.stringify(hash));
}
}
function getDriverName(){
	var XHR = new XMLHttpRequest();
	XHR.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = XHR.responseText;
	         var result=JSON.parse(response);
            	        console.log(result);
	        var address=document.getElementById("driver1")
	        for(var key in result.driver){
	        address.innerHTML+='<option value='+result.driver[key]+'>';
	        }

	    }
	};
	XHR.open("GET", "/api/getDriverName", true);

	XHR.send();
	}
function getContactNo(){
	var XHR = new XMLHttpRequest();
	XHR.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = XHR.responseText;
	         var result=JSON.parse(response);
            	        console.log(result);
	        var address=document.getElementById("contact1")
	        for(var key in result.contact){
	        address.innerHTML+='<option value='+result.contact[key]+'>';
	        }

	    }
	};
	XHR.open("GET", "/api/getContactNo", true);

	XHR.send();
	}
function getVehicleNo(){
	var XHR = new XMLHttpRequest();
	XHR.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = XHR.responseText;
	         var result=JSON.parse(response);
            	        console.log(result);
	        var address=document.getElementById("vehicle1")
	        for(var key in result.vehicle){
	        address.innerHTML+='<option value='+result.vehicle[key]+'>';
	        }

	    }
	};
	XHR.open("GET", "/api/getVehicleNo", true);

	XHR.send();
	}
function getPartyName(){
	var XHR = new XMLHttpRequest();
	XHR.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        var response = XHR.responseText;
	         var result=JSON.parse(response);
	        var address=document.getElementById("party1")
	        for(var key in result.party){
	        address.innerHTML+='<option value='+result.party[key]+'>';
	        }

	    }
	};
	XHR.open("GET", "/api/getPartyName", true);

	XHR.send();
	}
$(document).ready(function() {
    $('#permit').tokenfield({
      autocomplete: {
        source: ['red','blue','green','yellow','violet','brown','purple','black','white'],
        delay: 100
      },
      showAutocompleteOnFocus: true
    });
});

function searchInput(){







}

window.onload=getAddress();
window.onload=getDriverName();
window.onload=getContactNo();
window.onload=getVehicleNo();
window.onload=getPartyName();