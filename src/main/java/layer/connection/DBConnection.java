package layer.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
private static final String DB_DRIVER="com.mysql.jdbc.Driver";
private static final String DB_URL="jdbc:mysql://localhost/exam2";
private static final String DB_USER="root";
private static final String DB_PW="root";
private static DBConnection instance  = null;
private static Connection con = null;

//using singleton design pattern for DB connection
private DBConnection() {
	
}
private void connect() {
	try {
		Class.forName(DB_DRIVER);
		con = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);
		if(con == null) {
			System.out.println("Database is not connected...");
		}else {
			System.out.println("Database is connected...");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static DBConnection getInstance() {
	if(instance == null) {
		instance =new DBConnection();
	}
	return instance;
}

public static Connection getCon() {
	try {
		if(con==null || con.isClosed()) {
			System.out.println("Getting connection....");
			DBConnection.getInstance().connect();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
}

public static void closeCon(Connection con, PreparedStatement pst) {
	if(pst!=null)
		try {
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		if(con!=null && !con.isClosed()) {
			con.close();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Connection is closed...");
}

//method overriding for closing connection
public static void closeCon(Connection con, PreparedStatement pst,ResultSet rs) {
	if(rs!=null)
		try {
			rs.close();
			closeCon(con,pst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
