import java.io.Serializable;
import java.util.HashSet;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	public String email;
	public int hashContraseña;
	public String nombre;
	public String apellidos;
	public String dni;
	public String telefono;
	
	@SuppressWarnings("unused")
	private HashSet<String> productosComprados = new HashSet<>();
	
	// CONSTRUCTOR
	public Cliente(String email, String contraseña, String nombre, String apellidos, String dni, String telefono) {
		super();
		setEmail(email);
		setHashContraseña(contraseña);
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
		setTelefono(telefono);
	}

	// GETTERS & SETTERS
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if (email.isBlank() || email.isEmpty() || email == null)  {
			throw new IllegalArgumentException("El campo EMAIL no puede estar vacio, ni en blanco, ni nulo.");
		}
		this.email = email;
	}
	
	public int getHashContraseña() {
		return hashContraseña;
	}
	
	public void setHashContraseña(String contraseña) {
		if(contraseña.isBlank() || contraseña.isEmpty() || contraseña == null) {
			throw new IllegalArgumentException("El campo CONTRASEÑA no puede estar vacio, ni en blanco, ni nulo.");
		}
		this.hashContraseña = contraseña.hashCode();
	}
	
	public boolean comprobarContraseña(String contraseña) {
        return this.hashContraseña == contraseña.hashCode();
    }
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if(nombre.isBlank() || nombre.isEmpty() || nombre == null) {
			throw new IllegalArgumentException("El campo NOMBRE no puede estar vacio, ni en blanco, ni nulo.");
		}
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		if(apellidos.isBlank() || apellidos.isEmpty() || apellidos == null) {
			throw new IllegalArgumentException("El campo APELLIDOS no puede estar vacio, ni en blanco, ni nulo.");
		}
		this.apellidos = apellidos;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		if (dni.isBlank() || dni == null) {
			throw new IllegalArgumentException("El DNI no puede estar vacio.");
		}
		
		if (dni.length() != 9) {
			throw new IllegalArgumentException("El DNI debe tener 8 números seguidos de una letra mayúscula.");
		}
		
		String numeros = dni.substring(0, 8);
		char letra = dni.charAt(8);
		
		for (int i = 0; i < 8; i++) {
			if(!Character.isDigit(numeros.charAt(i))) {
				throw new IllegalArgumentException("Los primeros 8 caracteres del DNI deben ser números.");
			}
		}
		
		int numero = Integer.parseInt(numeros);
		char letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numero % 23);
		
		if (letra != letrasDNI ) {
			throw new IllegalArgumentException("La letra del DNI no es válida. Debería de ser "+letrasDNI+".");
		}
		
		this.dni = dni;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		if (telefono.isBlank() || telefono == null) {
			throw new IllegalArgumentException("El TELÉFONO no puede estar vacío.");
		}
		
		if (telefono.length() != 9) {
			throw new IllegalArgumentException("El TELÉFONO debe tener 9 dígitos.");
		}
		
		for (char c:telefono.toCharArray()) {
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException("El TELÉFONO solo puede contener números.");
			}
		}
		
		this.telefono = telefono;
	}
	
	public void registrarCompra(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.isBlank()) {
            throw new IllegalArgumentException("El PRODUCTO no puede estar vacío");
        }
        productosComprados.add(nombreProducto);
    }
	
	public boolean haCompradoProducto(String nombreProducto) {
        return productosComprados.contains(nombreProducto);
    }
	
	public HashSet<String> getProductosComprados() {
        return new HashSet<>(productosComprados);
    }
    
    @Override
    public String toString() {
        return "Datos del cliente: Email -> " + email + ", Nombre -> " + nombre + ", Apellidos -> " + apellidos + 
               ", DNI -> " + dni + ", Telefono -> " + telefono;
    }
}
