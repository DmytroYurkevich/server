package getarrays.io.server.repository;

import getarrays.io.server.model.Server;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Optional<Server> findByIpAddress(String ipAddress);
    Optional<Server> findByName(String name);
}
