package ovh.jakubk.shooting.range.management.database.dao.springdata;

import ovh.jakubk.shooting.range.management.model.Gun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GunDAO extends JpaRepository<Gun, Long> {

    Optional<Gun> findById(Long id);

    List<Gun> findAll();
}
