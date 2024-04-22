package ru.job4j.ood.srp.model;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Calendar;

public class EmployeeJsonXmlWrap {
    private String name;
    private String hired;
    private String fired;
    private double salary;

    public EmployeeJsonXmlWrap(Employee employee) {
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        this.name = employee.getName();
        this.hired = dateTimeParser.parse(employee.getHired());
        this.fired = dateTimeParser.parse(employee.getFired());
        this.salary = employee.getSalary();
    }

    public EmployeeJsonXmlWrap() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHired() {
        return hired;
    }

    public void setHired(String hired) {
        this.hired = hired;
    }

    public String getFired() {
        return fired;
    }

    public void setFired(String fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
