package com.employee.management.system.Service;
import com.employee.management.system.DTO.EmployeeDto;
import com.employee.management.system.Entity.Employee;
import com.employee.management.system.Exception.NotFoundException;
import com.employee.management.system.Repo.EmpRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {
   //iss loger ka ek hi instance bane iss pure class ke liye  isliye private staic final likha hai like singleton
    private static final Logger logger= LoggerFactory.getLogger(EmpServiceImpl.class);


    private EmpRepo empRepo;


    private ObjectMapper mapper;

    EmpServiceImpl(EmpRepo empRepo, ObjectMapper mapper) {
        this.empRepo = empRepo;
        this.mapper = mapper;
    }


    @Override
    public EmployeeDto registerEmployee(EmployeeDto dto) {

        // converting dto to entity
        Employee convertValue = mapper.convertValue(dto, Employee.class);
        Employee empEntity = empRepo.save(convertValue);

        // Again Converting entity into Dto
        return mapper.convertValue(empEntity, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> registerAllEmployee(List<EmployeeDto> list) {
        // converting dto to entity
        List<Employee> employeeslist = Arrays.asList(mapper.convertValue(list, Employee[].class));
        List<Employee> allEntity = empRepo.saveAll(employeeslist);
        // Again Converting entity into Dto
        return Arrays.asList(mapper.convertValue(allEntity, EmployeeDto[].class));

    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allemp = empRepo.findAll();
        List<EmployeeDto> employeeslist = Arrays.asList(mapper.convertValue(allemp, EmployeeDto[].class));
        logger.info("empl : " +employeeslist);
        return employeeslist;


    }

    @Override
    public EmployeeDto findEmployeeById(String id) {
        Employee empdbyid = empRepo.findById(id).orElseThrow(() -> new NotFoundException("employee id is not found " + id));
        return mapper.convertValue(empdbyid, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto dto, String id) {
        Optional<Employee> employee = empRepo.findById(id);
        Employee convertValue = mapper.convertValue(dto, Employee.class);
        convertValue.setId(id);
        Employee updateEnty = empRepo.save(convertValue);
        return mapper.convertValue(updateEnty, EmployeeDto.class);
    }

    @Override
    public String deleteEmployee(String id) {
        empRepo.deleteById(id);
        return "Record deleted successfully";
    }
}