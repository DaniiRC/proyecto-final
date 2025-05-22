import java.time.LocalDateTime;
import java.util.HashMap;

public class Pedido implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String emailCliente;
    private HashMap<String, Integer> productos;
    private double total;
    private LocalDateTime fechaHora;
    
    // CONSTRUCTOR
    public Pedido(String emailCliente) {
        this.emailCliente = emailCliente;
        this.productos = new HashMap<>();
        this.fechaHora = LocalDateTime.now();
        this.total = 0.0;
    }
    
    // GETTERS & SETTERS
    public String getEmailCliente() {
    	return emailCliente; 
    }
    
    public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
    
    public HashMap<String, Integer> getProductos() { 
    	return productos; 
    }
    
    public void setProductos(HashMap<String, Integer> productos) {
		this.productos = productos;
	}
    
    public double getTotal() { 
    	return total; 
    }

	public void setTotal(double total) {
		this.total = total;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public LocalDateTime getFechaHora() { 
    	return fechaHora; 
    }
    
    public void añadirProducto(Producto p, int cantidad, double descuento) {
        productos.put(p.getNombre(), cantidad);
        double precioConDescuento = p.calcularPrecioConDescuento(descuento);
        total += precioConDescuento * cantidad;
        p.setStock(p.getStock() - cantidad);
    }
}