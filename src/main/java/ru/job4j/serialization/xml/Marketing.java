package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "marketing")
public class Marketing {

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public Marketing(List<Employee> employees) {
        this.employees = employees;
    }

    public Marketing() {
    }

    @Override
    public String toString() {
        return "Marketing{"
                + "employees=" + employees
                + '}';
    }
}
