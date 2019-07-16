package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyName;
    private List<Employee> employeeList;
    public Company() {
        employeeList = new ArrayList<>();
    }

    public Company(String companyName) {
        this.companyName = companyName;
        employeeList = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployees(Employee employee) {
        employeeList.add(employee);
    }
}
