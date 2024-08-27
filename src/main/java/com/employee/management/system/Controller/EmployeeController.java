package com.employee.management.system.Controller;
import com.employee.management.system.DTO.EmployeeDto;
import com.employee.management.system.Service.EmpService;
import com.employee.management.system.Utility.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private EmpService empService;          //Here I haven't used autowired annotation instead of this used constructor injection.

    public EmployeeController(EmpService empService) {
        this.empService = empService;
    }


    @PostMapping(value = "/register")
    //Request ko get karke jaise hi iss particular bean me set kiya jayega   usse pahle validation check kiya jayega
    public ResponseEntity<Object> registerEmployee(@Valid @RequestBody EmployeeDto dto) {
        logger.info("Entering registerEmployee method with data: {}", dto);

            EmployeeDto employeeAdd = empService.registerEmployee(dto);
            if (employeeAdd != null) {
                logger.info("Employee registered successfully with ID: {}", employeeAdd.getId());
                return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "success", employeeAdd);
            } else {
                logger.error("Failed to register employee");
                return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY, false, "something went wrong", employeeAdd);
            }


    }

    @PostMapping(value = "/registerAll")
    public ResponseEntity<Object> registerAllEmployee(@RequestBody List<EmployeeDto> dto) {
        logger.info("Entering registerAllEmployee method with data: {}", dto);

        List<EmployeeDto> employeeAddAll = empService.registerAllEmployee(dto);
        if (employeeAddAll != null && !employeeAddAll.isEmpty()) {
            logger.info("All employees registered successfully");
            // If employees list is not empty, return success response
            return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "Employees all record registered successfully", employeeAddAll);
        } else {
            logger.error("Employee list is empty or null");
            // If employees list is empty or null, return error response
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Employee list is empty or null", null);
        }
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> getAllEmployees() {
        logger.info("Entering getAllEmployees method");
        List<EmployeeDto> list = empService.getAllEmployees();

        if (list != null && !list.isEmpty()) {
            logger.info("Retrieved all employee records successfully");
            return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "success", list);
        } else {
            logger.warn("No employee record exists in database");
            return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY, false, "No employee record exist in database", null);
        }

    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDto dto, @PathVariable String id) {
        logger.info("Entering updateEmployee method for ID: {}", id);
        EmployeeDto updateEmp = empService.updateEmployee(dto, id);
        logger.info("Employee with ID: {} updated successfully", id);
        return ResponseEntity.ok(updateEmp);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        logger.info("Entering deleteEmployee method for ID: {}", id);
        String result = empService.deleteEmployee(id);
        logger.info("Employee with ID: {} deleted successfully", id);
        return result;

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findEmployeeById(@PathVariable String id) {
        logger.info("Entering findEmployeeById method for ID: {}", id);
        EmployeeDto empdetail = empService.findEmployeeById(id);
        if (empdetail != null) {
            logger.info("Employee with ID: {} found", id);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Employee record found", empdetail);
        } else {
            logger.error("Employee with ID: {} not found", id);
            return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY, false, "Record not exist", empdetail);
        }
    }
}