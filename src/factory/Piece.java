package factory;

public class Piece {

	private String reference;

	public Piece(String reference) {
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String toString() {
		return "Reference: " + reference;
	}
}
