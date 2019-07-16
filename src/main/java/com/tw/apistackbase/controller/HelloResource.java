package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
        employeeList.add(new Employee(0,"zhangsan",18,"male"));
        return ResponseEntity.ok().body(employeeList);
    }

    @PostMapping
    public ResponseEntity created(@RequestBody Employee employee){
        employeeList.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity nodify(@RequestBody Employee employee){
        for(int i = 0; i<employeeList.size();i++){
            if(employeeList.get(i).getId()==employee.getId()){
                employeeList.get(i).setAge(employee.getAge());
                employeeList.get(i).setGender(employee.getGender());
                employeeList.get(i).setName(employee.getName());
            }
            break;
        }
        return ResponseEntity.ok().build();
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
