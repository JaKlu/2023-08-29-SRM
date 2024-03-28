package ovh.jakubk.shooting.range.management.database.dao.springdata;

import ovh.jakubk.shooting.range.management.model.Ammo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmmoDAO extends JpaRepository<Ammo, Long> {
}
