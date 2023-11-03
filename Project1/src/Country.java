/**
 * The Country class gets and sets the Country Name, Capitol, Population, GDP
 * 	(Gross Domestic Product), Area, and Happiness Index of a Country object.
 * 	It also contains a method to print a Country object.
 * 
 * @author  Emma Choi
 * @version 09/13/2023
 */
public class Country
{
	private String countryName;
	private String capitol;
	private double population;
	private double gdp;
	private double area;
	private double happyIndex;

	/**
	 * Sets the parameters to the variables in this class,
	 * 	creating a Country object.
	 * 
	 * @param countryName String of the country's name.
	 * @param capitol	  String of the country's capitol.
	 * @param population  double of the country's population.
	 * @param gdp		  double of the country's GDP.
	 * @param area		  double of the country's area.
	 * @param happyIndex  double of the country's happiness index.
	 */
	public Country(String countryName, String capitol, double population, double gdp, double area, double happyIndex)
	{
		this.countryName = countryName;
		this.capitol = capitol;
		this.population = population;
		this.gdp = gdp;
		this.area = area;
		this.happyIndex = happyIndex;
	}
	
	/**
	 * Gets the country's name.
	 * 
	 * @return the country's name.
	 */
	public String getCountryName()
	{
		return countryName;
	}
	
	/**
	 * Sets the country's name.
	 * 
	 * @param countryName the country's name.
	 */
	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}
	
	/**
	 * Gets the country's capitol.
	 * 
	 * @return the country's capitol.
	 */
	public String getCapitol()
	{
		return capitol;
	}
	
	/**
	 * Sets the country's capitol.
	 * 
	 * @param capitol the country's capitol.
	 */
	public void setCapitol(String capitol)
	{
		this.capitol = capitol;
	}
	
	/**
	 * Gets the country's population.
	 * 
	 * @return the country's population.
	 */
	public double getPopulation()
	{
		return population;
	}
	
	/**
	 * Sets the country's population.
	 * 
	 * @param population the country's population.
	 */
	public void setPopulation(double population)
	{
		this.population = population;
	}
	
	/**
	 * Gets the country's Gross Domestic Product (GDP).
	 * 
	 * @return the country's GDP.
	 */
	public double getGdp()
	{
		return gdp;
	}
	
	/**
	 * Sets the country's Gross Domestic Product (GDP).
	 * 
	 * @param gdp the country's Gross Domestic Product.
	 */
	public void setGdp(double gdp)
	{
		this.gdp = gdp;
	}
	
	/**
	 * Get's the area of the country.
	 * 
	 * @return the area of the country.
	 */
	public double getArea()
	{
		return area;
	}
	
	/**
	 * Sets the country's area.
	 * 
	 * @param area the country's area.
	 */
	public void setArea(double area)
	{
		this.area = area;
	}
	
	/**
	 * Gets the country's Happiness Index
	 * 	(measures the satisfaction of citizens).
	 * 
	 * @return the country's Happiness Index.
	 */
	public double getHappyIndex()
	{
		return happyIndex;
	}
	
	/**
	 * Sets the country's Happiness Index.
	 * 
	 * @param happyIndex the Happiness Index score of the country.
	 */
	public void setHappyIndex(double happyIndex)
	{
		this.happyIndex = happyIndex;
	}
	
	/**
	 * A method to print a Country object.
	 */
	public void printCountry()
	{
		System.out.printf("%s, %s, %6.3e, %.6f, %.3f\n", countryName, capitol, gdp, area, happyIndex);		
	}
}
