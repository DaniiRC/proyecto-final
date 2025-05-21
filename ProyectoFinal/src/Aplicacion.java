	import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
	
	static Scanner entrada = new Scanner(System.in);
	static int opcion;
	static HashMap<String, Cliente> clientes = new HashMap<>();
	static HashMap<String, Producto> productos = new HashMap<>();
	static List<Pedido> pedidos = new ArrayList<>();
    static Cliente clienteActual = null;

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
                System.out.print("Nombre para guardar los clientes: ");
                guardarClientes(entrada.nextLine());
                break;
            case 4:
                System.out.print("Nombre del archivo para cargar clientes: ");
                cargarClientes(entrada.nextLine());
                break;
			case 5:
				System.out.println("Saliendo del programa...");
				break;
			default:
				System.out.println("Opción introducida no válida. Intentelo de nuevo.");
			}
		} while (opcion != 5);
		
		entrada.close();
	}
	
	public static void mostrarMenu() {
		System.out.println("\n--- MENÚ ---");
		System.out.println("1. Agregar nuevo cliente.");	// OPCION 1
		System.out.println("2. Iniciar sesion.");			// OPCION 2
		System.out.println("3. Guardar datos");				// OPCION 3
        System.out.println("4. Cargar datos");				// OPCION 4
		System.out.println("5. Salir");						// OPCION 5
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
	
	public static void menuCliente() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("1. Realizar pedido");
            System.out.println("2. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            opcion = entrada.nextInt();
            entrada.nextLine();
            
            switch(opcion) {
                case 1:
                    realizarPedido();
                    break;
                case 2:
                    clienteActual = null;
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while(opcion != 2 && clienteActual != null);
    }
    
    public static void realizarPedido() {
        Pedido pedido = new Pedido(clienteActual.getEmail());
        
        System.out.println("\n--- PRODUCTOS DISPONIBLES ---");
        productos.forEach((nombre, producto) -> {
            System.out.printf("%s - Precio: %.2f€ - Stock: %d\n", 
                nombre, producto.getPrecio(), producto.getStock());
        });
        
        String seleccion;
        int cantidad;
        do {
            System.out.print("\nIntroduce el nombre del producto (0 para terminar): ");
            seleccion = entrada.nextLine();
            
            if(!seleccion.equals("0") && productos.containsKey(seleccion)) {
                Producto p = productos.get(seleccion);
                System.out.print("Cantidad: ");
                cantidad = entrada.nextInt();
                entrada.nextLine();
                
                if(cantidad > 0 && cantidad <= p.getStock()) {
                    pedido.añadirProducto(p, cantidad);
                    p.setStock(p.getStock() - cantidad);
                    System.out.printf("Añadido: %d x %s\n", cantidad, p.getNombre());
                } else {
                    System.out.println("Cantidad no válida o stock insuficiente.");
                }
            } else if(!seleccion.equals("0")) {
                System.out.println("Producto no encontrado.");
            }
        } while(!seleccion.equals("0"));
        
        pedidos.add(pedido);
        System.out.printf("\nPedido realizado. Total: %.2f€\n", pedido.getTotal());
    }
    
    private static void guardarClientes(String nombreArchivo) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nombreArchivo + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(clientes);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Se he guardado correctamente!");
        } catch (IOException e) {
            System.out.println("Error al guardar los clientes: " + e.getMessage());
        }
    }

    private static void cargarClientes(String nombreArchivo) {
    	try {
			FileInputStream fileInputStream = new FileInputStream(nombreArchivo + ".ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			@SuppressWarnings("unchecked")
			HashMap<String, Cliente> clientesCargados = (HashMap<String, Cliente>) objectInputStream.readObject();
			clientes = clientesCargados;
			objectInputStream.close();
			System.out.println("Clientes cargados correctamente desde archivo.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("No se encontró archivo de clientes previo, se iniciará con lista vacía.");
			clientes = new HashMap<>();
		}
    }
}
