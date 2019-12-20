import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import auxiliares.ApiRequests;
import modelo.Zapatillas;

public class AccesoPHPJson implements InterfazPHP {

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_ZAPATILLA, SET_ZAPATILLA, UPD_ZAPATILLA; // Datos de la conexion

	public AccesoPHPJson() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost/phpbd/adat_zapatillas_php/";
		GET_ZAPATILLA = "leeZapatillas.php";
		SET_ZAPATILLA = "escribirZapatilla.php";
		UPD_ZAPATILLA = "updateZapatilla.php";

	}

	public HashMap<Integer, Zapatillas> lee() {

		HashMap<Integer, Zapatillas> auxhm = new HashMap<Integer, Zapatillas>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para jugadores");

			String url = SERVER_PATH + GET_ZAPATILLA; // Sacadas de configuracion

			// System.out.println("La url a la que lanzamos la petición es " +
			// url); // Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			// System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("zapatillas");

					if (array.size() > 0) {

						// Declaramos variables
						Zapatillas nuevaZap;
						String nombre;
						int id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);

							nombre = row.get("nombre").toString();
							id = Integer.parseInt(row.get("id").toString());

							nuevaZap = new Zapatillas(id, nombre);

							auxhm.put(id, nuevaZap);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}
		Iterator it = auxhm.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue().toString());
			it.remove();
		}
		return auxhm;
	}

	@Override
	public void anadirZapatillaJSON() {
		try {
			JSONObject objZapatilla = new JSONObject();
			JSONObject objPeticion = new JSONObject();
			
			Scanner scanner1 = new Scanner(System.in);
			System.out.println("Escriba el Nombre de la Zapatilla que quiere anadir:");
			String nombre = scanner1.nextLine();

			objZapatilla.put("nombre", nombre);

			// Tenemos la zapatilla como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("zapatillaAnnadir", objZapatilla);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un jugador");

			String url = SERVER_PATH + SET_ZAPATILLA;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response); // Traza para pruebas
			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
		
	}

	@Override
	public void updateZapatillaJSON() {
		try {
			JSONObject objZapatilla = new JSONObject();
			JSONObject objPeticion = new JSONObject();
			
			Scanner scanner2 = new Scanner(System.in);
			System.out.println("Escriba el Nombre de la Zapatilla que quiere Actualizar:");
			String nombre = scanner2.nextLine();
			System.out.println("Escriba el Nombre de la Zapatilla que sustituye a la anterior:");
			String cambio = scanner2.nextLine();

			objZapatilla.put("nombre", nombre);
			objZapatilla.put("nombre", cambio);

			// Tenemos la zapatilla como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("zapatillaAnnadir", objZapatilla);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un jugador");

			String url = SERVER_PATH + UPD_ZAPATILLA;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response); // Traza para pruebas
			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
	}
}
