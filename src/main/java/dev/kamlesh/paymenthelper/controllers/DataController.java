package dev.kamlesh.paymenthelper.controllers;

import dev.kamlesh.paymenthelper.dto.ResponseDto;
import dev.kamlesh.paymenthelper.exceptions.PaySafeException;
import dev.kamlesh.paymenthelper.models.processpayment.ProcessPaymentRequest;
import dev.kamlesh.paymenthelper.services.PaySafeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class DataController {
    private PaySafeServiceImpl service;

    @Autowired
    public DataController(PaySafeServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/token")
    public ResponseDto getToken(@RequestParam(required = true) String id) {
        try {
            return ResponseDto.builder().status(HttpStatus.OK).data(service.getToken(id)).build();
        }catch (PaySafeException e) {
            return ResponseDto.builder().status(HttpStatus.OK).message(e.getMessage()).build();
        }catch (IOException e){
            return ResponseDto.builder().status(HttpStatus.FORBIDDEN).message(e.getMessage()).build();
        }
    }

    @PostMapping("/processPayment")
    public ResponseDto processPayment(@RequestParam String id, @RequestBody ProcessPaymentRequest body){
       try {
           return ResponseDto.builder().status(HttpStatus.OK).data(service.processPayment(id, body)).build();
       }catch (PaySafeException e){
           return ResponseDto.builder().status(HttpStatus.OK).message(e.getMessage()).build();
       }catch (IOException e){
           return ResponseDto.builder().status(HttpStatus.FORBIDDEN).message(e.getMessage()).build();
       }
    }
}
