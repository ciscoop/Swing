package sm.ciscoop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connessione {

	public static final String BASE_CONNECTION_URL = "jdbc:mysql://localhost/";
	public static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	private Connection c_connessione = null;
	private boolean c_useTransaction = false;

	public static void main(String[] args) {
		Connessione conn = new Connessione("infows", "root", "aiesh");
		conn.setUseTransaction(true);
		try {
			ResultSet rs = conn.createRecordset("select * from webservice");
			if( rs.next() ) {
				String szNome = rs.getString("nome");
				System.out.println("Valore "+szNome);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connessione(String database, String user, String password) {
		try {
			Class.forName(MYSQL_DRIVER_CLASS).newInstance();
			c_connessione = DriverManager.getConnection(BASE_CONNECTION_URL + database, user, password);
		} 
		catch (Exception e) {
			System.err.println("Cannot connect to database server");
		}
	}
	
	public void setUseTransaction(boolean value) {
		c_useTransaction = value;
	}
	
	public ResultSet createRecordset(String szQuery) throws SQLException {
		Statement statement = c_connessione.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    return statement.executeQuery(szQuery);
	}
	

	public void beginTrans() throws SQLException {
		c_connessione.setAutoCommit(c_useTransaction);
	}
	
	public void commit() throws Exception {
		try {
			if( ! c_useTransaction ) {
				throw new Exception("Errore durante il commit!");
			}
			c_connessione.commit();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			c_connessione.rollback();
		}
	}
	
	public void rollback() throws SQLException {
		c_connessione.rollback();
	}
}
