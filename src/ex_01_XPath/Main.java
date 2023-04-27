package ex_01_XPath;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, XPathExpressionException {

        File file = new File("I:\\Lesson_03_XPath_ JAXB\\src\\ex_01_XPath\\electric_tool.xml");

        InputSource inputSource = new InputSource(new FileInputStream(file));

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        System.out.println("Power_tools... ");

        getAllTools(inputSource, xPath);

        //getToolById(inputSource, xPath);

        //getToolOptionById(inputSource, xPath);

        //getToolAttribute(inputSource, xPath);

    }


    private static void getAllTools(InputSource inputSource, XPath xPath) throws XPathExpressionException {

        XPathExpression xPathExpression = xPath.compile("/power_tools/model/*");

        Object object = xPathExpression.evaluate(inputSource,XPathConstants.NODESET);
        NodeList nodeList = (NodeList)object;

        for(int i=0; i< nodeList.getLength(); i++) {

            Node tempNode = nodeList.item(i);

            String nodeName = tempNode.getNodeName();
            String nodeTextContent = tempNode.getTextContent();

            System.out.println("\t" + nodeName + ": " + nodeTextContent);

            if(tempNode.getNodeName() =="material"){
                System.out.println(" ");
            }
        }
    }

    private static void getToolById(InputSource inputSource, XPath xPath) throws XPathExpressionException {

        //XPathExpression xPathExpression = xPath.compile("//model[@id=1]/*");
        //XPathExpression xPathExpression = xPath.compile("//model[@id=2]/*");
        XPathExpression xPathExpression = xPath.compile("//model[brand = 'Makita']/*");

        Object object = xPathExpression.evaluate(inputSource,XPathConstants.NODESET);
        NodeList nodeList = (NodeList)object;

        System.out.println("\tmodel:");
        for(int i=0; i< nodeList.getLength(); i++) {

            Node tempNode = nodeList.item(i);

            String nodeName = tempNode.getNodeName();
            String nodeTextContent = tempNode.getTextContent();

            System.out.println("\t\t" + nodeName + " " + nodeTextContent);

            if(tempNode.getNodeName() =="material"){
                System.out.println(" ");
            }
        }
    }

    private static void getToolOptionById(InputSource inputSource, XPath xPath) throws XPathExpressionException {

        XPathExpression xPathExpression = xPath.compile("//model[@id = '1']/brand");
        //XPathExpression xPathExpression = xPath.compile("//model[@id = '2']/name");
        //XPathExpression xPathExpression = xPath.compile("//model[@id = '1']/material");

        String value = (String) xPathExpression.evaluate(inputSource,XPathConstants.STRING);
        System.out.println("\t" + value);

    }

    private static void getToolAttribute(InputSource inputSource, XPath xPath) throws XPathExpressionException {

        XPathExpression xPathExpression = xPath.compile("//energy/@*");
        //XPathExpression xPathExpression = xPath.compile("//power[@requirement =  '550']");
        //XPathExpression xPathExpression = xPath.compile("//model[power [@requirement = '550' ]]/*");
        //XPathExpression xPathExpression = xPath.compile("//model[acoustic [@capacity = '98' ]]/name");

        Object object = xPathExpression.evaluate(inputSource,XPathConstants.NODESET);
        NodeList nodeList = (NodeList)object;

        for(int i=0; i< nodeList.getLength(); i++) {

            Node tempNode = nodeList.item(i);

            String nodeName = tempNode.getNodeName();
            String nodeTextContent = tempNode.getTextContent();

            System.out.println("\t\t" + nodeName + " = '" + nodeTextContent + "'");

        }
    }
}
