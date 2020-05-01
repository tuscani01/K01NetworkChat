package chat4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Receiver extends Thread
{
	Socket socket;
	BufferedReader in = null;
	
	//Socket객체를 매개변수로 받는 생성자
	public Receiver(Socket socket) {
		this.socket = socket;
		//Socket객체 기반으로 input스트림을 생성한다
		//서버가 보내는 메세지를 읽어오는 역할을한다
		try {
			in = new BufferedReader(new
					InputStreamReader(this.socket.getInputStream()));
		}
		catch (Exception e) {
			System.out.println("예외1:"+ e);
		}
	}
	/*
	//Thread에서 main()메소드 역할 하는 함수로
	//직접 호출하면 안되고 반드시 start()를 통해
	//간접호출해야한다
	 */
	
	@Override
	public void run() {
		//스트림 통해 서버가 보낸 내용을 라인단위로 읽어온다
		while(in != null) {
			try {
				System.out.println("Thread Receive : "+ in.readLine());
			}
			catch (SocketException e) {
				System.out.println("SocketException발생됨");
				break;
			}
			catch (Exception e) {
				System.out.println("예외3:"+ e);
			}
		}
		try {
			in.close();
		}
		catch (Exception e) {
			System.out.println("예외3:"+ e);
		}
	}
}