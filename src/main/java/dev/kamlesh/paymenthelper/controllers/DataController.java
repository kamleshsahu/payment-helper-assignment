package dev.kamlesh.paymenthelper.controllers;

import dev.kamlesh.paymenthelper.dto.ResponseDto;
import dev.kamlesh.paymenthelper.exceptions.NullIDException;
import dev.kamlesh.paymenthelper.exceptions.PaySafeException;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentRequest;
import dev.kamlesh.paymenthelper.services.PaySafeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
@Validated
public class DataController {
    private static final String NULL_ID_MESSAGE = "email Id cant be null or empty";
    private PaySafeService service;

    @Autowired
    public DataController(PaySafeService service) {
        this.service = service;
    }

    @Validated
    @GetMapping("/token")
    public ResponseDto getToken(@RequestParam(required = true) String id) {
        try {
            if (StringUtils.isEmpty(id))
                throw new NullIDException(NULL_ID_MESSAGE);
            return ResponseDto.builder().status(HttpStatus.OK).data(service.getToken(id)).build();
        } catch (NullIDException e) {
            return ResponseDto.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        } catch (PaySafeException | IOException e) {
            return ResponseDto.builder().status(HttpStatus.FAILED_DEPENDENCY).message(e.getMessage()).build();
        } catch (Exception e) {
            return ResponseDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        }
    }

    @PostMapping("/processPayment")
    public ResponseDto processPayment(@RequestParam String id, @RequestBody ProcessPaymentRequest body) {
        try {
            return ResponseDto.builder().status(HttpStatus.OK).data(service.processPayment(id, body)).build();
        } catch (PaySafeException | IOException e) {
            return ResponseDto.builder().status(HttpStatus.FAILED_DEPENDENCY).message(e.getMessage()).build();
        } catch (Exception e) {
            return ResponseDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        }
    }
}
