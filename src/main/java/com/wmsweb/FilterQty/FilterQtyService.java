package com.wmsweb.FilterQty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FilterQtyService {
    @Autowired
    FilterQtyRepo filterQtyRepo;

    @PostMapping("/insertFilterQty")
    public String insertData(@RequestBody FilterQty filterqty) {
        String message = "{\"message\":\"Unsuccessful\"}";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(date);

        int insertData = (filterQtyRepo).insertData(filterqty.getSku(), filterqty.getQty(), currentDate, filterqty.getLine_no(), filterqty.getBatch_no());
        if (insertData > 0) {
            message = "{\"message\":\"Successful\"}";
        }

        return message;
    }
}
