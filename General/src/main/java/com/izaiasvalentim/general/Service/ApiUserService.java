package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Common.CustomExceptions.UserAlreadyExistsException;
import com.izaiasvalentim.general.Domain.ApiUser;
import com.izaiasvalentim.general.Domain.BaseUser;
import com.izaiasvalentim.general.Domain.DTO.ApiUser.ApiUserRegisterDTO;
import com.izaiasvalentim.general.Domain.DTO.ApiUser.ApiUserReturnDTO;
import com.izaiasvalentim.general.Repository.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserService {

    private final ApiUserRepository apiUserRepository;
    private final BaseUserService baseUserService;

    @Autowired
    public ApiUserService(ApiUserRepository apiUserRepository, BaseUserService baseUserService) {
        this.apiUserRepository = apiUserRepository;
        this.baseUserService = baseUserService;
    }

    public ApiUserReturnDTO registerUser(ApiUserRegisterDTO dto) {
        try {
            validateUserUniqueFields(dto);

            BaseUser baseUser = baseUserService.save(dto);

            ApiUser apiUser = new ApiUser();
            apiUser.setUser(baseUser);
            apiUser.setAddress(dto.address());
            apiUser.setPhone(dto.phone());
            apiUser.setAdmissionDate(dto.admissionDate());
            apiUser.setIsAdmin();
            apiUser.setIsActive(true);
            apiUser.setDeleted(false);
            apiUserRepository.save(apiUser);

            return new ApiUserReturnDTO(apiUser);
        } catch (Exception e) {
            throw new ErrorInProcessServiceException(e.getMessage());
        }
    }

    public ApiUserReturnDTO updateUser(ApiUserRegisterDTO dto) {
        try {
            ApiUser userToUpdate = apiUserRepository.findByUser(baseUserService.findByUsername(dto.username()))
                    .orElseThrow(() ->
                            new ResourceNotFoundException
                                    ("Error updating user. No record was found with the username provided."));

            if (dto.phone() != null && !dto.phone().isEmpty()) {
                userToUpdate.setPhone(dto.phone());
            }
            if (dto.address() != null && !dto.address().isEmpty()) {
                userToUpdate.setAddress(dto.address());
            }
            apiUserRepository.save(userToUpdate);
            return new ApiUserReturnDTO(userToUpdate);
        } catch (Exception e) {
            throw new ErrorInProcessServiceException("Error while updating user");
        }
    }

    public ApiUserReturnDTO getUserByUsername(String username) {
        ApiUser user = apiUserRepository.findByUser(baseUserService.findByUsername(username)).orElseThrow(() -> new
                ResourceNotFoundException("Error updating user. No record was found with the username provided."));
        return new ApiUserReturnDTO(user);
    }

    public void deleteUserByUsername(String username) {
        Optional<ApiUser> apiUser = apiUserRepository.findByUser(baseUserService.findByUsername(username));
        if (apiUser.isEmpty()) {
            throw new ResourceNotFoundException("Error deleting the user. No record was found with the username provided");
        }
        apiUser.get().setIsActive(false);
        apiUser.get().setDeleted(true);
        apiUserRepository.save(apiUser.get());
    }

    private void validateUserUniqueFields(ApiUserRegisterDTO dto) {
        String param = "username";

        BaseUser baseUser = baseUserService.findByUsername(dto.username());
        param = baseUser == null ? "email" : param;

        baseUser = baseUserService.findByEmail(dto.email());
        param = baseUser == null ? "CPF" : param;

        baseUser = baseUserService.findByCPF(dto.CPF());

        if (baseUser != null) {
            throw new UserAlreadyExistsException("User with this " + param + " already exists.");
        }
    }
}
