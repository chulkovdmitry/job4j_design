package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
    @XmlAttribute
    private String name;
    private Album album;
    @XmlAttribute
    private boolean active;
    @XmlAttribute
    private int yearOfCreation;
    @XmlElementWrapper()
    @XmlElement(name = "person")
    private String[] crew;

    public Group() {
    }

    public Group(String name, Album album,
               boolean active, int yearOfCreation, String[] crew) {
        this.name = name;
        this.album = album;
        this.active = active;
        this.yearOfCreation = yearOfCreation;
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "Group{"
                + "name='" + name + '\''
                + ", album=" + album
                + ", active=" + active
                + ", yearOfCreation=" + yearOfCreation
                + ", crew=" + Arrays.toString(crew)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final Group groupInfo = new Group(
                "Fury",
                new Album(14, "V", 82),
                true,
                1983,
                new String[]{"Jim", "Fred", "Jack"}
        );
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Group.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(groupInfo, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Group groupInfo2 = (Group) unmarshaller.unmarshal(reader);
            System.out.println(groupInfo2);
        }
    }
}
