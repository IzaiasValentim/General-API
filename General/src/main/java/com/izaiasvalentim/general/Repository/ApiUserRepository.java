package com.izaiasvalentim.general.Repository;

import com.izaiasvalentim.general.Domain.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
}
