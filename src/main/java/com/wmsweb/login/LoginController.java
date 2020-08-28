
package com.wmsweb.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginRepository loginRepository;

    @PostMapping("/insertloginData")
    public String insertData(@RequestBody Login login) {
        String message = "{\"message\":\"Unsuccessful\"}";
        int insertData = this.loginRepository.insertData(login.getUser_name(), login.getPassword());
        if (insertData > 0) {
            message = "{\"message\":\"Successful\"}";
        }
        return message;
    }

    @GetMapping("/getLogin")
    public String getLogin(@RequestParam("user_name") String user_name, @RequestParam("password") String password) {
        List<Login> list = (List<Login>) this.loginRepository.getLogin(user_name, password);
        String response = "{\"message\":\"Unsuccessful\"}";
        if (list.size() > 0) {
            response = "{\"message\":\"Successful\"}";
        }
        return response;
    }
}
