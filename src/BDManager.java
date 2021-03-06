import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class BDManager implements MetodosIn {
	int select;
	private String insertando;
	private String id;
	private String nombre;
	private String raza;
	private int introducido;
	Scanner scanner = new Scanner(System.in);

	public BDManager() {

	}

	@Override
	public void conectar() {
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream("config.ini"));
			String url = propiedades.getProperty("dburl");
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("password");
			try (Connection con = DriverManager.getConnection(url, user, pass)) {
				// Crea la conexion.
				System.out.println("�Conectado a la base de datos!");
				Statement stmt = con.createStatement();
				String query = "SELECT * from patos ;";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					// Muestra la informacion de la BBDD y la printea en pantalla con formato.
					String id = rs.getObject(1).toString();
					String nombre = rs.getObject(2).toString();
					String raza = rs.getObject(3).toString();
					System.out.println(
							"Id pato: " + id + " || " + " Nombre pato: " + nombre + " || " + " Raza pato: " + raza);
				}
			} catch (Exception e) {
				System.out.println("Problemas para crear la conexion");
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("�No se ha podido acceder al fichero config.ini o la propia BBDD");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void leer() {
		// -------
		// Este metodo se cumple en el metodo de conectar ya que me parecia
		// mejor poner todo en uno, y asi para mostrar los datos solo hay que utilizar
		// uno y directamente se conecta a la BBDD.
		// -------
	}

	@Override
	public void insertar() {
		Properties propiedades = new Properties();
		// Conexion basica a bbdd con url, usuario y contrasena.
		try {
			propiedades.load(new FileInputStream("config.ini"));
			String url = propiedades.getProperty("dburl");
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("password");
			Connection con = DriverManager.getConnection(url, user, pass);
			// Crea la conexion.
			Statement stmt = con.createStatement();
			System.out.println("�Conectado a la base de datos!");
			Scanner scanner1 = new Scanner(System.in);
			System.out.println("Escriba el Id:");
			id = scanner1.nextLine();
			System.out.println("Escriba el Nombre:");
			nombre = scanner1.nextLine();
			System.out.println("Escriba la Raza:");
			raza = scanner1.nextLine();
			try {
				// Insercion SQL.
				String sql = "INSERT INTO patos values ('" + id + "','" + nombre + "','" + raza + "')";
				// Ejecucion.
				stmt.executeUpdate(sql);
				System.out.println("Insertado correctamente");
			} catch (Exception e) {
				// Cuando no se ejecute la insercion.
				System.out.println("No se ha insertado");
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("�No se ha podido conectar a la BBDD!");
		}
	}

	@Override
	public void pasarDatos() {
		Properties propiedades = new Properties();
		// Conexion basica a bbdd con url, usuario y contrasena.
		try {
			propiedades.load(new FileInputStream("config.ini"));
			String url = propiedades.getProperty("dburl");
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("password");
			Connection con = DriverManager.getConnection(url, user, pass);
			// Crea la conexion.
			System.out.println("�Conectado a la base de datos!");
			System.out.println("�Insertando 5 primeros datos de la BBDD�");
			Statement stmt = con.createStatement();
			String query = "SELECT * from patos;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// Muestra la informacion de la BBDD y la printea en pantalla con formato.
				id = rs.getObject(1).toString();
				nombre = rs.getObject(2).toString();
				raza = rs.getObject(3).toString();
			}
			BufferedReader in = null;
			try {
				// Busca el fichero.
				in = new BufferedReader(new FileReader("Prueba3.txt"));
				// while ((total = in.readLine()) != null) {

				// }
			} catch (FileNotFoundException e1) {
				// Si no lo encuentra.
				System.out.println("No se ha encontrado el archivo");
				System.out.println(e1.getMessage());
			}
			try {
				// Insercion en fichero por teclado mediante scanner.
				PrintWriter out = new PrintWriter(new FileWriter("Prueba3.txt", true));
				insertando = id + ";" + nombre + ";" + raza;
				out.println(insertando);

				out.close();
			} catch (IOException e) {
				// Cuando no consigue insertar los datos propuestos.
				System.out.println("Error insercion en Fichero");
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void borrar() {
		System.out.println("**Escribe 1 para borrar todos los datos de la BBDD**");
		System.out.println("**Escribe 2 para borrar una linea de la BBDD**");
		select = scanner.nextInt();
		Properties propiedades = new Properties();
		switch (select) {
		case 1:
			System.out.println("Vaciar BBDD");
			try {
				propiedades.load(new FileInputStream("config.ini"));
				String url = propiedades.getProperty("dburl");
				String user = propiedades.getProperty("usuario");
				String pass = propiedades.getProperty("password");
				Connection con = DriverManager.getConnection(url, user, pass);
				// Crea la conexion.
				System.out.println("�Conectado a la base de datos!");
				Statement stmt = con.createStatement();
				stmt = con.createStatement();
				String query = "DELETE FROM pruebass";
				int deletedRows = stmt.executeUpdate(query);
				if (deletedRows > 0) {
					System.out.println("Borrados todos los datos de la bbdd correctamente��");
				} else {
					System.out.println("Table vaciaaa.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("�NO SE HA PODIDO CONECTAR!");
			}
			break;

		case 2:
			System.out.println("Borrar una linea");
			try {
				propiedades.load(new FileInputStream("config.ini"));
				String url = propiedades.getProperty("dburl");
				String user = propiedades.getProperty("usuario");
				String pass = propiedades.getProperty("password");
				Connection con = DriverManager.getConnection(url, user, pass);
				// Crea la conexion.

				if (con != null) {
					System.out.println("\n Conectado correctamente!");
				} else {
					System.out.println("Failed to make connection!");
				}

				Statement smt = con.createStatement();

				Scanner sc = new Scanner(System.in);

				System.out.print("Introduce el ID del Pato:");

				String ed = sc.nextLine();

				String q = "Select * from patos where id='" + ed + "'";

				// Ejecutar la query
				ResultSet rs = smt.executeQuery(q);

				if (rs.next()) {

					System.out.println("Id Patos:" + rs.getString(1));
					System.out.println("Nombre:" + rs.getString(2));
					System.out.println("Raza:" + rs.getString(3));
					System.out.println("Seguro que quieres borrar el pato?  si o no?");

					String chh = sc.nextLine();
					if (chh.equalsIgnoreCase("si")) {
						q = "delete from patos where id='" + ed + "'";
						smt.executeUpdate(q);
						System.out.println("Campo borrado...");
					}
				} else {
					System.out.println("-No encontrado-");
				}
				con.close();

			} catch (FileNotFoundException e1) {
				System.out.println(e1.getMessage());
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			break;
		}

	}

	@Override
	public void busqueda() {
		Properties propiedades = new Properties();
		System.out.println("**Escribe el numero de ID para encontrar la fila deseada**");
		introducido = scanner.nextInt();
		try {
			propiedades.load(new FileInputStream("config.ini"));
			String url = propiedades.getProperty("dburl");
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("password");
			Connection con = DriverManager.getConnection(url, user, pass);
			// Crea la conexion.
			System.out.println("�Conectado a la base de datos!");
			Statement stmt = con.createStatement();
			String query = "SELECT * from patos WHERE id=" + introducido + ";";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// Muestra la informacion de la BBDD y la printea en pantalla con formato.
				String id = rs.getObject(1).toString();
				String nombre = rs.getObject(2).toString();
				String raza = rs.getObject(3).toString();
				System.out.println(
						"Id pato: " + id + " || " + " Nombre pato: " + nombre + " || " + " Raza pato: " + raza);
			}
		} catch (Exception e) {
			System.out.println("�No se ha podido acceder al fichero config.ini o la propia BBDD");
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void modificar() {
		Properties propiedades = new Properties();
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("**Escribe el numero de ID para encontrar la fila que se quiere cambiar**");
		introducido = scanner.nextInt();

		System.out.println("Escriba el Nombre:");
		nombre = scanner1.nextLine();

		System.out.println("Escriba la Raza:");
		raza = scanner1.nextLine();
		try {
			propiedades.load(new FileInputStream("config.ini"));
			String url = propiedades.getProperty("dburl");
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("password");
			Connection con = DriverManager.getConnection(url, user, pass);
			// Crea la conexion.
			// Cambia los datos introducidos anteriormente.
			System.out.println("�Conectado a la base de datos!");
			Statement stmt = con.createStatement();
			String query = "UPDATE patos SET nombre = " + "'" + nombre + "'" + ", raza= " + "'" + raza + "'"
					+ " WHERE id =" + introducido + ";";
			stmt.executeUpdate(query);

			String queryy = "SELECT * from patos WHERE id=" + introducido + ";";
			ResultSet rs = stmt.executeQuery(queryy);
			while (rs.next()) {
				// Muestra la informacion de la BBDD que se ha actualizado y la printea en
				// pantalla con formato.
				String id = rs.getObject(1).toString();
				String nombre = rs.getObject(2).toString();
				String raza = rs.getObject(3).toString();
				System.out.println(
						"Id pato: " + id + " || " + " Nombre pato: " + nombre + " || " + " Raza pato: " + raza);
			}
		} catch (Exception e) {
			System.out.println("�No se ha podido acceder al fichero config.ini o la propia BBDD");
			System.out.println(e.getMessage());
		}
	}
}
