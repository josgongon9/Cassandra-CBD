package com.cbd.cassandra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cbd.cassandra.model.Actor;
import com.cbd.cassandra.repository.ActorRepository;

@SpringBootApplication
public class SpringDataCassandraApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataCassandraApplication.class, args);
		System.exit(0);
	}
	
	@Autowired
	ActorRepository actorRepository;


	@Override
	public void run(String... args) throws Exception {
		clearData();
		saveData();
		lookupLoop();

	}

	public void clearData() {
		actorRepository.deleteAll();
	}

	public void saveData() {

		String csvFile = "/Users/CSVOscars.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			int idCounter = 0;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] actor = line.split(cvsSplitBy);

				Actor act = new Actor();
				act.setId(idCounter);
				act.setYear(Integer.parseInt(actor[1].substring(1)));
				act.setAge(Integer.parseInt(actor[2].substring(1)));
				String name = actor[3].replace("\"", "");
				act.setFullname(name.substring(1));
				String movieName = actor[4].replace("\"", "");
				act.setMovie(movieName.substring(1));

				actorRepository.save(act);
				idCounter++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void lookupLoop() {

		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		Boolean exit = false;
		while (exit == false) {
			System.out.println("============================================================================\n"
					+ "============================================================================");
			System.out.println("Escoja una opción:\n" + "1. Para ver todo\n" + "2. Para Consultar por nombre\n"
					+ "3. Para consultar por edad máxima\n" + "4. Para consultar por año máximo\n"
					+ "5. Para consultar por año mínimo\n" + "6. Para consultar por nombre de la película\n"
					+ "0. Para salir\n");

			String num = myObj.nextLine(); // Read user input
			

			if (num.equals("1")) {

				System.out.println("===================Todos los actores===================");
				List<Actor> all = (List<Actor>) actorRepository.findAll();
				all.forEach(System.out::println);
			} else if (num.equals("2")) {

				System.out.println("===================Búsqueda por nombre===================");

				System.out.println("Introduzca un nombre");
				String fn = myObj.nextLine(); // Read user input
				
				List<Actor> peters = actorRepository.findByName(fn);
				peters.forEach(System.out::println);
				if (peters.isEmpty()) {
					System.out.println(
							"Sin resultados. Tienes que escribir el nombre y apellido para encontrar a un actor");
				}

			} else if (num.equals("3")) {

				System.out.println("===================Búsqueda por edad===================");
				System.out.println("Introduzca una edad máxima");
				String age = myObj.nextLine(); // Read user input
				if (checkIsNumber(age)) {

					int ageInt = Integer.parseInt(age);
					List<Actor> custsAgeGreaterThan = actorRepository.findActorHasAgeLessThan(ageInt);
					custsAgeGreaterThan.forEach(System.out::println);
				} else {

					System.out.println("Introduzca un número");
				}

			} else if (num.equals("4")) {

				System.out.println("===================Búsqueda por año===================");
				System.out.println("Introduzca un año máximo");
				String year = myObj.nextLine(); // Read user input
				if (checkIsNumber(year)) {

					int yearInt = Integer.parseInt(year);
					List<Actor> custsAgeGreaterThan = actorRepository.findActorYearLessThan(yearInt);
					custsAgeGreaterThan.forEach(System.out::println);
				} else {

					System.out.println("Introduzca un número");
				}

			} else if (num.equals("5")) {

				System.out.println("===================Búsqueda por año===================");
				System.out.println("Introduzca un año mínimo");
				String year = myObj.nextLine(); // Read user input
				if (checkIsNumber(year)) {

					int yearInt = Integer.parseInt(year);
					List<Actor> custsAgeGreaterThan = actorRepository.findActorYearGreaterThan(yearInt);
					custsAgeGreaterThan.forEach(System.out::println);
				} else {

					System.out.println("Introduzca un número");
				}

			} else if (num.equals("6")) {

				System.out.println("===================Búsqueda por película===================");

				System.out.println("Introduzca un nombre de pélicula");
				String fn = myObj.nextLine(); // Read user input
				System.out.println(fn);
				List<Actor> peters = actorRepository.findByFilm(fn);
				peters.forEach(System.out::println);
				if (peters.isEmpty()) {
					System.out.println("Sin resultados. Tienes que escribir el completo de la película para encontrar"
							+ "a un actor");
				}

			} else if (num.equals("0")) {
				exit = true;
			} else {
				System.out.println("Introduzca un número");
			}

		}

		myObj.close();
	}

	public static Boolean checkIsNumber(String arg) {

		boolean numeric = true;

		try {
			Double.parseDouble(arg);
		} catch (NumberFormatException e) {
			numeric = false;
		}

		return numeric;
	}
}
