package ovh.jakubk.shooting.range.management.database.dao.springdata;

import ovh.jakubk.shooting.range.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);
}