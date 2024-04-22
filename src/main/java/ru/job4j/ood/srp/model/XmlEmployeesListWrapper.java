package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class XmlEmployeesListWrapper {
    private List<EmployeeJsonXmlWrap> employee;

    public XmlEmployeesListWrapper() {

    }

    public XmlEmployeesListWrapper(List<EmployeeJsonXmlWrap> employee) {
        this.employee = employee;
    }

    public List<EmployeeJsonXmlWrap> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeJsonXmlWrap> employee) {
        this.employee = employee;
    }
}
