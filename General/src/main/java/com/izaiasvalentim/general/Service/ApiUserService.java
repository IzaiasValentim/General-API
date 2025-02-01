package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Common.CustomExceptions.UserAlreadyExistsException;
import com.izaiasvalentim.general.Domain.ApiUser;
import com.izaiasvalentim.general.Domain.DTO.ApiUser.ApiUserRegisterDTO;
import com.izaiasvalentim.general.Domain.Enums.TypeRoles;
import com.izaiasvalentim.general.Repository.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserService {

    private final ApiUserRepository apiUserRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public ApiUserService(ApiUserRepository apiUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.apiUserRepository = apiUserRepository;
        this.encoder = passwordEncoder;
    }

    public ApiUserRegisterDTO registerUser(ApiUserRegisterDTO dto) {
        try {
            validateUserUniqueFields(dto);

            ApiUser apiUser = new ApiUser();
            apiUser.setUsername(dto.username());
            apiUser.setCPF(dto.CPF());
            apiUser.setEmail(dto.email());
            apiUser.setPassword(encoder.encode(dto.password()));
            apiUser.setAddress(dto.address());
            apiUser.setPhone(dto.phone());
            apiUser.setAdmissionDate(dto.admissionDate());

            TypeRoles role = TypeRoles.getTypeById(dto.role());
            apiUser.setRole(role);

            apiUser.setIsAdmin();
            apiUser.setActive(true);
            apiUser.setDeleted(false);
            apiUserRepository.save(apiUser);

            return dto;
        } catch (Exception e) {
            throw new ErrorInProcessServiceException("Error while saving user");
        }
    }

    public ApiUserRegisterDTO updateUser(ApiUserRegisterDTO dto) {
        try {
            ApiUser userToUpdate = apiUserRepository.findByUsername(dto.username()).orElseThrow(() -> new
                    ResourceNotFoundException("Error updating user. No record was found with the username provided."));

            if (dto.CPF() != null && !dto.CPF().isEmpty()) {
                userToUpdate.setCPF(dto.CPF());
            }
            
            if (dto.phone() != null && !dto.phone().isEmpty()) {
                userToUpdate.setPhone(dto.phone());
            }
            if (dto.address() != null && !dto.address().isEmpty()) {
                userToUpdate.setAddress(dto.address());
            }
            apiUserRepository.save(userToUpdate);
            return dto;
        } catch (Exception e) {
            throw new ErrorInProcessServiceException("Error while updating user");
        }
    }

    public ApiUser getUserByUsername(String username) {
        return apiUserRepository.findByUsername(username).orElseThrow(() -> new
                ResourceNotFoundException("Error updating user. No record was found with the username provided."));
    }

    public void deleteUserByUsername(String username) {
        Optional<ApiUser> apiUser = apiUserRepository.findByUsername(username);
        if (apiUser.isEmpty()) {
            throw new ResourceNotFoundException("Error deleting the user. No record was found with the username provided");
        }
        apiUser.get().setDeleted(true);
        apiUserRepository.save(apiUser.get());
    }

    private void validateUserUniqueFields(ApiUserRegisterDTO dto) {
        String param = "username";

        ApiUser apiUser = apiUserRepository.findByUsername(dto.username()).orElse(null);
        param = apiUser == null ? "email" : param;

        apiUser = apiUserRepository.findByEmail(dto.email()).orElse(null);
        param = apiUser == null ? "CPF" : param;

        apiUser = apiUserRepository.findByCPF(dto.CPF()).orElse(null);

        if (apiUser != null) {
            throw new UserAlreadyExistsException("User with this " + param + " already exists.");
        }
    }
}
