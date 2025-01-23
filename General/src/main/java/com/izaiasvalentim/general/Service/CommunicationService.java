package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Domain.Communication;
import com.izaiasvalentim.general.Repository.CommunicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommunicationService {

    private final CommunicationRepository communicationRepository;

    @Autowired
    public CommunicationService(CommunicationRepository communicationRepository) {
        this.communicationRepository = communicationRepository;
    }

    public void registerNewCommunication(Communication communication) {
        try {
            communicationRepository.save(communication);
        } catch (Exception e) {
            throw new ErrorInProcessServiceException(e.getMessage());
        }
    }

    public List<Communication> getAllValidCommunications(Pageable pageable) {
        LocalDate today = LocalDate.now();
        return communicationRepository.findByIsDeletedFalseAndEndDateAfter(today, pageable).getContent();
    }

    public List<Communication> getAllCommunications(Pageable pageable) {
        return communicationRepository.findByIsDeletedFalse(pageable).getContent();
    }

    public Communication updateCommunication(Communication updateDto) {
        Communication communication = communicationRepository.findById(updateDto.getId()).orElse(null);

        if (communication == null) {
            throw new ResourceNotFoundException("Communication with id " + updateDto.getId() + " not found");
        }

        if (updateDto.getMessage() != null && !updateDto.getMessage().isEmpty()) {
            communication.setMessage(updateDto.getMessage());
        }
        if (updateDto.getScope() >= 0) {
            communication.setScope(updateDto.getScope());
        }
        if (updateDto.getEndDate() != null && updateDto.getEndDate().isAfter(communication.getEndDate())) {
            communication.setEndDate(updateDto.getEndDate());
        }
        return communicationRepository.save(communication);
    }

    public Communication endCommunication(long id) {
        Communication communicationToEnd = communicationRepository.findById(id).orElse(null);
        if (communicationToEnd == null) {
            throw new ResourceNotFoundException("Communication with id " + id + " not found");
        }
        LocalDate nowDate = LocalDate.now();
        communicationToEnd.setEndDate(nowDate);

        return communicationRepository.save(communicationToEnd);
    }

    public void deleteCommunication(long id) {
        Communication communicationToDelete = communicationRepository.findById(id).orElse(null);
        if (communicationToDelete == null) {
            throw new ResourceNotFoundException("Communication with id " + id + " not found");
        }
        communicationToDelete.setDeleted(true);

        communicationRepository.save(communicationToDelete);
    }
}
