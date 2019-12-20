package modelo;

public class Zapatillas {

	int zapId;
	String zapName;

	// Constructor
	public Zapatillas() {

	}
	
	public Zapatillas(int id, String nombre) {
		this.zapId = id;
		this.zapName = nombre;
	}

	// Getters y setters

	public int getZapId() {
		return zapId;
	}

	public void setZapId(int zapId) {
		this.zapId = zapId;
	}

	public String getZapName() {
		return zapName;
	}

	public void setZapName(String zapName) {
		this.zapName = zapName;
	}

}
