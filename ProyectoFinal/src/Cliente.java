
public class Cliente {
	public String email;
	public String contraseña;
	public String nombre;
	public String apellidos;
	public String dni;
	public String telefono;
	
	// CONSTRUCTOR
	public Cliente(String email, String contraseña, String nombre, String apellidos, String dni, String telefono) {
		super();
		setEmail(email);
		setContraseña(contraseña);
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
			System.out.println("El campo EMAIL no puede estar vacio, ni en blanco, ni nulo");
		}
		this.email = email;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		if(contraseña.isBlank() || contraseña.isEmpty() || contraseña == null) {
			System.out.println("El campo CONTRASEÑA no puede estar vacio, ni en blanco, ni nulo");
		}
		this.contraseña = contraseña;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if(nombre.isBlank() || nombre.isEmpty() || nombre == null) {
			System.out.println("El campo NOMBRE no puede estar vacio, ni en blanco, ni nulo");
		}
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		if(apellidos.isBlank() || apellidos.isEmpty() || apellidos == null) {
			System.out.println("El campo APELLIDOS no puede estar vacio, ni en blanco, ni nulo");
		}
		this.apellidos = apellidos;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		if (dni.length() > 9 || dni.length() < 9 || dni.isBlank() || dni.isEmpty() || dni == null) {
			System.out.println("El DNI no puede tener más o menos de 9 caracteres, ni estar vacio, ni estar en blanco, ni ser nulo ");
		}
		this.dni = dni;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		if (telefono.length() > 9 || telefono.length() < 9 || telefono.isBlank() || telefono.isEmpty() || telefono == null) {
			System.out.println("El TELEFONO no puede tener más o menos de 9 caracteres, ni estar vacío, ni estar en blanco, ni ser nulo");
		}
		this.telefono = telefono;
	}
	
}
