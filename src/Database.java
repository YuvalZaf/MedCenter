import java.sql.*;

public class Database
{
	Connection conn;//create connection
	Statement stmt;
	ResultSet result=null;

	
	public Database()//constructor
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test";
			conn=DriverManager.getConnection(url, "root", "root");
			stmt=conn.createStatement();
		}
		catch(ClassNotFoundException e) 
		{
			e.getMessage();
		}
		catch(SQLException ex) 
		{
			ex.getMessage();
		} 
	}
	
	public void createTable(String tableName)
	{
		try {
			stmt.executeUpdate("DROP TABLE IF EXISTS " + tableName);
			String createTable = "CREATE TABLE " + tableName +"(id Varchar(50), medicine int, treatingDoc Varchar(50), treatment int)";
			stmt.executeUpdate(createTable);
		}
		catch(SQLException ex) {
			ex.getMessage();
		}
	}
	
	public void insertIntoTable(String tableName, Note o) throws SQLException
	{
		if( o instanceof Prescription)
		{
			String insertDetails = "INSERT INTO " + tableName + "(id, medicine,treatingDoc,treatment) VALUES('" + o.getPatientID()+ "','" + ((Prescription)o).getMedicine() 
					+ "','"+ o.getDocID() + "','" + o.getTreatment() + "')";
			stmt.executeUpdate(insertDetails);
		}
		else
		{
			String insertDetails = "INSERT INTO " + tableName + "(id, medicine,treatingDoc,treatment) VALUES('" + o.getPatientID()+ "','" + -1 
					+ "','"+ o.getDocID() + "','" + o.getTreatment() + "')";
			stmt.executeUpdate(insertDetails);
		}
	}

}
