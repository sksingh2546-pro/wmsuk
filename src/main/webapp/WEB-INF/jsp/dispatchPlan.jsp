<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbookFactory"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFFactory"%>
<%@page import="org.apache.poi.ss.usermodel.WorkbookFactory"%>
<%@page import="org.apache.poi.ss.usermodel.CellType"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<body>
<%@page import="java.lang.String"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFSheet"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbook"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFRow"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFCell"%>
<table border="1">
<%

String filename ="C:/xampp/dispatchPlan.xlsx";
if (filename != null && !filename.equals("")) {
//try{
/* FileInputStream fs =
new FileInputStream(filename); */
File file = new File(filename);
XSSFWorkbook wb =XSSFWorkbookFactory.createWorkbook(file, false);
//XSSFWorkbook wb =new XSSFWorkbook(fs);

try{ XSSFSheet sheet = wb.getSheetAt(0);
XSSFSheet sheet1 = wb.createSheet("newDispatchPlan");

int rows = sheet.getPhysicalNumberOfRows();
for (int r = 0; r < rows; r++){
	XSSFRow rowCreate = sheet1.createRow(r); 
	%>
	<tr>
	<%
XSSFRow row = sheet.getRow(r);
int cells = row.getPhysicalNumberOfCells();
	
for(int cellNo=0;cellNo<cells;cellNo++){
	try{
XSSFCell cell = row.getCell(cellNo);
XSSFCell cellCreate = rowCreate.createCell(cellNo); 


if(cell.getCellType()==CellType.STRING){
	cellCreate.setCellValue(cell.getStringCellValue());

	%>
	<td><%=cell.getStringCellValue()%></td>
	<%
}else if(cell.getCellType()==CellType.NUMERIC){
	cellCreate.setCellValue(cell.getNumericCellValue());
	%>
	<td><%=cell.getNumericCellValue()%></td>
	<%
}


else if(cell.getCellType()==CellType.BLANK){
	cellCreate.setCellValue(" ");
	%>
	<td></td>
	<%
}

}catch(Exception e1){
	System.out.println("1:"+e1);
}
	System.out.println("5:ex");
	
}
%>
<tr>
<%
}
}catch(Exception es){
	System.out.println("ex:"+es);
}

try { 
    // this Writes the workbook gfgcontribute 
    FileOutputStream outs = new FileOutputStream(new File("newDispatchPlan.xlsx")); 
    wb.write(outs); 
    out.close(); 
    System.out.println("newDispatchPlan.xlsx written successfully on disk."); 
} 
catch (Exception e) { 
	
	System.out.println("Excel :"+e);
} 
%>
</table>
<%
/* }
catch(Exception ex){
System.out.println("2:"+ex.getLocalizedMessage());
}
 */
}
%>