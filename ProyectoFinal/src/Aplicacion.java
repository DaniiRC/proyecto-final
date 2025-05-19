import java.util.Scanner;

public class Aplicacion {
	
	static Scanner entrada = new Scanner(System.in);
	static int opcion;
	
	public static void main(String[] args) {
		do {
			mostrarMenu();
			System.out.println("Seleccione una opción: ");
			opcion = entrada.nextInt();
			entrada.nextLine();
			
			switch (opcion) {
			case 1: 
				//añadirUsuario();
				break;
			case 2:
				//iniciarSesion();
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
}
