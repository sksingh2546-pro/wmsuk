

package com.wmsweb.Purchase;

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

    @PostMapping({"/insertPurchaseData"})
    public String insertData(@RequestBody Purchase purchase) {
        String message = "{\"message\":\"Unsuccessful\"}";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String CurrentDate = sdf.format(date);
        int insertData = purchaseRepository.insertData(purchase.getOrder_id(), purchase.getPermit_no(), purchase.getSku(), purchase.getQty(), CurrentDate);
        if (insertData > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        commonDataRepository.insertData(purchase.getOrder_id(), "order", "1", CurrentDate);
        makeSorting(purchase.getOrder_id());
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

    public void makeSorting(long order_id) {
        List<Purchase> getUniqueList = purchaseRepository.getPurchaseList(order_id);
        HashSet<Purchase> uniqueList = new HashSet<Purchase>(getUniqueList);
        for (Purchase uPurchase : uniqueList) {
            int purchaseQty = 0;
            int increment = 1;
            List<Purchase> getPurchaseData = purchaseRepository.getPurchaseDetails(uPurchase.getSku(), order_id);
            for (Purchase purchase : getPurchaseData) {
                purchaseQty += purchase.getQty();
            }
            System.out.println("first loop" + purchaseQty);
            List<Production> getProductionData = productionRepository.getProductionData(uPurchase.getSku(), "PASS");
            for (int count = 0; count < increment; ++count) {
                int mainQty = purchaseQty - getProductionData.get(count).getQty();
                if (mainQty <= 0) {
                    sortingPurchaseRepository.insertData(uPurchase.getOrder_id(), uPurchase.getPermit_no(), uPurchase.getSku(), getProductionData.get(count).getBatch_no(), getProductionData.get(count).getBay_no(), purchaseQty, 0, uPurchase.getDate());
                    System.out.println("first" + mainQty);
                } else if (mainQty > 0) {
                    sortingPurchaseRepository.insertData(uPurchase.getOrder_id(), uPurchase.getPermit_no(), uPurchase.getSku(), getProductionData.get(count).getBatch_no(), getProductionData.get(count).getBay_no(), getProductionData.get(count).getQty(), 0, uPurchase.getDate());
                    System.out.println("second" + mainQty);
                    purchaseQty -= getProductionData.get(count).getQty();
                    ++increment;
                }
            }
            purchaseRepository.updatePurchaseStatus(uPurchase.getOrder_id());
        }
    }

    @PostMapping({"/changeStatusWithOrderid"})
    public String changeStatusWithOrderid(@RequestParam("order_id") long order_id) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updatePurchaseStatus = purchaseRepository.changeStatusWithOrderid(order_id);
        if (updatePurchaseStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }
}
