package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


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


    @Test
    void setBalanceTest() {
        Account account = new Account("1", "Test", 100.0);
        account.setBalance(150.0);
        Assertions.assertEquals(150.0, account.balance());
    }

    @Test
    void getBalanceTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        String accountId = repository.createAccount("Orvyl", 89.9);
        Assertions.assertEquals(89.9, balanceService.getBalance(accountId));
    }

    @Test
    void getBalanceNonExistentAccountTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        Assertions.assertNull(balanceService.getBalance("nonExistentId"));
    }

    @Test
    void creditTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        String accountId = repository.createAccount("Orvyl", 50.0);
        balanceService.credit(accountId, 100.0);
        Double balance = balanceService.getBalance(accountId);
        Assertions.assertEquals(150.0, balance);
    }

    @Test
    void debitTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        String accountId = repository.createAccount("Orvyl", 150.0);
        balanceService.debit(accountId, 100.0);
        Double balance = balanceService.getBalance(accountId);
        Assertions.assertEquals(50.0, balance);
    }

    @Test
    void transferToAndFromTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        String senderAccountId = repository.createAccount("Orvyl", 150.0);
        String receiverAccountId = repository.createAccount("Siglo", 100.0);
        balanceService.transfer(senderAccountId, receiverAccountId, 50.0);
        Double senderBalance = balanceService.getBalance(senderAccountId);
        Double receiverBalance = balanceService.getBalance(receiverAccountId);
        Assertions.assertEquals(100.0, senderBalance);
        Assertions.assertEquals(150.0, receiverBalance);
    }


    @Test
    void increaseBalanceTest() {
        Account account = new Account("1", "Test", 100.0);
        account.increaseBalance(50.0);
        Assertions.assertEquals(150.0, account.balance());
    }



    @Test
    void decreaseBalanceTest() {
        Account account = new Account("1", "Test", 200.0);
        Account account2 = new Account("2", "Test2", 50.0);
        account.decreaseBalance(50.0);
        account2.decreaseBalance(100.0);
        Assertions.assertEquals(150.0, account.balance());
        Assertions.assertEquals(50.0, account2.balance());

    }

    @Test
    void debitAccountNotFoundTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        balanceService.debit("non-existing-account", 100.0);

        // Capture the output for verification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        balanceService.setPrintStream(printStream);

        // Verify that the "Account not found" message is printed
        balanceService.debit("non-existing-account", 100.0);
        String expectedOutput = "Account not found";
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    void creditAccountNotFoundTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        balanceService.credit("non-existing-account", 100.0);

        // Capture the output for verification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        balanceService.setPrintStream(printStream);

        // Verify that the "Account not found" message is printed
        balanceService.credit("non-existing-account", 100.0);
        String expectedOutput = "Account not found";
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    void transferFromAccountNotFoundTest() {
        AccountRepository repository = new AccountRepository();
        BalanceService balanceService = new BalanceService(repository);
        String receiverAccountId = repository.createAccount("Siglo", 100.0);

        // Capture the output for verification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        balanceService.setPrintStream(printStream);

        // Verify that the "Account not found" message is printed
        balanceService.transfer("non-existing-account", receiverAccountId, 50.0);
        String expectedOutput = "Account not found";
        Assertions.assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    void noRegisteredAccountTest(){
        AccountRepository repository = new AccountRepository();
        Assertions.assertTrue(repository.noRegisteredAccount());
    }




}

