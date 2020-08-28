

package com.wmsweb.production;

import com.wmsweb.FilterQty.FilterQty;
import com.wmsweb.FilterQty.FilterQtyRepo;
import com.wmsweb.SortingPurchase.SortingPurchase;
import com.wmsweb.SortingPurchase.SortingPurchaseRepository;
import com.wmsweb.bayCapacity.BayCapacity;
import com.wmsweb.bayCapacity.BayCapacityRepository;
import com.wmsweb.model.ProductionModel;
import com.wmsweb.productionPlan.ProductionPlan;
import com.wmsweb.productionPlan.ProductionPlanRepository;
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
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class ProductionController {
    @Autowired
    ProductionRepository productionRepository;
    @Autowired
    BayCapacityRepository bayCapacityRepository;
    @Autowired
    SortingPurchaseRepository sortingPurchaseRepository;
    @Autowired
    FilterQtyRepo filterQtyRepo;
    @Autowired
    ProductionPlanRepository productionPlanRepository;


    @PostMapping("/insertProduction")
    public String insertProduction(@RequestBody Production production, @RequestParam("line_no") String line_no) {
        int totQty = 0;
        int todayProPlan = 0;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        String response = "{\"message\":\"Unsuccessful\"}";
        if (production.getStatus() == null) {
            production.setStatus("PASS");
        }
        List<FilterQty> fList = filterQtyRepo.getFilterData(sdf1.format(date), production.getSku(), line_no, production.getBatch_no());

        for (FilterQty i : fList) {

            totQty += i.getQty();

        }


        List<ProductionPlan> pList = productionPlanRepository.getProductionPlan(production.getSku());

        for (ProductionPlan list : pList) {
            if (line_no.trim().contentEquals(list.getLine_no().trim()) && production.getBatch_no() == list.getBatch_no()) {
                todayProPlan = list.getQty();

            }
        }

        if (totQty < todayProPlan && totQty + production.getQty() <= todayProPlan) {


            filterQtyRepo.insertData(production.getSku(), production.getQty()
                    , sdf1.format(date), line_no, production.getBatch_no());
            List<Production> productionList = productionRepository.getProductionData(production.getBatch_no(),
                    production.getSku(), production.getBay_no(), production.getStatus());
            if (productionList.size() > 0) {
                int update = productionRepository.updateProduction(production.getBatch_no(), sdf.format(date)
                        , productionList.get(0).getQty() + production.getQty()
                        , production.getSku(), production.getBay_no(), production.getStatus());
                if (update > 0) {
                    response = "{\"message\":\"Successful\"}";

                }
            } else {
                int insert = productionRepository.insertProduction(production.getBatch_no(), sdf.format(date), production.getQty(), production.getSku(), production.getBay_no(), production.getStatus());
                if (insert > 0) {
                    response = "{\"message\":\"Successful\"}";

                }
            }
        } else {
            response = "{\"message\":\"Limit Exceeded\"}";
        }

        return response;
    }

    @PostMapping("updateProductionOrder")
    public String updateProductionOrder(@RequestBody Production production) {
        List<Production> productionList = productionRepository.getProductionData(production.getBatch_no(),
                production.getSku(), production.getBay_no(), "PASS");
        String response = "{\"message\":\"Unsuccessful\"}";
        if (productionList.size() > 0) {
            int update = productionRepository.updateProduction(production.getBatch_no(),
                    productionList.get(0).getDate(), productionList.get(0).getQty() - production.getQty()
                    , production.getSku(), production.getBay_no(), "PASS");
            if (update > 0) {
                response = "{\"message\":\"Successful\"}";
            }
        }
        return response;
    }

    @PostMapping("/changeBayAndStatus")
    public String changeBayAndStatus(@RequestBody Production production) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        String response = "{\"message\":\"Unsuccessful\"}";
        if (production.getStatus() == null) {
            production.setStatus("PASS");
        }
        List<Production> productionList = productionRepository.getProductionData(production.getBatch_no(),
                production.getSku(), production.getBay_no(), production.getStatus());
        if (productionList.size() > 0) {
            int update = productionRepository.updateProduction(production.getBatch_no(), sdf.format(date)
                    , productionList.get(0).getQty() + production.getQty()
                    , production.getSku(), production.getBay_no(), production.getStatus());
            if (update > 0) {
                response = "{\"message\":\"Successful\"}";

            }
        }
        return response;
    }

    @GetMapping("/getSkuData")
    public Map<String, HashSet<String>> getSkuData(@RequestParam("sku") String sku) {
        ArrayList<String> allData = (ArrayList<String>) productionRepository.getAllSku(sku);
        HashSet<String> uniqueSku = new HashSet<String>(allData);
        HashMap<String, HashSet<String>> hmap = new HashMap<String, HashSet<String>>();
        hmap.put("production", uniqueSku);
        return hmap;
    }

    @GetMapping("/getAllProductionData")
    public Map<String, ArrayList<ProductionModel>> getAllData() {
        this.productionRepository.deleteProduction();
        List<BayCapacity> bayList = this.bayCapacityRepository.getBay();
        ArrayList<ProductionModel> productionList = new ArrayList<ProductionModel>();
        for (BayCapacity bayCapacity : bayList) {
            List<Production> allData = (List<Production>) productionRepository.getAllProductionData(bayCapacity.getBay());
            if (allData.size() > 0) {
                for (Production production : allData) {
                    productionList.add(new ProductionModel(production.getSku(), production.getBatch_no(), production.getQty(), production.getBay_no(), production.getStatus(), production.getDate()));
                }
            } else {
                productionList.add(new ProductionModel("Empty", 0L, 0, bayCapacity.getBay(), "Empty", "Empty"));
            }
        }
        HashMap<String, ArrayList<ProductionModel>> hmap = new HashMap<String, ArrayList<ProductionModel>>();
        hmap.put("production", productionList);
        return hmap;
    }

    @GetMapping("/getQuantity")
    public Map<String, Integer> getQuantity(@RequestParam("sku") String sku) {
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        int purchaseQty = 0;
        List<SortingPurchase> getPurchaseQty = (List<SortingPurchase>) sortingPurchaseRepository.getSortingPurchase(sku);
        for (SortingPurchase sortingPurchase : getPurchaseQty) {
            purchaseQty += sortingPurchase.getQty();
        }
        int qty = 0;
        ArrayList<Production> allData = (ArrayList<Production>) productionRepository.getQuantity(sku, "PASS");
        for (Production production : allData) {
            qty += production.getQty();
        }
        hmap.put("quantity", qty - purchaseQty);
        return hmap;
    }

    @GetMapping("/getBatchNo")
    public Map<String, HashSet<Long>> getSkuData() {
        ArrayList<Long> allData = (ArrayList<Long>) productionRepository.getBatchNo();
        HashSet<Long> uniqueSku = new HashSet<Long>(allData);
        HashMap<String, HashSet<Long>> hmap = new HashMap<String, HashSet<Long>>();
        hmap.put("BatchNo", uniqueSku);
        return hmap;
    }

    @GetMapping("/getSearchProduct")
    public Map<String, ArrayList<Production>> getSearchProduct(@RequestParam(name = "sku", required = false) String sku, @RequestParam(name = "batch_no", required = false) String batch_no, @RequestParam(name = "bay_no", required = false) String bay_no) {
        HashMap<String, ArrayList<Production>> hmap = new HashMap<String, ArrayList<Production>>();
        if (batch_no == null || batch_no.equalsIgnoreCase("select") || batch_no.isEmpty()) {
            batch_no = "0";
        }
        ArrayList<Production> list = (ArrayList<Production>) productionRepository.getSearchProduct(sku, Long.parseLong(batch_no), bay_no);
        hmap.put("SearchData", list);
        return hmap;
    }

    @GetMapping("/getProductionData1")
    public Map<String, ArrayList<Production>> getProductionComplete() {
        HashMap<String, ArrayList<Production>> hmap = new HashMap<String, ArrayList<Production>>();
        ArrayList<Production> list = (ArrayList<Production>) productionRepository.getProductionComplete();
        hmap.put("productionData", list);
        return hmap;
    }

    @GetMapping("/generateExcel")
    public void createProductionSheet(HttpServletResponse response1) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        try {

            List<Production> productionList = productionRepository.getAllProductionData();
            try {
                Sheet sheet = workbook.createSheet("All Data");
                Row row0 = sheet.createRow(0);

                row0.createCell(0).setCellValue("Sr.No.");
                row0.createCell(1).setCellValue("Date");
                row0.createCell(2).setCellValue("Bay No");
                row0.createCell(3).setCellValue("SKU");
                row0.createCell(4).setCellValue("Batch No");
                row0.createCell(5).setCellValue("Quantity");
                row0.createCell(6).setCellValue("Status");

                int j = 1;
                for (Production productionData : productionList) {
                    System.out.println("bay no " + productionData.getBay_no());
                    Row row1 = sheet.createRow(j++);
                    row1.createCell(0).setCellValue(j - 1);
                    row1.createCell(1).setCellValue(productionData.getDate());
                    row1.createCell(2).setCellValue(productionData.getBay_no());
                    row1.createCell(3).setCellValue(productionData.getSku());
                    row1.createCell(4).setCellValue(productionData.getBatch_no());
                    row1.createCell(5).setCellValue(productionData.getQty());
                    row1.createCell(6).setCellValue(productionData.getStatus());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            List<Production> productionList = productionRepository.getTodayProductionData();
            try {
                Sheet sheet1 = workbook.createSheet("Yesterday's Data");
                Row row0 = sheet1.createRow(0);

                row0.createCell(0).setCellValue("Sr.No.");
                row0.createCell(1).setCellValue("Date");
                row0.createCell(2).setCellValue("Bay No");
                row0.createCell(3).setCellValue("SKU");
                row0.createCell(4).setCellValue("Batch No");
                row0.createCell(5).setCellValue("Quantity");
                row0.createCell(6).setCellValue("Status");

                int j = 1;
                for (Production productionData : productionList) {
                    System.out.println("bay no " + productionData.getBay_no());
                    Row row1 = sheet1.createRow(j++);
                    row1.createCell(0).setCellValue(j - 1);
                    row1.createCell(1).setCellValue(productionData.getDate());
                    row1.createCell(2).setCellValue(productionData.getBay_no());
                    row1.createCell(3).setCellValue(productionData.getSku());
                    row1.createCell(4).setCellValue(productionData.getBatch_no());
                    row1.createCell(5).setCellValue(productionData.getQty());
                    row1.createCell(6).setCellValue(productionData.getStatus());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response1.setHeader("content-disposition", "attachment;filename=Production Report.xls");
        workbook.write(response1.getOutputStream());
    }

}
