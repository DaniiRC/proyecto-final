
public class Accesorios extends Producto{
	public String material;
	
	// CONSTRUCTOR
	public Accesorios(String nombre, double precio, int stock, int valoracion, String material) {
		super(nombre, precio, stock, valoracion);
		this.material = material;
	}

	// GETTERS & SETTERS
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		if(material.isBlank() || material.isEmpty() || material == null) {
			throw new IllegalArgumentException("El MATERIAL no puede estar ni vacío, ni en blanco, ni nulo.");
		}
		this.material = material;
	}

	
	
	
}
