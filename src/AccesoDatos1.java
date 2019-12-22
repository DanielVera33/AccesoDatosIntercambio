import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

import utils.HibernateTry;

public class AccesoDatos1 {
	public static void main(String[] args) throws IOException {
		MetodosIn datos1 = new FileManager();
		MetodosIn datos2 = new BDManager();
		utils.MetodosIn datos3 = new HibernateTry();
		MetodosIn datos4 = new MongoManager();
		InterfazPHP datos5 = new AccesoPHPJson();
		int select;
		int seleccion;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("** Menu Oficial ** ");
			System.out.println("****");
			System.out.println("1. Mostrar datos de BBDD. "); // Funciona
			System.out.println("2. Añadir datos a BBDD. "); // Funciona
			System.out.println("3. Mostrar datos de Fichero . "); // Funciona
			System.out.println("4. Añadir datos Fichero "); // Funciona
			System.out.println("5. Pasar los datos del 	Fichero a la BBDD"); // Funciona
			System.out.println("6. Pasar los datos de la BBDD al Fichero"); // Funciona
			System.out.println("** *** **");
			System.out.println("******* FASE 2 *******");
			System.out.println("7. Formas de eliminar datos de Fichero"); // Funciona MITAD
			System.out.println("8. Formas de eliminar datos de BBDD"); // Funciona
			System.out.println("9. Modificar datos de Fichero");
			System.out.println("10. Modificar datos de BBDD"); // Funciona
			System.out.println("11. Buscar datos de BBDD"); // Funciona
			System.out.println("12. Buscar datos de Fichero"); // Funciona
			System.out.println("** *** **");
			System.out.println("******* FASE 3 *******");
			System.out.println("13. HIBERNATE");
			System.out.println("******* FASE 4 *******");
			System.out.println("14. MONGODB");
			System.out.println("******* FASE 5 *******");
			System.out.println("15. PHP JSON");
			select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.println(" Prueba1 ");
				datos2.conectar();
				break;

			case 2:
				System.out.println(" Prueba2 ");
				System.out.println("Escriba lo que quiere introducir");
				datos2.insertar();
				break;

			case 3:
				System.out.println(" Prueba3 ");
				datos1.leer();
				break;

			case 4:
				System.out.println(" Prueba4 ");
				System.out.println("Escriba lo que quiere introducir");
				datos1.insertar();
				break;

			case 5:
				System.out.println(" Prueba5 ");
				System.out.println("Compruebe que el fichero contiene este formato: id;Pato;Blanco");
				datos1.pasarDatos();
				break;

			case 6:
				System.out.println(" Prueba6 ");
				datos2.pasarDatos();
				break;

			case 7:
				System.out.println(" Prueba7 ");
				datos1.borrar();
				break;

			case 8:
				System.out.println(" Prueba8 ");
				datos2.borrar();
				break;

			case 9:
				System.out.println(" Prueba9 ");
				datos1.modificar();
				break;

			case 10:
				System.out.println(" Prueba10 ");
				datos2.modificar();
				break;

			case 11:
				System.out.println(" Prueba11 ");
				datos2.busqueda();
				break;

			case 12:
				System.out.println(" Prueba12 ");
				datos1.busqueda();
				break;
				
			case 13:
				do {
					System.out.println(" Hibernate Seleccionado ");
					System.out.println("**** Menu Oficial Hibernate **** ");
					System.out.println("**** Porfavor Seleccione que desea: ");
					System.out.println("1. Leer Datos de BBDD"); // Funciona.
					System.out.println("2. Insertar Datos a BBDD"); // Funciona.
					System.out.println("3. Borrar Datos de BBDD"); // Funciona.
					System.out.println("4. Pasar Datos de BBDD a Fichero");
					System.out.println("5. Modificar Datos de BBDD"); // Funciona.
					System.out.println("6. Busqueda Datos de BBDD"); // Funciona
					System.out.println("** *** **");

					seleccion = scanner.nextInt();
					switch (seleccion) {

					case 1:
						System.out.println("Leer Datos de BBDD");
						datos3.leer();
						break;

					case 2:
						System.out.println("Insertar Datos a BBDD");
						datos3.insertar();
						break;

					case 3:
						System.out.println("Borrar Datos a BBDD");
						datos3.borrar();
						break;

					case 4:
						System.out.println("Pasar Datos de BBDD a Fichero");
						datos3.pasarDatos();
						break;

					case 5:
						System.out.println("Modificar datos de BBDD");
						datos3.modificar();
						break;

					case 6:
						System.out.println("Buscar datos de BBDD");
						datos3.busqueda();
						break;
					}
					break;
				} while (select != 7);
			case 14:
				do {
					System.out.println(" MongoDB Seleccionado ");
					System.out.println("**** Menu Oficial MONGO **** ");
					System.out.println("**** Porfavor Seleccione que desea: ");
					System.out.println("1. Leer todos los datos JSON"); // Funciona.
					System.out.println("2. Busqueda Uno en JSON"); // Funciona.
					System.out.println("3. Opciones para Borrar JSON"); // Funciona.
					System.out.println("4. Modificar Uno en JSON"); // Funciona.
					System.out.println("5. Insertar Uno en JSON"); // Funciona.
					System.out.println("** *** **");

					seleccion = scanner.nextInt();
					switch (seleccion) {

					case 1:
						System.out.println("Leer todos los datos JSON");
						datos4.leer();
						break;

					case 2:
						System.out.println("Busqueda Uno en JSON");
						datos4.busqueda();
						break;

					case 3:
						System.out.println("Opciones Borrar JSON");
						datos4.borrar();
						break;

					case 4:
						System.out.println("Modificar Uno en JSON");
						datos4.modificar();
						break;

					case 5:
						System.out.println("Insertar Uno en JSON");
						datos4.insertar();
						break;
					}
					break;
				} while (select != 6);
			case 15:
				do {
					System.out.println(" PHP JSON Seleccionado ");
					System.out.println("**** Menu Oficial PHP **** ");
					System.out.println("**** Porfavor Seleccione que desea: ");
					System.out.println("1. Leer todos los datos JSON"); // Funciona.
					System.out.println("2. Busqueda Uno en JSON");
					System.out.println("3. Borrar JSON"); // Funciona.
					System.out.println("4. Modificar Uno en JSON"); // Funciona.
					System.out.println("5. Insertar Uno en JSON"); // Funciona.
					System.out.println("** *** **");

					seleccion = scanner.nextInt();
					switch (seleccion) {

					case 1:
						System.out.println("Leer todos los datos JSON");
						datos5.lee();
						break;

					case 2:
						System.out.println("Busqueda Uno en JSON");
						break;

					case 3:
						System.out.println("Opciones Borrar JSON");
						datos5.borrarZapatillaJSON();
						break;

					case 4:
						System.out.println("Modificar Uno en JSON");
						datos5.updateZapatillaJSON();
						break;

					case 5:
						System.out.println("Insertar Uno en JSON");
						datos5.anadirZapatillaJSON();
						break;
					}
					break;
				} while (select != 6);
			}
		} while (select != 16);
	}
}