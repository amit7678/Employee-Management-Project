package com.employee.management.system.Repo;
import com.employee.management.system.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Employee, String> {
}
