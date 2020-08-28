

package com.wmsweb.commonData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CommonDataService {
    @Autowired
    CommonDataRepository commonDataRepository;

    @PostMapping("/insertCommonData")
    public String insertData(@RequestBody CommonData commonData) {
        String message = "{\"message\":\"Unsuccessful\"}";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String CurrentDate = sdf.format(date);
        int insertData = commonDataRepository.insertData(commonData.getOrder_id(), commonData.getType(), commonData.getPriority(), CurrentDate);
        if (insertData > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }

    @PostMapping("/updateCommonDataStatus")
    public String updateCommonDataStatus(@RequestParam("id") long id, @RequestParam("status") int status) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int updateCommonDataStatus = commonDataRepository.updateCommonDataStatus(id, status);
        if (updateCommonDataStatus > 0) {
            message = "{\"message\":\"Updated Successfully\"}";
        }
        return message;
    }

    @GetMapping("/getCommonData")
    public Map<String, ArrayList<CommonData>> getTransportStatus() {
        HashMap<String, ArrayList<CommonData>> hmap = new HashMap<String, ArrayList<CommonData>>();
        ArrayList<CommonData> list = (ArrayList<CommonData>) commonDataRepository.getCommonData();
        hmap.put("CommonData", list);
        return hmap;
    }
}
