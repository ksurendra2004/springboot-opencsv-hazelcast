package com.opencsvdemo.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "emp_details")
public class Employee {

    @CsvBindByName(column = "Employee Id")
    @Id
    @Column(name = "emp_id")
    private Long empId;

    @CsvBindByName(column = "Employee Name")
    @Column(name = "emp_name")
    private String empName;

    @CsvBindByName(column = "Employee Mail Id")
    @Column(name = "emp_mail_id")
    private String empMailId;

    @CsvBindByName(column = "Employee SkillSet")
    @Column(name = "emp_skill_set")
    private String empSkillSet;
}
