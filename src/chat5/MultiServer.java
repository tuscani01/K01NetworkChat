package chat5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer
{
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String s = "";
		String name = "";

	try {
		serverSocket = new ServerSocket(9999);
		System.out.println("서버가 시작되었다");
		
		/*
		 1명의 클라이언트 접속시 접속허용(accept())해주고 동시에
		 MultiServerT쓰레드 생성
		 해당 쓰레드는 1명의 클라이언트가 전송하는 메세지를 읽어서 Echo해주는 역할 담당한다
		 */
			socket = serverSocket.accept();
	
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
			
			if(in != null) {
				name = in.readLine();
				System.out.println(name +" 접속");
				out.println("> "+ name +"님이 접속했다");
			}
			
			while(in != null) {
				s = in.readLine();
				if(s==null) {
					break;
				}
				System.out.println(name +" ==> "+ s);
				out.println("> "+ name +" ==> "+ s);
			}
			
			System.out.println("BYE!!");
	}
	catch (Exception e) {
		System.out.println("예외1:"+ e);
	}
	finally {
		try {
			in.close();
			out.close();
			socket.close();
			serverSocket.close();
		}
		catch (Exception e) {
			System.out.println("예외2:"+ e);
		//e.printStackTrace();
		}
	}
	}
	}