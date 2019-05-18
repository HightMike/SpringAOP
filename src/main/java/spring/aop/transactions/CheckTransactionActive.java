package spring.aop.transactions;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class CheckTransactionActive {
    public void checkTransactionActive() {
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
    }
}
