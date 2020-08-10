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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
@Log4j2
public class PaySafeService {
    private static final String PAYSAFE_PAYMENT_ERROR = "error while processing payment";
    private static final String PAYSAFE_CREATEPROFILE_ERROR = "error while creating customer profile";
    private static final String PAYSAFE_SINGLEUSETOKEN_ERROR = "error while generating single use token";

    private final PaySafeAPI service;
    private final CustomerRepository repository;

    @Autowired
    public PaySafeService(PaySafeAPI service, CustomerRepository customerRepository) {
        this.repository = customerRepository;
        this.service = service;
    }

    public ProcessPaymentResponse processPayment(String id, ProcessPaymentRequest body) throws IOException, PaySafeException {
        Response<ProcessPaymentResponse> paysaferesponse = service.processPayment(body).execute();
        if (paysaferesponse.isSuccessful()) {
            return paysaferesponse.body();
        } else {
            throw handleError(PAYSAFE_PAYMENT_ERROR, paysaferesponse.message(), paysaferesponse.errorBody().string());
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
            setMerchantRefIfNull(response.body());
            return response.body();
        } else {
            throw handleError(PAYSAFE_SINGLEUSETOKEN_ERROR, response.message(), response.errorBody().string());
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
            throw handleError(PAYSAFE_CREATEPROFILE_ERROR, response.message(), response.errorBody().string());
        }
    }

    private void setMerchantRefIfNull(SingleUseTokenResponse response) {
        if (StringUtils.isEmpty(response.getMerchantRefNum()))
            response.setMerchantRefNum(UUID.randomUUID().toString());
    }

    private PaySafeException handleError(String desc, String message, String errorbody) {
        String errorMessage = String.format("%s: %s %s", desc, message, errorbody);
        log.error(errorMessage);
        return new PaySafeException(errorMessage);
    }
}
