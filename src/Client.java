import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String address;
	private int port;

	public Client(String address, int port) throws IOException {
		this.address = address;
		this.port = port;

		try {
			socket = new Socket(address, port);
			in = new DataInputStream(System.in);
			out = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO: Event loop logic for client

		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new Client("127.0.0.1", 80);
	}

}
