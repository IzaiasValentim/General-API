package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Domain.ApiUser;
import com.izaiasvalentim.general.Domain.DTO.ApiUser.ApiUserRegisterDTO;
import com.izaiasvalentim.general.Domain.Enums.TypeRoles;
import com.izaiasvalentim.general.Repository.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiUserService {

    private final ApiUserRepository apiUserRepository;

    @Autowired
    public ApiUserService(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }

    public ApiUserRegisterDTO registerUser(ApiUserRegisterDTO dto) {
        try {
            ApiUser apiUser = new ApiUser();
            apiUser.setUsername(dto.username());
            apiUser.setEmail(dto.email());
            apiUser.setCPF(dto.CPF());
            apiUser.setAddress(dto.address());
            apiUser.setPhone(dto.phone());
            apiUser.setAdmissionDate(dto.admissionDate());

            TypeRoles role = TypeRoles.getTypeById(dto.role());
            apiUser.setRole(role);

            apiUser.setAdmin();
            apiUser.setActive(true);
            apiUser.setDeleted(false);
            apiUserRepository.save(apiUser);

            return dto;
        } catch (Exception e) {
            throw new ErrorInProcessServiceException("Error while saving user");
        }
    }

}
