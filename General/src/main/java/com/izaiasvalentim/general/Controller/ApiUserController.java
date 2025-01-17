package com.izaiasvalentim.general.Controller;

import com.izaiasvalentim.general.Domain.ApiUser;
import com.izaiasvalentim.general.Domain.Enums.TypeRoles;
import com.izaiasvalentim.general.Repository.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/ApiUsers")
public class ApiUserController {

    private final ApiUserRepository apiUserRepository;

    @Autowired
    public ApiUserController(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAllApiUsers() {
        List<ApiUser> apiUsers = apiUserRepository.findAll();
        return new ResponseEntity<>(apiUsers, HttpStatus.OK);
    }

    @GetMapping(value = "saveDummy")
    public ResponseEntity<?> saveDummy() {
        try {
            var apiUser = new ApiUser("izaias@gmail.com", "izaias", "123", "8444444",
                    "uma casa", new Date(), null, true, false);
            TypeRoles role = TypeRoles.ADMIN;
            apiUser.setRole(role);
            apiUser.setAdmin();
            apiUserRepository.save(apiUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
