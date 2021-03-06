package com.leadroyal.xxe.sample;

import com.leadroyal.xxe.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DocumentBuilderFactorySample {
    public void safe() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String FEATURE = null;
        // 2019年7月17日20:24:45
        // 测试环境8u172
        FEATURE = XMLConstants.FEATURE_SECURE_PROCESSING; // 开启可挡回显xxe和blind-xxe
        dbf.setFeature(FEATURE, true);
        FEATURE = "http://apache.org/xml/features/disallow-doctype-decl"; // 开启可挡回显xxe和blind-xxe
        dbf.setFeature(FEATURE, true);
        FEATURE = "http://xml.org/sax/features/external-parameter-entities"; // 开启可挡blind-xxe
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://xml.org/sax/features/external-general-entities"; // 开启可挡回显xxe
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd"; // 无效
        dbf.setFeature(FEATURE, false);
        dbf.setXIncludeAware(false); // 无效
        dbf.setExpandEntityReferences(false); // 无效
        DocumentBuilder builder = dbf.newDocumentBuilder();
        builder.parse(ResourceUtils.getPoc1());
    }

    public void unsafe() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        String FEATURE = null;
        FEATURE = XMLConstants.FEATURE_SECURE_PROCESSING;
        dbf.setFeature(FEATURE, true);
        FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
        dbf.setFeature(FEATURE, true);
        FEATURE = "http://xml.org/sax/features/external-parameter-entities";
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://xml.org/sax/features/external-general-entities";
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
        dbf.setFeature(FEATURE, false);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);
        builder.parse(ResourceUtils.getPoc1());
    }

    public static void test(){
        try {
            new DocumentBuilderFactorySample().safe();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
