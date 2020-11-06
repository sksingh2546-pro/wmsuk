

package com.wmsweb.productionPlan;

import com.wmsweb.companyCode.CompanyCodeRepository;
import com.wmsweb.skuList.SkuList;
import com.wmsweb.skuList.SkuListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class ProductionPlanController {
    @Autowired
    SkuListRepository skuListRepository;
    @Autowired
    ProductionPlanRepository productionPlanRepository;
    @Autowired
    CompanyCodeRepository companyCodeRepository;


    Date date;
    SimpleDateFormat sdf;

    public ProductionPlanController() {
        this.date = new Date();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @PostMapping({"/insertProductionPlan"})
    @ResponseBody
    public String insertProductionPlan(@RequestBody ProductionPlan productionPlan) {
        String message = "{\"message\":\"Unsuccessful\"}";
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yy");
        int month=Calendar.getInstance().get(Calendar.MONTH);
        String batch_format=String.format("%04d", Integer.parseInt(productionPlan.getBatch_no()));
        String batch_no=companyCodeRepository.getCompanyCode()+"-"+batch_format+"-"+(month+1)+sdf2.format(date);
        List<ProductionPlan> getSku=productionPlanRepository.getTodayProductionPlan(productionPlan.getSku()
                ,sdf1.format(date)+" 00:00:00",sdf.format(date),productionPlan.getLine_no(),batch_no);
        if(getSku.size()>0)
        {
         int update=productionPlanRepository.updateProduction_plan(
                 getSku.get(0).getQty()+productionPlan.getQty(),
                 productionPlan.getSku(),productionPlan.getLine_no());
         if(update>0){
             message = "{\"message\":\"Successful\"}";
         }
        }else {
            int insert = productionPlanRepository.insertProduction_plan(batch_no,
                    sdf.format(this.date), productionPlan.getQty(),
                    productionPlan.getSku(), productionPlan.getLine_no());
            if (insert > 0) {
                message = "{\"message\":\"Successful\"}";
            }
        }


        return message;
    }

    @GetMapping({"/getSkuList"})
    public Map<String, ArrayList<SkuList>> getSkuList() {
        ArrayList<SkuList> list = (ArrayList<SkuList>) skuListRepository.getSkuList();
        HashMap<String, ArrayList<SkuList>> hmap = new HashMap<String, ArrayList<SkuList>>();
        hmap.put("sku", list);
        return hmap;
    }

    @GetMapping({"/getTodayProductionPlan"})
    public Map<String, ArrayList<ProductionPlan>> getTodayProductionPlan() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, ArrayList<ProductionPlan>> hmap = new HashMap<String, ArrayList<ProductionPlan>>();
        ArrayList<ProductionPlan> productionPlanList = (ArrayList<ProductionPlan>) productionPlanRepository.getTodayProductionPlan(sdf2.format(date) + " 00:00:00", sdf.format(date));
        System.out.println(productionPlanList.size());
        hmap.put("proplan", productionPlanList);
        return hmap;
    }

    @GetMapping("getLineNo")
    public Map<String,TreeSet<String>>getLineNo(){
        TreeSet<String>tempSet=new TreeSet<>();
        List<String>duplicateData=productionPlanRepository.getLineNo();
        TreeSet<String> tList=new TreeSet<>(duplicateData);
        HashMap<String,TreeSet<String>>hMap=new HashMap<>();
        hMap.put("dp",tList);
        return hMap;
    }
}
