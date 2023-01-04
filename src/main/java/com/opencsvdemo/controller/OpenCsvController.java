package com.opencsvdemo.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsvdemo.model.Employee;
import com.opencsvdemo.service.OpenCsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@RestController
public class OpenCsvController {
    @Autowired
    private OpenCsvService openCsvService;

    private final Logger logger = LoggerFactory.getLogger(OpenCsvController.class);

    @PostMapping("/csv_file")
    public String processCsvFile(@RequestParam("file") MultipartFile file) throws Exception {
        //Employee employee = new Employee();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            System.out.print(reader.read());
            // create csv bean reader
            //CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
            List<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
                    .withType(Employee.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();

            // convert `CsvToBean` object to list of employees
            //List<Employee> empList = csvToBean.parse();
            csvToBean.forEach(System.out::println);
            return openCsvService.processCsvData(csvToBean);
        } catch (IOException ex) {ex.getStackTrace();}
        return "";
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) throws Exception {
        return openCsvService.getEmployee(id);
    }

}
