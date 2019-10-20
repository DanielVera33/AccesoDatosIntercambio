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
		int select;
		int seleccion;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("** Menu Oficial ** ");
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
					System.out.println(" Porfavor Seleccione que desea: ");
					System.out.println("1. Leer Datos de BBDD"); //Funciona.
					System.out.println("2. Insertar Datos a BBDD"); //Funciona.
					System.out.println("3. Borrar Datos de BBDD"); //Funciona.
					System.out.println("4. Pasar Datos de BBDD a Fichero");
					System.out.println("5. Modificar Datos de BBDD"); //Funciona.
					
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
					}

					break;
				} while (select != 6);
			}

		} while (select != 14);

	}

}