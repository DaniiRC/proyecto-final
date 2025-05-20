import java.util.HashMap;
import java.util.Scanner;

public class Aplicacion {
	
	static Scanner entrada = new Scanner(System.in);
	static int opcion;
	static HashMap<String, Cliente> clientes = new HashMap<>();
	static HashMap<String, Producto> productos = new HashMap<>();

	public void productosPredefinidos() {
	    try {
	        productos.put("Oliva Virgen Extra", new AceiteOliva("Oliva Virgen Extra", 10.5, 20, 5, "picual", "Jaén"));
	        productos.put("Hojiblanca Premium", new AceiteOliva("Hojiblanca Premium", 12.0, 15, 6, "hojiblanca", "Córdoba"));
	        
	        productos.put("Aceite con Aroma Dulce", new AceiteAromatizado("Aceite Dulce", 20.3, 10, 7, "azúcar", 4));
	        productos.put("Aceite con Aroma Salado", new AceiteAromatizado("Aceite Salado", 21.2, 5, 8, "sal", 2));
	        
	        productos.put("Crema Hidratante", new Cosmetica("Crema Hidratante", 15.5, 12, 8, "Con aceite de oliva"));
	        productos.put("Bálsamo Labial", new Cosmetica("Bálsamo Labial", 4.75, 25, 7, "Protección solar"));
	        
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error al cargar producto: " + e.getMessage());
	    }
	}
	
	
	public static void main(String[] args) {
		do {
			mostrarMenu();
			System.out.println("Seleccione una opción: ");
			opcion = entrada.nextInt();
			entrada.nextLine();
			
			switch (opcion) {
			case 1: 
				añadirUsuario();
				break;
			case 2:
				iniciarSesion();
				break;
			case 3:
				System.out.println("Saliendo del programa...");
			default:
				System.out.println("Opción introducida no válida. Intentelo de nuevo.");
			}
		} while (opcion != 3);
		
		entrada.close();
	}
	
	public static void mostrarMenu() {
		System.out.println("\n--- MENÚ ---");
		System.out.println("1. Agregar nuevo cliente.");	// OPCION 1
		System.out.println("2. Iniciar sesion.");			// OPCION 2
		System.out.println("3. Salir");						// OPCION 3
	}
	
	public static void añadirUsuario() {
        System.out.println("\n--- NUEVO CLIENTE ---");
        System.out.print("Email: ");
        String email = entrada.nextLine();

        if (clientes.containsKey(email)) {
            System.out.println("Ya existe un cliente con ese email.");
            return;
        }

        System.out.print("Contraseña: ");
        String contraseña = entrada.nextLine();
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = entrada.nextLine();
        System.out.print("DNI: ");
        String dni = entrada.nextLine();
        System.out.print("Teléfono: ");
        String telefono = entrada.nextLine();

        try {
            Cliente nuevo = new Cliente(email, contraseña, nombre, apellidos, dni, telefono);
            clientes.put(email, nuevo);
            System.out.println("Cliente añadido correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al añadir cliente: " + e.getMessage());
        }
    }
	
	public static void iniciarSesion() {
		System.out.println("--- INICIAR SESION ---");
		System.out.println("Email: ");
		String email = entrada.nextLine();
		
		if(!clientes.containsKey(email)) {
			System.out.println("No existe ningun cliente con ese email.");
			return;
		}
		
		System.out.println("Contraseña: ");
		String contraseña = entrada.nextLine();
		
		Cliente cliente = clientes.get(email);
		if (cliente.comprobarContraseña(contraseña)) {
			System.out.println("¡Has iniciado sesion correctamente! Bienvenido cliente " + cliente.getNombre());
		} else {
			System.out.println("¡Contraseña incorrecta!");
		}
	}
}
