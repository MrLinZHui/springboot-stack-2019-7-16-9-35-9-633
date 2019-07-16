package com.tw.apistackbase.controller;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/companies")
public class HandleCompany {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    @GetMapping("")
    public ResponseEntity companys(){
        List<Company> companyList = new ArrayList<>();
        Company company = new Company("alibaba");
        Employee employee =new Employee(1,"lisi",20,"male");
        Employee employee1 = new Employee(2,"wangwu",20,"male");
        company.addEmployees(employee);
        company.addEmployees(employee1);
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("companyName",company.getCompanyName());
        jsonpObject.put("employeeNumber",company.getEmployeeList().size());
        jsonpObject.put("employeeNumber",company.getEmployeeList().size());
        jsonpObject.put("employees",company.getEmployeeList());
        return ResponseEntity.ok(jsonpObject);
    }
    @GetMapping("/{id}")
    public ResponseEntity getcompany(@PathVariable ){
        List<Company> companyList = new ArrayList<>();
        Company company = new Company("alibaba");
        Employee employee =new Employee(1,"lisi",20,"male");
        Employee employee1 = new Employee(2,"wangwu",20,"male");
        company.addEmployees(employee);
        company.addEmployees(employee1);
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("companyName",company.getCompanyName());
        jsonpObject.put("employeeNumber",company.getEmployeeList().size());
        jsonpObject.put("employeeNumber",company.getEmployeeList().size());
        jsonpObject.put("employees",company.getEmployeeList());
        return ResponseEntity.ok(jsonpObject);
    }
}
