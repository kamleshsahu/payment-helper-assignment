package dev.kamlesh.paymenthelper.repository;

public interface CustomerRepository {
    String getPaySafeCustomerId(String id);

    void addPaySafeCustomerId(String id, String paysafeCustomerId);
}
