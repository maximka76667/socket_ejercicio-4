package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

	public Client() {

		Scanner scanner = new Scanner(System.in);

		try {
			Socket clientSocket = new Socket("localhost", 5000);
			DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

			while (true) {
				System.out.println("Reserva silla: ");
				System.out.print("Fila: ");
				int row = scanner.nextInt();
				scanner.nextLine();

				System.out.print("Columna: ");
				int column = scanner.nextInt();
				scanner.nextLine();

				dataOutputStream.writeInt(row);
				dataOutputStream.writeInt(column);

				int response = dataInputStream.readInt();

				if (response == 0) {
					System.out.println("No hay más lugar en el vagon");
					break;
				}

				if (response == 1) {
					System.out.println("Reservado");
					break;
				}

				System.out.println("Ocupado");
				System.out.println("Lista de las sillas libres");
				ArrayList<Integer[]> emptySeats = (ArrayList<Integer[]>) objectInputStream.readObject();

				for (Integer[] emptySeat : emptySeats) {
					System.out.println((emptySeat[0] + 1) + " " + (emptySeat[1] + 1));
				}

			}

			clientSocket.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		scanner.close();
	}

	public static void main(String[] args) {
		new Client();
	}
}
