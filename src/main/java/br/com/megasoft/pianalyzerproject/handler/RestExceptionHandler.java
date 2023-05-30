package br.com.megasoft.pianalyzerproject.handler;

import br.com.megasoft.pianalyzerproject.constant.ErrorMessage;
import br.com.megasoft.pianalyzerproject.exception.ExceptionDetails;
import br.com.megasoft.pianalyzerproject.exception.FileReadFailedException;
import br.com.megasoft.pianalyzerproject.exception.InvalidFileException;
import br.com.megasoft.pianalyzerproject.exception.UploadFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UploadFailedException.class)
    public ResponseEntity<Object> UploadFailedExceptionHandler(UploadFailedException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.FILE_UPLOAD_FAILED, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileReadFailedException.class)
    public ResponseEntity<Object> FileReadFailedExceptionHandler(FileReadFailedException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.FILE_READ_FAILED, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Object> InvalidFileExceptionHandler(InvalidFileException exception) {
        return new ResponseEntity(new ExceptionDetails(ErrorMessage.INVALID_FILE, HttpStatus.BAD_REQUEST.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
