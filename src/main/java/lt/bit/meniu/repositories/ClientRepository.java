package lt.bit.meniu.repositories;

import lt.bit.meniu.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
