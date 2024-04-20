package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ItReportTest {

    @Test
    public void whenGeneratedWithOneEmployee() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ItReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(String.join(",",
                        worker.getName(),
                        parser.parse(worker.getHired()),
                        parser.parse(worker.getFired()),
                        String.valueOf(worker.getSalary())
                ))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenGeneratedWithTwoEmployees() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerSmallSalary = new Employee("Ivan", now, now, 100);
        Employee workerBigSalary = new Employee("Petr", now, now, 500);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        store.add(workerBigSalary);
        store.add(workerSmallSalary);
        Report engine = new ItReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(String.join(",",
                        workerBigSalary.getName(),
                        parser.parse(workerBigSalary.getHired()),
                        parser.parse(workerBigSalary.getFired()),
                        String.valueOf(workerBigSalary.getSalary())
                ))
                .append(System.lineSeparator())
                .append(String.join(",",
                        workerSmallSalary.getName(),
                        parser.parse(workerSmallSalary.getHired()),
                        parser.parse(workerSmallSalary.getFired()),
                        String.valueOf(workerSmallSalary.getSalary())
                ))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}