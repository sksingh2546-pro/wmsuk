package com.wmsweb.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")

public class StockController {
@Autowired
    StockRepo stockRepo;
@PostMapping("insertStockData")
    public String insertStockData(@RequestBody Stock stock){
    String message = "{\"message\":\"Unsuccessful\"}";
    Date date=new Date();
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    List<Stock> stockList=stockRepo.getProductionData(stock.getExpiry(),stock.getBarcode(),stock.getSku());
    if(stockList.size()>0){
        int update=stockRepo.updateProduction(stockList.get(0).getQty()+stock.getQty(),
                stock.getExpiry(),stock.getBarcode(),stock.getSku());
        if(update>0){
            message="{\"message\":\"Updated\"}";
        }
    }else {
        int insert = stockRepo.insertProduction(stock.getSku(), stock.getExpiry(), stock.getQty(),
                stock.getStatus(), sdf.format(date), stock.getBarcode());
        if(insert>0){
            message="{\"message\":\"Inserted\"}";
        }
    }
    return message;
}
}
