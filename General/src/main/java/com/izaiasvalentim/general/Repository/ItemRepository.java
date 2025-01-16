package com.izaiasvalentim.general.Repository;

import com.izaiasvalentim.general.Domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
