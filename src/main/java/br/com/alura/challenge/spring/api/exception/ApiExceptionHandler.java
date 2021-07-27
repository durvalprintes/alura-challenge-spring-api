package br.com.alura.challenge.spring.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.alura.challenge.spring.api.util.Util;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

        @Autowired
        private MessageSource messsage;

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
                return new ResponseEntity<>(
                                new ErrorBuilder(HttpStatus.NOT_FOUND.value())
                                                .setPath(((ServletWebRequest) request).getRequest().getRequestURI())
                                                .setError("ResourceNotFoundException")
                                                .setMessage(getBundleMessage(ex.getMessage())).build(),
                                HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
                return new ResponseEntity<>(new ErrorBuilder(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                .setPath(((ServletWebRequest) request).getRequest().getRequestURI())
                                .setError("BusinessException").setMessage(getBundleMessage(ex.getMessage())).build(),
                                HttpStatus.UNPROCESSABLE_ENTITY);
        }

        @Override
        public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {
                List<ErrorDto.FieldError> fields = ex.getBindingResult().getAllErrors().stream()
                                .map(error -> new ErrorDto.FieldError(((FieldError) error).getField(),
                                                getBundleMessage(messsage.getMessage(error,
                                                                LocaleContextHolder.getLocale()))))
                                .collect(Collectors.toList());
                return ResponseEntity.badRequest()
                                .body(new ErrorBuilder(status.value())
                                                .setPath(((ServletWebRequest) request).getRequest().getRequestURI())
                                                .setError("MethodArgumentNotValidException").setFields(fields)
                                                .setMessage("Falha na validação do(s) campo(s)").build());
        }

        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                        HttpHeaders headers, HttpStatus status, WebRequest request) {
                return ResponseEntity.badRequest()
                                .body(new ErrorBuilder(status.value())
                                                .setPath(((ServletWebRequest) request).getRequest().getRequestURI())
                                                .setError("HttpMessageNotReadableException")
                                                .setMessage(getBundleMessage("error.invalid.json")).build());
        }

        private static String getBundleMessage(String code) {
                return Util.getText(code);
        }

}
