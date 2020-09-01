

package com.wmsweb.productionPlan;

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
        List<ProductionPlan> pList = (List<ProductionPlan>) productionPlanRepository.getProductionPlan(productionPlan.getSku());
        long batch = 0L;
        if (pList.size() == 0) {
            batch = 1L;
        } else {
            batch = pList.get(0).getBatch_no() + 1L;
        }
        List<ProductionPlan> getSku=productionPlanRepository.getTodayProductionPlan(productionPlan.getSku()
                ,sdf1.format(date)+" 00:00:00",sdf.format(date),productionPlan.getLine_no());
        if(getSku.size()>0)
        {
         int update=productionPlanRepository.updateProduction_plan(
                 getSku.get(0).getQty()+productionPlan.getQty(),
                 productionPlan.getSku(),productionPlan.getLine_no());
         if(update>0){
             message = "{\"message\":\"Successful\"}";
         }
        }else {
            int insert = productionPlanRepository.insertProduction_plan(batch,
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
}
