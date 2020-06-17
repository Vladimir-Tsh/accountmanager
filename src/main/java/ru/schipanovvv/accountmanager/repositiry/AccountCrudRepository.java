package ru.schipanovvv.accountmanager.repositiry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.schipanovvv.accountmanager.db.Account;

public interface AccountCrudRepository extends CrudRepository<Account, Integer> {
}


