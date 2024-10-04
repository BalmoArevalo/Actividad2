import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class bibliotecaBusquedaBinaria {
    public static void main(String[] args) {
        Libro[] libros = {
                new Libro("El Quijote", "978-3-16-148410-0", "Miguel de Cervantes"),
                new Libro("Cien Años de Soledad", "978-0-14-028333-4", "Gabriel García Márquez"),
                new Libro("Donde los Árboles Cantan", "978-84-675-5004-7", "Laura Gallego"),
                new Libro("La Sombra del Viento", "978-0-7432-7356-5", "Carlos Ruiz Zafón")
        };

        //ordenar los libros por ISBN
        Arrays.sort(libros, (l1, l2) -> l1.isbn.compareTo(l2.isbn));

        // Solicitar el ISBN
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.print("Ingrese el ISBN del libro que desea buscar: ");
                String isbnBuscado = scanner.nextLine();

                if (isbnBuscado.trim().isEmpty()) {
                    System.out.println("No se ingresó un ISBN. Intente de nuevo.");
                } else {
                    // Ejecutar búsqueda binaria
                    int resultado = buscarLibroPorISBN(libros, isbnBuscado);

                    // Mostrar resultado
                    if (resultado != -1) {
                        System.out.println("Libro encontrado: " + libros[resultado].titulo);
                        System.out.println("Autor: " + libros[resultado].autor);
                    } else {
                        System.out.println("El libro con ISBN " + isbnBuscado + " no se encontró.");
                    }
                }

                // Preguntar si desea buscar otro libro
                System.out.print("¿Desea buscar otro libro? (1. Sí, 2. No): ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion != 1) {
                    continuar = false;
                    System.out.println("Gracias por usar el sistema de búsqueda.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Intente nuevamente.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }

        // Cerrar el Scanner
        scanner.close();
    }

    public static int buscarLibroPorISBN(Libro[] libros, String isbnBuscado){
        int inicio = 0;
        int fin = libros.length - 1;

        while (inicio <= fin) {
            int mitad = (inicio + fin) / 2;
            int comparacion = libros[mitad].isbn.compareTo(isbnBuscado);

            if (comparacion == 0) {
                return mitad; // ISBN encontrado
            } else if (comparacion < 0) {
                inicio = mitad + 1; // Buscar en la mitad derecha
            } else {
                fin = mitad - 1; // Buscar en la mitad izquierda
            }
        }
        return -1; // ISBN no encontrado
    }
}
