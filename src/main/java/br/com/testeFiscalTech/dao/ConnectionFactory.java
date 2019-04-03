package br.com.testeFiscalTech.dao;



import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Criado em: 02/04/2019
 * @author thiagoaugs
 * Esta classe encapsula a abertura e fechamento de conexoes de um banco de dados.
 */

public class ConnectionFactory {

	public static Connection getConnection() throws Exception {

		String jdbcURL = "jdbc:postgresql://localhost:5432/example" ;
		String user = "postgres";
		String passwd = "postgres";

		try {

			DriverManager.registerDriver(new org.postgresql.Driver());
			Connection conn;
			conn = DriverManager.getConnection(jdbcURL, user, passwd);
			conn.setAutoCommit(true);
			return conn;
	
		} catch (Exception e) {
			try {
				throw e;
			} catch (java.sql.SQLException e1) {
				System.out.println(e1);			}
		}
		return null;
	}
	
	
}
