
public class Producto {
	public String nombre;
	public double precio;
	public int stock;
	public int valoracion;
	
	// CONTRUCTOR
	public Producto(String nombre, double precio, int stock, int valoracion) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.valoracion = valoracion;
	}
	
	// GETTERS & SETTERS
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if(nombre.length() < 4 || nombre.length() > 20 || nombre.isBlank()) {
			throw new IllegalArgumentException("El NOMBRE del producto no puede tener menos de 4 o más de 20 carácteres.");
		}
		
		if(nombre.isBlank()) {
			throw new IllegalArgumentException("El NOMBRE no puede estar en blanco.");
		}
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		if(precio < 0) {
			throw new IllegalArgumentException("El PRECIO tiene que ser mayor a 0.");
		}
		this.precio = precio;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		if(stock < 0) {
			throw new IllegalArgumentException("El STOCK tiene que ser mayor a 0.");
		}
		this.stock = stock;
	}
	
	public int getValoracion() {
		return valoracion;
	}
	
	public void setValoracion(int valoracion) {
		if(valoracion < 0 || valoracion > 10) {
			throw new IllegalArgumentException("La VALORACIÓN tiene que estar entre los números 0 y 10.");
		}
		this.valoracion = valoracion;
	}
	
}
