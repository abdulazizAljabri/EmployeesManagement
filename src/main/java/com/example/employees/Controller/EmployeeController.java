package com.example.employees.Controller;

import com.example.employees.ApiResponse.ApiResponse;
import com.example.employees.Model.Employees;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employees/")
public class EmployeeController {

    ArrayList<Employees> employeesList = new ArrayList<>();

    @GetMapping("getEmployees")
    public ArrayList<Employees> getEmployees() {
        return employeesList;
    }

    @PostMapping("addEmployees")
    public ResponseEntity addEmployees(@RequestBody @Valid Employees employees, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        employeesList.add(employees);
        return ResponseEntity.status(200).body(new ApiResponse("addEmployee successfully"));
    }


    @PutMapping("updateEmployees/{index}")
    public ResponseEntity updateEmployees(@PathVariable int index, @RequestBody Employees employees, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        employeesList.set(index, employees);
        return ResponseEntity.status(200).body(new ApiResponse("updateEmployee successfully"));
    }

    @DeleteMapping("deleteEmployees/{index}")
    public ResponseEntity deleteEmployees(@PathVariable int index) {
        employeesList.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("deleteEmployee successfully"));
    }

    @PutMapping("vacation/{index}/{day}")
    public ResponseEntity vacationEmployee(@PathVariable int index,@PathVariable int day, @RequestBody Employees employees) {

            if(employees.getAnnualLeave() < day){
                return ResponseEntity.status(400).body(new ApiResponse("You don't have AnnualLeave"));
            }
            employees.setOnLeave(true);
           int newAnnaulLeave =  employees.getAnnualLeave() - day;
           employees.setAnnualLeave(newAnnaulLeave);

        return ResponseEntity.status(200).body(new ApiResponse("You take "+day + " day Of "));
    }



}
