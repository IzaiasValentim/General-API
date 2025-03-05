package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceAlreadyExistsException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Domain.Client;
import com.izaiasvalentim.general.Domain.DTO.Client.ClientRegisterDTO;
import com.izaiasvalentim.general.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void requestRegistration(ClientRegisterDTO clientDTO) {

        if (clientRepository.findByIdentificationNumber(clientDTO.getIdentificationNumber()).isPresent()) {
            throw new ResourceAlreadyExistsException("One Client already exists with the same identification number");
        }

        Client clientToRegister = clientDTO.registerDTOToClient();
        clientRepository.save(clientToRegister);

    }

    public ClientRegisterDTO approveClientRegistration(String identificationNumber) {
        try {
            Client clientToApprove = clientRepository.findByIdentificationNumber(identificationNumber).orElseThrow(() -> new ResourceNotFoundException("Client with identificationNumber " + identificationNumber + " not exists."));
            System.out.println(clientToApprove);
            clientToApprove.setActive(true);
            clientRepository.save(clientToApprove);


            return new ClientRegisterDTO(clientToApprove);
        } catch (Exception e) {
            throw new ErrorInProcessServiceException("Error occurred while approving a client registration.");
        }
    }
}
