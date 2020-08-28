

package com.wmsweb.PalletQty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PalletQtyService {
    @Autowired
    PalletQtyRepository palletQtyRepository;

    @PostMapping("/insertPalletQty")
    public String insertData(@RequestBody PalletQty palletQty) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int insertDta = palletQtyRepository.insertData(palletQty.getSku(), palletQty.getPallet_qty());
        if (insertDta > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }

    @GetMapping("/getPalletQty")
    public Map<String, ArrayList<PalletQty>> getPalletqty() {
        HashMap<String, ArrayList<PalletQty>> hmap = new HashMap<String, ArrayList<PalletQty>>();
        ArrayList<PalletQty> PalletQty = (ArrayList<PalletQty>) palletQtyRepository.getPalletQty();
        hmap.put("palletQty", PalletQty);
        return hmap;
    }
}
