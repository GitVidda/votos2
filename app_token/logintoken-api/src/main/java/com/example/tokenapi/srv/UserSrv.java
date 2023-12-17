package com.example.tokenapi.srv;

import com.example.tokenapi.dao.UserDao;
import com.example.tokenapi.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserSrv {
    private final UserDao userDao;
    private final TokenSrv tokenSrv;

    public String verifyLogin(Map<String, Object> params) {
        User user = userDao.findFirstByUser(params.get("user").toString());
        if (user != null) {
            if (params.get("password").toString().equals(Base64Coder.decodeString(user.getPassword()))) {
                return tokenSrv.generateToken(user.getUser());
            }
        }
        return "";
    }
}
