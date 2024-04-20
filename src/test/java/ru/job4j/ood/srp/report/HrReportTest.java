package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class HrReportTest {

    @Test
    public void whenReportGeneratedWithTreeEmployees() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerSmallSalary = new Employee("Ivan", now, now, 100);
        Employee workerBigSalary = new Employee("Petr", now, now, 500);
        Employee workerMiddleSalary = new Employee("Vova", now, now, 300);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        store.add(workerSmallSalary);
        store.add(workerBigSalary);
        store.add(workerMiddleSalary);

        Report engine = new HrReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerBigSalary.getName()).append(" ")
                .append(workerBigSalary.getSalary())
                .append(System.lineSeparator())
                .append(workerMiddleSalary.getName()).append(" ")
                .append(workerMiddleSalary.getSalary())
                .append(System.lineSeparator())
                .append(workerSmallSalary.getName()).append(" ")
                .append(workerSmallSalary.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}