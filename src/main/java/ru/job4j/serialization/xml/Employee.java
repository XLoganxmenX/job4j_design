package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
public class Employee {

    @XmlElement
    private String person;
    @XmlElement
    private String sex;
    @XmlElement
    private String birthday;
    @XmlElement
    private String job;
    @XmlElement
    private int experience;
    @XmlElement
    private boolean married;

    public Employee() {
    }

    public Employee(String person, String sex, String birthday, String job, int experience, boolean married) {
        this.person = person;
        this.sex = sex;
        this.birthday = birthday;
        this.job = job;
        this.experience = experience;
        this.married = married;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "person='" + person + '\''
                + ", sex='" + sex + '\''
                + ", birthday='" + birthday + '\''
                + ", job='" + job + '\''
                + ", experience=" + experience
                + ", married=" + married
                + '}';
    }
}
