
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

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
                                            <a href="logout" class="btn btn-default btn-flat">Sign out</a>
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
                                  <li class="active"><a href="updateProduction"><i class="fa fa-circle-o"></i>Update Product</a></li>
                                   <li><a href="excelImport" "><i class="fa fa-circle-o"></i>Add SKU</a></li>
                                   <li><a href="changeSkuCapacity" "><i class="fa fa-circle-o"></i>Update SKU</a></li>
                                   <li><a href="changeBayCapacity" "><i class="fa fa-circle-o"></i>Update Bay</a></li>
                                   <li><a href="searchProduct"><i class="fa fa-circle-o"></i>Search Product</a></li>
                                   <li><a href="productionPlan"><i class="fa fa-circle-o"></i>Production Plan</a></li></ul>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-arrow-circle-right"></i>
                                <span>OUT</span>
                                <i class="fa fa-angle-left pull-right"></i>                                                       
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="transport"><i class="fa fa-circle-o"></i>Make An Order</a></li>
                               <li><a href="orderDetails"><i class="fa fa-circle-o"></i> Order Details</a></li>
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
                
            </h1>
           <ol class="breadcrumb">
  <h4 style="float: left;margin-right:20px;" ><a href="in"><i class="fa fa-arrow-circle-left"></i>&nbsp;IN</a></h4> <h4 style="float: right "><a href="index"><i class="fa fa-home"></i>&nbsp;Home</a></h4>

            </ol>
        </section>

         <div class="container">
            <h2>Edit Product</h2>
             <section class="content">
                    <div class="panel panel-primary" style="width:28.5cm">
                        <div class="container">
                            <b><h2>Update Details</h2></b>
                          
                            <table id="proTable" class="table table-bordered table-striped" style="width:27.7cm">
                        <thead>
                        <tr>
                          <th>Bay No.</th>
                          <th>Batch No.</th>
                          <th>SKU</th>
                          <th>Quantity</th>
                           <th>Status</th>
                          <th></th><th></th>

                        </tr>
                        </thead>
                        <tbody id="table">
                        
                        </tbody>
                      </table>
					
						
                        </div>
                    </div>
                </section>
                
                
                
                
                
                </div>
            
       
    </div>
	
	
	
	 <div id="changeBay" class="modal fade" role="dialog">
                <div class="modal-dialog">
                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header modal-success">
                      <button type="button" id="myBtn" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Change Bay</h4>
                       
                        
                      
                     </div>
                   <br>
				             <table id="table1" class="table table-bordered table-striped" style="width:15cm;margin-left:.4cm">
                        <thead>
                        <tr>
                          <th>Bay No</th>
                          <th>Batch No.</th>
                          <th>Sku.</th>
                          <th>Quantity.</th>
                          <th>Status.</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                        <td ><select  style="width:2.5cm;height:1cm" id="bayNo" ></select></td>
                        <td  id="batchNo" ></td>
                        <td  id="sku" ></td>
                        <td contenteditable='true' id="quantity" ></td>
                        <td  id="status" ></td>
                      
                        </tr>
                        </tbody>
                      </table>
				   
				   
                    <div class="modal-footer">
                    <button type="submit" id="deleteJoinee" class="btn btn-success" onclick="shiftProduction()"  >Done</button>
                      <button type="submit" class="btn btn-warning" data-dismiss="modal">Close</button>
                    </div>
                  </div>
	</div>
	</div>
	 <div id="changeStatus" class="modal fade" role="dialog">
                <div class="modal-dialog">
                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header modal-success">
                      <button type="button" id="myBtn" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Change Status</h4>
                       
                        
                      
                     </div>
                   <br>
				             <table id="table2" class="table table-bordered table-striped" style="width:15cm;margin-left:.4cm">
                        <thead>
                        <tr>
                          <th>Bay No</th>
                          <th>Batch No.</th>
                          <th>Sku.</th>
                          <th>Quantity.</th>
                          <th>Status.</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                        <td style="width:3cm" id="bayNo1" ></td>
                        <td id="batchNo1" ></td>
                        <td id="sku1" ></td>
                        <td contenteditable='true' id="quantity1" ></td>
                        <td><select id="status1" style="width:2.5cm;height:1cm">
                        
                         <option value="PASS">PASS</option>
                          <option value="HOLD">HOLD</option>
                        </select></td>
                      
                        </tr>
                        </tbody>
                      </table>
				   
				   
                    <div class="modal-footer">
                    <button type="submit" id="deleteJoinee" class="btn btn-success" onclick="statusProduction()" >Done</button>
                      <button type="submit" class="btn btn-warning" data-dismiss="modal">Close</button>
                    </div>
                  </div>
	</div>
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

<!-- jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
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
<script src="paho.js"></script>
<script src="updateProduction.js"></script>

</body>
</html>
