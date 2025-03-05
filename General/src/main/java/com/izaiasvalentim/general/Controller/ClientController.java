package com.izaiasvalentim.general.Controller;

import com.izaiasvalentim.general.Domain.DTO.Client.ClientIdentification;
import com.izaiasvalentim.general.Domain.DTO.Client.ClientRegisterDTO;
import com.izaiasvalentim.general.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    public ResponseEntity<?> registerClient(@RequestBody ClientRegisterDTO clientRegisterDTO) {
        clientService.requestRegistration(clientRegisterDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/")
    @PreAuthorize("hasAuthority('SCOPE_MANAGER')")
    public ResponseEntity<?> approveClient(@RequestBody ClientIdentification identificationNumber) {
        System.out.println(identificationNumber);
        ClientRegisterDTO clientToReturn = clientService.approveClientRegistration(identificationNumber.identificationNumber());
        return new ResponseEntity<>(clientToReturn, HttpStatus.OK);
    }
}
