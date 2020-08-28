	<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>

<%
    PreparedStatement ps;
	 ResultSet rs;
	 ArrayList<String> sklistitem=new ArrayList<String>();
	 ArrayList<String> sklist=new ArrayList<String>();
	 ArrayList<String> baylist=new ArrayList<String>();
	 ArrayList<String> lotlist=new ArrayList<String>();
	 ArrayList<Integer> qtylist=new ArrayList<Integer>();
	 ArrayList<Integer> orderidlist=new ArrayList<Integer>();
	 String[] newsku; 
		int order_add=0,orderid=0,qty=0,quantity=0,tempqty=0;
		String s3[]=request.getParameterValues("hidden_sku[]");
		String s5[]=request.getParameterValues("hidden_quantity[]");
        String sku=null,skulist=null,bay=null,lot=null,skuitem=null;
        ArrayList<String> mainlist=new ArrayList<String>();
        int increment=1;
        List<String> tmpList ;
        TreeSet<String> uniquesku;
       int listincrement=0;
        
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wms","root","");
 
	ps=con.prepareStatement("select order_id,sku from order_details where order_state=0");
	 rs=ps.executeQuery();
		 while(rs.next()){
			 orderid=rs.getInt(1);
		sku=rs.getString(2);
		sklistitem.add(rs.getString(2));
	  // out.print(sklistitem);
		listincrement++;
		    	 }
		 uniquesku = new TreeSet<String>(sklistitem);
		// out.print(uniquesku);
		// out.println(uniquesku);
		 String mskulist[]=uniquesku.toArray(new String[uniquesku.size()]);
		 	   for(int list=0;list<mskulist.length;list++){
		 		   
		 		
		 		  ps=con.prepareStatement("select sum(quantity) from order_details  where sku='"+mskulist[list]+"' and order_state=0");
		 		 rs=ps.executeQuery();
		 		 while(rs.next()){
		 			 
		 		   qty=rs.getInt(1);
		 			//out.println(mskulist[list]);
		 		//out.println(qty);	
		 		
		 		 }
		 		ps=con.prepareStatement("select * from product  where sku='"+mskulist[list]+"' order By lot_no asc");
				 rs=ps.executeQuery();
				 while(rs.next()){
					 
					 sklist.add(rs.getString(2));
					
					 baylist.add(rs.getString(3));
					 
					 lotlist.add(rs.getString(4));
					 qtylist.add(rs.getInt(5));
					 
					 mainlist.add(rs.getString(2));
					 mainlist.add(rs.getString(3));
					 mainlist.add(rs.getString(4));
					 mainlist.add(rs.getString(5));
					 //out.print(sklist);
					 
					}
				// out.println(sklist);
				 //out.println(mainlist);
				 
				 String skuarray[]=sklist.toArray(new String[sklist.size()]);
		 String bayarray[]=baylist.toArray(new String[baylist.size()]);
		 String lotarray[]=lotlist.toArray(new String[lotlist.size()]);
		 Integer[] qtyarray = qtylist.toArray(new Integer[qtylist.size()]);
		 
		 int order_id=orderid;
		 int i=0;
		 
		 for( ;i<increment;i++){
			
	    	 tempqty=qty-qtyarray[i];
	    	// out.println("--"+skuarray[i]);
	    	 //out.print("---"+qtyarray[i]);
	    	// out.print("----"+tempqty);
	    	 if(qtyarray[i]==0){
	    		 increment++;
	    		 
	    		
	    	 }
	    	 else if(tempqty<=0){
	    		ps=con.prepareStatement("insert into get_order_details (order_id,sku,bay_no,lot_no,quantity)values("+order_id+",'"+skuarray[i]+"','"+bayarray[i]+"',"+lotarray[i]+","+qty+")");
			 int insert=ps.executeUpdate();
				 
				 out.println(skuarray[i]+qty);
				 out.println("first"+increment);
				 //tempqty=0;
	    	 }else if(tempqty>0){
	    	 ps=con.prepareStatement("insert into get_order_details (order_id,sku,bay_no,lot_no,quantity)values("+order_id+",'"+skuarray[i]+"','"+bayarray[i]+"',"+lotarray[i]+","+qtyarray[i]+")");
			int insert=ps.executeUpdate();
			out.println(skuarray[i]+qtyarray[i]);
			if(insert>0){
			 qty=qty-qtyarray[i];
			 out.println("second"+increment);
			 increment++;
			}
			// out.println("----"+increment);
	     }  }out.println("out"+increment);
		    
				 sklist.clear();
				 baylist.clear();
				 lotlist.clear();
				 qtylist.clear();
				
				increment=1;
				
				ps=con.prepareStatement("update order_details set order_state=1 where sku='"+mskulist[list]+"'and order_state=0");
				 int update=ps.executeUpdate();
				
		 	   }
		 	  
		 	  out.println(listincrement);
		 	response.sendRedirect("updatedummy");
					
		
			 
}
 catch(Exception e){out.print(e);}



%>
		