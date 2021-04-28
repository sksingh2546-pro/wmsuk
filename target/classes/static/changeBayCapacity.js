function bayList(){
	var xhttp1 = new XMLHttpRequest();
	xhttp1.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = xhttp1.responseText;
	        var result=JSON.parse(response);
	        console.log(result);
	        var barcode=document.getElementById("barcode");
	      var bay=document.getElementById("bay");
	      var ppTable=document.getElementById("pp");
	      ppTable.innerHTML="";
	      ppTable.innerHTML+='<tr  style="background:#3c8dbc;color:white;height:40px;text-align:center">'+
	       '<th style="text-align:center">Barcode</th>'+
          '<th style="text-align:center">BAY</th>'+
          '<th style="text-align:center">CAPACITY</th>'+
          '</tr>'
	      bay.innerHTML="";

          for(var key in result.bay){
        	  bay.innerHTML+='<option value='+result.bay[key].bay+'>';
        	  ppTable.innerHTML+='<tr  style="text-align:center">'+
                 '<td style="text-align:center">'+result.bay[key].barcode+'</td>'+
              '<td style="text-align:center">'+result.bay[key].bay+'</td>'+
              '<td style="text-align:center">'+parseInt(result.bay[key].capacity)+'</td></tr>'
        	  
          }
	    }
	};
	xhttp1.open("GET", gUrl.url+"/getBayList", true);

	xhttp1.send();
	}

window.onload=bayList();

function insertBay(){
	var barcode=document.getElementById("barocode1").value;
	var bay=document.getElementById("bay1").value;
	var capacity=document.getElementById("capacity").value;
	if(barcode==""){
		/*alert("Please Enter Barcode");*/
         Toastify({
                                    text: "Please Enter Barcode ",
                                    duration: 3000,
                                    gravity: "top",
                                    position: 'center',
                                    backgroundColor: "Red",
                                    close : true
                                  }).showToast();
	}
	else if(bay==""){
	/*	alert("Please Enter Bay");*/
	 Toastify({
                                        text: "Please Enter Bay",
                                        duration: 3000,
                                        gravity: "top",
                                        position: 'center',
                                        backgroundColor: "Red",
                                        close : true
                                      }).showToast();

	}else if(capacity==""){
	/*alert("Please Enter Capacity")*/;
	 Toastify({
                                        text: "Please Enter Capacity",
                                        duration: 3000,
                                        gravity: "top",
                                        position: 'center',
                                        backgroundColor: "Red",
                                        close : true
                                      }).showToast();
	}
	
	else{
		 var XHR2 = new XMLHttpRequest();
         var hash={"barcode":""+barcode+"","bay":""+bay+"","capacity":""+capacity+""}
		
		XHR2.open("POST", gUrl.url+"/insertBay");
		XHR2.setRequestHeader("Content-Type", "application/json;charset=UTF-8");


	XHR2.onload = function() {
	          console.log(XHR2.responseText);
	          var response = JSON.parse(XHR2.responseText);
	          if(response['message']=="Successful") {
	        	  

	                Toastify({
                                                                                  text: "Successfully Inserted ",
                                                                                  duration: 3000,
                                                                                  gravity: "top",
                                                                                  position: 'center',
                                                                                  backgroundColor: "Green",
                                                                                  close : true
                                                                               }).showToast();
                                                                               setTimeout(function(){
                                                                                location.reload();
                                                                               },3000)


	          }else if(response['message']=="Updated"){

	         /* alert("Successfully Updated");*/
	           Toastify({
                                                               text: "Successfully Updated ",
                                                               duration: 3000,
                                                               gravity: "top",
                                                               position: 'center',
                                                               backgroundColor: "Green",
                                                               close : true
                                                            }).showToast();
                                                            setTimeout(function(){
                                                             location.reload();
                                                            },3000)

             	          }



			  else {

	           /* alert("unSuccessful");*/
	           Toastify({
                                                  text: "Something Went Wrong ",
                                                  duration: 3000,
                                                  gravity: "top",
                                                  position: 'center',
                                                  backgroundColor: "Red",
                                                  close : true
                                               }).showToast();
	        
	          }
	      }


	XHR2.send(JSON.stringify(hash));
}
}
function getBay(){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		       // Typical action to be performed when the document is ready:
		        var response = xhttp.responseText;
		   var barcode=document.getElementById("barocode");
		    var result=JSON.parse(response);
		    console.log(result);
		       for(var key in result.bay){
		    	   barcode.innerHTML+= '<option value='+result.bay[key].barcode+'>'
		       }
		    }
		};
		xhttp.open("GET", gUrl.url+"/getBayList", true);

		xhttp.send();
		}

getBay();