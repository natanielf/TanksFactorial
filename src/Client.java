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
	private boolean connected;

	public Client(String address, int port) throws IOException {
		this.address = address;
		this.port = port;
		this.connected = false;

		try {
			System.out.println("Connecting to " + address + ":" + port + "...");
			socket = new Socket(address, port);
			in = new DataInputStream(System.in);
			out = new DataOutputStream(socket.getOutputStream());
			connected = true;
			System.out.println("Connected to server.");
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) throws IOException {
		new Client("127.0.0.1", 80);
	}

	public void disconnect() {
		if (connected) {
			try {
				in.close();
				out.close();
				socket.close();
				connected = false;
				System.out.println("Disconnected from the server.");
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public void sendData(String tank) {

	}

	public String getData() {
		return "{}";
	}

}
