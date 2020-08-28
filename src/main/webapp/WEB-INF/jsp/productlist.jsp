
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
                                <li class="active"><a href="update"><i class="fa fa-circle-o"></i>Add Product</a></li>
                                <li><a href="#" onclick="alert('Inserted SKU!');"><i class="fa fa-circle-o"></i>Add SKU</a></li>
                                <li><a href="productlist"><i class="fa fa-circle-o"></i>Search Product</a></li>
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
                                <li><a href="transport"><i class="fa fa-circle-o"></i>Make An Order</a></li>
                                <li><a href="orderlist"><i class="fa fa-circle-o"></i> Order List</a></li>
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
            <h2>Product List</h2>
            <p>Products Exits in the Store</p> 
             <section class="content">
                    <div class="panel panel-primary">
                        <div class="container">
                            <b><h2>Order Details</h2></b>
                            <form class="form-inline" action="productlist" method="post">
                                <table>
                                    <tr>
                                        <td>

                                            <div class="form-group">
                                                <label for="code">SKU:</label><br>
                                             
                                                   <select name = "sku" id = "sku" class="form-control">
                                                         <option value = "">Select...</option>
                                                              <option value = "BPQPJBCVL">BPQPJBCVL</option>
                                                                <option value = "BPPPJBCVL">BPPPJBCVL</option>
                                                                 <option value = "BPNPJBCVL">BPNPJBCVL</option>
                                                                <option value = "IBQPJBCVL">IBQPJBCVL</option>
                                                                 <option value = " IBPPJBCVL">IBPPJBCVL</option>
                                                                 <option value = "IBNPJBCVL">IBNPJBCVL</option>
                                                                <option value = "HPQPJBCVL">HPQPJBCVL</option>
                                                                 <option value = "HPPPJBCVL">HPPPJBCVL</option>
                                                                 <option value = "HPNPJBCVL">HPNPJBCVL</option>
                                                                <option value = "BPQCHDCVL">BPQCHDCVL</option>
                                                                 <option value = "BPNCHDCVL">BPNCHDCVL</option>
                                                                 <option value = "BPRCQCHDCVL">BPRCQCHDCVL</option>
                                                                <option value = "BPRCNCHDCVL">BPRCNCHDCVL</option>
																<option value = "BPRCPCHDCVL">BPRCPCHDCVL</option>
																  <option value = "HPQCHDCVL">HPQCHDCVL</option>                                                              <option value = "IBNCHD">HPQPJBCVL</option>
                                                                 <option value = "HPPCHDCVL">HPPCHDCVL</option>
                                                                 <option value = "HPNCHDCVL">HPNCHDCVL</option>
                                                                 
                                                                 
                                                                  </select>

                                            </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </td>
                                    
                                        <td>
                                           <div class="form-group">
                                                <label for="code">Bay No:</label><br>
                                               <input type="product_name" class="form-control" id="bayno" name="bayno" placeholder="Enter Bay No" >

                                            </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </td>
                                        
                                          
                                    
                                     <td>
                                          
                                        

                                            <div class="form-group">
                                                <label for="code">Lot No:</label><br>
                                               <input type="product_name" class="form-control" id="lotno" name="lotno" placeholder="Enter Lot No" >

                                            </div>
                                        </td>
                                  
                                   </tr>
                                    
                                    <tr>

                                        <td>
                                            <br>
                                            <button class="btn btn-danger">Search</button>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                            </form>
                            
                            
                           
                            
                            <table class="table table-hover">
                        <thead>
                            <tr>
                                
                                <th>SKU.</th>
                                <th>Bay No</th>
                                <th>Lot No</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%@page import="java.sql.*" %>

<% String s2=request.getParameter("sku");
String s1=request.getParameter("bayno");
String s3=request.getParameter("lotno");

try{
	
		Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wms","root","");

PreparedStatement ps=con.prepareStatement("select sku,bay_no,lot_no,quantity from product");
ResultSet rs=ps.executeQuery();
while(rs.next()){
	
	
	
	String a2=rs.getString(1);
	String a1=rs.getString(2);
	String a3=rs.getString(3);
	
	if(s1.equals(a1)||s2.equals(a2)||s3.equals(a3)){
		 %>
		 
         <tr>
             <td><%=rs.getString(1)%></td>
             <td><%=rs.getString(2)%></td>
             <td><%=rs.getString(3)%></td>
             <td><%=rs.getString(4)%></td>
             
             <td>
		
	<%}
	
	}}catch(Exception e){
	
	
	
}%>
 
                          
                           
                    </tbody>
                </table>
                        </div>
                    </div>
                </section>
                
                
                
                
                
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
</body>
</html>
