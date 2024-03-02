package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@XmlRootElement(name = "hr")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hr {

    @XmlElement
    private Marketing marketing;

    public Hr() {
    }

    public Hr(Marketing marketing) {
        this.marketing = marketing;
    }

    @Override
    public String toString() {
        return "Hr{"
                + "marketing=" + marketing
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Hr hr = new Hr(new Marketing(List.of(
                new Employee("Ivanov Fedor Petrovich", "Male", "02.01.1980",
                        "CMO", 10, true),
                new Employee("Fedotov Petr Ivanovich", "Male", "18.11.1991",
                        "Manager", 4, true),
                new Employee("Kazancev Oleg Olegovich", "Male", "8.11.1993",
                        "Specialist", 2, false)
        )));

        JAXBContext context = JAXBContext.newInstance(Hr.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String hrXml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(hr, writer);
            hrXml = writer.getBuffer().toString();
            System.out.println(hrXml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(hrXml)) {
            Hr newHr = (Hr) unmarshaller.unmarshal(reader);
            System.out.println(newHr);
        }
    }
}
