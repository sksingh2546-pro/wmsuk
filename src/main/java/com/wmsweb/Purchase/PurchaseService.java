

package com.wmsweb.Purchase;

import com.wmsweb.SortingPurchase.SortingPurchase;
import com.wmsweb.SortingPurchase.SortingPurchaseRepository;
import com.wmsweb.commonData.CommonDataRepository;
import com.wmsweb.production.Production;
import com.wmsweb.production.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    SortingPurchaseRepository sortingPurchaseRepository;
    @Autowired
    ProductionRepository productionRepository;
    @Autowired
    CommonDataRepository commonDataRepository;
    int i=1;

    @PostMapping({"/insertPurchaseData"})
    public String insertData(@RequestBody Purchase purchase) throws InterruptedException {
        String message = "{\"message\":\"Unsuccessful\"}";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String CurrentDate = sdf.format(date);

        int insertData = purchaseRepository.insertData(purchase.getOrder_id(), purchase.getPermit_no(), purchase.getSku(), purchase.getQty(), CurrentDate);
        if (insertData > 0) {
            message = "{\"message\":\"Successful\"}";
            try {
            //    commonDataRepository.insertData(purchase.getOrder_id(), "order", "1", CurrentDate);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        Thread.sleep(2000);
        makeSorting(purchase.getOrder_id(),purchase.getSku());
        return message;
    }

    @PostMapping("/upadatePurchaseStatus")
    public String updatePurchaseStatus(@RequestBody Purchase purchase) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updatePurchaseStatus = purchaseRepository.updatePurchaseStatus(purchase.getOrder_id());
        if (updatePurchaseStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @GetMapping({"/getPurchaseDetails"})
    public Map<String, ArrayList<Purchase>> getpurchaseDetails() {
        HashMap<String, ArrayList<Purchase>> hmap = new HashMap<String, ArrayList<Purchase>>();
        ArrayList<Purchase> list = (ArrayList<Purchase>) purchaseRepository.getPurchaseDetails();
        hmap.put("purchaseDetails", list);
        return hmap;
    }

    public void makeSorting(long order_id,String sku) {
        sortingPurchaseRepository.deleteQty();
        List<Purchase> getUniqueList = purchaseRepository.getPurchaseList(order_id,sku);
        HashSet<Purchase> uniqueList = new HashSet<Purchase>(getUniqueList);
        for (Purchase uPurchase : uniqueList) {
            int purchaseQty = 0;
            int increment = 1;
            List<Purchase> getPurchaseData = purchaseRepository.getPurchaseDetails(uPurchase.getSku(), order_id);
            for (Purchase purchase : getPurchaseData) {
                purchaseQty += purchase.getQty();
            }
            List<Production> getProductionData = productionRepository.getProductionData(uPurchase.getSku(), "PASS");
            for (int count = 0; count < increment; ++count) {
                int mainQty=0;
                List<SortingPurchase> getSortingPurchase=sortingPurchaseRepository.getSortingPurchase(
                        getProductionData.get(count).getSku(),getProductionData.get(count).getBatch_no()
                        ,getProductionData.get(count).getBay_no()
                );
                 if(getSortingPurchase.size()>0){
                     int qty=getProductionData.get(count).getQty();
                     int tmpQty = 0;
                     for(SortingPurchase sp:getSortingPurchase){
                     tmpQty+=sp.getQty();}
                     int tQty=qty-tmpQty;
                     if(tQty<=0){
                         ++increment;
                     }
                     else if(tQty>0){
                         int sendQty=0;
                         if(tQty>purchaseQty){
                             sendQty=purchaseQty;
                         }
                         else{
                             mainQty+=purchaseQty -tQty;
                             sendQty=mainQty;
                         }


                         if (mainQty <= 0) {
                             System.out.println();
                             sortingPurchaseRepository.insertData(uPurchase.getOrder_id(), uPurchase.getPermit_no(), uPurchase.getSku(), getProductionData.get(count).getBatch_no(), getProductionData.get(count).getBay_no(), sendQty, 0, uPurchase.getDate());
                             System.out.println("first_1" + mainQty);
                         } else if (mainQty > 0) {
                             sortingPurchaseRepository.insertData(uPurchase.getOrder_id(), uPurchase.getPermit_no(), uPurchase.getSku(), getProductionData.get(count).getBatch_no(), getProductionData.get(count).getBay_no(), tQty, 0, uPurchase.getDate());
                             System.out.println("second_1" + mainQty);
                             purchaseQty -= tQty;
                             ++increment;
                         }
                     }
                 }
                 else{
                     int sendQty=0;
                     if(mainQty>0){
                         sendQty=mainQty;

                     }
                     else if(mainQty==0){
                         mainQty+=purchaseQty-getProductionData.get(count).getQty();

                             sendQty=purchaseQty;

                     }


                if (mainQty <= 0) {
                    sortingPurchaseRepository.insertData(uPurchase.getOrder_id(), uPurchase.getPermit_no(), uPurchase.getSku(), getProductionData.get(count).getBatch_no(), getProductionData.get(count).getBay_no(), sendQty, 0, uPurchase.getDate());
                    System.out.println("first_2" + mainQty);
                } else if (mainQty > 0) {
                    sortingPurchaseRepository.insertData(uPurchase.getOrder_id(), uPurchase.getPermit_no(), uPurchase.getSku(), getProductionData.get(count).getBatch_no(), getProductionData.get(count).getBay_no(), getProductionData.get(count).getQty(), 0, uPurchase.getDate());
                    System.out.println("second_2" + mainQty);
                    purchaseQty -= getProductionData.get(count).getQty();
                    ++increment;
                }}
            }
            purchaseRepository.updatePurchaseStatus(uPurchase.getOrder_id());
        }
    }

    @PostMapping({"/changeStatusWithOrderid"})
    public String changeStatusWithOrderid(@RequestParam("order_id") long order_id) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updatePurchaseStatus = purchaseRepository.runChangeStatusWithOrderId(order_id);
        if (updatePurchaseStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @PostMapping({"/insertBatchPurchaseData"})
    public String insertBatchPurchaseData(@RequestBody Purchase purchase) throws InterruptedException {
        String message = "{\"message\":\"Unsuccessful\"}";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String CurrentDate = sdf.format(date);
        int insertData = purchaseRepository.insertData(purchase.getOrder_id(),
                purchase.getPermit_no(), purchase.getSku(), purchase.getQty(),
                CurrentDate,purchase.getBay_no(),purchase.getBatch_no());
        if (insertData > 0) {
            int insert=sortingPurchaseRepository.insertData(purchase.getOrder_id(),
                    purchase.getPermit_no(),purchase.getSku(),purchase.getBatch_no(),
                    purchase.getBay_no(),purchase.getQty(),0,CurrentDate);
            if (insert>0){
            message = "{\"message\":\"Successful\"}";
        }}
        return message;
    }

}
