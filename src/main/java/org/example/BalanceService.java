package org.example;
import java.io.PrintStream;
public class BalanceService {
    private AccountRepository accountRepository;
    private PrintStream printStream;

    public BalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.printStream = System.out; // Default to standard output
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public Double getBalance(String accountId) {
        Account account = accountRepository.getAccount(accountId);
        if (account != null) {
            return account.balance();
        } else {
            return null;
        }
    }

    public void credit(String accountId, Double amount) {
        Account account = accountRepository.getAccount(accountId);
        if (account != null) {
            account.increaseBalance(amount);
        } else {
            printStream.println("Account not found");
        }
    }

    public void debit(String accountId, Double amount) {
        Account account = accountRepository.getAccount(accountId);
        if (account != null) {
            account.decreaseBalance(amount);
        } else {
            printStream.println("Account not found");
        }
    }

    public void transfer(String senderAccountId, String receiverAccountId, Double amount) {
        Account senderAccount = accountRepository.getAccount(senderAccountId);
        Account receiverAccount = accountRepository.getAccount(receiverAccountId);
        if (senderAccount != null && receiverAccount != null) {
            senderAccount.decreaseBalance(amount);
            receiverAccount.increaseBalance(amount);
        } else {
            printStream.println("Account not found");
        }
    }
}
