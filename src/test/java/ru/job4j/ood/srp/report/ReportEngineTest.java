package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenThreeEmployeesGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerSmallSalary = new Employee("Ivan", now, now, 100);
        Employee workerBigSalary = new Employee("Petr", now, now, 500);
        Employee workerMiddleSalary = new Employee("Vova", now, now, 300);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(workerSmallSalary);
        store.add(workerBigSalary);
        store.add(workerMiddleSalary);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workerSmallSalary.getName()).append(" ")
                .append(parser.parse(workerSmallSalary.getHired())).append(" ")
                .append(parser.parse(workerSmallSalary.getFired())).append(" ")
                .append(workerSmallSalary.getSalary())
                .append(System.lineSeparator())
                .append(workerBigSalary.getName()).append(" ")
                .append(parser.parse(workerBigSalary.getHired())).append(" ")
                .append(parser.parse(workerBigSalary.getFired())).append(" ")
                .append(workerBigSalary.getSalary())
                .append(System.lineSeparator())
                .append(workerMiddleSalary.getName()).append(" ")
                .append(parser.parse(workerMiddleSalary.getHired())).append(" ")
                .append(parser.parse(workerMiddleSalary.getFired())).append(" ")
                .append(workerMiddleSalary.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}