package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeJsonXmlWrap;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private final Store store;
    private final Gson gson;

    public JsonReport(Store store, Gson gson) {
        this.store = store;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<EmployeeJsonXmlWrap> employees = new LinkedList<>();

        for (Employee employee : store.findBy(filter)) {
            employees.add(new EmployeeJsonXmlWrap(employee));
        }

        return gson.toJson(employees);
    }
}
