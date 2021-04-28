
/*function bayList(){
	var xhttp1 = new XMLHttpRequest();
	xhttp1.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        var response = xhttp1.responseText;
	        var result=JSON.parse(response);
	        console.log(result);
	      var bay=document.getElementById("bay");
	      bay.innerHTML="";
	      bay.innerHTML='<option value="select">Select</option>';
          for(var key in result.bay){
        	  bay.innerHTML+='<option value='+result.bay[key].bay+'>'+result.bay[key].bay+'</option>';

          }
	    }
	};
	xhttp1.open("GET", gUrl.url+"/getBayList", true);

	xhttp1.send();
	}

window.onload=bayList();*/

/*<!--function skuList(){
	var xhttp1 = new XMLHttpRequest();
	xhttp1.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = xhttp1.responseText;
	        var result=JSON.parse(response);
	        console.log(result);
	      var sku=document.getElementById("sku");
	      sku.innerHTML="";
	      sku.innerHTML='<option value="select">Select</option>';
          for(var key in result.sku){
        	  sku.innerHTML+='<option value='+result.sku[key].sku+'>'+result.sku[key].sku+'</option>';
          }
	    }
	};
	xhttp1.open("GET", "/api/getSkuList", true);

	xhttp1.send();
	}
window.onload=skuList();*/

function getProduction(){
	var xhttp1 = new XMLHttpRequest();
	xhttp1.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = xhttp1.responseText;
	        var result=JSON.parse(response);
	        console.log(result);
	      var table=document.getElementById("ip");
	      table.innerHTML="";
	      table.innerHTML=' <tr  style="background:#3c8dbc;color:white;height:40px;text-align:center">'+
                                                         '<th style="text-align:center">BARCODE</th>'+
                                                         '<th style="text-align:center">SKU</th>'+
                                                         '<th style="text-align:center">EXPIRY</th>'+
                                                         '<th style="text-align:center">QTY</th>'+
                                                         '</tr>';
          for(var key in result.production){
        	  table.innerHTML+='<tr  style="text-align:center">'+
                                      '<td style="text-align:center">'+result.production[key].barcode+'</td>'+
                                      '<td style="text-align:center">'+result.production[key].sku+'</td>'+
                                      '<td style="text-align:center">'+result.production[key].expiry+'</td>'+
                                      '<td style="text-align:center">'+result.production[key].qty+'</td>'+
                                      '</tr>';

          }
	    }
	};
	xhttp1.open("GET", gUrl.url+"/allProductionData", true);

	xhttp1.send();
	}

window.onload=bayList();
window.onload=getProduction();

function insertManualProduction(){
    var barcode=document.getElementById("barcode").value;
	var sku=document.getElementById("sku").value;
	var expiry=document.getElementById("date").value;
	var qty=document.getElementById("qty").value;
	if(barcode==""||barcode=="select"){
		/*alert("Please Select Barcode");*/
		 Toastify({
                                    text: "Please Select Barcode",
                                    duration: 3000,
                                    gravity: "top",
                                    position: 'center',
                                    backgroundColor: "Red",
                                    close : true
                                  }).showToast();
	}
	else if(sku==""){
		/*alert("Please Enter Sku ");*/
		 Toastify({
                                            text: "Please Enter Sku ",
                                            duration: 3000,
                                            gravity: "top",
                                            position: 'center',
                                            backgroundColor: "Red",
                                            close : true
                                          }).showToast();
	}
    else if(expiry==""){
		/*alert("Please Enter Expiry")*/
		Toastify({
                                                    text: "Please Enter Expiry ",
                                                    duration: 3000,
                                                    gravity: "top",
                                                    position: 'center',
                                                    backgroundColor: "Red",
                                                    close : true
                                                  }).showToast();
	}
	else if(qty==""){
		/*alert("Select Any Quantity")*/
		Toastify({
                                                            text: "Select Any Quantity ",
                                                            duration: 3000,
                                                            gravity: "top",
                                                            position: 'center',
                                                            backgroundColor: "Red",
                                                            close : true
                                                          }).showToast();
	}
	else{
		 var XHR2 = new XMLHttpRequest();
         var hash={"barcode":""+barcode+"","sku":""+sku+"","expiry":""+expiry+"","qty":""+qty+""}

		XHR2.open("POST", "/api/insertManualProduction");
		XHR2.setRequestHeader("Content-Type", "application/json;charset=UTF-8");


	XHR2.onload = function() {
	          console.log(XHR2.responseText);
	          var response = JSON.parse(XHR2.responseText);
	          if(response['message']=="Successful") {
			      /* alert("Successfully Inserted");*/
			      Toastify({
                                                                              text: "Successfully Inserted ",
                                                                              duration: 3000,
                                                                              gravity: "top",
                                                                              position: 'center',
                                                                              backgroundColor: "Green",
                                                                              close : true
                                                                            }).showToast();
	           	   document.getElementById("barcode").value="";
	           	   document.getElementById("sku").value="";
	           	   document.getElementById("date").value="";
	           	   document.getElementById("qty").value="";
                   getProduction()
	          }
			  else {
	            /*alert("unSuccessful");*/
	            Toastify({
                    text: "unSuccessful ",
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

function skuList(){
	var xhttp1 = new XMLHttpRequest();
	xhttp1.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = xhttp1.responseText;
	        var result=JSON.parse(response);
	        console.log(result);
	      var sku=document.getElementById("sku");
	      /*sku.innerHTML="";*/
          for(var key in result.sku){
        	  sku.innerHTML+='<option value='+result.sku[key].sku+'>'+ result.sku[key].sku+'</option>';
          }
	    }
	};
	xhttp1.open("GET", "/api/getSkuList", true);

	xhttp1.send();
	}

window.onload=skuList();


function bayList(){
	var xhttp1 = new XMLHttpRequest();
	xhttp1.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = xhttp1.responseText;
	        var result=JSON.parse(response);
	        console.log(result);
	      var select1=document.getElementById("select1");
	      select1.innerHTML="";
          for(var key in result.bay){
        	  select1.innerHTML+='<option value='+result.bay[key].barcode+'>';
          }
	    }
	};
	xhttp1.open("GET", gUrl.url+"/getBayList", true);

	xhttp1.send();
	}

window.onload=bayList();


