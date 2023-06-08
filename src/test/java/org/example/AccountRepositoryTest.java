package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    @Test
    void successfulAccountCreation() {
        // Setup
        AccountRepository repository = new AccountRepository();
        // Kick
        String accountId= repository.createAccount("Orvyl", 89.9);
        // Verify
        Assertions.assertEquals(1, repository.getNumberOfAccounts());
        Assertions.assertEquals("Orvyl",repository.getAccount(accountId).name());
    }

    @Test
    void getAccountTest() {
        AccountRepository repository = new AccountRepository();

        String accountId = repository.createAccount("Orvyl", 89.9);
        Assertions.assertEquals("Orvyl", repository.getAccount(accountId).name());
        Assertions.assertEquals(89.9, repository.getAccount(accountId).balance());
        Assertions.assertEquals(null, repository.getAccount("randomid"));
    }

    @Test
    void successfulDelete() {
        //Setup
        AccountRepository repository = new AccountRepository();
        String id= repository.createAccount("Orvyl", 89.9);
        //Kick
        repository.deleteAccount(id);
        //Verify
        Assertions.assertEquals(0, repository.getNumberOfAccounts());
    }

    @Test
    void getNumberOfAccounts() {
        //Setup and Kick
        AccountRepository repository = new AccountRepository();
        String id0= repository.createAccount("Orvyl", 89.9);
        String id1= repository.createAccount("Orvyl", 89.9);
        String id2= repository.createAccount("Orvyl", 89.9);
        String id3= repository.createAccount("Orvyl", 89.9);

        //Verify
        Assertions.assertEquals( 4, repository.getNumberOfAccounts());

        //Setup
        repository.deleteAccount(id3);

        //Verify
Assertions.assertEquals(3, repository.getNumberOfAccounts());


    }
}