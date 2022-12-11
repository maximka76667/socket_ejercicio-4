package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	final int PORT = 5000;

	// Vagon configuration
	final int rows = 4, columns = 10;

	public Server() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server started at port " + PORT);

			Vagon vagon = new Vagon(rows, columns);

			// Response a todos los clientes mas 5
			for (int i = 0; i < rows * columns + 5; i++) {

				Socket clientSocket = serverSocket.accept();

				DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

				// Simulacion de la reserva de la silla 1, 2
				// vagon.reservar(1, 2);

				// Simulacion de la reserva de todo el vagon
				// vagon.rellenar();

				while (true) {
					int row = dataInputStream.readInt() - 1;
					int column = dataInputStream.readInt() - 1;

					boolean result = vagon.reservar(row, column);

					// Si reservada con exito devolver 1
					if (result) {
						dataOutputStream.writeInt(1);
						break;
					}

					ArrayList<Integer[]> emptySeats = vagon.getEmptySeats();

					// Si no hay sillas libres devolver 0
					if (emptySeats.size() <= 0) {
						dataOutputStream.writeInt(0);
						break;
					}

					// Else devolver 2
					dataOutputStream.writeInt(2);
					objectOutputStream.writeObject(emptySeats);
				}

			}

			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Server();
	}
}
