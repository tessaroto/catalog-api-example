package br.com.ifood.shop.catalog.web;


import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ifood.shop.catalog.error.DataNotFoundException;



//@RestControllerAdvice
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler  {
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all fields errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        
        headers.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(body, headers, status);

    }
    
//    @ExceptionHandler(value = {
//    		InsufficientBalanceException.class,
//    		AccountLockedException.class
//    })
//    public void springHandleBadRequest(HttpServletResponse response) throws IOException {
//    	response.addHeader("Content-Type", "application/json;charset=UTF-8");
//        response.sendError(HttpStatus.BAD_REQUEST.value());
//    }

    @ExceptionHandler(value = {
    		DataNotFoundException.class
    })
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
    	response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
    
    
//    private static final String PATH = "/error";
////
////    @Value("${debug}")
////    private boolean debug;
//
////    @Autowired
////    private ErrorAttributes errorAttributes;
//
//    @RequestMapping(value = PATH)
//    ResponseEntity<Object> error(HttpServletRequest request, HttpServletResponse response) {
//        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring. 
//        // Here we just define response body.
//    	//return new Error(response.getStatus(), getErrorAttributes(request, debug));
//    	System.out.println("ERRRRRRRRRRRR");
//    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    public String getErrorPath() {
//        return PATH;
//    }
//
////    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
////    	WebRequest webRequest = new ServletWebRequest(request);
////        return errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
////    }
////    
    
}

