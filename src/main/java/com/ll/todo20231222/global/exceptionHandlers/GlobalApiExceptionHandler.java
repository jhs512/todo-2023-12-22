package com.ll.todo20231222.global.exceptionHandlers;

import com.ll.todo20231222.global.exceptions.GlobalException;
import com.ll.todo20231222.global.rq.Rq;
import com.ll.todo20231222.global.rsData.RsData;
import com.ll.todo20231222.standard.base.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class GlobalApiExceptionHandler {
    private final Rq rq;

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus
    public ResponseEntity<RsData<Empty>> handle(GlobalException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getRsData().getStatusCode());
        rq.setStatusCode(ex.getRsData().getStatusCode());

        return new ResponseEntity<>(ex.getRsData(), status);
    }
}
