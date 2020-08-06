package dev.kamlesh.paymenthelper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ResponseDto<T> {
    private HttpStatus status;
    private String message;
    private T data;
}
