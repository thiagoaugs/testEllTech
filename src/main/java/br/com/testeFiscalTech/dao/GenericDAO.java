package br.com.testeFiscalTech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Criado em: 02/04/2019
 * @author thiagoaugs
 * Esta classe encapsula a os metodos genericos para update/list.
 */
public abstract class GenericDAO {

	protected void setParamsIntoPreparedStatement(PreparedStatement pstmt,
			List params, boolean select, Connection conn) throws Exception {

		if (conn == null)
			throw new SQLException("Conex�o NULA");

		int i = 0;
		Object param = null;
		try {
			for (i = 0; i < params.size(); i++) {
				param = params.get(i);
				if (param == null) {
					pstmt.setNull(i + 1, Types.NULL);
				}
				/**
				 * Neste caso, se b == true, seta valor 1, caso contr�rio seta
				 * NULL
				 */
				else if (param instanceof Boolean) {
					boolean b = ((Boolean) param).booleanValue();
					if (b)
						pstmt.setInt(i + 1, 1);
					else
						pstmt.setInt(i + 1, 0);
				} else if (param instanceof Date
						|| param instanceof java.sql.Date) {
					Date d = (Date) param;
					pstmt.setTimestamp(i + 1,
							new java.sql.Timestamp(d.getTime()));
				} else if (param instanceof Integer) {
					int j = ((Integer) param).intValue();
					if (j >= 0)
						pstmt.setInt(i + 1, j);
					else
						pstmt.setNull(i + 1, Types.INTEGER);
				} else if (param instanceof Long) {
					long j = ((Long) param).longValue();
					if (j >= 0)
						pstmt.setLong(i + 1, j);
					else
						pstmt.setNull(i + 1, Types.NUMERIC);
				} else if (param instanceof Double) {
					double n = ((Double) param).doubleValue();
					if (n > 0D)
						pstmt.setDouble(i + 1, n);
					else
						pstmt.setNull(i + 1, Types.DOUBLE);

				} else if (param instanceof Timestamp) {
					Timestamp t = (Timestamp) param;
					pstmt.setTimestamp(i + 1, t);
				} else if (param instanceof Time) {
					Time t = (Time) param;
					pstmt.setTime(i + 1, t);
				} else {
					Object obj = param;
					pstmt.setObject(i + 1, obj);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	protected Integer executeUpdateId(Connection connection, String sql,
			List params, boolean autoCommit) throws Exception {
		int lastId = 0;
		if (connection == null)
			throw new SQLException("Conexão NULA");
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			setParamsIntoPreparedStatement(pstmt, params, false, connection);
			pstmt.executeUpdate();
			final ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				lastId = rs.getInt(1);
			}
		} catch (SQLException e) {
			lastId = 0;
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw e1;
			}
			throw e;
		} finally {
			try {
				if (autoCommit) {
					if (connection != null)
						connection.commit();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return lastId;
	}

	protected void executeUpdate(Connection connection, String sql,
			List params, boolean autoCommit) throws Exception {
		if (connection == null)
			throw new SQLException("Conexï¿½o NULA");
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			setParamsIntoPreparedStatement(pstmt, params, false, connection);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw e1;
			}
			throw e;
		} finally {
			try {
				if (autoCommit) {
					if (connection != null)
						connection.commit();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	protected List listar(Connection connection, String sql,
			List params, boolean autoCommit) throws Exception {

		List lista = null;
		if (connection == null)
			throw new SQLException("Conexï¿½o NULA");
		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement(sql);
			setParamsIntoPreparedStatement(pstmt, params, false, connection);
			final ResultSet rs = pstmt.executeQuery();

			lista = new ArrayList();
			if (rs.next()) {
				lista.add(rs);
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw e1;
			}
			throw e;
		} finally {
			try {
				if (autoCommit) {
					if (connection != null)
						connection.commit();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return lista;
	}


	public Connection getConnection() throws Exception {
		return ConnectionFactory.getConnection();
	}

	/**
	 * This method is intended to close all tracks of a connection The resultset
	 * parameter can be null The other parameters will send a log warn if
	 * something goes wrong.
	 *
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	protected void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				if (stmt == null) {
					// busca o stmt do RS e fecha ele se nï¿½o for nulo
					Statement st = rs.getStatement();
					if (st != null)
						st.close();
				}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			} else
				System.out.print("CORE " + " Statement was null");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			} else
				System.out.print("CORE " + " Connection was null or closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void finalize() throws Throwable {
		super.finalize();
	}

}
