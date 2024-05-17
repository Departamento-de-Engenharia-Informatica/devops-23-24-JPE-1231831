package com.gregturnquist;

import com.greglturnquist.payroll.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    public void testEmployee() {
        // Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 10;
        String email = "frodo.baggins@shire.com";

        // Act
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);

        // Assert

        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(description, employee.getDescription());
        assertEquals(jobYears, employee.getJobYears());
        assertEquals(email, employee.getEmail());
    }

    @Test
    public void testEquals() {
        // Arrange
        Employee employee1 = new Employee("Frodo", "Baggins", "ring bearer", 10, "frodo.baggins@shire.com");
        Employee employee2 = new Employee("Frodo", "Baggins", "ring bearer", 10, "frodo.baggins@shire.com");
        Employee employee3 = new Employee("Bilbo", "Baggins", "ring finder", 50, "bilbo.baggins@shire.com");

        // Assert
        assertEquals(employee1, employee2);
        assertNotEquals(employee1, employee3);
    }

    @Test
    public void testHashCode() {
        // Arrange
        Employee employee1 = new Employee("Frodo", "Baggins", "ring bearer", 10, "frodo.baggins@shire.com");
        Employee employee2 = new Employee("Frodo", "Baggins", "ring bearer", 10, "frodo.baggins@shire.com");
        Employee employee3 = new Employee("Bilbo", "Baggins", "ring finder", 50, "bilbo.baggins@shire.com");

        // Assert
        assertEquals(employee1.hashCode(), employee2.hashCode());
        assertNotEquals(employee1.hashCode(), employee3.hashCode());
    }

    @Test
    public void testToString() {
        // Arrange
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 10, "frodo.baggins@shire.com");

        // Assert
        assertEquals("Employee{id=null, firstName='Frodo', lastName='Baggins', description='ring bearer', email='frodo.baggins@shire.com', jobYears='10'}", employee.toString());
    }

    @Test
    public void testIsValidEmail() {
        // Arrange
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 10, "frodo.baggins@shire.com");

        // Assert
        assertTrue(employee.isValidEmail("test.email+alex@leetcode.com"));

    }
}