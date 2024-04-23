package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.XmlEmployeesListWrapper;
import ru.job4j.ood.srp.store.MemoryStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XmlReportTest {
    Marshaller marshaller;

    @BeforeEach
    public void init() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XmlEmployeesListWrapper.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Test
    public void whenGeneratedWithOneEmployee() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        store.add(worker);

        Report engine = new XmlReport(store, marshaller);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n")
                .append("<employees>").append("\n")
                .append("    <employee>").append("\n")
                .append("        <fired>").append(parser.parse(worker.getFired())).append("</fired>\n")
                .append("        <hired>").append(parser.parse(worker.getHired())).append("</hired>\n")
                .append("        <name>").append(worker.getName()).append("</name>\n")
                .append("        <salary>").append(worker.getSalary()).append("</salary>\n")
                .append("    </employee>").append("\n")
                .append("</employees>").append("\n");

        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenGeneratedWithTwoEmployee() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerSmallSalary = new Employee("Ivan", now, now, 100);
        Employee workerBigSalary = new Employee("Petr", now, now, 500);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        store.add(workerSmallSalary);
        store.add(workerBigSalary);

        Report engine = new XmlReport(store, marshaller);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n")
                .append("<employees>").append("\n")
                .append("    <employee>").append("\n")
                .append("        <fired>").append(parser.parse(workerSmallSalary.getFired())).append("</fired>\n")
                .append("        <hired>").append(parser.parse(workerSmallSalary.getHired())).append("</hired>\n")
                .append("        <name>").append(workerSmallSalary.getName()).append("</name>\n")
                .append("        <salary>").append(workerSmallSalary.getSalary()).append("</salary>\n")
                .append("    </employee>").append("\n")
                .append("    <employee>").append("\n")
                .append("        <fired>").append(parser.parse(workerBigSalary.getFired())).append("</fired>\n")
                .append("        <hired>").append(parser.parse(workerBigSalary.getHired())).append("</hired>\n")
                .append("        <name>").append(workerBigSalary.getName()).append("</name>\n")
                .append("        <salary>").append(workerBigSalary.getSalary()).append("</salary>\n")
                .append("    </employee>").append("\n")
                .append("</employees>").append("\n");

        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenGeneratedWithThreeEmployee() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee workerSmallSalary = new Employee("Ivan", now, now, 100);
        Employee workerMiddleSalary = new Employee("Vova", now, now, 300);
        Employee workerBigSalary = new Employee("Petr", now, now, 500);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();

        store.add(workerSmallSalary);
        store.add(workerMiddleSalary);
        store.add(workerBigSalary);

        Report engine = new XmlReport(store, marshaller);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n")
                .append("<employees>").append("\n")
                .append("    <employee>").append("\n")
                .append("        <fired>").append(parser.parse(workerSmallSalary.getFired())).append("</fired>\n")
                .append("        <hired>").append(parser.parse(workerSmallSalary.getHired())).append("</hired>\n")
                .append("        <name>").append(workerSmallSalary.getName()).append("</name>\n")
                .append("        <salary>").append(workerSmallSalary.getSalary()).append("</salary>\n")
                .append("    </employee>").append("\n")
                .append("    <employee>").append("\n")
                .append("        <fired>").append(parser.parse(workerMiddleSalary.getFired())).append("</fired>\n")
                .append("        <hired>").append(parser.parse(workerMiddleSalary.getHired())).append("</hired>\n")
                .append("        <name>").append(workerMiddleSalary.getName()).append("</name>\n")
                .append("        <salary>").append(workerMiddleSalary.getSalary()).append("</salary>\n")
                .append("    </employee>").append("\n")
                .append("    <employee>").append("\n")
                .append("        <fired>").append(parser.parse(workerBigSalary.getFired())).append("</fired>\n")
                .append("        <hired>").append(parser.parse(workerBigSalary.getHired())).append("</hired>\n")
                .append("        <name>").append(workerBigSalary.getName()).append("</name>\n")
                .append("        <salary>").append(workerBigSalary.getSalary()).append("</salary>\n")
                .append("    </employee>").append("\n")
                .append("</employees>").append("\n");

        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

}