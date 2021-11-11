package com.gadomanager.gadomanager.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DAOFactory {

	public static Connection getConexao() {
		try {

			List<String> prop = getProperties();
			final String url = prop.get(1);
			final String usuario = prop.get(2);
			final String senha = prop.get(3);
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static List<String> getProperties() {
		List<String> propertyList = new ArrayList<>();
		DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
			Document doc = xmlBuilder.parse("src/main/java/META-INF/persistence.xml");
			NodeList properties = doc.getElementsByTagName("property");
			for (int i = 0; i <= 4; i++) {
				Node property = properties.item(i);
				if (property.getNodeType() == Node.ELEMENT_NODE) {
					Element propertyEl = (Element) property;
					String propertyValue = propertyEl.getAttribute("value");
					propertyList.add(propertyValue);
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyList;
	}

}
