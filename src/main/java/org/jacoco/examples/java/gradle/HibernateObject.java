package org.jacoco.examples.java.gradle;

import java.util.Date;
import java.util.HashMap;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.*;

/**
 *
 * @author Никита
 */

public class HibernateObject {

    @NotNull(message="Name is empty")
    @Length(min = 5, message="Name should be at least 5 symbols long")
    @Getter @Setter private String objectName;
    
    @NotNull(message="Version is empty")
    @Range(min = 0, max = 10, message = "Product version is not in bounds of [0; 10]")
    @Getter @Setter private Double objectVersion;
    
    @NotNull(message = "Date is empty")
    @Past(message = "Date is invalid")
    @Getter @Setter private Date objectCreationDate;
    
    @NotNull(message = "Url is empty")
    @Pattern(regexp = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$",
            message ="Wrong url format")
    @Getter @Setter private String objectUrl;
    
    @NotNull(message = "Object chars are empty")
    @Size(min= 4, message = "CharData shorter than 4 is invalid")
    @Getter @Setter private char [] objectCharData;
    
    @NotNull(message = "Object map is empty")
    @Size(max = 3, message = "Map is invalid")
    @Getter @Setter private HashMap objectMap;
    
    @NotNull(message = "State is empty")
    @AssertTrue(message = "Product is inactive")
    @Getter @Setter private Boolean objectState;

    @NotNull(message = "Object id is empty")
    @PositiveOrZero(message = "Object id has invalid value")
    @Getter @Setter private Integer objectId;
    
    @NotNull(message = "Object email is empty")
    @Email(message = "Email is invalid")
    @Getter @Setter private String objectEmail;
    
    @NotNull(message = "Product is empty")
    @Valid()
    @Getter @Setter private ObjectProduct objectProduct;
    
    public class ObjectProduct{
        
        @NotNull(message="Name is empty")
        @Length(min = 5, message="Name should be at least 5 symbols long")
        @Getter @Setter private String name;
        
        @NotNull(message="Version is empty")
        @Range(min = 0, max = 10, message = "Product version is not in bounds of [0; 10]")
        @Getter @Setter private double version;
    
        public ObjectProduct(){
            this.name = "default";
            this.version = 0.00;
        }
        
        public void setThisProduct(HibernateObject object){
            name = object.objectName;
            version = object.objectVersion;
        }
    }
    
}
