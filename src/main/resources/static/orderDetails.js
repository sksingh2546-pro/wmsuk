function orderDetails() {
    var xhttp1 = new XMLHttpRequest();
    xhttp1.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            // Typical action to be performed when the document is ready:
            var response = xhttp1.responseText;
            var result = JSON.parse(response);
            console.log(result);

            var tbody = document.getElementById("order");
            tbody.innerHTML="";
            for ( var key in result.orderDetails) {
            if(result.orderDetails[key].status==0){
                tbody.innerHTML += '<tr>'
                + '<td>'
                + result.orderDetails[key].order_id
                + '</td>'
                + '<td >'
                + result.orderDetails[key].vehicle_no
                + '</td>'
                + '<td >'
                + result.orderDetails[key].party_name
                + '</td>'
                + '<td >'
                + result.orderDetails[key].weight
                + '</td>'
                + '<td >'
                + result.orderDetails[key].state
                + '</td>'
                + '<td >'
                + result.orderDetails[key].quantity
                + '</td>'
                + '<td style="background:orange;" align="center">Pending Order</td>'
                +'<td align="center"><input type="radio" name="orders" ></td></tr>'
        }
            else if(result.orderDetails[key].status==1){
                 tbody.innerHTML += '<tr>'
                 + '<td>'
                 + result.orderDetails[key].order_id
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].vehicle_no
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].party_name
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].weight
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].state
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].quantity
                 + '</td>'
                + '<td style="background:lightgreen;" align="center">Order Process</td>'
                +'<td align="center"><input type="radio" name="orders" ></td></tr>'
            }

            else if(result.orderDetails[key].status==2){
                 tbody.innerHTML += '<tr>'
                 + '<td>'
                 + result.orderDetails[key].order_id
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].vehicle_no
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].party_name
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].weight
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].state
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].quantity
                 + '</td>'
                 + '<td style="background:green;" align="center">Order Completed</td>'
                 +'<td align="center"><input type="radio" name="orders" ></td></tr>'

            }

            else if(result.orderDetails[key].status==3){
                 tbody.innerHTML += '<tr>'
                 + '<td>'
                 + result.orderDetails[key].order_id
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].vehicle_no
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].party_name
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].weight
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].state
                 + '</td>'
                 + '<td >'
                 + result.orderDetails[key].quantity
                 + '</td>'
                 + '<td style="background:red;" align="center">Order Canceled</td>'
                 +'<td align="center"><input type="radio" name="orders" ></td></tr>'
            }
            }
        }
    };
    xhttp1.open("GET", "/api/getOrderDetails", true);

    xhttp1.send();
}

window.onload = orderDetails();

function getSpecificOrderId(){
var row=document.getElementsByName("orders");
for(var count=0;count<row.length;count++){
if(row[count].checked){
console.log(document.getElementById("orderTable").rows[count+1].cells[0].innerHTML);
localStorage.clear();
localStorage.setItem("order_id",document.getElementById("orderTable").rows[count+1].cells[0].innerHTML);
console.log(document.getElementById("orderTable").rows[count+1].cells[6].innerHTML);
if(document.getElementById("orderTable").rows[count+1].cells[6].innerHTML=="Pending Order"){
window.location.href="productOrder";
}else
{
window.location.href="completeProduct";
}
}
}

}

