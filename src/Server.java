import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	private Socket socket;
	private ServerSocket server;
	private DataInputStream in;
	private int port;
	private String hostAddress;
	private boolean connected;

	public Server(int port) throws UnknownHostException {
		this.port = port;
		this.hostAddress = InetAddress.getLocalHost().getHostAddress();
		this.connected = false;

		try {
			server = new ServerSocket(port);
			System.out.println("Server has started on " + hostAddress + ":" + port + ".");
			System.out.println("Waiting for a connection...");
			socket = server.accept();
			connected = true;
			System.out.println("A client connected." + " (" + socket.getInetAddress() + ")");
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println(e);
		}

		// TODO: Event loop logic for server

		if (connected) {
			try {
				socket.close();
				in.close();
				connected = false;
			} catch (IOException e) {
				System.err.println(e);
			}
		}

	}

	public static void main(String[] args) throws UnknownHostException {
		new Server(80);
	}

}
