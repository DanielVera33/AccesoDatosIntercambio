import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import auxiliares.ApiRequests;
import modelo.Zapatillas;

public class AccesoNode implements InterfazPHP {

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_ZAPATILLA, SET_ZAPATILLA, UPD_ZAPATILLA, DELETE_ZAPATILLA; // Datos de la conexion

	public AccesoNode() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost:8000/";
		GET_ZAPATILLA = "leeZapatillas";
		SET_ZAPATILLA = "escribirZapatilla";
		UPD_ZAPATILLA = "updateZapatilla";
		DELETE_ZAPATILLA = "borrarZapatilla";

	}

	public HashMap<Integer, Zapatillas> lee() {

		HashMap<Integer, Zapatillas> auxhm = new HashMap<Integer, Zapatillas>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para Zapatillas");

			String url = SERVER_PATH + GET_ZAPATILLA; // Sacadas de configuracion

			// System.out.println("La url a la que lanzamos la petición es " +
			// url); // Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			// System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONArray respuesta = (JSONArray) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else {

				// System.out.println(response);

				if (respuesta.size() > 0) {
					int id;
					String nombre;

					for (int i = 0; i < respuesta.size(); i++) {
						JSONObject row = (JSONObject) respuesta.get(i);

						id = Integer.parseInt(row.get("id").toString());
						nombre = row.get("nombre").toString();

						System.out.println("Id: " + id + " || " + "Nombre: " + nombre);

					}

				}

				System.exit(-1);

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
			//objPeticion.put("zapatillaAnnadir", objZapatilla);

			String json = objZapatilla.toJSONString();

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

					System.out.println("Almacenado zapatilla enviado por JSON Remoto");

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
			Scanner scanner = new Scanner(System.in);

			System.out.println("Escriba el id de la Zapatilla que quiere Actualizar:");
			int id = scanner.nextInt();
			System.out.println("Escriba el NUEVO Nombre de la Zapatilla que quiere Actualizar:");
			String nombre = scanner.next();

			objZapatilla.put("id", id);
			objZapatilla.put("nombre", nombre);

			// Tenemos la zapatilla como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			String json = objZapatilla.toJSONString();

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
			} else { 
				
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
	public void borrarZapatillaJSON() {
		try {
			JSONObject objZapatilla = new JSONObject();
			Scanner scanner = new Scanner(System.in);

			System.out.println("Esta seguro de que quiere borrar todo ?");
			String eleccion = scanner.next();
			if (!eleccion.equals("si")) {
				System.out.println("Gracias por no borrar todo");
			} else {

				String json = objZapatilla.toJSONString();

				String url = SERVER_PATH + DELETE_ZAPATILLA;

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
