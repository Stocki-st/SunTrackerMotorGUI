package communication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class extends the Player class by a SocketPlayer and offers related methods
 * @author stocki
 *
 */
public class SocketConnection {

	final static int DEFAUTL_PORT = 7777;

	private static int port;
	private static String address;
	static ServerSocket serverSocket;
	static Socket clientSocket;
	static PrintWriter clientSocketOutput;
	static BufferedReader clientSocketInput;
	static Socket MyClient;

    static DataOutputStream OutputStream = null;
    static DataInputStream InputStream = null;

	public SocketConnection(String address, int port) {
			SocketConnection.port = port;
			SocketConnection.address = address;
	}

	public static String getAddress() {
		return address;
	}

	public static void setAddress(String address) {
		SocketConnection.address = address;
	}


	/**
	 * set the port number of the socket
	 * @param port
	 */
	public void setPort(int port) {
		SocketConnection.port = port;
	}

	/**
	 * get the port number of the socket
	 * @return port
	 */
	public static int getPort() {
		return port;
	}

	/**
	 * opens the socket connection and waits for client to start the game
	 * @throws IOException
	 */
	public static void createSocket() throws IOException {
	    try {
	           MyClient = new Socket(address, port);
	           System.out.println("Connection established");

	           OutputStream = new DataOutputStream(MyClient.getOutputStream());
	           InputStream = new DataInputStream(MyClient.getInputStream());
	    }
	    catch (IOException e) {
	        System.out.println(e);
	    }

	}
}