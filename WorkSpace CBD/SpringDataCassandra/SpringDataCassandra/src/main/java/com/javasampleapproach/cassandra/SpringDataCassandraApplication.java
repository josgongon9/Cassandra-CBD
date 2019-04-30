package com.javasampleapproach.cassandra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasampleapproach.cassandra.model.Customer;
import com.javasampleapproach.cassandra.repository.CustomerRepository;

@SpringBootApplication
public class SpringDataCassandraApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataCassandraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		clearData();
		saveData();
		lookup();
	}

	public void clearData() {
		customerRepository.deleteAll();
	}

	public void saveData() {

		String csvFile = "/Users/josem/Desktop/Libro1.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] customer = line.split(cvsSplitBy);

				// System.out.println("Customer [id= " + customer[0] + " , name=" + customer[1]
				// + " , surname=" + customer[2] + " , age=" + customer[3] + "]");

				Customer cust = new Customer();
				cust.setId(Integer.valueOf(customer[0]));
				cust.setFirstname(customer[1]);
				cust.setLastname(customer[2]);
				cust.setAge(Integer.valueOf(customer[3]));

				System.out.println(cust);
				customerRepository.save(cust);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void lookup() {

		Scanner myObj = new Scanner(System.in); // Create a Scanner object
		System.out.println("Escoja una opción:" + "1. Para ver todo" + "2. Para Consultar por nombre"
				+ "3. Para consultar por edad");

		int num = myObj.nextInt(); // Read user input
		System.out.println(num);

		if (num == 1) {

			System.out.println("===================Select All===================");
			List<Customer> all = (List<Customer>) customerRepository.findAll();
			all.forEach(System.out::println);
		} else if (num == 2) {

			System.out.println("===================Lookup Customers from Cassandra by Firstname===================");
			Scanner myFirstName = new Scanner(System.in); // Create a Scanner object
			System.out.println("Introduzca un nombre");
			String fn = myFirstName.nextLine(); // Read user input
			System.out.println(fn);
			List<Customer> peters = customerRepository.findByFirstname(fn);
			peters.forEach(System.out::println);
		} else if (num == 3) {

			System.out.println("===================Lookup Customers from Cassandra by Age===================");
			List<Customer> custsAgeGreaterThan25 = customerRepository.findCustomerHasAgeGreaterThan(25);
			custsAgeGreaterThan25.forEach(System.out::println);
		}

	}
}