package chat77;

/*
 jdbc연동
 */

public class IConnect
{
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin://@localhost:1521:orcl";
	
	void connect(String user, Stiring pass); //DB연결
	void close(); //자원반납

}
