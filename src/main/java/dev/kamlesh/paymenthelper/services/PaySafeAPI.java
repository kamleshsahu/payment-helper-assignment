package dev.kamlesh.paymenthelper.services;

import dev.kamlesh.paymenthelper.models.createprofile.CreateProfileRequest;
import dev.kamlesh.paymenthelper.models.createprofile.CreateProfileResponse;
import dev.kamlesh.paymenthelper.models.createtoken.SingleUseTokenRequest;
import dev.kamlesh.paymenthelper.models.createtoken.SingleUseTokenResponse;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentRequest;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaySafeAPI {
    @POST("paymenthub/v1/customers/{CustomerId}/singleusecustomertokens")
    Call<SingleUseTokenResponse> getSingleUseToken(@Path("CustomerId") String customerId, @Body SingleUseTokenRequest body);

    @POST("paymenthub/v1/payments")
    Call<ProcessPaymentResponse> processPayment(@Body ProcessPaymentRequest body);

    @POST("paymenthub/v1/customers")
    Call<CreateProfileResponse> createCustomerProfile(@Body CreateProfileRequest body);
}
