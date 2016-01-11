package com.annie.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class DB {
	private Statement stmt=null;

	public static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/selenium?" + "user=root&password=123456");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static Statement getStatement(Connection conn) {
		Statement stmt = null; 
		try {
			if(conn != null) {
				stmt = conn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static ResultSet getResultSet(Statement stmt, String sql) {
		ResultSet rs = null;
		try {
			if(stmt != null) {
				rs = stmt.executeQuery(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void closeConn(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStmt(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeRs(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet query(String strSql)
	{
		ResultSet rs=null;
		try{
			
			rs=stmt.executeQuery(strSql);
			return rs;
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			return rs;
		}
		
	}

	public boolean insert(String[] sqls)
	{
		boolean breturn=false;
		try{
			conn.setAutoCommit(false);
			for(int i=0;i<sqls.length;i++)
			{
				if (sqls[i]!=null){stmt.addBatch(sqls[i]);}
				
			}
			stmt.executeBatch();
		   conn.commit();
		   conn.setAutoCommit(true);
		   breturn=true;
		   }
		catch(SQLException ex){}
		return breturn;
	}
	

	public int executeSql(String strSql)
	{System.out.println("sql:"+strSql);
	 int result=0;
	 try{
		 stmt=conn.createStatement();
		 result=stmt.executeUpdate(strSql);
		 
	 }catch(SQLException ex){
		 
		 System.out.println("at DB.executeSql()");
		 ex.printStackTrace();
	 }
	 return result;
	}
	
	
	public static void close(Connection conn){
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt !=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
