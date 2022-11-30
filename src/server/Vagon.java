package server;

public class Vagon {

	private boolean[][] seats;
	private int rows, columns;

	public Vagon(int rows, int columns) {
		this.seats = new boolean[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}

	public void reservar(int row, int column) {
		boolean seat = seats[row][column];
		if (!seat) {
			seat = true;
			System.out.println("Reservado");
		}
	}

	public boolean hasEmptySeats() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (!seats[i][j])
					return true;
			}
		}
		return false;
	}

}
