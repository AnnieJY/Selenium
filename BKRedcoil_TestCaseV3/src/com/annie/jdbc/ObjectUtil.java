package com.annie.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.annie.base.ObjectTest;



public class ObjectUtil {

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	public void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addObject(ObjectTest obt) {
		String sql = "insert into test_object (id,website,page,area,object_type,object_key_type,object_pagename, object_key,description,status,createtime,createuser,updatetime,updateuser,deletetime,deleteuser) values (?,?, ?, ?, ?, ?, ?,?,?, ?, ?, ?, ?, ?, ?,?)";
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, obt.getId());
			pstmt.setString(2, obt.getWebsite());
            pstmt.setString(3, obt.getPage());
			pstmt.setString(4, obt.getArea());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch obtock
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
			DB.close(pstmt);
		}
	}
}
