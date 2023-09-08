package com.kuba.shooting.range.management.database.dao.springdata;

import com.kuba.shooting.range.management.model.Ammo;
import org.springframework.data.repository.ListCrudRepository;

public interface AmmoDAO extends ListCrudRepository<Ammo, Long> {
}
