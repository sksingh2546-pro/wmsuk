

package com.wmsweb.GoodStatusChange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GoodsStatusChangeService {
    @Autowired
    GoodsStatusChangeRepository goodsStatuschangeRepository;

    @PostMapping("/insertGoodStatus")
    public String insertaData(@RequestBody GoodsStatusChange goodstatuschange) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int insertData = goodsStatuschangeRepository.insertData(goodstatuschange.getBay_no(), goodstatuschange.getBatch_no(), goodstatuschange.getQty(), goodstatuschange.getSku(), goodstatuschange.getStatus());
        if (insertData > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }

    @PostMapping("/updateGoodStatus")
    public String updateGoodstatus(@RequestParam("id") long id) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateData = goodsStatuschangeRepository.updateGoodstatus(id);
        if (updateData > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @GetMapping("/getGoodStatusChange")
    public Map<String, ArrayList<GoodsStatusChange>> getTransportStatus() {
        HashMap<String, ArrayList<GoodsStatusChange>> hmap = new HashMap<String, ArrayList<GoodsStatusChange>>();
        ArrayList<GoodsStatusChange> list = (ArrayList<GoodsStatusChange>) goodsStatuschangeRepository.getGoodStatusChange();
        hmap.put("GoodStatusData", list);
        return hmap;
    }
}
