
function transportList() {
	var xhttp1 = new XMLHttpRequest();
	xhttp1.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// Typical action to be performed when the document is ready:
			var response = xhttp1.responseText;
			var result = JSON.parse(response);
			console.log(result);

			var tbody = document.getElementById("table");
			tbody.innerHTML="";
			for ( var key in result.trans) {
				tbody.innerHTML += '<tr>'
						+ '<td>'
						+ result.trans[key].order_id
						+ '</td>'
						+ '<td >'
						+ result.trans[key].party_name
						+ '</td>'
						+ '<td >'
						+ result.trans[key].address
						+ '</td>'
						+ '<td >'
						+ result.trans[key].state
						+ '</td>'
						+ '<td align="center"><input class="btn btn-primary" type="button" data-toggle="modal" data-target="#verify" onclick="openForm(this)" value="Schedule Bay" id="vProduction"/></td>'
						+'</tr>'

			}
		}
	};
	xhttp1.open("GET", gUrl.url+"/getTransDetails", true);

	xhttp1.send();
}

window.onload = transportList();



function openForm(element) {

	var row = element.parentNode.parentNode.rowIndex
	var tboby = document.getElementById("proTable");
	var objCells = tboby.rows.item(row).cells;
	document.getElementById("orderId").innerHTML = objCells.item(0).innerHTML;

}
 

   function addDriverDetails(){
  	 var orderId=document.getElementById("orderId").innerHTML;
  	  var driverName=document.getElementById("driverName").innerHTML;
  	  var Contact=document.getElementById("Contact").innerHTML;
  	  var Vehicle=document.getElementById("Vehicle").innerHTML;
  	  var bayNo=document.getElementById("bayNo").value;
  	  var XHR2 = new XMLHttpRequest();
  	  if(driverName==""){
        alert("Enter Driver Name")
  	  }
  	  else if(Contact==""){
        alert("Enter Contact Number")

  	  }
  	 else if(Vehicle==""){
        alert("Enter Vehicle Number")

  	  }
  	  else if(bayNo=="select"){
        alert("Select Truck Bay")

  	  }else{
  	  var hash={"order_id":""+orderId+"","driver_name":""+driverName+""
  	  ,"contact_no":""+Contact+"" ,"vehicle_no":""+Vehicle+"","truck_bay_no":""+bayNo+"" }
  	 console.log(hash)
  	 XHR2.open("POST",gUrl.url+"/addDriverName");
  	 XHR2.setRequestHeader("Content-Type","application/json;charset=UTF-8");

  	  XHR2.onload = function() {
  		  console.log(XHR2.responseText);
  	      var response =JSON.parse(XHR2.responseText);
  	      if(response['message']=="Successful") {
              alert("Successful");
		  window.location.reload(true);

  	 } else {

  	 alert("Unsuccessful"); } }

  	 XHR2.send(JSON.stringify(hash));}}
  
  

	

	