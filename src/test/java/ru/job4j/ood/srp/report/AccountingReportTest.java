package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingReportTest {

    @Test
    public void whenReportGeneratedWithEURO() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(worker);
        Report engine = new AccountingReport(store, parser, converter, Currency.EUR);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportGeneratedWithUSD() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(worker);
        Report engine = new AccountingReport(store, parser, converter, Currency.USD);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportGeneratedWithUSDAndTwoEmployees() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerBigSalary = new Employee("Ivan", now, now, 500);
        Employee workerSmallSalary = new Employee("Petr", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(workerBigSalary);
        store.add(workerSmallSalary);
        Report engine = new AccountingReport(store, parser, converter, Currency.USD);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workerBigSalary.getName()).append(" ")
                .append(parser.parse(workerBigSalary.getHired())).append(" ")
                .append(parser.parse(workerBigSalary.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, workerBigSalary.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(workerSmallSalary.getName()).append(" ")
                .append(parser.parse(workerSmallSalary.getHired())).append(" ")
                .append(parser.parse(workerSmallSalary.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, workerSmallSalary.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportGeneratedWithUSDAndThreeEmployees() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerBigSalary = new Employee("Ivan", now, now, 500);
        Employee workerMiddleSalary = new Employee("Ivan", now, now, 500);
        Employee workerSmallSalary = new Employee("Petr", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        store.add(workerBigSalary);
        store.add(workerMiddleSalary);
        store.add(workerSmallSalary);
        Report engine = new AccountingReport(store, parser, converter, Currency.USD);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workerBigSalary.getName()).append(" ")
                .append(parser.parse(workerBigSalary.getHired())).append(" ")
                .append(parser.parse(workerBigSalary.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, workerBigSalary.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(workerMiddleSalary.getName()).append(" ")
                .append(parser.parse(workerMiddleSalary.getHired())).append(" ")
                .append(parser.parse(workerMiddleSalary.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, workerMiddleSalary.getSalary(), Currency.USD))
                .append(System.lineSeparator())
                .append(workerSmallSalary.getName()).append(" ")
                .append(parser.parse(workerSmallSalary.getHired())).append(" ")
                .append(parser.parse(workerSmallSalary.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, workerSmallSalary.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

}