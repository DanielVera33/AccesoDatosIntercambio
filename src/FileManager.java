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

public class FileManager implements MetodosIn {
	int select;
	private String text;
	private String linea;
	private String insertando;
	Scanner scanner = new Scanner(System.in);

	public FileManager() {

	}

	public void conectar() {

	}

	@Override
	public void leer() {
		try {
			// Busca el archivo y lee lo que encuentra en el mostrandolo por pantalla.
			BufferedReader in;
			in = new BufferedReader(new FileReader("Prueba3.txt"));
			text = in.readLine();
			while (text != null) {
				System.out.println(text);
				// Para leer diferentes lineas
				text = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			// Lo que ocurre cuando no consigue leer los datos del fichero.
			System.out.println("Error en lectura de Fichero");
			e.printStackTrace();
		}
	}

	@Override
	public void insertar() {
		try {
			// Insercion en fichero por teclado mediante scanner.
			PrintWriter out = new PrintWriter(new FileWriter("Prueba3.txt", true));
			Scanner scanner1 = new Scanner(System.in);
			insertando = scanner1.nextLine();
			out.print("\n" + insertando);
			out.close();
		} catch (IOException e) {
			// Cuando no consigue insertar los datos propuestos.
			System.out.println("Error insercion en Fichero");
			e.printStackTrace();
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
			// Crea la conexion.
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("¡Conectado a la base de datos!");

			BufferedReader in = null;
			try {
				// Busca el fichero.
				in = new BufferedReader(new FileReader("Prueba3.txt"));
			} catch (FileNotFoundException e1) {
				// Si no lo encuentra.
				System.out.println("No se ha encontrado el archivo");
				e1.printStackTrace();
			}
			try {
				while ((text = in.readLine()) != null) {
					// Lee texto de fichero y lo printea en consola sin separar
					System.out.println(text);
					Statement stmt = con.createStatement();

					// Para leer diferentes lineas y separar por partes
					String[] parts = text.split(";");
					System.out.println(parts.toString());
					try {
						// Insercion SQL.
						String sql = "INSERT INTO patos values ('" + parts[0] + "','" + parts[1] + "','" + parts[2]
								+ "')";
						// Ejecucion.
						stmt.executeUpdate(sql);
						System.out.println("Insertado correctamente");
					} catch (Exception e) {
						// Cuando no se ejecute la insercion.
						System.out.println("No se ha insertado");
						e.printStackTrace();
					}
				}
				in.close();
			} catch (IOException e) {
				// Si no consigue leer o encontrar el fichero, o no existe.
				System.out.println("Error en lectura de Fichero o separacion por split");
				e.printStackTrace();
			}

		} catch (Exception e) {
			// Si no consigue conectarse a la BBDD.
			System.out.println("ERROR, no se ha conectado a la base de datos");
			e.printStackTrace();
		}

	}

	@Override
	public void borrar() {
		System.out.println("**Escribe 1 para borrar todos los datos del fichero**");
		System.out.println("**Escribe 2 para borrar una linea del fichero**");
		select = scanner.nextInt();
		switch (select) {
		case 1:
			System.out.println("Vaciar Fichero");
			try {
				PrintWriter out = new PrintWriter(new FileWriter("PruebaBorrando.txt"));
				out.print("");
				out.close();
			} catch (IOException e) {
				// Lo que ocurre cuando no consigue entrar en los datos del fichero.
				System.out.println("Error en lectura o escritura de Fichero");
				e.printStackTrace();
			}

			break;

		case 2:
			System.out.println("Borrar una linea");

			break;

		}
	}
}
