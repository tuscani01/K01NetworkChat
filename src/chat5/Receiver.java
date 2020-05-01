package chat5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

//클라이언트가 입력한 메세지를 서버로 전송해주는 쓰레드클래스
public class Receiver extends Thread
{
	Socket socket;
	BufferedReader in = null;
	
	
	//생성자에서 output스트림생성
	public Receiver(Socket socket) {
		this.socket = socket;
		//Socket객체 기반으로 input스트림을 생성한다
		//서버가 보내는 메세지를 읽어오는 역할을한다
		try {
			in = new BufferedReader(new
					InputStreamReader(this.socket.getInputStream()));
		}
		catch (Exception e) {
			System.out.println("예외>Receiver>생성자:"+ e);
		}
	}
	
	@Override
	public void run() {
		//소켓 종료되면 while()문을 벗어나서 input스트림을 종료한다
		//스트림 통해 서버가 보낸 내용을 라인단위로 읽어온다
		
		//Q로 입력하기전까지의 메세지를 서버로 전송한다
		while(in != null) {
			try {
				//클라이언트가 입력한 "대화명"을 서버로 전송
				System.out.println("Thread Receive : "+ in.readLine());
			}
			catch (SocketException ne) {
				System.out.println("SocketException발생됨");
				break;
			}
			catch (Exception e) {
				System.out.println("예외>Receiver>run1:"+ e);
			}
		}
		try {
			in.close();
		}
		catch (Exception e) {
			System.out.println("예외>Receiver>run2:"+ e);
		}
	}
}
