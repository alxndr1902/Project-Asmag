package com.projectasmag.asmag.exceptiohandler;

import com.projectasmag.asmag.dto.ErrorResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResponseDTO<>(errorMessage), httpStatus);
    }

    @ExceptionHandler(MultipleLoanTargetException.class)
    public ResponseEntity<?> handleMultipleLoanTargetException(MultipleLoanTargetException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResponseDTO<>(errorMessage), httpStatus);
    }

    @ExceptionHandler(InvalidLoanOwnershipException.class)
    public ResponseEntity<?> handleInvalidLoanOwnershipException(InvalidLoanOwnershipException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResponseDTO<>(errorMessage), httpStatus);
    }

    @ExceptionHandler(DataIntegrationException.class)
    public ResponseEntity<?> handleVersionNotMatchException(DataIntegrationException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        var message = e.getMessage();

        return new ResponseEntity<>(new ErrorResponseDTO<>(message), httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getAllErrors().stream()
                .map((ObjectError oe) -> oe.getDefaultMessage()).toList();
        return new ResponseEntity<>(new ErrorResponseDTO<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UUIDNotValidException.class)
    public ResponseEntity<?> handleUUIDNotValidException(UUIDNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
         var errorMessage = e.getMessage();

         return new ResponseEntity<>(new ErrorResponseDTO<>(errorMessage),  httpStatus);
    }

    @ExceptionHandler(DataIsNotUniqueException.class)
    public ResponseEntity<?> handleDataIsNotUniqueException(UUIDNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getMessage();

        return new ResponseEntity<>(new ErrorResponseDTO<>(errorMessage),  httpStatus);
    }
}
