/**
 * The PriorityQ class implements a priority queue of Country
 * 	objects using a sorted array. The higher the happiness
 * 	index of the country, the higher it's priority. It supports
 * 	the methods PriorityQ, insert, remove, printPriorityQ,
 * 	isEmpty, and isFull. Each method edits or checks the
 * 	priority queue array.
 * 
 * @author Emma Choi
 * @version 10/05/2023
 */

public class PriorityQ
{
	// Variable declaration.
	private Country[] countries;
	private int maxSize;
	private int numCountries;
	
	/**
	 * The PriorityQ method is a constructor method that creates
	 * 	the priority queue array based on a provided size.
	 * 
	 * @param s maximum size of the priority queue array.
	 */
	public PriorityQ(int s)
	{
		maxSize = s;
		countries = new Country[maxSize];
		numCountries = 0;
	}// End constructor PriorityQ.
	
	/**
	 * The insert method checks to see if the priority queue array
	 * 	is full and if so, will let the user know. Otherwise, a
	 * 	new country will be inserted based on its happiness index.
	 * 	This is an O(N) method.
	 * 
	 * @param country the country to be inserted.
	 */
	public void insert(Country country)
	{
		if(isFull())
		{
			System.out.println("Priority Queue is full!");
		}
		else
		{
		
			if(numCountries == 0)
			{
				countries[numCountries++] = country;
			}// End inner if.
			else
			{
				int i;
				for(i = numCountries - 1; i >= 0; i--)
				{
					if(country.getHappyIndex() < countries[i].getHappyIndex())
						countries[i + 1] = countries[i];
					else
						break;
				}// End for.
			
				countries[i + 1] = country;
				numCountries++;
			}// End inner else.
		}// End else.
	}// End insert method.
	
	/**
	 * Method remove will remove a country from the queue and
	 * 	return it. This is an O(1) method. When used, the country
	 * 	with the highest priority (highest happiness index) will
	 * 	be removed and not seen in the output.
	 * 
	 * @return the removed country.
	 */
	public Country remove()
	{
		return countries[--numCountries];
	}// End remove method.

	/**
	 * Method printPriorityQ prints the priority queue from the
	 * 	front to rear of it using a for loop.
	 */
	public void printPriorityQ()
	{
		String headers = String.format("%-40s%-20s%-20s%-20s%-20s", "Name", "Capitol", "GDPPC", "APC", "HappinessIndex");
		int headersLength = headers.length();
		System.out.println(headers);
		
		for(int i = 0; i < headersLength - 5; i++)
		{
			System.out.print("-");
		}
		
		System.out.println();
		
		int i;
		for(i = numCountries - 1; i >= 0; i--)
		{
			Country c = countries[i];
			if(c != null)
				System.out.printf("%-40s%-20s%-20s%-20s%-20s%n", c.getCountryName(), c.getCapitol(),
						String.format("%.3f", c.getGdp()/c.getPopulation()), String.format("%.6f", (c.getArea()/c.getPopulation())), String.format("%.3f", c.getHappyIndex()));
		}
		
		System.out.println();
	}// End printPriorityQ method.
	
	/**
	 * Method isEmpty checks to see if the priority queue is empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return numCountries == 0;
	}
	
	/**
	 * Method isFull checks to see if the priority queue is full.
	 * 
	 * @return true if full, false otherwise.
	 */
	public boolean isFull()
	{
		return numCountries == maxSize;
	}
	
}// End PriorityQ class.
