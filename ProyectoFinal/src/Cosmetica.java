
public class Cosmetica extends Producto{
	private static final long serialVersionUID = 1L;
	public String descripcion;

	// CONSTRUCTOR
	public Cosmetica(String nombre, double precio, int stock, int valoracion, String descripcion) {
		super(nombre, precio, stock, valoracion);
		setDescripcion(descripcion);
	}

	// GETTERS & SETTERS
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if(descripcion.isBlank() || descripcion.isEmpty() || descripcion == null) {
			throw new IllegalArgumentException("La DESCRIPCIÓN no puede estar ni vacío, ni en blanco, ni nulo.");
		}
		this.descripcion = descripcion;
	}
	
}
