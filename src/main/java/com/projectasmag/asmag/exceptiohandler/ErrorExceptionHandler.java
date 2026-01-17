package com.projectasmag.asmag.exceptiohandler;

import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.exceptiohandler.exception.InvalidLoanOwnershipException;
import com.projectasmag.asmag.exceptiohandler.exception.MultipleDataNotFoundException;
import com.projectasmag.asmag.exceptiohandler.exception.MultipleLoanTargetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Map<String, Object> body = new HashMap<>();
        body.put("error", "404 DATA_NOT_FOUND");
        body.put("message", e.getMessage());
        body.put("id",  e.getId());

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(MultipleLoanTargetException.class)
    public ResponseEntity<?> handleMultipleLoanTargetException(MultipleLoanTargetException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, Object> body = new HashMap<>();
        body.put("error", "400 LOAN_HAS_MULTIPLE_TARGET");
        body.put("message", e.getMessage());
        body.put("asset target ID", e.getAssetTargetID());
        body.put("location target ID", e.getLocationTargetID());
        body.put("employee target ID", e.getEmployeTargetID());

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(MultipleDataNotFoundException.class)
    public ResponseEntity<?> handleMultipleDataNotFoundException(MultipleDataNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Map<String, Object> body = new HashMap<>();
        body.put("error", "404 DATA_NOT_FOUND");
        body.put("message", e.getMessage());
        body.put("ids", e.getIds());

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(InvalidLoanOwnershipException.class)
    public ResponseEntity<?> handleInvalidLoanOwnershipException(InvalidLoanOwnershipException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, Object> body = new HashMap<>();
        body.put("error", "400 INVALID_LOAN_OWNERSHIP");
        body.put("message", e.getMessage());
        body.put("loan ID", e.getLoanId());
        body.put("loan detail ID", e.getLoanDetailIds());

        return new ResponseEntity<>(body, httpStatus);
    }
}
