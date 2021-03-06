package chat4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultiClient
{

	public static void main(String[] args)
	{
		System.out.println("이름을 입력해라:");
		Scanner scanner = new Scanner(System.in);
		String s_name = scanner.nextLine();
		
		PrintWriter out = null;
		//서버의 메세지를 일겅오는 기능이 Receiver로 옮겨짐
		//BufferedReader in = null;
		
		try {
			/*
			 c:\> java 패키지명 .MultiClient접속할 IP주소
			 => 위와 같이 하면 해당 IP주소로 접속할수있다
			 만약 IP주소가 없다면 localhost(127.0.0.1)로 접속된다
			 */
			String ServerIP = "localhost";
			if(args.length > 0) {
				ServerIP = args[0];
			}
			
			Socket socket = new Socket(ServerIP, 9999);
			//서버와 연결되면 콘솔에 메세지출력
			System.out.println("서버와 연결되었다..");
			
			//서버에서 보낸 메세지를 읽어올 Receiver쓰레드 시작
			Thread receiver = new Receiver(socket);
			//setDaemon(true)가 없으며 독립쓰레드로 생성됨
			receiver.start();
			
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(s_name);
			
			while(out!=null) {
				try {
					String s2 = scanner.nextLine();
					if(s2.equals("q") || s2.equals("Q")) {
						break;
					}
					else {
						//클라이언트 메세지 서버로 전송
						out.println(s2);
					}
				}
				catch (Exception e) {
					System.out.println("예외:"+ e);
				}
			}
			//스트림과 소켓을 종료한다
			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("예외발생[MultiClient]"+ e);
		}
	}
}



