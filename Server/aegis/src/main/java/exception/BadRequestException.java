package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private String exceptionDetail;
    private Object fieldValue;
    
    public BadRequestException(String exceptionDetail, String fieldValue){
        super(exceptionDetail+" - "+fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }
    
    public String getExceptionDetail(){
        return exceptionDetail;
    }

    public Object getFieldValue(){
        return fieldValue;
    }
}
