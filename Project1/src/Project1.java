import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * COP3530: Project 1 - Array Searches and Sorts
 * <p>
 * Class Project1 involves sorting and searching arrays. Input of
 * 	a file is needed, which is then converted into an array and
 * 	used to do various requirements, such as sorting by name, or
 * 	calculating Kendall's Tau-b Correlation. Project1 uses an array
 * 	of countries and manipulates that data as needed.
 * <p>
 * Project1 requires an input file and outputs that information
 * 	either as a report, sorted by name, sorted by Happiness Index,
 * 	or sorted by GDP per capita. Furthermore, this class allows the
 * 	user to find and print a certain country and prints Kendall's
 * 	Tau-b correlation between GDPPC, APC, and the Happiness Index.
 * <p>
 * Project1 uses various methods to complete these requirements and
 * 	some methods use other methods to get the job done.
 * 
 * @author Emma Choi
 * @version 09/13/2023
 */
public class Project1
{
	/**
	 * Method main takes in a file, reads the file, and converts each
	 * 	element into a Country object. It lists how many records were
	 * 	read and includes a switch case to allow the user to choose
	 * 	what they would like to be done to the data from a menu of options.
	 * 	Based on the user's input, respective methods are called.
	 * 
	 * @param args an array of command-line arguments.
	 */
	public static void main(String[] args)
	{
		System.out.println("COP3530 Project 1");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		System.out.println("Array Searches and Sorts");
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter the file name: ");
		String fileName = scnr.next();// Gets the file name from the user.
		
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(fileName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}

		try
		{
			@SuppressWarnings("unused")
			String headers = br.readLine(); // Read in the headers.
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		String l;
		int counter = 0;
		Country[] countriesArray = new Country[128];
		try
		{
			while((l = br.readLine()) != null)
			{
				String[] countries = l.split(","); // Temporary array to store countries.
				
				String countryName = countries[0];
				String capitol = countries[1];
				double population = Double.parseDouble(countries[2]);
				double gdp = Double.parseDouble(countries[3]);
				double area = Double.parseDouble(countries[4]);
				double happyIndex = Double.parseDouble(countries[5]);
				
				Country c = new Country(countryName, capitol, population, gdp, area, happyIndex);
				countriesArray[counter] = c; // Add c to the official array of each country object.
				counter++;
				
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.printf("There were %d records read.", counter);
		System.out.println();
		
		// Switch case.
		boolean flag = true;
		do
		{
			System.out.println("1. Print a countries report");
			System.out.println("2. Sort by Name");
			System.out.println("3. Sort by Happiness Index");
			System.out.println("4. Sort by GDP per capita");
			System.out.println("5. Find a print a given country");
			System.out.println("6. Print Kendall's tau matrix");
			System.out.println("7. Quit");
			
			System.out.println("Enter your choice: ");
			int choice = scnr.nextInt();
			
			switch(choice)
			{
			case 1:
				printCountriesReport(countriesArray);
				break;
			case 2:
				sortByName(countriesArray);
				break;
			case 3:
				sortByHappyIndex(countriesArray);
				break;
			case 4:
				sortByGdp(countriesArray);
				break;
			case 5:
				printGivenCountry(countriesArray);
				break;
			case 6:
				printKendallsMatrix(countriesArray);
				break;
			case 7:
				System.out.println("Have a good day!");
				flag = false;
				break;
			default:
				System.out.println("Invalid choice! Enter 1-7: ");
				break;
			}
			
		}while(flag);
		
		scnr.close();
	}// End main.

	/**
	 * Method printCountriesReport uses countriesArray to print each
	 * 	Country object in the array. As long as c (the Country object)
	 * 	is not empty (null), the method will print the country in a
	 * 	certain format by using the getter methods in Country.java.
	 * 
	 * @param countriesArray an array of Country objects.
	 */
	public static void printCountriesReport(Country[] countriesArray)
	{
		String headers = String.format("%-40s%-20s%-20s%-20s%-20s", "Name", "Capitol", "GDPPC", "APC", "HappinessIndex");
		int headersLength = headers.length();
		System.out.println(headers);
		
		for(int i = 0; i < headersLength - 5; i++)
		{
			System.out.print("-");
		}
		
		System.out.println();
		
		for(Country c : countriesArray)
		{
			if(c != null)
				System.out.printf("%-40s%-20s%-20s%-20s%-20s%n", c.getCountryName(), c.getCapitol(),
						String.format("%.3f", c.getGdp()/c.getPopulation()), String.format("%.6f", (c.getArea()/c.getPopulation())), c.getHappyIndex());
		}
		
		System.out.println();
		
	}
	
	/**
	 * Method sortByName uses countriesArray and sorts the countries in
	 * 	alphabetical order using Insertion Sort. It uses inner and outer
	 * 	to keep track of the order in which the countries are being moved,
	 * 	and compares a temporary Country object to the current Country
	 * 	object (countriesArray[inner]) and determines whether either needs
	 * 	to be moved. Finally, a message is printed to the user that the
	 * 	countries are now sorted by name.
	 * 
	 * @param countriesArray an array of Country objects.
	 */
	public static void sortByName(Country[] countriesArray)
	{
		int arrayLength = countriesArray.length;
		int inner;
		int outer;

		for(outer = 1; outer < arrayLength; outer++)
		{
			Country temp = countriesArray[outer]; // Temporary Country object to use in comparison.

			if(temp != null)
			{
				inner = outer - 1;

				while(inner >= 0 && countriesArray[inner] != null && countriesArray[inner].getCountryName().compareTo(temp.getCountryName()) > 0)
				{
					countriesArray[inner + 1] = countriesArray[inner];
					inner--;
				}

				countriesArray[inner + 1] = temp;
			}// End inner if.
		}// End for loop.
		
		System.out.println("Countries sorted by Name.");
		System.out.println();
	}// End sortByName.
	
	/**
	 * Method sortByHappyIndex sorts the countriesArray elements in
	 * 	ascending order by their Happiness Index number. It uses
	 * 	Selection Sort to do so.
	 * 
	 * @param countriesArray an array of Country objects.
	 */
	public static void sortByHappyIndex(Country[] countriesArray)
	{
		int arrayLength = countriesArray.length;
		
		for(int outer = 0; outer < arrayLength - 1; outer++)
		{
			int lowest = outer;
			
			for(int inner = outer + 1; inner < arrayLength; inner++)
			{
				if((countriesArray[inner] != null) && ((countriesArray[lowest] == null) || 
					(countriesArray[inner].getHappyIndex() < countriesArray[lowest].getHappyIndex())))
				{
					lowest = inner;
				}
			}// End inner for loop.
			
			Country temp = countriesArray[outer];
			countriesArray[outer] = countriesArray[lowest];
			countriesArray[lowest] = temp;
			
		}// End outer for loop.

		System.out.println("Countries sorted by Happiness Index.");
		System.out.println();
	}
	
	/**
	 * Method sortByGdp uses Bubble Sort to sort countriesArray by
	 * 	GDP per capita (GDPPC) in ascending order. To get GDPPC, the
	 * 	method divides GDP by population, using the getter methods
	 * 	from Country.java. It uses a boolean variable to execute the
	 * 	do-while loop, which sorts using Bubble Sort. Finally, a
	 * 	message is printed to the user that the countries have been
	 * 	sorted by GDPPC.
	 * 
	 * @param countriesArray an array of Country objects.
	 */
	public static void sortByGdp(Country[] countriesArray)
	{
		int arrayLength = countriesArray.length;
		boolean swap;
		
		do
		{
			swap = false;
			for(int i = 0; i < arrayLength - 1; i++)
			{
				Country country1 = countriesArray[i];
				Country country2 = countriesArray[i + 1];
				
				if(country1 != null && country2 != null)
				{
					double gdppc1 = country1.getGdp() / country1.getPopulation(); // GDP divided by population = GDPPC.
					double gdppc2 = country2.getGdp() / country2.getPopulation();
					
					if(gdppc1 > gdppc2)
					{
						Country temp = countriesArray[i];
						countriesArray[i] = countriesArray[i + 1];
						countriesArray[i + 1] = temp;
						swap = true;
					}// End second inner if.
				}// End inner if.
			}// End for loop.
		}while(swap); // End do-while loop.
		
		System.out.println("Countries sorted by GDP per capita.");
		System.out.println();
	}
	
	/**
	 * Method printGivenCountry allows the user to enter a country
	 * 	name. If the name is not correct, an error will print.
	 * 	Otherwise, the program will search and print the user's
	 * 	entered country using either Binary Search or Sequential
	 * 	Search. There are a few conditions to this method.
	 * <p>
	 * If the data is sorted by name, i.e., then the method will find
	 * 	the user's input (a country name) using Binary Search. Otherwise,
	 * 	the program will find the user's country through Sequential Search.
	 * 	Then, the program will print that country's information.
	 * <p>
	 * Three other methods are used to complete this method, isSortedByName,
	 * 	binarySearch, and sequentialSearch.
	 * 
	 * @param countriesArray an array of Country objects.
	 */
	public static void printGivenCountry(Country[] countriesArray)
	{
		@SuppressWarnings("resource") // Note to Dr. Liu or grader: Closing givenCountryScanner yields a NoSuchElementException
									 //		error. I'm not sure why, I've tried many different ways of fixing this issue within my code.
									// 		I figured it could be because I've used multiple scanners. Either way, I wanted my
								   //		code to run without errors, so I commented out givenCountryScnr.close() below.
		
		Scanner givenCountryScnr = new Scanner(System.in); // Creates a scanner for this method.
		System.out.print("Enter country name: ");
		System.out.println();
		String userInput = givenCountryScnr.nextLine().trim();
		
		if(isSortedByName(countriesArray))
		{
			int index = binarySearch(countriesArray, userInput);
			
			if(index != -1)
			{
				System.out.println();
				System.out.println("Binary search is used");
				System.out.println();
				System.out.printf("Name: %s", countriesArray[index].getCountryName());
				System.out.println();
				System.out.printf("Capitol: %s", countriesArray[index].getCapitol());
				System.out.println();
				System.out.printf("GDPPC: %s", String.format("%.3f", countriesArray[index].getGdp()/countriesArray[index].getPopulation()));
				System.out.println();
				System.out.printf("APC: %s", String.format("%.6f", (countriesArray[index].getArea()/countriesArray[index].getPopulation())));
				System.out.println();
				System.out.printf("Happiness: %s", countriesArray[index].getHappyIndex());
				System.out.println();
				System.out.println();
			}// End inner if.
			else
			{
				System.out.printf("Error: country %s not found", userInput);
				System.out.println();
				System.out.println();
			}// End inner else.
		}// End outer if.
		
		else
		{
			int index = sequentialSearch(countriesArray, userInput);
			
			if(index != -1)
			{
				System.out.println();
				System.out.println("Sequential search is used");
				System.out.println();
				System.out.printf("Name: %s", countriesArray[index].getCountryName());
				System.out.println();
				System.out.printf("Capitol: %s", countriesArray[index].getCapitol());
				System.out.println();
				System.out.printf("GDPPC: %s", String.format("%.3f", countriesArray[index].getGdp()/countriesArray[index].getPopulation()));
				System.out.println();
				System.out.printf("APC: %s", String.format("%.6f", (countriesArray[index].getArea()/countriesArray[index].getPopulation())));
				System.out.println();
				System.out.printf("Happiness: %s", countriesArray[index].getHappyIndex());
				System.out.println();
				System.out.println();
			}// End inner if.
			else
			{
				System.out.printf("Error: country %s not found", userInput);
				System.out.println();
				System.out.println();
			}// End inner else.	
		}// End else.
		
		// givenCountryScnr.close();
		
	}// End printGivenCountry method.
	
	/**
	 * Method isSortedByName determines whether the countriesArray
	 * 	is sorted alphabetically. It will return false if the
	 * 	comparison of country name's is greater than zero, and true
	 * 	otherwise.
	 * 
	 * @param countriesArray an array of Country objects.
	 * @return true or false.
	 */
	public static boolean isSortedByName(Country[] countriesArray)
	{
		for(int i = 1; i < countriesArray.length; i++)
		{
			if(countriesArray[i - 1] != null && countriesArray[i] != null)
			{
				if(countriesArray[i - 1].getCountryName().compareTo(countriesArray[i].getCountryName()) > 0)
				{
					return false;
				}// End inner if.
			}// End if.
		}// End for loop.
		
		return true;
	}
	
	/**
	 * Method binarySearch completes a binary search using the array
	 * 	of countries and user input, which would be the needed country
	 * 	name as asked for in method printGivenCountry.
	 * <p>
	 * If the country is found right away, the variable middle is
	 * 	returned, which is the array index. Otherwise, the binary
	 * 	search continues. If the country is not found, negative 1
	 * 	is returned.
	 * 
	 * @param countriesArray an array of Country objects.
	 * @param userInput the name of the country.
	 * @return negative 1 or variable middle (array index).
	 */
	public static int binarySearch(Country[] countriesArray, String userInput)
	{
		int left = 0;
		int right = countriesArray.length - 1;
		
		while(left <= right)
		{
			int middle = left + (right - left) / 2;
			
			if(countriesArray[middle] != null)
			{
				String middleName = countriesArray[middle].getCountryName();
				
				int comparison = middleName.compareTo(userInput);
				
				if(comparison == 0)
				{
					return middle; // Returns the found country index.
				}// End inner if.
				else if(comparison < 0)
				{
					left = middle + 1;
				}// End else if.
				else
				{
					right = middle - 1;
				}// End else.
			}// End if.
		}// End while loop.
		
		return -1; // Country was not found.
		
	}// End binarySearch method.
	
	/**
	 * Method sequentialSearch runs a sequential search for the user's
	 * 	inputed country. Using the array of countries and the user
	 * 	input, if the country is found the method will return i (which
	 * 	would be the index at found country), otherwise it will return
	 * 	negative 1, indicating the country has not been found.
	 * 
	 * @param countriesArray an array of Country objects.
	 * @param userInput the name of the country.
	 * @return negative 1 or i (array index).
	 */
	public static int sequentialSearch(Country[] countriesArray, String userInput)
	{
		for(int i = 0; i < countriesArray.length; i++)
		{
			if(countriesArray[i] != null && countriesArray[i].getCountryName().equalsIgnoreCase(userInput))
			{
				return i; // Returns the found country index.
			}// End inner if.
		}// End for loop.
		
		return -1; // Country was not found.
		
	}// End sequentialSearch method.
	
	/**
	 * Method printKendallsMatrix uses the array of countries and 2
	 * 	other methods to complete the calculation of Kendall's Tau-b
	 * 	Correlation. It uses the ascending rankings of GDPPC and APC
	 * 	in relation to the Happiness Index score.
	 * 
	 * @param countriesArray an array of Country objects.
	 */
	public static void printKendallsMatrix(Country[] countriesArray)
	{
		int[] gdppcRank = calcRank(countriesArray, "GDPPC");
		int[] apcRank = calcRank(countriesArray, "APC");
		int[] happyIndexRank = calcRank(countriesArray, "HappinessIndex");
		
		double kendallsGdpHappy = calcKendalls(gdppcRank, happyIndexRank);
		double kendallsApcHappy = calcKendalls(apcRank, happyIndexRank);
		
		// Print out Kendall's Correlation Matrix
		System.out.println();
		System.out.println("+---------------------------------------------+");
		System.out.println("|               |     GDPPC     |     APC     |");
		System.out.println("|---------------|-----------------------------|");
		System.out.printf("|Happiness Index|      %.4f       %.4f   |\n", kendallsGdpHappy, kendallsApcHappy);
		System.out.println("+---------------------------------------------+");
		
	}// End printKendallsMatrix method.
	
	/**
	 * Method calcRank uses the array of countries and a String s
	 * 	(representing GDPPC, APC, or Happiness Index) to gather
	 * 	the necessary data for completing the Kendall's Correlation.
	 * 	If s were to be GDPPC, for example, then the GDP data is
	 * 	gathered using the getters in Country.java. The method then
	 * 	ranks the country data.
	 * 
	 * @param countriesArray an array of Country objects.
	 * @param s String representing the data of either GDPPC, APC, or Happiness Index.
	 * @return ranks of data
	 */
	public static int[] calcRank(Country[] countriesArray, String s)
	{
		int[] ranks = new int[countriesArray.length];
		double[] values = new double[countriesArray.length];
		
		for(int i = 0; i < countriesArray.length; i++)
		{
			if(s.equals("GDPPC"))
			{
				values[i] = countriesArray[i].getGdp() / countriesArray[i].getPopulation();
			}
			
			else if(s.equals("APC"))
			{
				values[i] = countriesArray[i].getArea() / countriesArray[i].getPopulation();
			}
			
			else if(s.equals("HappinessIndex"))
			{
				values[i] = countriesArray[i].getHappyIndex();
			}
		}// End for loop.
		
		int[] index = new int[countriesArray.length];
		for(int i = 0; i < countriesArray.length; i++)
		{
			index[i] = i;
		}// End for loop for indexes.
		
		for(int i = 0; i < index.length - 1; i++)
		{
			for(int j = 0; j < index.length - i - 1; j++)
			{
				if(values[index[j]] > values[index[j + 1]])
				{
					int temp = index[j];
					index[j] = index[j + 1];
					index[j + 1] = temp;
				}// End inner if.
			}// End inner for loop.
		}// End for loop for sorting.
		
		int rank = 1;
		for(int i : index)
		{
			ranks[i] = rank++;
		}
		
		return ranks;
		
	}// End calculateRank method.
	
	/**
	 * Method calcKendalls calculates Kendall's Correlation through the
	 * 	use of 2 integer arrays. The array ranks1 takes in either the
	 * 	GDPPC numbers or the APC numbers, which are then compared to
	 * 	happyIndexRank respectively. The amount of 'agrees' and
	 * 	'disagrees' are counted, and the final result is calculated
	 * 	within the return statement.
	 * 
	 * @param ranks1 GDPPC ranks or APC ranks
	 * @param happyIndexRank Happiness Index ranks
	 * @return Kendall's Tau-b Correlation
	 */
	public static double calcKendalls(int[] ranks1, int[] happyIndexRank)
	{
		int agree = 0;
		int disagree = 0;
		
		for(int i = 0; i < ranks1.length - 1; i++)
		{
			for(int j = i + 1; j < ranks1.length; j++)
			{
				int sign1 = Integer.compare(ranks1[i],  ranks1[j]);
				int sign2 = Integer.compare(happyIndexRank[i],  happyIndexRank[j]);
				
				if(sign1 == sign2)
				{
					agree++;
				}// End inner if.
				
				else
				{
					disagree++;
				}// End inner else.
			}// End inner for loop.
		}// End outer for loop.
		
		// Return Kendall's Correlation.
		return (agree - disagree) / Math.sqrt((agree + disagree) * (agree + disagree - 1));
		
	}// End calcKendalls method.
	
}// End class Project1.


	

