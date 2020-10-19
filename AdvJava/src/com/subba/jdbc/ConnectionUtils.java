package com.subba.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtils {

	public static Connection getConnection() throws Exception {

		// pointing the properties file

		FileInputStream fis = new FileInputStream("C:\\Users\\kul_subbarao\\eclipse-workspace_PhotonAdvJava\\AdvJava\\src\\com\\subba\\jdbc\\Database.properties");

		// Creating proeprties object

		Properties props = new Properties();

		// Loading the proeprties

		props.load(fis);

		// reading the properties from properties files

		String databaseName = props.getProperty("databaseName");

		// database decision logic

		String driverClassName = null;
		String databaseUrl = null;

		String databaseUserName = null;

		String databasePassword = null;

		if ("oracle".equalsIgnoreCase(databaseName)) {

			// getting driver type

			String driverType = props.getProperty("driverType");
			databaseUserName = props.getProperty("OracleUsername");
			databasePassword = props.getProperty("OraclePassword");

			if ("type1".equalsIgnoreCase(driverType)) {

				driverClassName = props.getProperty("Type1DriverClass");
				databaseUrl = props.getProperty("Type1DriverUrlURL");
				

			} else if ("type4".equalsIgnoreCase(driverType)) {

				driverClassName = props.getProperty("Type4DriverClass");
				databaseUrl = props.getProperty("Type4DriverURL");
			
			}

		} else if ("mySQL".equalsIgnoreCase(databaseName))

		{

			// mysql database
			
			driverClassName = props.getProperty("MySQLJdbcDriverClass");
			databaseUrl = props.getProperty("MySQLJdbcUrl");
			databaseUserName = props.getProperty("MySQLusername");
			databasePassword = props.getProperty("MySQLPassword");

		} else {

			// postgrasesql database
		}

		System.out.println("####### Driver Class Loading details###########");

		System.out.println("Database name   :::::::" + databaseName);
		System.out.println("Database Driver" + driverClassName);
		System.out.println("Database  URL" + databaseUrl);
		


		// Registering the driver and getting connection

		Class.forName(driverClassName);

		Connection con = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
		
		//Underlying Connection interface implementation class
		
		System.out.println("Connection Interface implementation class name ::"+con.getClass().getName());
		
		//returning the connection object

		return con;

	}
}
