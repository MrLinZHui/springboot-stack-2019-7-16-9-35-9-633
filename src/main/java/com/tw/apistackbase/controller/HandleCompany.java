package com.tw.apistackbase.controller;
import net.sf.json.JSONArray;
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
    public ResponseEntity companys(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "0") int pagesize) {
        List<Company> companyList = new ArrayList<>();
        Company company = new Company("alibaba", 1);
        Employee employee = new Employee(1, "lisi", 20, "male");
        Employee employee1 = new Employee(2, "wangwu", 20, "male");
        company.addEmployees(employee);
        company.addEmployees(employee1);
        companyList.add(company);
        JSONArray jsonArray = new JSONArray();
        if (page == 0 && pagesize == 0) {
            for (Company company1 : companyList) {
                JSONObject jsonpObject = new JSONObject();
                jsonpObject.put("companyName", company1.getCompanyName());
                jsonpObject.put("employeeNumber", company1.getEmployeeList().size());
                jsonpObject.put("employees", company1.getEmployeeList());
                jsonArray.add(jsonpObject);
            }

        }else{
            int count = 0;
            for(int i = (page-1)*pagesize;i<companyList.size();i++){
                if(count==pagesize){
                    break;
                }
                JSONObject jsonpObject = new JSONObject();
                jsonpObject.put("page", page);
                jsonpObject.put("pagesize", pagesize);
                jsonpObject.put("companyName", companyList.get(i).getCompanyName());
                jsonpObject.put("employeeNumber", companyList.get(i).getEmployeeList().size());
                jsonpObject.put("employees", companyList.get(i).getEmployeeList());
                jsonArray.add(jsonpObject);
                count++;
            }
        }
        return ResponseEntity.ok(jsonArray);
    }
    @GetMapping("/{id}")
    public ResponseEntity getcompany(@PathVariable int id){
        List<Company> companyList = new ArrayList<>();
        Company company = new Company("alibaba",1);
        Company company2 = new Company("huawei",2);
        Employee employee =new Employee(1,"lisi",20,"male");
        Employee employee1 = new Employee(2,"wangwu",20,"male");
        company.addEmployees(employee);
        company.addEmployees(employee1);
        companyList.add(company);
        Company company1 = companyList.stream().filter(company3 -> company3.getCompanyId()==id).collect(Collectors.toList()).get(0);
        return ResponseEntity.ok(company1);
    }
    @GetMapping("/{id}/employees")
    public ResponseEntity getemployees(@PathVariable int id){
        List<Company> companyList = new ArrayList<>();
        Company company = new Company("alibaba",1);
        Company company2 = new Company("huawei",2);
        Employee employee =new Employee(1,"lisi",20,"male");
        Employee employee1 = new Employee(2,"wangwu",20,"male");
        company.addEmployees(employee);
        company.addEmployees(employee1);
        companyList.add(company);
        Company company1 = companyList.stream().filter(company3 -> company3.getCompanyId()==id).collect(Collectors.toList()).get(0);

        return ResponseEntity.ok(company1.getEmployeeList());
    }
    @PostMapping
    public ResponseEntity created(@RequestBody Company company){
        log.info("company:"+company);
        List<Company> companyList = new ArrayList<>();
        companyList.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity nodify(@PathVariable int id,@RequestBody Company company){
        List<Company> companyList = new ArrayList<>();
        Company company3 = new Company("alibaba",1);
        Employee employee =new Employee(1,"lisi",20,"male");
        Employee employee1 = new Employee(2,"wangwu",20,"male");
        company3.addEmployees(employee);
        company3.addEmployees(employee1);
        companyList.add(company3);
        Company company1 = new Company();
        for(int i = 0; i<companyList.size();i++){
            if(companyList.get(i).getCompanyId()==id){
                companyList.get(i).setCompanyName(company.getCompanyName());
                company1 = companyList.get(i);
            }
            break;
        }
        return ResponseEntity.ok(company1);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        List<Company> companyList = new ArrayList<>();
        Company company3 = new Company("alibaba",1);
        Employee employee =new Employee(1,"lisi",20,"male");
        Employee employee1 = new Employee(2,"wangwu",20,"male");
        company3.addEmployees(employee);
        company3.addEmployees(employee1);
        companyList.add(company3);
        for(int i = 0; i<companyList.size();i++){
            if(companyList.get(i).getCompanyId()== id){
                companyList.remove(companyList.get(i));
            }
        }
        return ResponseEntity.ok(companyList);
    }

}
