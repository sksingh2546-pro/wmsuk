
package com.wmsweb.SortingPurchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class SortingPurchaseService {
    @Autowired
    SortingPurchaseRepository sortingPurchaseRepository;

    @PostMapping({"/updateSortingPurchaseStatus"})
    public String updatePurchaseStatus(@RequestBody SortingPurchase sortingPurchase) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateSortingPurchaseStatus = sortingPurchaseRepository.updateSortingPurchaseStatus(sortingPurchase.getId(), sortingPurchase.getStatus());
        if (updateSortingPurchaseStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @GetMapping({"/getSortingPurchase"})
    public Map<String, ArrayList<SortingPurchase>> getSortingPurchase() {
        HashMap<String, ArrayList<SortingPurchase>> hmap = new HashMap<String, ArrayList<SortingPurchase>>();
        ArrayList<SortingPurchase> list = (ArrayList<SortingPurchase>) sortingPurchaseRepository.getSortingPurchase();
        hmap.put("SortingPurchase", list);
        return hmap;
    }

    @GetMapping({"/getOrderProduct"})
    public Map<String, ArrayList<SortingPurchase>> getOrderProduct(@RequestParam("order_id") long order_id) {
        HashMap<String, ArrayList<SortingPurchase>> hmap = new HashMap<String, ArrayList<SortingPurchase>>();
        ArrayList<SortingPurchase> list = (ArrayList<SortingPurchase>) sortingPurchaseRepository.getOrderProduct(order_id);
        hmap.put("OrderIdProduct", list);
        return hmap;
    }

    @PostMapping({"/updateWithOrderId"})
    public String updateWithOrderId(@RequestParam("order_id") long order_id) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateSortingPurchaseStatus = sortingPurchaseRepository.updateWithOrdeId(order_id);
        if (updateSortingPurchaseStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }
}
