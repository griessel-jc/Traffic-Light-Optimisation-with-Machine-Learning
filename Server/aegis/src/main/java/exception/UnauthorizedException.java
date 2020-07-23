package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{
    private String exceptionDetail;
    private Object fieldValue;
    
    public UnauthorizedException(String exceptionDetail, String fieldValue){
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
