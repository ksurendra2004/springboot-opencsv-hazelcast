package com.opencsvdemo.repository;

import com.opencsvdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenCsvRepository extends JpaRepository<Employee, Long> {}

