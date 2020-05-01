package chat77;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IConnectImpl implements IConnect {
{
	
	public PreparedStatement psmt;
	public Connection con;
	public ResultSet rs;
	
	public IConnectImpl() {
		System.out.println("IConnectImpl 기본생성자 호출");
	}
	
	public IConnectImpl(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			connect(user, pass);
			System.out.println("드라이버 로딩성공");
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	@Override
	public void connect(String user, String pass) {
		try {
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
			System.out.println("드라이버 연결성공");
		}
		catch(SQLException e) {
			System.out.println("데이터베이스 연결오류");
			e.printStackTrace();
		}
	}
	@Override
	public void close() {
		try {
			if(con != null) con.close();
			if(psmt != null) psmt.close();
			if(rs != null) rs.close();
			System.out.println("자원 반납완료");
		}
		catch(Exception e) {
			System.out.println("자원 반납시 오류발생");
		}
		}
	}