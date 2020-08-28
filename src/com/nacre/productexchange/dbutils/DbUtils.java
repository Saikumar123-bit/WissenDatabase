package com.nacre.productexchange.dbutils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils 
{
	static Connection connection;
	static String username;
	static String password;
	static String url;
	static String driver;
	
	static 
	{
		try
		{
			Properties p = new Properties();
			InputStream is=DbUtils.class.getClassLoader().getResourceAsStream("db.properties");
			System.out.println("Control is in DbUtils class");
		
			File f = new File("db.properties");
			System.out.println("File last Modified at--->"+f.lastModified());
		
			p.load(is);
			driver = p.getProperty("jdbc.driverClassName");
			url=p.getProperty("jdbc.dataSourceUrl");
			username=p.getProperty("jdbc.username");
			password=p.getProperty("jdbc.password");
						
			Class.forName(driver);
			
		}
		
		catch(IOException e)
		{
			System.out.println("Problem with--->"+e.getMessage());
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class Loading problem"+e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error in connecting with mysql---->"+e.getMessage());
		}
	}
	public static Connection getConnection()
	{
		if(connection!=null)
		{
			return connection;
		}
		else
		{
			try{
				connection=DriverManager.getConnection(url,username,password);				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return connection;
	}
	public static void main(String[] args)
	{
		System.out.println("Connection Established With Mysql...");
	}
}