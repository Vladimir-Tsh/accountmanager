package ru.schipanovvv.accountmanager.repositiry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.schipanovvv.accountmanager.db.AccountOperation;

public interface AccountOperationCrudRepository extends CrudRepository<AccountOperation, Integer> {
}
