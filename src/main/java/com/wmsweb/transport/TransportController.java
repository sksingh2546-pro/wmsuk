

package com.wmsweb.transport;

import com.wmsweb.Purchase.PurchaseRepository;
import com.wmsweb.SortingPurchase.SortingPurchase;
import com.wmsweb.SortingPurchase.SortingPurchaseRepository;
import com.wmsweb.commonData.CommonDataRepository;
import com.wmsweb.model.OrderDetailsModel;
import com.wmsweb.model.PermitListModel;
import com.wmsweb.permitNo.PermitNoRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransportController {
    @Autowired
    TransportRepository transportRepository;
    @Autowired
    PermitNoRepository permitNoRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    SortingPurchaseRepository sortingPurchaseRepository;
    @Autowired
    CommonDataRepository commonDataRepository;

    @PostMapping("/insertTransport")
    @ResponseBody
    public String insertTransport(@RequestBody Transport transport) {
        String message = "{\"message\":\"Unsuccessful\"}";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        transport.setDate(simpleDateFormat.format(date));
        Transport transport2 = transportRepository.save(transport);
        System.out.println();
        String[] permitNo = transport2.getPermit_no().split(",");
        int insert = 0;
        for (String permit_no : permitNo) {
            insert = permitNoRepository.insertPermitNo(transport2.getOrder_id(), permit_no);
        }
        if (insert > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }

    @GetMapping("/getAddress")
    public Map<String, HashSet<String>> getAddress() {
        HashSet<String> set = (HashSet<String>) transportRepository.getAddress();
        HashMap<String, HashSet<String>> hmap = new HashMap<String, HashSet<String>>();
        hmap.put("address", set);
        return hmap;
    }

    @GetMapping("/getDriverName")
    public Map<String, HashSet<String>> getDriverName() {
        HashSet<String> set = (HashSet<String>) transportRepository.getDriverName();
        HashMap<String, HashSet<String>> hmap = new HashMap<String, HashSet<String>>();
        hmap.put("driver", set);
        return hmap;
    }

    @GetMapping("/getContactNo")
    public Map<String, HashSet<String>> getContactNo() {
        HashSet<String> set = (HashSet<String>) this.transportRepository.getContactNo();
        HashMap<String, HashSet<String>> hmap = new HashMap<String, HashSet<String>>();
        hmap.put("contact", set);
        return hmap;
    }

    @GetMapping("/getVehicleNo")
    public Map<String, HashSet<String>> getVehicleNo() {
        HashSet<String> set = (HashSet<String>) this.transportRepository.getVehicleNo();
        HashMap<String, HashSet<String>> hmap = new HashMap<String, HashSet<String>>();
        hmap.put("vehicle", set);
        return hmap;
    }

    @GetMapping("/getPartyName")
    public Map<String, HashSet<String>> getPartyName() {
        HashSet<String> set = (HashSet<String>) this.transportRepository.getPartyName();
        HashMap<String, HashSet<String>> hmap = new HashMap<String, HashSet<String>>();
        hmap.put("party", set);
        return hmap;
    }

    @GetMapping("/getTransportData")
    public Map<String, ArrayList<PermitListModel>> getTransportData() {
        List<Transport> list = (List<Transport>) this.transportRepository.getTransportData();
        HashMap<String, ArrayList<PermitListModel>> hmap = new HashMap<String, ArrayList<PermitListModel>>();
        ArrayList<PermitListModel> transportArrayList = new ArrayList<PermitListModel>();
        if (list.size() > 0) {
            List<String> permitList = this.permitNoRepository.getPermitList(list.get(0).getOrder_id());
            PermitListModel permitListModel = new PermitListModel(list.get(0).getOrder_id(), permitList);
            transportArrayList.add(permitListModel);
        }
        hmap.put("permitList", transportArrayList);
        return hmap;
    }

    @GetMapping("/getTransportStatus")
    public Map<String, ArrayList<Transport>> getTransportStatus(@RequestParam("order_id") long order_id) {
        HashMap<String, ArrayList<Transport>> hmap = new HashMap<String, ArrayList<Transport>>();
        ArrayList<Transport> list = (ArrayList<Transport>) transportRepository.getTransportStatus(order_id);
        hmap.put("TransportStatusData", list);
        return hmap;
    }

    @PostMapping("/updateTransportStatus")
    public String updateData(@RequestParam("order_id") long order_id, @RequestParam("status") int status) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateData = transportRepository.updateStatus(order_id, status);
        if (updateData > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @GetMapping("/getOrderDetails")
    public Map<String, ArrayList<OrderDetailsModel>> getOrderDetails() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        ArrayList<OrderDetailsModel> orderDetailsModelArrayList = new ArrayList<OrderDetailsModel>();
        List<Transport> getTransportDetails = (List<Transport>) this.transportRepository.getOrder(sdf2.format(date) + "-01 00:00:00", sdf.format(date));
        System.out.println(sdf2.format(date) + "-01 00:00:00");
        System.out.println(sdf.format(date));
        for (Transport transport : getTransportDetails) {
            int qty = 0;
            int status = 0;
            List<SortingPurchase> getQty = sortingPurchaseRepository.getOrderProduct(transport.getOrder_id());
            for (SortingPurchase sortingPurchase : getQty) {
                qty += sortingPurchase.getQty();
                status = sortingPurchase.getStatus();
            }
            if (qty != 0) {
                orderDetailsModelArrayList.add(new OrderDetailsModel(transport.getOrder_id(), transport.getVehicle_no(), transport.getParty_name(), transport.getTotal_weight(), transport.getState(), qty, status));
            }
        }
        HashMap<String, ArrayList<OrderDetailsModel>> hmap = new HashMap<String, ArrayList<OrderDetailsModel>>();
        hmap.put("orderDetails", orderDetailsModelArrayList);
        return hmap;
    }

    @GetMapping("/cancelOrder")
    public String changeTransportStatusWithOrderId(@RequestParam("order_id") long order_id) {
        String message = "{\"message\":\"Unsuccessful\"}";
        List<Transport> list = transportRepository.getTransportStatus(order_id);
        if (list.get(0).getStatus() == 1) {
            int updateData = transportRepository.changeTransportStatusWithOrderId(order_id);
            if (updateData > 0) {
                purchaseRepository.changeStatusWithOrderid(order_id);
                sortingPurchaseRepository.updateWithOrdeId(order_id);
                commonDataRepository.cancelOrder(order_id);
                message = "{\"message\":\"Updated Successfully\"}";
            }
        } else {
            int updateData = transportRepository.cancelOrderNotRunning(order_id);
            if (updateData > 0) {
                purchaseRepository.changeStatusWithOrderid(order_id);
                sortingPurchaseRepository.updateWithOrdeId(order_id);
                commonDataRepository.cancelOrder(order_id);
                message = "{\"message\":\"Updated Successfully\"}";
            }
        }
        return message;
    }

    @GetMapping("/getOrderId")
    public Map<String, ArrayList<SortingPurchase>> getOrderId() {
        HashMap<String, ArrayList<SortingPurchase>> hmap = new HashMap<String, ArrayList<SortingPurchase>>();
        ArrayList<Transport> list = (ArrayList<Transport>) transportRepository.getOrderId();
        if (list.size() > 0) {
            ArrayList<SortingPurchase> list2 = (ArrayList<SortingPurchase>) sortingPurchaseRepository.getOrderProduct(list.get(0).getOrder_id());
            transportRepository.updateStatus(list.get(0).getOrder_id());
            sortingPurchaseRepository.updateStatus(list.get(0).getOrder_id());
            hmap.put("order", list2);
        }
        return hmap;
    }

    @PostMapping("/updateTransport")
    public String updateData(@RequestBody Transport transport) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateData = transportRepository.updatetransport(transport.getOrder_id(), transport.getTotal_qty(), transport.getTotal_weight());
        if (updateData > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @GetMapping("/generateTransportExcel")
    public void createTransportSheet(HttpServletResponse response1) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        try {

            List<Transport> tplist = transportRepository.getAllTransportData();
            try {
                Sheet sheet = workbook.createSheet("Yesterday Data");
                Row row0 = sheet.createRow(0);

                row0.createCell(0).setCellValue("Sr.No.");
                row0.createCell(1).setCellValue("Date");
                row0.createCell(2).setCellValue("Driver Name");
                row0.createCell(3).setCellValue("Vehical Number");
                row0.createCell(4).setCellValue("Party Name");
                row0.createCell(5).setCellValue("Address");
                row0.createCell(6).setCellValue("State");
                row0.createCell(7).setCellValue("Permit Number");
                row0.createCell(8).setCellValue("Truck Bay Number");
                row0.createCell(9).setCellValue("Total Quantity");
                row0.createCell(10).setCellValue("Total Weight");
                row0.createCell(11).setCellValue("Status");


                int j = 1;
                for (Transport transport1 : tplist) {
                    System.out.println("bay no " + transport1.getDriver_name());
                    Row row1 = sheet.createRow(j++);
                    row1.createCell(0).setCellValue(j - 1);
                    row1.createCell(1).setCellValue(transport1.getDate());
                    row1.createCell(2).setCellValue(transport1.getDriver_name());
                    row1.createCell(3).setCellValue(transport1.getVehicle_no());
                    row1.createCell(4).setCellValue(transport1.getParty_name());
                    row1.createCell(5).setCellValue(transport1.getAddress());
                    row1.createCell(6).setCellValue(transport1.getState());
                    row1.createCell(7).setCellValue(transport1.getPermit_no());
                    row1.createCell(8).setCellValue(transport1.getTruck_bay_no());
                    row1.createCell(9).setCellValue(transport1.getTotal_qty());
                    row1.createCell(10).setCellValue(transport1.getTotal_weight());
                    row1.createCell(11).setCellValue(transport1.getStatus());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response1.setHeader("content-disposition", "attachment;filename=Transport Report.xls");
        workbook.write(response1.getOutputStream());
    }

}
