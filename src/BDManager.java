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
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class BDManager implements MetodosIn {
	private String insertando;
	private String id;
	private String nombre;
	private String raza;

	public BDManager() {

	}

	@Override
	public void conectar() {
		// Properties propiedades = new Properties();
		try {
			// propiedades.load(new FileInputStream("config.ini"));
			// Conexion basica a bbdd con url, usuario y contrasena.
			String url = "jdbc:mysql://localhost:3306/productos"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			// String user = propiedades.getProperty("usuario");
			// String pass = propiedades.getProperty("password");
			String user = "root";
			String pass = "";
			Connection con = DriverManager.getConnection(url, user, pass);
			// Crea la conexion.
			System.out.println("¡Conectado a la base de datos!");
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
			System.out.println("¡No se ha podido acceder al fichero config.ini o la propia BBDD");
			e.printStackTrace();
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
		// Conexion basica a bbdd con url, usuario y contrasena.
		String url = "jdbc:mysql://localhost:3306/productos"
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String pass = "";
		try (Connection con = DriverManager.getConnection(url, user, pass)) {
			// Crea la conexion.
			Statement stmt = con.createStatement();
			System.out.println("¡Conectado a la base de datos!");
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
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("¡No se ha podido conectar a la BBDD!");
		}
	}

	@Override
	public void pasarDatos() {
		// Conexion basica a bbdd con url, usuario y contrasena.
		String url = "jdbc:mysql://localhost:3306/productos"
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String pass = "";
		try (Connection con = DriverManager.getConnection(url, user, pass)) {
			// Crea la conexion.
			System.out.println("¡Conectado a la base de datos!");
			System.out.println("¡Insertando 5 primeros datos de la BBDD¡");
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
				e1.printStackTrace();
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
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
