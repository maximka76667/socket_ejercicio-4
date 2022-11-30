package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {

	final int PORT = 3000;

	// Vagon configuration
	final int rows = 4, columns = 10;

	public Controller() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);

			Socket clientSocket = serverSocket.accept();

			DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());

			Vagon vagon = new Vagon(rows, columns);

			int row = dataInputStream.readInt();
			int column = dataInputStream.readInt();

			DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Controller();
	}
}
