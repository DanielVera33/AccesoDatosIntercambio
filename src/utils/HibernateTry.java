package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.Zapatillas;

public class HibernateTry implements MetodosIn {

	Scanner scanner = new Scanner(System.in);
	private String insercion;
	private int select;
	private int seleccion;
	private int busqueda;

	Session session;

	public HibernateTry() {

		HibernateUtil util = new HibernateUtil();

		session = util.getSessionFactory().openSession();

	}

	@Override
	public void conectar() {

	}

	@Override
	public void leer() {
		System.out.println("Inicio Consulta Simple Zapatillas");

		Query q = session.createQuery("select e from Zapatillas e");
		List results = q.list();

		Iterator zapatillasIterator = results.iterator();

		while (zapatillasIterator.hasNext()) {
			Zapatillas zapas = (Zapatillas) zapatillasIterator.next();

			System.out.println("-	Id: " + zapas.getZapId() + " - Nombre: " + zapas.getZapName());

		}

		System.out.println("Fin Consulta Zapatillas");

	}

	@Override
	public void insertar() {
		System.out.println("Inicio Inserción Masiva de Datosss");
		System.out.println("Ingrese Nombre de marca zapatilla: ");
		insercion = scanner.next();
		Zapatillas e1 = new Zapatillas();
		e1.setZapName(insercion);
		session.beginTransaction();
		session.save(e1);
		session.getTransaction().commit();
		System.out.println("Zapatilla Insertada en BBDD: " + insercion);
		System.out.println("Fin Inserción Masivaaaaaa");
		System.out.println("** *** **");
	}

	@Override
	public void pasarDatos() {

	}

	@Override
	public void borrar() {
		System.out.println("**Escribe 1 para borrar todos los datos de la BBDD**");
		System.out.println("**Escribe 2 para borrar una linea de la BBDD**");

		select = scanner.nextInt();
		switch (select) {
		case 1:
			System.out.println("Vaciar BBDD");
			
			session.beginTransaction();
			Query q = session.createQuery("delete from Zapatillas");
			q.executeUpdate();
			session.getTransaction().commit();
			
			System.out.println("Tabla vacia......");
			break;

		case 2:
			Zapatillas zapas;
			System.out.print("Introduce el ID de la Zapatilla a borrar :");
			busqueda = scanner.nextInt();
			zapas = (Zapatillas) session.get(Zapatillas.class, busqueda);
			session.beginTransaction();
			session.delete(zapas);
			session.getTransaction().commit();
			System.out.println("Zapatilla borrada......");
			break;
		}

	}

	@Override
	public void busqueda() {
		Zapatillas zapas;
		System.out.println("Inicio Consulta Simple Zapatilla seleccionada");
		busqueda = scanner.nextInt();
		zapas = (Zapatillas) session.get(Zapatillas.class, busqueda);
		session.beginTransaction();
		System.out.println("-	Id: " + zapas.getZapId() + " - Nombre: " + zapas.getZapName());
		session.getTransaction().commit();
	}

	@Override
	public void modificar() {
		Zapatillas zapas;
		System.out.println("Introduce el id de la zapatilla que quieres modificar");
		busqueda = scanner.nextInt();
		
		zapas = (Zapatillas) session.get(Zapatillas.class, busqueda);
		System.out.println("Pon el nuevo nombre de la marca de zapatilla");
		String nombre;
		nombre = scanner.next();
		session.beginTransaction();
		zapas.setZapName(nombre);
		session.update(zapas);
		session.getTransaction().commit();
		System.out.println("Se ha actualizado el nombre de la zapatilla");
		

	}
}
