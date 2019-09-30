import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class AccesoDatos1 {
	public static void main(String[] args) throws IOException {
		MetodosIn datos1 = new FileManager();
		MetodosIn datos2 = new BDManager();
		int select;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("** Menu Oficial ** ");
			System.out.println("1. Mostrar datos de BD. "); // Funciona
			System.out.println("2. Añadir datos a BD. "); // Funciona
			System.out.println("3. Mostrar datos de Fichero . "); // Funciona
			System.out.println("4. Añadir datos Fichero "); // Funciona
			System.out.println("5. Pasar los datos del 	Fichero a la Base de Datos"); // Funciona
			System.out.println("6. Pasar los datos de la Base Datos al Fichero"); // Funciona
			System.out.println("7. Formas de eliminar datos de Fichero");
			System.out.println("** *** **");
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
			}

		} while (select != 8);

	}

}