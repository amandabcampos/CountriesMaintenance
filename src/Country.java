import java.util.Comparator;

/*
 * 
 * @author amandabcampos
 *
 */

public class Country {

	private String name;
	private int population;
	private String continent;
	static final Comparator<Country> POP_ORDER = new Comparator<>() {

		@Override
		public int compare(Country country1, Country country2) {
			if (country1.getPopulation() > country2.getPopulation()) {
				return -1;
			} else if (country1.getPopulation() < country2.getPopulation()) {
				return 1;
			}
			return 0;
		}
		
	};
	
	static final Comparator<Country> ALP_ORDER = new Comparator<>() { 

		@Override
		public int compare(Country country1, Country country2) {
			if (country1.getName().compareTo(country2.getName()) > 0) {
				return 1;
			} else if (country1.getName().compareTo(country2.getName()) < 0) {
				return -1;
			}
			return 0;
		}
		
	};
	

	public Country(String name, int population, String continent) {
		this.name = name;
		this.population = population;
		this.continent = continent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	@Override
	public String toString() {
		return "----------------------------------------------------------\n" + 
				String.format("%20s %15d %15s", name, population, continent);
	}

}
