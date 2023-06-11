package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class AccountRepositoryTest {

    @Test
    void successfulAccountCreation() {
        AccountRepository repository = new AccountRepository();
        String accountId = repository.createAccount("Orvyl", 89.9);
        Assertions.assertEquals(1, repository.getNumberOfAccounts());
        Assertions.assertEquals("Orvyl", repository.getAccount(accountId).name());
    }

    @Test
    void getAccountTest() {
        AccountRepository repository = new AccountRepository();
        String accountId = repository.createAccount("Orvyl", 89.9);
        Assertions.assertEquals("Orvyl", repository.getAccount(accountId).name());
        Assertions.assertEquals(89.9, repository.getAccount(accountId).balance());
        Assertions.assertNull(repository.getAccount("randomid"));
    }

    @Test
    void successfulDelete() {
        AccountRepository repository = new AccountRepository();
        String id = repository.createAccount("Orvyl", 89.9);
        repository.deleteAccount(id);
        Assertions.assertEquals(0, repository.getNumberOfAccounts());
    }

    @Test
    void getNumberOfAccounts() {
        AccountRepository repository = new AccountRepository();
        String id0 = repository.createAccount("Orvyl", 89.9);
        String id1 = repository.createAccount("Orvyl1", 89.9);
        String id2 = repository.createAccount("Orvyl2", 89.9);
        String id3 = repository.createAccount("Orvyl3", 89.9);

        Assertions.assertEquals(4, repository.getNumberOfAccounts());

        repository.deleteAccount(id3);

        Assertions.assertEquals(3, repository.getNumberOfAccounts());
    }

}

