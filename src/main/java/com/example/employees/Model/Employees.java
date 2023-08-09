package com.example.employees.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employees {

    @NotEmpty(message = "Cannot be empty")
    @Size(min = 4 ,message = "Should be Length more then 4 ")
    private String name;

    @NotEmpty(message = "Cannot be null")
    @Size(min = 2 ,message = "Should be more then 2 ")
    private String id;


      @Positive
      @Min(value =25 , message ="age is Under 25" )
    @NotNull(message = "Cannot be null")
    private int age;

    @NotEmpty(message = "Cannot be null")
    @Pattern(regexp = "(supervisor|coordinator)",message = "must be supervisor or coordinator only")
    private String position;

       @AssertFalse
      private boolean onLeave ;

       @Min(value =1990)
       @Max(value =2023)
    private int employementYear;

    @NotNull(message = "Cannot be null")
    private int annualLeave;

}
