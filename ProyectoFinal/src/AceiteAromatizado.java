
public class AceiteAromatizado extends Producto{
	
	public String ingreAromatizante;
	public int intensidad;
	
	// CONTRUCTOR
	public AceiteAromatizado(String nombre, double precio, int stock, int valoracion, String ingreAromatizante, int intensidad) {
		super(nombre, precio, stock, valoracion);
		this.ingreAromatizante = ingreAromatizante;
		this.intensidad = intensidad;
	}	
	
	// GETTERS & SETTERS
	public String getIngreAromatizante() {
		return ingreAromatizante;
	}

	public void setIngreAromatizante(String ingreAromatizante) {
		if(ingreAromatizante.isBlank() || ingreAromatizante.isEmpty() || ingreAromatizante == null) {
			throw new IllegalArgumentException("El INGREDIENTE AROMATIZANTE no puede estar ni vacío, ni en blanco, ni nulo.");
		}
		this.ingreAromatizante = ingreAromatizante;
	}

	public int getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(int intensidad) {
		if(intensidad < 1 || intensidad > 5) {
			throw new IllegalArgumentException("La INTENSIDAD tiene estar entre los números 1 y 5.");
		}
		this.intensidad = intensidad;
	}
}
