package com.izaiasvalentim.general.Controller;

import com.izaiasvalentim.general.Domain.ApiUser;
import com.izaiasvalentim.general.Domain.DTO.ApiUser.ApiUserRegisterDTO;
import com.izaiasvalentim.general.Service.ApiUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/users")
public class ApiUserController {

    private final ApiUserService apiUserService;

    public ApiUserController(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody ApiUserRegisterDTO dto) {
        ApiUserRegisterDTO userSaved = apiUserService.registerUser(dto);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUSer(@RequestBody ApiUserRegisterDTO dto) {
        ApiUserRegisterDTO userUpdated = apiUserService.updateUser(dto);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    // Temporary Method.
    @GetMapping()
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        ApiUser apiUser = apiUserService.getUserByUsername(username);

        return new ResponseEntity<>(apiUser, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUserByUsername(@RequestParam String username) {
        apiUserService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
