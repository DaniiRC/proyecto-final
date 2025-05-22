import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {

	static Scanner entrada = new Scanner(System.in);
	static int opcion;
	static HashMap<String, Cliente> clientes = new HashMap<>();
	static HashMap<String, Producto> productos = new HashMap<>();
	static List<Pedido> pedidos = new ArrayList<>();
	static Cliente clienteActual = null;

	public static void productosPredefinidos() {
		try {
			productos.put("Oliva Virgen Extra", new AceiteOliva("Oliva Virgen Extra", 10.5, 20, 5, "picual", "Jaén"));
			productos.put("Hojiblanca Premium",
					new AceiteOliva("Hojiblanca Premium", 12.0, 15, 6, "hojiblanca", "Córdoba"));

			productos.put("Aceite con Aroma Dulce", new AceiteAromatizado("Aceite Dulce", 20.3, 10, 7, "azúcar", 4));
			productos.put("Aceite con Aroma Salado", new AceiteAromatizado("Aceite Salado", 21.2, 5, 8, "sal", 2));

			productos.put("Crema Hidratante", new Cosmetica("Crema Hidratante", 15.5, 12, 8, "Con aceite de oliva"));
			productos.put("Bálsamo Labial", new Cosmetica("Bálsamo Labial", 4.75, 25, 7, "Protección solar"));

		} catch (IllegalArgumentException e) {
			System.out.println("Error al cargar producto: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		productosPredefinidos();
		do {
			try {
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
			} catch (InputMismatchException e) {
	            System.out.println("Error: Debe ingresar un número.");
	            entrada.nextLine();
			} catch (Exception e) {
	            System.out.println("Error inesperado: " + e.getMessage());
	            entrada.nextLine();
			}
			
		} while (opcion != 5);

		entrada.close();
	}

	public static void mostrarMenu() {
		System.out.println("\n--- MENÚ ---");
		System.out.println("1. Agregar nuevo cliente."); // OPCION 1
		System.out.println("2. Iniciar sesion."); // OPCION 2
		System.out.println("3. Guardar datos"); // OPCION 3
		System.out.println("4. Cargar datos"); // OPCION 4
		System.out.println("5. Salir"); // OPCION 5
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

		if (email.equalsIgnoreCase("administrador")) {
			System.out.print("Contraseña: ");
			String contraseña = entrada.nextLine();

			// Cliente administrador
			if (contraseña.equals("admin")) {
				try {
					clienteActual = new Cliente("administrador", "admin", "Administrador", "adminApellido", "12345678Z",
							"123456789");
					System.out.println("\nBienvenido usuario 'Administrador'!");
					menuCliente();
				} catch (IllegalArgumentException e) {
					System.out.println("\nError al iniciar sesión de administrador: " + e.getMessage());
				}
				return;
			} else {
				System.out.println("\nContraseña incorrecta para usuario 'Administrador'!");
				return;
			}
		}

		if (!clientes.containsKey(email)) {
			System.out.println("No existe ningun cliente con ese email.");
			return;
		}

		System.out.println("Contraseña: ");
		String contraseña = entrada.nextLine();

		Cliente cliente = clientes.get(email);
		if (cliente.comprobarContraseña(contraseña)) {
			clienteActual = cliente;
			System.out.println("Has iniciado sesion correctamente! Bienvenido cliente " + cliente.getNombre());
			menuCliente();
		} else {
			System.out.println("Contraseña incorrecta!");
		}
	}

	public static void menuCliente() {
		if (clienteActual == null)
			return;

		boolean salir = false;

		while (!salir) {
			try {
				System.out.println("\n--- MENÚ CLIENTE ---");
				System.out.println("1. Realizar pedido");
				System.out.println("2. Añadir valoración a producto");

				// Opciones especiales para el usuario Administrador (ver estadisticas)
				if (clienteActual.getEmail().equalsIgnoreCase("administrador")) {
					System.out.println("3. Ver estadísticas");
					System.out.println("4. Cerrar sesión");
				} else {
					System.out.println("3. Cerrar sesión");
				}

				System.out.print("Seleccione una opción: ");
				int opcion = entrada.nextInt();
				entrada.nextLine();

				switch (opcion) {
				case 1:
					realizarPedido();
					break;
				case 2:
					añadirValoracion();
					break;
				case 3:
					if (clienteActual.getEmail().equalsIgnoreCase("administrador")) {
						mostrarEstadisticas();
					} else {
						salir = true;
						clienteActual = null;
					}
					break;
				case 4:
					if (clienteActual.getEmail().equalsIgnoreCase("administrador")) {
						salir = true;
						clienteActual = null;
					}
					break;
				default:
					System.out.println("Opción no válida");
				}
			} catch (InputMismatchException e) {
	            System.out.println("Error: Debe ingresar un número.");
	            entrada.nextLine();
			} catch (Exception e) {
	            System.out.println("Error inesperado: " + e.getMessage());
	            entrada.nextLine();
			}
		}
	}

	public static void realizarPedido() {
		Pedido pedido = new Pedido(clienteActual.getEmail());

		String seleccion;
		int cantidad;
		do {
			System.out.println("\n--- PRODUCTOS DISPONIBLES ---");
			productos.forEach((nombre, producto) -> {
				System.out.printf("%s - Precio: %.2f€ - Stock: %d\n", nombre, producto.getPrecio(), producto.getStock());
						producto.getStock();
			});

			System.out.print("\nIntroduce el nombre del producto (0 para terminar): ");
			seleccion = entrada.nextLine();

			if (!seleccion.equals("0") && productos.containsKey(seleccion)) {
				Producto p = productos.get(seleccion);
				double descuento = 0.0;

				// Descuentos para los productos especificos
				if (p instanceof AceiteAromatizado || p instanceof Cosmetica) {
					System.out.print("Introduce el descuento a aplicar (%): ");
					descuento = entrada.nextDouble();
					entrada.nextLine();
				}
				
				System.out.print("Cantidad: ");
				cantidad = entrada.nextInt();
				entrada.nextLine();

				if (cantidad > 0 && cantidad <= p.getStock()) {
					pedido.añadirProducto(p, cantidad, descuento);
					p.setStock(p.getStock() - cantidad);
					
					clienteActual.registrarCompra(p.getNombre());
					System.out.printf("\nAñadido: %d x %s\n", cantidad, p.getNombre());
				} else {
					System.out.println("Cantidad no válida o stock insuficiente.");
				}
			} else if (!seleccion.equals("0")) {
				System.out.println("Producto no encontrado.");
			}
		} while (!seleccion.equals("0"));

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

	public static void añadirValoracion() {
		if (clienteActual == null) {
			System.out.println("Debe iniciar sesión primero");
			return;
		}

		System.out.println("\n--- PRODUCTOS COMPRADOS ---");
		productos.forEach((nombre, producto) -> {
			if (clienteActual.haCompradoProducto(nombre)) {
				System.out.println("- " + nombre);
			}
		});

		System.out.print("\nIntroduce el nombre del producto a valorar: ");
		String nombreProducto = entrada.nextLine();

		if (!productos.containsKey(nombreProducto) || !clienteActual.haCompradoProducto(nombreProducto)) {
			System.out.println("No has comprado este producto o no existe");
			return;
		}

		System.out.print("Introduce tu valoración (0-10): ");
		int valoracion = entrada.nextInt();
		entrada.nextLine();

		try {
			Producto producto = productos.get(nombreProducto);
			producto.setValoracion(valoracion);
			System.out.println("Valoración añadida correctamente");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void mostrarEstadisticas() {
		if (clienteActual == null || !clienteActual.getEmail().equals("administrador")) {
			System.out.println("Acceso denegado. Solo para administradores.");
			return;
		}

		if (productos.isEmpty()) {
			System.out.println("No hay productos registrados.");
			return;
		}

		// Variables para estadísticas
		Producto masCaro = null;
		Producto masBarato = null;
		Producto mejorValorado = null;
		Producto peorValorado = null;

		double precioMasAlto = Double.MIN_VALUE;
		double precioMasBajo = Double.MAX_VALUE;
		int valoracionMasAlta = Integer.MIN_VALUE;
		int valoracionMasBaja = Integer.MAX_VALUE;

		// Calcular estadísticas
		for (Producto producto : productos.values()) {

			if (producto.getPrecio() > precioMasAlto) {
				precioMasAlto = producto.getPrecio();
				masCaro = producto;
			}
			if (producto.getPrecio() < precioMasBajo) {
				precioMasBajo = producto.getPrecio();
				masBarato = producto;
			}

			// Valoraciones
			if (producto.getValoracion() > valoracionMasAlta) {
				valoracionMasAlta = producto.getValoracion();
				mejorValorado = producto;
			}
			if (producto.getValoracion() < valoracionMasBaja) {
				valoracionMasBaja = producto.getValoracion();
				peorValorado = producto;
			}
		}

		System.out.println("\n--- ESTADÍSTICAS DE PRODUCTOS ---");

		System.out.println("\n· Producto más caro: "
				+ (masCaro != null ? masCaro.getNombre() + " -> " + masCaro.getPrecio() + "€" : "N/A"));

		System.out.println("· Producto más barato: "
				+ (masBarato != null ? masBarato.getNombre() + " -> " + masBarato.getPrecio() + "€" : "N/A"));

		System.out.println("\n· Producto mejor valorado: "
				+ (mejorValorado != null ? mejorValorado.getNombre() + " -> " + mejorValorado.getValoracion() + "/10"
						: "N/A"));

		System.out.println("· Producto peor valorado: "
				+ (peorValorado != null ? peorValorado.getNombre() + " -> " + peorValorado.getValoracion() + "/10"
						: "N/A"));

	}

	private static boolean puedeTenerDescuento(Producto producto) {
		return producto instanceof AceiteAromatizado || producto instanceof Cosmetica;
	}

	@SuppressWarnings("unused")
	private static double solicitarDescuento(Producto producto) {
		if (!puedeTenerDescuento(producto)) {
			return 0.0;
		}

		System.out.print("Introduce el porcentaje de descuento para " + producto.getNombre() + " (0-100): ");
		double descuento = entrada.nextDouble();
		entrada.nextLine();

		if (descuento < 0 || descuento > 100) {
			System.out.println("Descuento no válido. Se aplicará 0% de descuento.");
			return 0.0;
		}

		return descuento;
	}
}
