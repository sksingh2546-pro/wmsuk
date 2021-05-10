
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
    public String insertData(@RequestBody OutGoods outgoods, @RequestParam("cQty") int cQty) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateQty = sortingpurchaseRepository.updateQty(outgoods.getQty(),
                outgoods.getOrder_id(),
                outgoods.getBatch_no(), outgoods.getSku(), outgoods.getBarcode());

        System.out.println("" + updateQty);
        if (updateQty > 0) {
            int insertData = outgoodsRepository.insertData(outgoods.getBatch_no(),
                    outgoods.getBarcode(), outgoods.getSku(), cQty,
                    outgoods.getOrder_id(),outgoods.getExpiry(),outgoods.getP_barcode());
            if (insertData > 0) {
                message = "{\"message\":\"Successful\"}";
            }
        }
        return message;
    }

    @GetMapping("/getOutGoods")
    public Map<String, ArrayList<OutGoods>> getPalletQty() {
        HashMap<String, ArrayList<OutGoods>> hmap = new HashMap<String, ArrayList<OutGoods>>();
        ArrayList<OutGoods> PalletQty = (ArrayList<OutGoods>) outgoodsRepository.getOutGoodsData();
        hmap.put("outGoods", PalletQty);
        return hmap;
    }
}
