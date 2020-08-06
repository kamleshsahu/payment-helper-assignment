package dev.kamlesh.paymenthelper.services;

import dev.kamlesh.paymenthelper.exceptions.PaySafeException;
import dev.kamlesh.paymenthelper.models.createprofile.CreateProfileRequest;
import dev.kamlesh.paymenthelper.models.createprofile.CreateProfileResponse;
import dev.kamlesh.paymenthelper.models.createtoken.SingleUseTokenRequest;
import dev.kamlesh.paymenthelper.models.createtoken.SingleUseTokenResponse;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentRequest;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentResponse;
import dev.kamlesh.paymenthelper.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
@Log4j2
public class PaySafeServiceImpl {
    private final PaySafeService service;
    private final CustomerRepository repository;

    @Autowired
    public PaySafeServiceImpl(Retrofit retrofit, CustomerRepository customerRepository) {
        this.repository = customerRepository;
        this.service = retrofit.create(PaySafeService.class);
    }

    public ProcessPaymentResponse processPayment(String id, ProcessPaymentRequest body) throws IOException,PaySafeException {
        Response<ProcessPaymentResponse> paysaferesponse = service.processPayment(body).execute();
        if (paysaferesponse.isSuccessful()) {
            ProcessPaymentResponse response = paysaferesponse.body();
            return paysaferesponse.body();
        } else {
            log.error("error while processin payment",paysaferesponse.errorBody().string());
            throw new PaySafeException(paysaferesponse.errorBody().string());
        }
    }

    public SingleUseTokenResponse getToken(String id) throws IOException, PaySafeException {
        String customerId = repository.getPaySafeCustomerId(id);

        if (customerId == null) {
            customerId = createCustomerProfile(id);
            repository.addPaySafeCustomerId(id, customerId);
        }

        Response<SingleUseTokenResponse> response =
                service.getSingleUseToken(customerId, getRequestForSingleToken()).execute();
        if (response.isSuccessful()) {
            if (response.body().getMerchantRefNum() == null)
                response.body().merchantRefNum = UUID.randomUUID().toString();
            return response.body();
        } else {
            log.error("error while generating simgle use token",response.errorBody().string());
            throw new PaySafeException(response.errorBody().string());
        }
    }

    private SingleUseTokenRequest getRequestForSingleToken() {
        return SingleUseTokenRequest
                .builder()
                .paymentTypes(Collections.singletonList("CARD"))
                .build();
    }

    private String createCustomerProfile(String id) throws IOException, PaySafeException {
        CreateProfileRequest request = CreateProfileRequest
                .builder().merchantCustomerId(id).build();
        Response<CreateProfileResponse> response = service.createCustomerProfile(request).execute();
        if (response.isSuccessful()) {
            return response.body().getId();
        } else {
            log.error("error while creating customer profile");
            throw new PaySafeException(response.errorBody().string());
        }
    }
}
