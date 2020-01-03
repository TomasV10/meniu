package lt.bit.meniu.repositories;

import lt.bit.meniu.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    Account findByEmail(String email);
}
