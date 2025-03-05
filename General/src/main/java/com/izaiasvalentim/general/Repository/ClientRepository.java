package com.izaiasvalentim.general.Repository;

import com.izaiasvalentim.general.Domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByIdentificationNumber(String identificationNumber);
}
