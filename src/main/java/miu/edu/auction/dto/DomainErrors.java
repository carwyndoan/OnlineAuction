package miu.edu.auction.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DomainErrors {

    private String errorType;
    private String message;

    public List<DomainError> getErrors() {
        return errors;
    }

    public void setErrors(List<DomainError> errors) {
        this.errors = errors;
    }

    private List<DomainError> errors = new ArrayList<DomainError>();

     public void addError(DomainError error) {
         errors.add(error);
    }

 }
	
 
