package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class JdbcDemo {
	public static void main(String[] args) {

		String driver = "com.mysql.jdbc.Driver";
		// String url =
		// "jdbc:mysql://localhost:3306/mysql?useUnicode=true&amp;characterEncoding=gb2312";
		String url = "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=gbk";
		// String url = "jdbc:mysql://localhost:3306/student";
		String uid = "root";
		String pwd = "";

		try {
			// װ������
			Class.forName(driver);
			// �������ݿ�����
			Connection conn = DriverManager.getConnection(url, uid, pwd);

			/*------��ͨ��ѯ------*/
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from stuinfo");
			// String strsql="select * from stuinfo";
			// Statement stm = conn.createStatement();
			// ResultSet rs = stm.executeQuery(strsql);
			while (rs.next()) {
				System.out.println(rs.getRow() + "," + rs.getString(1) + ","
						+ rs.getString(2) + "," + rs.getString(3) + ","
						+ rs.getInt(4));
			}

			/*---ʹ��Ԥ������䣬��ȫ�Ըߣ��ٶȿ�---*/
			String sql = "select * from stuinfo where snum=? and age = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, "0001");
			pstm.setInt(2, 18);
			ResultSet rs1 = pstm.executeQuery();
			while (rs1.next()) {
				System.out.println(rs1.getString(2) + "��������:" + rs1.getInt(4));
			}

			/*-----ִ�д洢���̣���������ʵ��insert����-----*/
			/*
			 * String a = "{call paddstu(?,?)}"; CallableStatement cst1 =
			 * conn.prepareCall(a); cst1.setString(1,"111");
			 * cst1.setString(2,"������"); cst1.executeUpdate();
			 */

			/*-----ִ�д���������洢����-----*/
			String pp = "{call pcountstu(?)}";
			CallableStatement cst2 = conn.prepareCall(pp);
			cst2.registerOutParameter(1, Types.INTEGER);
			cst2.execute();
			int count = cst2.getInt(1);
			System.out.println("���м�¼" + count + "��");

			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

        public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/cms?characterEncoding=utf8";
		String user = "root";
		String pwd = "123";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, pwd);

			System.out.println("���ݿ����ƣ�" + conn.getCatalog());
			System.out.println(conn.getClientInfo());
			
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println(dbmd.getDatabaseProductName());
			System.out.println(dbmd.getDriverName());
			System.out.println(dbmd.getURL());
			System.out.println(dbmd.getUserName());
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
