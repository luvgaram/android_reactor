import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Dispatcher {
	private final int HEADER_SIZE = 6;

	public void dispatch(ServerSocket serverSocket, HandleMap handleMap) {
		try {
			Socket socket = serverSocket.accept();
			
			Runnable demultiplexer = new Demultiplexer(socket, handleMap);
			Thread thread = new Thread(demultiplexer);
			thread.start();
			
//			demultiplex(socket, handleMap);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
//	public void demultiplex(Socket socket, HandleMap handleMap) {
//		try {
//			InputStream inputStream = socket.getInputStream();
//			
//			byte[] buffer = new byte[HEADER_SIZE];
//			inputStream.read(buffer);
//			String header = new String(buffer);
//			
//			// HandleMap을 받아서 헤더값으로 get한 EventHandler에게 이벤트 발생
//			handleMap.get(header).handleEvent(inputStream);
//			
//			// 데이터 처리 후 접속 종료
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
