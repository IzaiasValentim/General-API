package com.izaiasvalentim.general.Repository;

import com.izaiasvalentim.general.Domain.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, Long> {
}
