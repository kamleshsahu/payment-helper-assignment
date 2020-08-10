package dev.kamlesh.paymenthelper.controllers;

import dev.kamlesh.paymenthelper.dto.ResponseDto;
import dev.kamlesh.paymenthelper.exceptions.PaySafeException;
import dev.kamlesh.paymenthelper.models.createtoken.SingleUseTokenResponse;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentRequest;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentResponse;
import dev.kamlesh.paymenthelper.services.PaySafeService;
import dev.kamlesh.paymenthelper.testutility.FakerUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.util.Assert.notNull;

class DataControllerTest {
    private static final String PAYSAFE_PAYMENT_ERROR = "error while processing payment";
    private static final String PAYSAFE_SINGLEUSETOKEN_ERROR = "error while generating single use token";
    private static final String ID = FakerUtility.getRandomString();
    private static final String SINGLE_USE_TOKEN = FakerUtility.getRandomString();
    private static final String PAYMENT_HANDLE_TOKEN = FakerUtility.getRandomString();

    @InjectMocks
    DataController dataController;

    @Mock
    private PaySafeService paySafeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getToken_WhenNoUserId_ThenReturnBADRequest() {
        ResponseDto<SingleUseTokenResponse> response = dataController.getToken(null);
        assertNull(response.getData());
        assertNotNull(response.getMessage());
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void getToken_WhenPaySafeFails_ThenReturnFailedDependecy() throws Exception {
        when(paySafeService.getToken(ID))
                .thenThrow(new PaySafeException(PAYSAFE_SINGLEUSETOKEN_ERROR));
        ResponseDto<SingleUseTokenResponse> response = dataController.getToken(ID);
        assertNull(response.getData());
        assertNotNull(response.getMessage());
        assertEquals(response.getStatus(), HttpStatus.FAILED_DEPENDENCY);
    }

    @Test
    void getToken_WhenUnknownExpeption_ThenInternalServerError() throws Exception {
        when(paySafeService.getToken(ID))
                .thenThrow(NullPointerException.class);
        ResponseDto<SingleUseTokenResponse> response = dataController.getToken(ID);
        assertNull(response.getData());
        assertEquals(response.getStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getToken_WhenUserIdPresent_ThenReturnSuccessfulResponse() throws Exception {
        when(paySafeService.getToken(ID))
                .thenReturn(SingleUseTokenResponse.builder().id(ID)
                        .singleUseCustomerToken(SINGLE_USE_TOKEN).build());
        ResponseDto<SingleUseTokenResponse> response = dataController.getToken(ID);
        verify(paySafeService, times(1)).getToken(any());
        notNull(response.getData());
        assertEquals(response.getData().getId(), ID);
        assertEquals(response.getData().getSingleUseCustomerToken(), SINGLE_USE_TOKEN);
    }

    @Test
    void processPayment_WhenUnknownException_ThenReturnServerError() throws Exception {
        when(paySafeService.processPayment(eq(ID), any(ProcessPaymentRequest.class)))
                .thenThrow(mock(NullPointerException.class));

        ResponseDto<ProcessPaymentResponse> response = dataController.processPayment(ID,
                ProcessPaymentRequest.builder().paymentHandleToken(PAYMENT_HANDLE_TOKEN).build());
        assertNull(response.getData());
        assertEquals(response.getStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void processPayment_WhenPaySafeFails_ThenReturnFailedDependency() throws Exception {
        when(paySafeService.processPayment(eq(ID), any(ProcessPaymentRequest.class)))
                .thenThrow(new PaySafeException(PAYSAFE_PAYMENT_ERROR));

        ResponseDto<ProcessPaymentResponse> response = dataController.processPayment(ID,
                ProcessPaymentRequest.builder().paymentHandleToken(PAYMENT_HANDLE_TOKEN).build());
        assertNull(response.getData());
        assertEquals(response.getStatus(), HttpStatus.FAILED_DEPENDENCY);
    }

    @Test
    void processPayment_WhenValidRequest_ThenReturnSuccessResponse() throws Exception {
        when(paySafeService.processPayment(eq(ID), any(ProcessPaymentRequest.class)))
                .thenReturn(mock(ProcessPaymentResponse.class));

        ResponseDto<ProcessPaymentResponse> response = dataController.processPayment(ID,
                ProcessPaymentRequest.builder().paymentHandleToken(PAYMENT_HANDLE_TOKEN).build());

        verify(paySafeService, times(1)).processPayment(any(), any());
        notNull(response.getData());
        assertEquals(response.getStatus(), HttpStatus.OK);
    }

}