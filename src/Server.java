import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private Socket socket;
	private ServerSocket server;
	private DataInputStream in;
	private int port;

	public Server(int port) {
		this.port = port;

		try {
			server = new ServerSocket(port);
			System.out.println("Server has started on 127.0.0.1:80.\r\nWaiting for a connection...");
			socket = server.accept();
			System.out.println("A client connected.");
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			// TODO: Event loop logic for server

			socket.close();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server(80);
	}

}
