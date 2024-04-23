package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonReportTest {

    @Test
    public void whenGeneratedWithOneEmployee() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        store.add(worker);
        Report engine = new JsonReport(store, gson);
        StringBuilder expected = new StringBuilder()
                .append("[").append("\n")
                .append("  {").append("\n")
                .append(String.join("," + "\n",
                        "    \"name\": " + "\"" + worker.getName() + "\"",
                        "    \"hired\": " + "\"" + parser.parse(worker.getHired()) + "\"",
                        "    \"fired\": " + "\"" + parser.parse(worker.getFired()) + "\"",
                        "    \"salary\": " + worker.getSalary()
                )).append("\n")
                .append("  }").append("\n")
                .append("]");
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenGeneratedWithTwoEmployees() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerSmallSalary = new Employee("Ivan", now, now, 100);
        Employee workerBigSalary = new Employee("Petr", now, now, 500);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        store.add(workerSmallSalary);
        store.add(workerBigSalary);
        Report engine = new JsonReport(store, gson);
        StringBuilder expected = new StringBuilder()
                .append("[").append("\n")
                .append("  {").append("\n")
                .append(String.join("," + "\n",
                        "    \"name\": " + "\"" + workerSmallSalary.getName() + "\"",
                        "    \"hired\": " + "\"" + parser.parse(workerSmallSalary.getHired()) + "\"",
                        "    \"fired\": " + "\"" + parser.parse(workerSmallSalary.getFired()) + "\"",
                        "    \"salary\": " + workerSmallSalary.getSalary()
                )).append("\n")
                .append("  },\n")
                .append("  {").append("\n")
                .append(String.join("," + "\n",
                        "    \"name\": " + "\"" + workerBigSalary.getName() + "\"",
                        "    \"hired\": " + "\"" + parser.parse(workerBigSalary.getHired()) + "\"",
                        "    \"fired\": " + "\"" + parser.parse(workerBigSalary.getFired()) + "\"",
                        "    \"salary\": " + workerBigSalary.getSalary()
                )).append("\n")
                .append("  }").append("\n")
                .append("]");
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenGeneratedWithThreeEmployees() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerSmallSalary = new Employee("Ivan", now, now, 100);
        Employee workerMiddleSalary = new Employee("Vova", now, now, 300);
        Employee workerBigSalary = new Employee("Petr", now, now, 500);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        store.add(workerSmallSalary);
        store.add(workerMiddleSalary);
        store.add(workerBigSalary);
        Report engine = new JsonReport(store, gson);
        StringBuilder expected = new StringBuilder()
                .append("[").append("\n")
                .append("  {").append("\n")
                .append(String.join("," + "\n",
                        "    \"name\": " + "\"" + workerSmallSalary.getName() + "\"",
                        "    \"hired\": " + "\"" + parser.parse(workerSmallSalary.getHired()) + "\"",
                        "    \"fired\": " + "\"" + parser.parse(workerSmallSalary.getFired()) + "\"",
                        "    \"salary\": " + workerSmallSalary.getSalary()
                )).append("\n")
                .append("  },\n")
                .append("  {").append("\n")
                .append(String.join("," + "\n",
                        "    \"name\": " + "\"" + workerMiddleSalary.getName() + "\"",
                        "    \"hired\": " + "\"" + parser.parse(workerMiddleSalary.getHired()) + "\"",
                        "    \"fired\": " + "\"" + parser.parse(workerMiddleSalary.getFired()) + "\"",
                        "    \"salary\": " + workerMiddleSalary.getSalary()
                )).append("\n")
                .append("  },\n")
                .append("  {").append("\n")
                .append(String.join("," + "\n",
                        "    \"name\": " + "\"" + workerBigSalary.getName() + "\"",
                        "    \"hired\": " + "\"" + parser.parse(workerBigSalary.getHired()) + "\"",
                        "    \"fired\": " + "\"" + parser.parse(workerBigSalary.getFired()) + "\"",
                        "    \"salary\": " + workerBigSalary.getSalary()
                )).append("\n")
                .append("  }").append("\n")
                .append("]");
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}