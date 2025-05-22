
public class AceiteOliva extends Producto{
	private static final long serialVersionUID = 1L;
	public String tipos;
	public String procedencia;
	
	// CONSTRUCTOR
	public AceiteOliva(String nombre, double precio, int stock, int valoracion, String tipo, String procedencia) {
		super(nombre, precio, stock, valoracion);
		setTipos(tipo);
		setProcedencia(procedencia);
	}

	// GETTERS & SETTERS
	public String getTipos() {
		return tipos;
	}

	public void setTipos(String tipo) {
		if(!tipo.equalsIgnoreCase("picual") && !tipo.equalsIgnoreCase("hojiblanca") && !tipo.equalsIgnoreCase("cornicabra") && !tipo.equalsIgnoreCase("arbequina")) {
			throw new IllegalArgumentException("El TIPO tiene que ser 'Picual' o 'Hojiblanca' o 'Cornicabra' o 'Arbequina'.");
		}
		this.tipos = tipo;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		if(procedencia.isBlank() || procedencia.isEmpty() || procedencia == null) {
			throw new IllegalArgumentException("La PROCEDENCIA no puede estar ni vacía, ni en blanco, ni nula.");
		}
		this.procedencia = procedencia;
	}
	
	
	
}
