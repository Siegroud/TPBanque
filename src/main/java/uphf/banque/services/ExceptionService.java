package uphf.banque.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uphf.banque.exceptions.NotValidException;
import uphf.banque.exceptions.ProcessException;

public class ExceptionService {

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<String> handleNotValidException(NotValidException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage().toString());
    }

    @ExceptionHandler(ProcessException.class)
    public ResponseEntity<String> handleProcessException(ProcessException e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage().toString());
    }
}
