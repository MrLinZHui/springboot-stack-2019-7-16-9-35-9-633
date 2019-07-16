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
@RequestMapping("/employees")
public class HelloResource {
    private List<Employee> employeeList = new ArrayList();
    private final Logger log = Logger.getLogger(this.getClass().getName());

    //    @GetMapping(path = "/{userName}", produces = {"application/json"})
//    public ResponseEntity<String> getAll(@PathVariable String userName) {
//
//        return ResponseEntity.ok("Hello:" + userName);
//    }
    @GetMapping("")
    public ResponseEntity getAll(){
        List<Employee> employeeList = new ArrayList();
        employeeList.add(new Employee(0,"zhangsan",18,"male"));
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable int id){
        Employee employee = null;
        employeeList.add(new Employee(1,"lisi",20,"male"));
        for(Employee employee1: employeeList){
            if(id==employee1.getId()){
                employee = employee1;
                break;
            }
        }
       return ResponseEntity.ok(employee);
    }

    @GetMapping("/page")
    public ResponseEntity page(int page,Integer pagesize){
        employeeList.add(new Employee(1,"lisi",20,"male"));
        employeeList.add(new Employee(2,"wangwu",20,"male"));
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("page",page);
        jsonpObject.put("pagesize",pagesize);
        List<Employee> employees =new ArrayList<>();
        int count = 0;
        for(int i = (page-1)*pagesize;i<employeeList.size();i++){
            if(count==pagesize){
                break;
            }
            employees.add(employeeList.get(i));
            count++;
        }
        jsonpObject.put("employees",employees);
        return ResponseEntity.ok(jsonpObject);
    }
    @GetMapping(path = "/gender")
    public ResponseEntity gender(String gender){
        List<Employee> employeeList = new ArrayList<>();
        log.info("gender:%s"+gender);
        employeeList.add(new Employee(1,"lisi",20,"male"));
        employeeList.add(new Employee(2,"wangwu",20,"male"));
        employeeList.add(new Employee(3,"linliu",20,"woman"));
        List<Employee> employees = new ArrayList<>();
//        for(Employee employee:employeeList){
//            if(employee.getGender().equals(gender)){
//                employees.add(employee);
//            }
//        }
        employees = employeeList.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }
    @PostMapping
    public ResponseEntity created(@RequestBody Employee employee){
        log.info("employee:"+employee);
        employeeList.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity nodify(@PathVariable int id,@RequestBody Employee employee){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee3 = new Employee(1,"lingling",19,"male");
        employeeList.add(employee3);
        Employee employee1 = new Employee();
        for(int i = 0; i<employeeList.size();i++){
            if(employeeList.get(i).getId()==id){
                employeeList.get(i).setAge(employee.getAge());
                employeeList.get(i).setGender(employee.getGender());
                employeeList.get(i).setName(employee.getName());
                employee1 = employeeList.get(i);
            }
            break;
        }
        return ResponseEntity.ok(employee1);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        for(int i = 0; i<employeeList.size();i++){
            if(employeeList.get(i).getId()== id){
                employeeList.remove(employeeList.get(i));
            }
        }
        return ResponseEntity.ok(employeeList);
    }
}
