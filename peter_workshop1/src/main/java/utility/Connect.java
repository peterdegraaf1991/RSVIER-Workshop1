package utility;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connect {
private final static Logger LOG = LoggerFactory.getLogger(Connect.class);

		private static String username = null;
		private static String password = null;
		private static String url = null;
		
		public static Connection getConnection() {
			Connection connection = null;

				File xmlFile = new File("src/main/java/utility/LoginDetails.xml");
			try{		
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(xmlFile);
				document.getDocumentElement().normalize();
				username = document.getElementsByTagName("username").item(0).getTextContent();
				password = document.getElementsByTagName("password").item(0).getTextContent();
				url = document.getElementsByTagName("url").item(0).getTextContent();
				connection = DriverManager.getConnection(url,username,password);}
			
			catch(SAXException | IOException | ParserConfigurationException | SQLException e){
   				e.printStackTrace();
   				}
    		LOG.info("Succesfully connected to Database as: " + username);
    		return connection;
			    }
		}



