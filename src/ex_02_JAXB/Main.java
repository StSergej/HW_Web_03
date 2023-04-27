package ex_02_JAXB;

import ex_02_JAXB.entity.ModelType;
import ex_02_JAXB.entity.ObjectFactory;
import ex_02_JAXB.entity.PowerToolsType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JAXBException {

        ObjectFactory objectFactory = new ObjectFactory();

        PowerToolsType powerTool = objectFactory.createPowerToolsType();
        ModelType model = objectFactory.createModelType();
        ModelType model2 = objectFactory.createModelType();

        model.setId("1");
        model.setName("Drill");
        model.setBrand("Makita");
        model.setOrigin("China");
        model.setHandy("Double-handed");
        model.setVitality("Low");
        model.setEnergy("consumption = 230 Volt");
        model.setPower("requirement = 550 Watt");
        model.setTurnover("frequency = 0 - 4200 RPM");
        model.setRotation("moment = 65 Nm");
        model.setAcoustic("capacity = 98 dB");
        model.setMaterial("PVC and steel");

        powerTool.getModel().add(model);

        model2.setId("2");
        model2.setName("Screw gun");
        model2.setBrand("Bosch");
        model2.setOrigin("Poland");
        model2.setHandy("One-handed");
        model2.setVitality("Low");
        model2.setEnergy("consumption = 3Ah_Li-ion");
        model2.setPower("requirement = 18 Volt");
        model2.setTurnover("frequency = 0 - 1700 RPM");
        model2.setRotation("moment = 22 Nm");
        model2.setAcoustic("capacity = 45 dB");
        model2.setMaterial("PVC");

        powerTool.getModel().add(model2);

        File outFile = new File("I:\\Lesson_03_XPath_ JAXB\\src\\ex_02_JAXB\\out_ElTools.xml");

        convertToXML(powerTool, outFile); // сохраняем объект в XML файл
        System.out.println("------------------------------------------");

        File fileName = new File("I:\\Lesson_03_XPath_ JAXB\\src\\ex_02_JAXB\\electric_tools_JAXB.xml");

        PowerToolsType fromXMLToPowerTools = fromXML(fileName); // получаем объект из XML файла
        System.out.println(fromXMLToPowerTools.toString());

    }

    private static void convertToXML(PowerToolsType powerTool, File file) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(PowerToolsType.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(powerTool, file);
        marshaller.marshal(powerTool, System.out);

    }

    private static PowerToolsType fromXML(File file) throws JAXBException{

        JAXBContext context = JAXBContext.newInstance(PowerToolsType.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (PowerToolsType) unmarshaller.unmarshal(file);

    }
}
