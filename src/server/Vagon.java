package server;

import java.util.ArrayList;

public class Vagon {

	private boolean[][] seats;
	private int rows, columns;

	public Vagon(int rows, int columns) {
		this.seats = new boolean[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}

	// Devuelve true = reservado y false = ocupado
	public boolean reservar(int row, int column) {
		row = includeInRange(row, 0, this.rows - 1);
		column = includeInRange(column, 0, this.columns - 1);

		boolean seat = seats[row][column];
		if (!seat) {
			seats[row][column] = true;
			System.out.println("Reservado " + (row + 1) + " " + (column + 1));
			return true;
		}

		return false;
	}

	public ArrayList<Integer[]> getEmptySeats() {
		ArrayList<Integer[]> emptySeats = new ArrayList<Integer[]>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (!seats[i][j])
					emptySeats.add(new Integer[] { i, j });
			}
		}
		return emptySeats;
	}

	// Ocupar todas las sillas para simulacion del vagon lleno
	public void rellenar() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				seats[i][j] = true;
			}
		}
	}

	// Si cliente pone un numero mayor que numero posible
	// se usa numero maximo posible
	// y contrario con numero minimo
	public int includeInRange(int number, int min, int max) {
		if (number > max) {
			return max;
		}
		if (number < min) {
			return min;
		}
		return number;
	}

}
