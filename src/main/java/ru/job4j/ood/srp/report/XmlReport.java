package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeJsonXmlWrap;
import ru.job4j.ood.srp.model.XmlEmployeesListWrapper;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements Report {
    private final Store store;
    private final Marshaller xmlMarshaller;

    public XmlReport(Store store, Marshaller xmlMarshaller) {
        this.store = store;
        this.xmlMarshaller = xmlMarshaller;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<EmployeeJsonXmlWrap> wrappedEmployees = new LinkedList<>();

        for (Employee employee : store.findBy(filter)) {
            wrappedEmployees.add(new EmployeeJsonXmlWrap(employee));
        }

        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            xmlMarshaller.marshal(new XmlEmployeesListWrapper(wrappedEmployees), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
