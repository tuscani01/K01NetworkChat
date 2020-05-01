package chat77;

import java.net.Socket;
import java.util.Scanner;

public class MultiClient {
	
	/*
	 이름, 공백에 엔터값만 입력
	 
	 */
	public static String nameException(String name) throws CustomException{
		
		if(name.contains(" ")) {
			System.out.println("이름에 공백이 들어 갈수없다");
			CustomException ce = new CustomException();
			throw ce;
		} else if(name.equals(null)) {
			System.out.println("이름에 null값이 들어 갈수없다");
			CustomException ce = new CustomException();
			throw ce;
		} else if(name.equals("\r\n")) {
			System.out.println("엔터값은 이름이 될수 없다");
			CustomException ce = new CustomException();
			throw ce;
		}
		
		return name;
	}

	public static void main(String[] args) {
		
		String s_name;
		
		while(true) {
			try {
				System.out.print("이름을 입력하세요:");
				Scanner scanner = new Scanner(System.in);
				s_name = nameException(scanner.nextLine());
				System.out.println(s_name);
				break;
			}
			catch(Exception e) {
			}
		}
		
		try {
			String ServerIP = "localhost";
			if(args.length > 0) {
				ServerIP = args[0];
			}
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("서버와 연결되었습니다..");
			Thread receiver = new Receiver(socket);
			receiver.start();
			
			Thread sender = new Sender(socket, s_name);
			sender.start();
		}
		catch(Exception e) {
			System.out.println("예외발생[MultiClient]" + e);
		}
	}
}