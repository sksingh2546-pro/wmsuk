
package com.wmsweb.OutGoods;

import com.wmsweb.SortingPurchase.SortingPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OutGoodsService {
    @Autowired
    OutGoodsRepository outgoodsRepository;
    @Autowired
    SortingPurchaseRepository sortingpurchaseRepository;

    @PostMapping("/insertOutGoods")
    public String insertData(@RequestBody OutGoods outgoods) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateQty = sortingpurchaseRepository.updateQty(outgoods.getQty(), outgoods.getOrder_id(), outgoods.getBatch_no(), outgoods.getSku(), outgoods.getBay_no());

        System.out.println("" + updateQty);
        if (updateQty > 0) {
            int insertData = outgoodsRepository.insertData(outgoods.getBatch_no(), outgoods.getBay_no(), outgoods.getSku(), outgoods.getQty(), outgoods.getOrder_id());
            if (insertData > 0) {
                message = "{\"message\":\"Successful\"}";
            }
        }
        return message;
    }

    @GetMapping("/getOutGoods")
    public Map<String, ArrayList<OutGoods>> getPalletqty() {
        HashMap<String, ArrayList<OutGoods>> hmap = new HashMap<String, ArrayList<OutGoods>>();
        ArrayList<OutGoods> PalletQty = (ArrayList<OutGoods>) outgoodsRepository.getoutGoodsData();
        hmap.put("outGoods", PalletQty);
        return hmap;
    }
}
