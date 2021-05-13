<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
<h1>You are not logged in</h1><br/>
<a href="login">Please Login</a>
<%} else {
%> --%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Warehouse Management System</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
         <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
               <!-- Ionicons -->
               <link rel="stylesheet" href="ionicons.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
       
        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
        <!-- Morris chart -->
        <link rel="stylesheet" href="plugins/morris/morris.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
        <!-- Date Picker -->
        <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
        <!-- Daterange picker -->
        <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap wysihtml5 - text editor -->
        <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
      <link rel="stylesheet" type="text/css" href="toastify.min.css">
<link rel="stylesheet" href="bootstrap.css">
 		<style>
        	.slideline{
        		height:70vh;
        		border-right:solid;
        		border-color:#3c8dbc;
        		margin-left:0px;
        	}
        	@media only screen and (max-width: 992px) {
				  .slideline {
				    display:none;
				  }
				}
			</style>	
  <script src="jquery-min.js"></script>
        <script>
            $(document).ready(function(){
                 $("#town").change(function(){
                     var value = $(this).val();
                     $.get("data",{bayno:value},function(data){
                      $("#javaquery").html(data);
                     });
                 });
             });
        </script>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <header class="main-header">
               
                <a href="index" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>W</b>MS</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Warehouse</b> Management System</span>
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->
                            <li class="dropdown messages-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                </a> 
                            </li>
                            <!-- Notifications: style can be found in dropdown.less -->
                            <li class="dropdown notifications-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                </a>

                            </li>
                            <!-- Tasks: style can be found in dropdown.less -->

                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="Image/logo.png" class="user-image" alt="User Image">
                                    <span class="hidden-xs"> </span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="Image/logo2.png" class="img-circle" alt="User Image">

                                        <p>
                                      
                                        <i style="font-size: 18px"><%=session.getAttribute("userid")%></i><br>
                                            
                                            <small></small>
                                        </p>
                                    </li>

                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="login" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->

                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="Image/logo.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p></p>

                        </div>
                    </div>
                   
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-arrow-circle-left"></i> <span>IN</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                         <!--   <li><a href="productionPlan"><i class="fa fa-circle-o"></i>Production Plan</a></li>-->
                                                   <li class="active"><a data-toggle="modal" data-target="#modalid"><i class="fa fa-circle-o"></i>Insert Product</a></li>
                                                  <!-- <li class="active"><a href="updateProduction"><i class="fa fa-circle-o"></i>Update Product</a></li>--->
                                                    <li><a href="verifyProduct"><i class="fa fa-circle-o"></i>Verify Production</a></li>
                                                   <li><a href="searchProduct"><i class="fa fa-circle-o"></i>Search Product</a></li>
                                                   <li><a href="excelImport" "><i class="fa fa-circle-o"></i>Add SKU</a></li>
                                                       <li><a href="changeBayCapacity"><i class="fa fa-circle-o"></i>Update Bay</a></li>
                                                      <li><a href="changeSkuCapacity"><i class="fa fa-circle-o"></i>Update SKU</a></li>
                                                    <li><a href="/api/generateExcel"><i class="fa fa-circle-o"></i>GenerateReport</a></li>
                                                    <li><a href="downloadProductionExcel"><i class="fa fa-circle-o"></i>Download Production</a></li>
                                                  <!--   <li><a href="productionPlanImport"><i class="fa fa-circle-o"></i>Import Production Plan</a></li>-->

                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-arrow-circle-right"></i>
                                <span>OUT</span>
                                <i class="fa fa-angle-left pull-right"></i>                                                       
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                                  <li><a href="transport"><i class="fa fa-circle-o"></i>Make A Plan</a></li>
                                                  <li><a href="addDriverDetails"><i class="fa fa-circle-o"></i> Place Order To Bay</a></li>
                                                  <li><a href="orderDetails"><i class="fa fa-circle-o"></i> Order List</a></li>
                                                 <!--  <li><a href="dispatchExcelImport"><i class="fa fa-circle-o"></i> Import Dispatch Plan</a></li>--->
                                                  <li><a href="/api/generateTExcel"><i class="fa fa-circle-o"></i> Generate Report</a></li>
                                                  <li><a href="downloadTransportExcel"><i class="fa fa-circle-o"></i>Download Dispatch Plan</a></li>
                                                <!--   <li><a href="manualOrder"><i class="fa fa-circle-o"></i>Mannual Order</a></li>--->
                                                   <li><a href="complete"><i class="fa fa-circle-o"></i> Complete Order</a></li>

                            </ul>
                        </li>

                       
                    
                
                </li>
            </ul>



        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Dashboard
                <small></small>
            </h1>
            <ol class="breadcrumb">
               <h4 style="float: left;margin-right:20px;"><a href="in"><i class="fa fa-arrow-circle-left"></i>&nbsp;IN</a></h4> <h4 style="float: right "><a href="index"><i class="fa fa-home"></i>&nbsp;Home</a></h4>

            </ol>
        </section>
		<br><br>
        <!-- Main content -->
        <section class="content">
                    <div class="panel panel-primary">
                    
                        <div class="container-fluid" >
                        
                           
                         
                           <div class="row">
                           		<div class="col-md-3">
                           		<div style="width:100%;display:inline-block">
                            <h2 style="margin-left:10px"><b>Add SKU Capacity</b></h2></div>
                                <table width="100% ">
                               
                                    <tr>
                                        <td>
                                            <div class="form-group" >
                                           
                                               <h5 style="font-weight:bold;"> SKU: &nbsp;<label for="product_name" style="font-weight:bold;color:red;"> *</label></h5>
                                              <input list="sku" id="sku1" name="sku" class="form-control" placeholder="Enter SKU ...">
                                              <datalist id="sku">  </datalist>
                                    </div>    </td>
										
                                    </tr>
                                        <tr>
                                        <td>

                                            <div class="form-group" >
                                            <br>
                                               <h5 style="font-weight:bold;">Capacity: &nbsp; <label for="code" style="font-weight:bold;color:red;"> *</label><br></h5>
                                                <input type="number" class="form-control" id="capacity" name="capacity" placeholder="Enter Capacity ..." required>

                                            </div>

                                        </td>
                                        
                                    </tr>                                    
                                   
                                       <tr>
                 <tr>
                                        <td>

                                            <div class="form-group" >
                                            <br>
                                               <h5 style="font-weight:bold;">Pallet Weight: &nbsp; <label for="code" style="font-weight:bold;color:red;"> *</label><br></h5>
                                                <input type="number" class="form-control" id="weight" name="weight" placeholder="Enter Capacity ..." required>

                                            </div>

                                        </td>

                                    </tr>



                                    <tr>

                                        <td>
										<br><br>
                                            <div class="form-group" style="margin-left:20%;">
                                            <button  class="btn btn-danger" onclick="insertSku()" >Submit</button>
                                       </div> </td>
                                    </tr>
                                    
                                   
                                    
                                </table>
                                
                                </div>
                                <div class="col-md-1">
                                	  <div class="slideline" ></div>
                                </div>
                                <div class="col-md-8">
                                 <div style="width:69%;display:inline-block">
	                            <h2 style="margin-left:20%"><b>All SKU And Capacity</b></h2>
	                            </div>
                                <div style="height:450px;overflow:auto">
                                <table  border="1" style="width:100%;"id="pp">
                                <tr  style="background:#3c8dbc;color:white;height:40px;text-align:center">
                                <th style="text-align:center">SKU</th>
                                <th style="text-align:center" >CAPACITY</th>
                                <th style="text-align:center" >WEIGHT</th>
                                </tr>
                                </table>
                                </div>
                                </div>    
                                                        
                        </div>
                    </div>
                </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
        </div>
      <!--  <strong>Copyright &copy; 2018-2019 <a href="#"></a>.</strong> -->
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>

    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

	<!--popup modal  -->
	<div class="modal fade" id="modalid" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background:#3c8dbc">
        <h3 class="modal-title" style="color:white">Enter Password To Insert Production</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
           <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      		<div class="row"> 
      			<div class="col-md-12">
      				<h2 id="comp_name"></h2>
      			</div>
      		</div> 
      		<br>
      			<div class="row"> 
      			<div class="col-md-12">
      				<input type="password" id="password" class="form-control" placeholder="Enter Password">
      			</div>
      		</div> 
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="popup()">Confirm</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<!-- jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="raphael-min.js"></script>
<script src="plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
 <script src="url.js"></script>
<script src="changeSkuCapacity.js"></script>
<script type="text/javascript" src="toastify.js"></script>
<script src="paho.js"></script>
<script src="completionAlert.js"></script>

<script>
document.getElementById("comp_name").innerHTML=localStorage.getItem("user_id");

function popup(){
	var XHR = new XMLHttpRequest();
	XHR.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	        var response = XHR.responseText;
	        var result=JSON.parse(response);
	        console.log(result);
			if(result.message=="Successful"){
			
			window.location.href="insertProduction";
			}else{
			alert("Please Enter Correct Password");
			}
	    }
	};
	XHR.open("GET", gUrl.url+"/getPassword?user_name="+document.getElementById("comp_name").innerHTML+"&password="+document.getElementById("password").value, true);
	XHR.send();
	}
</script>
</body>
</html>
<%-- <%}%> --%>