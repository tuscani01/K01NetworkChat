package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiServer {

		public static void main(String[] args) {

			ServerSocket serverSocket = null;
			Socket socket = null;
			PrintWriter out = null;
			BufferedReader in = null;
			String s = "";
			String name = "";

			try {
				serverSocket = new ServerSocket(9999);
				System.out.println("서버가 시작되었습니다.");

				socket = serverSocket.accept();

				System.out.println(socket.getInetAddress() +":"+
				socket.getPort());

				if(in != null) {
					name = in.readLine();
					System.out.println(name +" 접속");
					out.println("> "+ name +"님이 접속했습니다.");
				}

				while(in != null) {
					s = in.readLine();
					if(s==null) {
						break;
					}
					System.out.println(name +" ==> "+ s);
					out.println(">  "+ name +" ==> "+ s);
				}

				System.out.println("Bye...!!!");
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
				}
			}
		}
	}
	public class MultiClient {

		public static void main(String[] args) {

			System.out.print("이름을 입력하세요:");
			Scanner scanner = new Scanner(System.in);
			String s_name = scanner.nextLine();

			PrintWriter out = null;
			BufferedReader in = null;

			try {
				String ServerIP = "localhost";
				if(args.length > 0) {
					ServerIP = args[0];
				}
				Socket socket = new Socket(ServerIP, 9999);
				System.out.println("서버와 연결되었습니다...");

				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				out.println(s_name);
				

				while(out!=null) {
					try {
						if(in!=null) {
							System.out.println("Receive : "+ in.readLine());
						}

						String s2 = scanner.nextLine();
						if(s2.equals("q") || s2.equals("Q")) {
							break;
						}
						else {
							out.println(s2);
						}
					}
					catch (Exception e) {
						System.out.println("예외:"+ e);
					}
				}

				in.close();
				out.close();
				socket.close();
			}
			catch (Exception e) {
				System.out.println("예외발생[MultiClient]"+ e);
			}
		}
	}

}
