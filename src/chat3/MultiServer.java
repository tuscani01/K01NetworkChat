package chat3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer
{
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	static PrintWriter out = null;
	static BufferedReader in = null;
	static String s= "";
	
	public MultiServer() {
		//실행부없음
	}
	//서버 초기화 담당 메소드
	public static void init() {
		//클라이언트로부터 전송받은 이름저장
		String name = "";
	
	try {
		//9999포트를 열고 클라이언트 접속대기
		serverSocket = new ServerSocket(9999);
		System.out.println("서버가 시작되었다");
		
		//클라이언트의 접속요청을 허가함
		socket = serverSocket.accept();
		
		System.out.println(socket.getInetAddress() +":"+
		socket.getPort());
		//클라이언트로 메세지 보낼준비(output스트림)
		out = new PrintWriter(socket.getOutputStream(), true);
		//클라이언트가 보내주는 메세지를 읽을준비(input스트림)
		in = new BufferedReader(new 
				InputStreamReader(socket.getInputStream()));
		
		//클라이언트가 최초로 보내는 메세지는 접속자의 이름
		if(in != null) {
			name = in.readLine();
			//이름을 콘솔에 출력하고..
			System.out.println(name +" 접속");
			//클라이언트로 echo해준다
			out.println("> "+ name +"님이 접속했습니다");
		}
		
		//클라이언트가 전송하는 메세지를 계속해서 읽어옴
		while(in !=null) {
		
		s = in.readLine();
		if(s==null) {
			break;
		}
		//읽어온 메세지 콘솔 출력
		System.out.println(name +" ==> "+s);
		//DB처리는 여기다 하면된다
		
		//클라이언트에게 Echo해준다
		sendAllMsg(name, s);
		}
		
		System.out.println("BYE");
	}
	catch (Exception e) {
		System.out.println("예외1:"+ e);
		//e.printStackTrace();
	}
	finally {
		try {
			//입출력스트림 종료
			in.close();
			out.close();
			//소켓 종료
			socket.close();
			serverSocket.close();
		}
		catch (Exception e) {
			System.out.println("예외2:"+ e);
			//e.printStackTrace();
			}
		}
	}

public static void sendAllMsg(String name, String msg) {
	try {
		out.println("> "+ name +" ==> "+ msg);
	}
	catch (Exception e) {
		System.out.println("예외:"+e);
	}
}
public static void main(String[] args) {
	init();
}
}
