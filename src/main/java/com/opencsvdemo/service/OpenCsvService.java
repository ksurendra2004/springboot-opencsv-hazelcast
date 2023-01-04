package com.opencsvdemo.service;

import com.hazelcast.map.IMap;
import com.opencsvdemo.model.Employee;
import com.opencsvdemo.repository.OpenCsvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OpenCsvService {

    private final IMap<Long, Employee> employeeCache;
    //@Autowired
    private final OpenCsvRepository openCsvRepository;

    private final Logger logger = LoggerFactory.getLogger(OpenCsvService.class);

    public OpenCsvService(IMap<Long, Employee> employeeCache, OpenCsvRepository openCsvRepository) {
        this.employeeCache = employeeCache;
        this.openCsvRepository = openCsvRepository;
    }

    public String processCsvData(List<Employee> empList) throws Exception {
        empList.forEach(employee -> employeeCache.put(employee.getEmpId(), employee));
        logger.info("employeeCache: {}", employeeCache.values());
        empList.forEach(openCsvRepository::save);
        //openCsvRepository.saveAll(empList);
        //openCsvRepository.save(empList);
        return "Saved CSV data successfully";
    }

    public Optional<Employee> getEmployee(@PathVariable Long id) throws Exception {
        logger.info("emp details: "+Optional.ofNullable(employeeCache.get(id)));
        return Optional.ofNullable(employeeCache.get(id));
    }

}
