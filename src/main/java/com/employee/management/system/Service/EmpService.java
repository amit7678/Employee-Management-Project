package com.employee.management.system.Service;
import com.employee.management.system.DTO.EmployeeDto;
import java.util.*;
public interface EmpService {
    public EmployeeDto registerEmployee(EmployeeDto dto);

    public List<EmployeeDto> registerAllEmployee(List<EmployeeDto> list);

    public List<EmployeeDto>getAllEmployees();

    public EmployeeDto findEmployeeById(String id);

    public EmployeeDto updateEmployee(EmployeeDto dto,String id);

    public String deleteEmployee(String id);
}
