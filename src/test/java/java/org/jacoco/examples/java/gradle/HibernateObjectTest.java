/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.jacoco.examples.java.gradle.HibernateObject.ObjectProduct;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Никита
 */
public class HibernateObjectTest {
    
   private static Validator validator;
   private static HibernateObject hibernateObject;
   @BeforeClass
   public static void setUp(){
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
        hibernateObject = new HibernateObject();
        hibernateObject.setObjectName("abcde");
        hibernateObject.setObjectVersion(1.11);
        hibernateObject.setObjectId(1);
        hibernateObject.setObjectCharData(new char[]{'a','b','c','d'});
        hibernateObject.setObjectState(true);
        hibernateObject.setObjectCreationDate(new GregorianCalendar(2016, Calendar.FEBRUARY, 1).getTime());
        hibernateObject.setObjectUrl("https://stackoverflow.com");
        hibernateObject.setObjectMap(new HashMap<>());
        hibernateObject.getObjectMap().put("test1", 1);
        hibernateObject.getObjectMap().put("test2", 2);
        hibernateObject.setObjectEmail("test@gmail.com");
        hibernateObject.setObjectProduct(hibernateObject.new ObjectProduct());
   }
   
    /**
     * Test of validation for null values.
     */
    @Test
   public void testNullValidation() {

      HibernateObject instance = hibernateObject;
      instance.setObjectName(null);
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertNull(instance.getObjectName());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Name is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectName("abcde");
      instance.setObjectCharData(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectCharData());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Object chars are empty",constraintViolations.iterator().next().getMessage());

      instance = hibernateObject;
      instance.setObjectCharData(new char[]{'a','b','c','d'});
      instance.setObjectCreationDate(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectCreationDate());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Date is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectCreationDate(new GregorianCalendar(2016, Calendar.FEBRUARY, 1).getTime());
      instance.setObjectUrl(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectUrl());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Url is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectUrl("https://stackoverflow.com");
      instance.setObjectEmail(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectEmail());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Object email is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectEmail("test@gmail.com");
      instance.setObjectId(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectId());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Object id is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectId(1);
      instance.setObjectMap(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectMap());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Object map is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectMap(new HashMap<>());
      instance.setObjectProduct(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectProduct());      
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Product is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectProduct(hibernateObject.new ObjectProduct());
      instance.setObjectState(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectState()); 
      assertEquals( 1, constraintViolations.size() );
      assertEquals("State is empty",constraintViolations.iterator().next().getMessage());
      
      instance = hibernateObject;
      instance.setObjectState(Boolean.TRUE);
      instance.setObjectVersion(null);
      constraintViolations = validator.validate( instance );
      assertNull( instance.getObjectVersion());
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Version is empty",constraintViolations.iterator().next().getMessage());
      instance.setObjectVersion(1.11);
   }
     /**
     * Test of validation for length of properties.
     */
   @Test
   public void testLengthValidation(){
      HibernateObject instance = hibernateObject;
      instance.setObjectName("abcd");
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Name should be at least 5 symbols long",constraintViolations.iterator().next().getMessage());
      instance.setObjectName("abcde");
   }
     /**
     * Test of validation for range of values of properties.
     */
   @Test
   public void testRangeValidation(){
      HibernateObject instance = hibernateObject;
      instance.setObjectVersion(11.1);
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Product version is not in bounds of [0; 10]",constraintViolations.iterator().next().getMessage());
      instance.setObjectVersion(1.11);
   }   
   /**
     * Test of validation for past dates of properties.
     */
   @Test
   public void testPastValidation(){
      HibernateObject instance = hibernateObject;
      instance.setObjectCreationDate(new GregorianCalendar(2018, Calendar.FEBRUARY, 1).getTime());
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Date is invalid",constraintViolations.iterator().next().getMessage());
      instance.setObjectCreationDate(new GregorianCalendar(2016, Calendar.FEBRUARY, 1).getTime());
   }   
   /**
     * Test of validation for pattern of values of properties.
     */
   @Test
   public void testPatternValidation(){
      HibernateObject instance = hibernateObject;
      instance.setObjectUrl("crack");
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Wrong url format",constraintViolations.iterator().next().getMessage());
      instance.setObjectUrl("https://stackoverflow.com");
   }  
   /**
     * Test of validation for size of properties.
     */
   @Test
   public void testSizeValidation(){
      HibernateObject instance = hibernateObject;
      instance.getObjectMap().put("test1", 1);
      instance.getObjectMap().put("test2", 2);
      instance.getObjectMap().put("test3", 3);
      instance.getObjectMap().put("test4", 4);
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Map is invalid",constraintViolations.iterator().next().getMessage());
        instance.setObjectMap(new HashMap<>());
        instance.getObjectMap().put("test1", 1);
        instance.getObjectMap().put("test2", 2);
   
      instance.setObjectCharData(new char[]{'a','b','c'});
      constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("CharData shorter than 4 is invalid",constraintViolations.iterator().next().getMessage());
      instance.setObjectCharData(new char[]{'a','b','c','d'});
   } 
   /**
     * Test of validation for true state of properties.
     */
    @Test
    public void testAssertTrueValidation(){
      HibernateObject instance = hibernateObject;
      instance.setObjectState(false);
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Product is inactive",constraintViolations.iterator().next().getMessage());
      instance.setObjectState(true);
   }
    /**
     * Test of validation for positive and zero values of properties.
     */
    @Test
    public void testPositiveOrZeroValidation(){
      HibernateObject instance = hibernateObject;
      instance.setObjectId(-1);
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Object id has invalid value",constraintViolations.iterator().next().getMessage());
      instance.setObjectId(1);
   }  
    /**
     * Test of validation for email properties.
     */
    @Test
    public void testEmailValidation(){
      HibernateObject instance = hibernateObject;
      instance.setObjectEmail("email");
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Email is invalid",constraintViolations.iterator().next().getMessage());
      instance.setObjectEmail("test@gmail.com");
   }
    /**
     * Test of validation for object properties.
     */
    @Test
    public void testValidValidation(){
      HibernateObject instance = hibernateObject;
      instance.getObjectProduct().setName("abcd");
      Set<ConstraintViolation<HibernateObject>> constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Name should be at least 5 symbols long",constraintViolations.iterator().next().getMessage());
      instance.getObjectProduct().setName("abcde");
      
      instance.getObjectProduct().setVersion(11.1);
      constraintViolations = validator.validate( instance );
      assertEquals( 1, constraintViolations.size() );
      assertEquals("Product version is not in bounds of [0; 10]",constraintViolations.iterator().next().getMessage());
      instance.getObjectProduct().setVersion(1.11);
   }    
    /**
     * Test of setting a product object method.
     */
    @Test
    public void testSetThisProduct(){
    HibernateObject instance = hibernateObject;
    instance.getObjectProduct().setThisProduct(hibernateObject);
        assertEquals("Names don't equal", hibernateObject.getObjectName(), instance.getObjectProduct().getName());
        assertEquals("Versions don't equal", hibernateObject.getObjectVersion(), instance.getObjectProduct().getVersion(),0);
    }
    
}
