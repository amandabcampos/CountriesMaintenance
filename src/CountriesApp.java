import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * 
 * @author amandabcampos
 *
 */

public class CountriesApp {
	
	private static FileHelper<Country> fileHelper = new FileHelper<>("src/countries.txt", new CountryLineConverter()); 

	public static void main(String[] args) {
		
//		Path path1 = Paths.get("src/countries.txt");
//		if (Files.notExists(path1)) {
//			try {
//				Files.createFile(path1);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
//		Path path2 = Paths.get("src/countries.dat");
//		if (Files.notExists(path2)) {
//			try {
//				Files.createFile(path2);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
		Scanner scnr = new Scanner(System.in);
		
		greeting();
		displayMenu();
		
		boolean looping = true;
		while (looping) {
			int option = Validator.getInt(scnr, "Enter menu number: ", 1, 5);
			System.out.println();
			switch(option) {
			case 1:
				String sorting = Validator.getString(scnr, "Enter sorting method (a - alphabetically or p - population): ");
				listCountries(sorting);
				displayMenu();
				break;	
			case 2:
				String country = Validator.getString(scnr, "Enter country's name: ");
				int population = Validator.getInt(scnr, "Enter country's population: ", 0, Integer.MAX_VALUE);
				String continent = Validator.getString(scnr, "Enter country's continent: ");
				addCountry(country, population, continent);
				displayMenu();
				break;
			case 3:
				deleteCountry(Validator.getString(scnr, "Enter country's name: "));
				displayMenu();
				break;
			case 4:
				String newName = Validator.getString(scnr, "Enter country: ");
				int newPopulation = Validator.getInt(scnr, "New population: ", 0, Integer.MAX_VALUE);
				modifyPopulation(newName, newPopulation);
				displayMenu();
				break;
			case 5:
				System.out.print("Buh-bye!");
				looping = false;
			}
		}
		
		scnr.close();

	}
	
	public static void greeting() {
		System.out.println("Welcome to the Countries Maintenance Application!\n");
	}
	
	public static void displayMenu() {
		System.out.println("1- See the list of countries\n2- Add a country\n3- Delete country\n4- Modify Population\n5- Exit");
	}
	
	public static void listCountries(String sorting) {
		List<Country> countries = fileHelper.readAll();  
		
		
		if (sorting.startsWith("p")) {
			Collections.sort(countries, Country.POP_ORDER);	
		} else {
			Collections.sort(countries, Country.ALP_ORDER);	
		}
		
		System.out.printf("%20s %15s %15s\n", "Name", "Population", "Continent");
		for (Country country : countries) {
			System.out.println(country);
		}
		System.out.println();
	}
	
	public static void addCountry(String name, int population, String continent) {
		Country country = new Country(name, population, continent);
		fileHelper.append(country);
		System.out.println("\nThis country has been saved!\n");
	}
	
	public static void deleteCountry(String name) {
		
		List<Country> countries = fileHelper.readAll();
		
		List<Country> countriesCopy = new ArrayList<>();
		for (Country country : countries) {
			if (!country.getName().toLowerCase().equals(name.toLowerCase())) {
				countriesCopy.add(country);
			}
		}
		
		fileHelper.rewrite(countriesCopy);
		System.out.println("Country deleted\n");
	}
	
	public static void modifyPopulation(String name, int population) {
		List<Country> countries = fileHelper.readAll();
		List<Country> countriesCopy = new ArrayList<>();
		for (Country country : countries) {
			if (country.getName().toLowerCase().equals(name.toLowerCase())) {
				country.setPopulation(population);
			}
			countriesCopy.add(country);
		}
		
		fileHelper.rewrite(countriesCopy);
		System.out.println("Population updated!\n");
	}
	

}
