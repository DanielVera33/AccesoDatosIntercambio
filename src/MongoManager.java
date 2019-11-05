
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import modelo.Zapatillas;

/**
 * Java + MongoDB Zapatillas Example
 * 
 */
public class MongoManager implements MetodosIn {
	@Override
	public void conectar() {
	}

	@Override
	public void leer() {
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("zapatillasdb");
			DBCollection table = db.getCollection("zapatillas");
			DBCursor cursor2 = table.find();
			while (cursor2.hasNext()) {
				System.out.println(cursor2.next());
			}
		} catch (MongoException e) {
			System.out.println(e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void insertar() {
		try {
			Scanner scanner2 = new Scanner(System.in);

			System.out.println("Escriba el Nombre de la zapatilla:");
			String nombreZapatilla = scanner2.nextLine();

			System.out.println("Escriba el pais de la zapatilla:");
			String paisZapatilla = scanner2.nextLine();

			System.out.println("Escriba el año en el que se creo la marca:");
			String creacionZapatilla = scanner2.nextLine();

			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("zapatillasdb");
			DBCollection table = db.getCollection("zapatillas");

			BasicDBObject document = new BasicDBObject();
			document.put("nombreZapatilla", nombreZapatilla);
			document.put("paisZapatilla", paisZapatilla);
			document.put("creacion", creacionZapatilla);
			table.insert(document);

			System.out.println("Nueva Marca Zapatilla Insertada");

		} catch (MongoException e) {
			System.out.println(e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void pasarDatos() {

	}

	@Override
	public void borrar() {
		Scanner scanner1 = new Scanner(System.in);

		System.out.println("**Escribe 1 para borrar uno del JSON**");
		System.out.println("**Escribe 2 para borrar todos del JSON**");
		int select = scanner1.nextInt();
		switch (select) {
		case 1:

			try {
				System.out.println("Escriba el Nombre de la Zapatilla que quiere BORRAR");
				String ZapaSearch = scanner1.nextLine();

				MongoClient mongo = new MongoClient("localhost", 27017);
				DB db = mongo.getDB("zapatillasdb");
				DBCollection table = db.getCollection("zapatillas");

				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("nombreZapatilla", ZapaSearch);

				table.remove(searchQuery);

			} catch (MongoException e) {
				System.out.println(e.getMessage());
			} catch (UnknownHostException e) {
				System.out.println(e.getMessage());
			}

			break;
			
		case 2: 
			try {
				Scanner scanner3 = new Scanner(System.in);
				System.out.println("Has seleccionado borrar todo el JSON, estas loco????");
				
				MongoClient mongo = new MongoClient("localhost", 27017);
				DB db = mongo.getDB("zapatillasdb");
				DBCollection table = db.getCollection("zapatillas");
				
				System.out.println("***********");
				System.out.println("Te vuelvo a dar la opcion de no borrarlo, estas seguro?? yes/no");
				String opcion = scanner3.nextLine();
				
				if (opcion.equalsIgnoreCase("yes")) {
					DBCursor cursor = table.find();
					while (cursor.hasNext()) {
					    table.remove(cursor.next());
					}
				} else {
					System.out.println("Hiciste bien en no borrar todos.");
				}

			} catch (MongoException e) {
				System.out.println(e.getMessage());
			} catch (UnknownHostException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		}
	}

	@Override
	public void busqueda() {
		try {
			System.out.println("Escriba el Nombre de la Zapatilla que quiere BUSCAR");
			Scanner scanner1 = new Scanner(System.in);
			String Zapa = scanner1.nextLine();

			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("zapatillasdb");
			DBCollection table = db.getCollection("zapatillas");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nombreZapatilla", Zapa);

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} catch (MongoException e) {
			System.out.println(e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void modificar() {
		try {
			Scanner scanner1 = new Scanner(System.in);

			System.out.println("Escriba el Nombre de la zapatilla que quiere MODIFICAR");
			String zapaSearch = scanner1.nextLine();

			System.out.println("Escriba el Nombre de la nueva zapatilla");
			String zapaNewName = scanner1.nextLine();
			System.out.println("Escriba el Pais de la nueva zapatilla");
			String zapaNewPais = scanner1.nextLine();
			System.out.println("Escriba el Año de creacion de la nueva zapatilla");
			String zapaNewDate = scanner1.nextLine();

			/////////////

			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("zapatillasdb");
			DBCollection table = db.getCollection("zapatillas");

			////////////

			BasicDBObject query = new BasicDBObject();
			query.put("nombreZapatilla", zapaSearch);

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("nombreZapatilla", zapaNewName);
			newDocument.put("paisZapatilla", zapaNewPais);
			newDocument.put("creacion", zapaNewDate);

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);
			System.out.println("Zapatilla cambiada completamente");
		} catch (MongoException e) {
			System.out.println(e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
	}
}