package com.kuba.shooting.range.management.database.dao.springdata;

import com.kuba.shooting.range.management.model.Ammo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmmoDAO extends JpaRepository<Ammo, Long> {
}
