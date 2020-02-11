

/*
 * 
 * @author amandabcampos
 *
 */

public class CountryLineConverter implements LineConverter<Country> {

	@Override
	public String toLine(Country country) {
		return String.format("%s|%d|%s", country.getName(), country.getPopulation(), country.getContinent());
	}

	@Override
	public Country fromLine(String line) {
		
		String[] parts = line.split("\\|");
		String name = parts[0];
		int population = Integer.parseInt(parts[1]);
		String continent = parts[2];
		return new Country(name, population, continent);
	}

}
