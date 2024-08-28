package com.employee.management.system.Service;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.employee.management.system.DTO.EmployeeDto;
import com.employee.management.system.Entity.Employee;
import com.employee.management.system.Exception.NotFoundException;
import com.employee.management.system.Repo.EmpRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@ContextConfiguration(classes = {EmpServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EmpServiceImplTest {
    @MockBean
    private EmpRepo empRepo;

    @Autowired
    private EmpServiceImpl empServiceImpl;

    @MockBean
    private ObjectMapper objectMapper;


    @Test
    void testRegisterEmployee() throws IllegalArgumentException {
        // Arrange
        when(objectMapper.convertValue(Mockito.<Object>any(), Mockito.<Class<Object>>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(NotFoundException.class, () -> empServiceImpl
                .registerEmployee(new EmployeeDto("42", "Jane", "Doe", "Emp Age", "jane.doe@example.org", "6625550144")));
        verify(objectMapper).convertValue(isA(Object.class), isA(Class.class));
    }


    @Test
    void testRegisterEmployee2() throws IllegalArgumentException {
        // Arrange
        Employee employee = mock(Employee.class);
        doNothing().when(employee).setEmpAge(Mockito.<String>any());
        doNothing().when(employee).setEmpEmail(Mockito.<String>any());
        doNothing().when(employee).setEmpPhoneNumber(Mockito.<String>any());
        doNothing().when(employee).setFirstName(Mockito.<String>any());
        doNothing().when(employee).setId(Mockito.<String>any());
        doNothing().when(employee).setLastName(Mockito.<String>any());
        employee.setEmpAge("Emp Age");
        employee.setEmpEmail("jane.doe@example.org");
        employee.setEmpPhoneNumber("6625550144");
        employee.setFirstName("Jane");
        employee.setId("42");
        employee.setLastName("Doe");
        when(empRepo.save(Mockito.<Employee>any())).thenReturn(employee);
        when(objectMapper.convertValue(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(null);


        EmployeeDto actualRegisterEmployeeResult = empServiceImpl
                .registerEmployee(new EmployeeDto("42", "Jane", "Doe", "Emp Age", "jane.doe@example.org", "6625550144"));

        // Assert
        verify(employee).setEmpAge(eq("Emp Age"));
        verify(employee).setEmpEmail(eq("jane.doe@example.org"));
        verify(employee).setEmpPhoneNumber(eq("6625550144"));
        verify(employee).setFirstName(eq("Jane"));
        verify(employee).setId(eq("42"));
        verify(employee).setLastName(eq("Doe"));
        verify(objectMapper, atLeast(1)).convertValue(Mockito.<Object>any(), Mockito.<Class<Employee>>any());
        verify(empRepo).save(isNull());
        assertNull(actualRegisterEmployeeResult);
    }
    @Test
    void testGetAllEmployees() throws IllegalArgumentException {

        when(empRepo.findAll()).thenReturn(new ArrayList<>());
        when(objectMapper.convertValue(Mockito.<Object>any(), Mockito.<Class<Object>>any()))
                .thenThrow(new NotFoundException("An error occurred"));


        assertThrows(NotFoundException.class, () -> empServiceImpl.getAllEmployees());
        verify(objectMapper).convertValue(isA(Object.class), isA(Class.class));
        verify(empRepo).findAll();
    }
}
