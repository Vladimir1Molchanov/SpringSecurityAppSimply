package vm.projects.SpringSecurityApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vm.projects.SpringSecurityApp.model.User;
import java.util.Optional;

public interface UserR extends JpaRepository<User, Long> {
    Optional<User> findByFirstName(String name);
}
