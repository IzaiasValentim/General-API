package com.izaiasvalentim.general.Common.Config;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceAlreadyExistsException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Common.utils.StandardErrorModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class CustomsExceptionsHandler {

    private ResponseEntity<StandardErrorModel> generateStandardErrorModel(RuntimeException runtimeException,
                                                                          HttpServletRequest request,
                                                                          HttpStatus httpStatus) {
        StandardErrorModel generalErrorModel = new StandardErrorModel();
        generalErrorModel.setTimestamp(Instant.now());
        generalErrorModel.setMessage(runtimeException.getMessage());
        generalErrorModel.setStatus(httpStatus.value());
        generalErrorModel.setPath(request.getRequestURI());
        generalErrorModel.setError(httpStatus.getReasonPhrase());
        return new ResponseEntity<>(generalErrorModel, httpStatus);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardErrorModel> resourceAlreadyExists
            (ResourceAlreadyExistsException resourceAlreadyExistsException, HttpServletRequest request) {
        return generateStandardErrorModel(resourceAlreadyExistsException, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorModel> resourceNotFound
            (ResourceNotFoundException resourceNotFoundException, HttpServletRequest request) {
        return generateStandardErrorModel(resourceNotFoundException, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorInProcessServiceException.class)
    public ResponseEntity<StandardErrorModel> errorInProcessService
            (ErrorInProcessServiceException errorInProcessServiceException, HttpServletRequest request) {
        return generateStandardErrorModel(errorInProcessServiceException, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
