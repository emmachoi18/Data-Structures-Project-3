import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 - Linked Lists
 * <p>
 * Class Project3 utilizes Linked Lists, specifically a stack that uses a
 * 	double-ended singly linked list, and a priority queue that uses a sorted
 * 	doubly linked list. The priority of the priority queue is based on the
 * 	country's Happiness Index. Input of a file is needed, which is then read,
 *  added to the stack and priority queue in their respective ways through
 *  method calls or popping from the stack and inserting, and finally utilized
 *  to complete method calls which can delete a certain interval block of
 *  countries, or simply print the priority queue. This class also contains
 *  helper methods to assist in the completion of the above tasks.
 * 	
 * <p>
 *  Project3 requires a file as input, which contains countries and their
 *   name, capitol, population, GDP (gross domestic product), area, and
 *   Happiness Index score. Output generated from this information is a report
 *   of the countries from its stack and priority queue (the priority queue
 *   will have the highest Happiness Index scoring countries at the highest
 *   priority). Furthermore, should the user choose option 1 from the menu,
 *   they can remove certain countries with a Happiness Index between the
 *   numbers they specified. The user may choose option 2 to print the priority
 *   queue again and view their deletions.
 * 
 * @author Emma Choi
 * @version 10/25/2023
 */

public class Project3
{
	/**
	 * Method main takes in a file and runs it through the readCountries method.
	 * 	By doing this, the method is able to fill up the stack, countriesStack,
	 * 	and therefore fill the priority queue, countriesPQ. The method also
	 * 	contains a switch case in which users can choose from three options, which
	 * 	will manipulate the countries (removal) or simply print them. Respective
	 * 	methods are called within the switch case.
	 * 
	 * @param args an array of command-line arguments.
	 */
	public static void main(String[] args)
	{
		System.out.println("COP3530 Project 3");
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter the file name: ");
		String fileName = scnr.next();// Gets the file name from the user.
		
		// Create and print stack.
		System.out.println("Stack Contents:");
		System.out.println();
		Stack countriesStack = readCountries(fileName);
		countriesStack.printStack();
		System.out.println();
		
		// Create and print priority queue.
		System.out.println("Priority Queue Contents:");
		System.out.println();
		PriorityQ countriesPQ = new PriorityQ();
		while(!countriesStack.isEmpty())
		{
			Country country = countriesStack.pop();
			countriesPQ.insert(country);
		}// End while loop.
		sortByHappinessIndex(countriesPQ);
		countriesPQ.printPriorityQ();
		System.out.println();
	
		// Switch case.
		boolean flag = true;
		do
		{
			System.out.println("1. Enter a happiness interval for deletions on priority queue");
			System.out.println("2. Print priority queue");
			System.out.println("3. Exit");
			
			System.out.println("Enter your choice: ");
			if(scnr.hasNextInt())
			{
				int choice = scnr.nextInt();
				
				switch(choice)
				{
				case 1:
					intervalDelete(countriesPQ);
					break;
				case 2:
					printPriorityQueue(countriesPQ);
					break;
				case 3:
					System.out.println("Have a good day!");
					flag = false;
					break;
				default:
					System.out.println("Invalid choice! Enter 1-3: ");
					break;
				}// End switch.
			}// End if.
				
			else
			{
				System.out.println("Invalid choice! Enter 1-3: ");
				scnr.next();
			}// End else.
			
		}while(flag);
		
		scnr.close();
	}// End main.
	
	/**
	 * Method readCountries takes in a fileName and reads the file. Then, the
	 * 	countries from the file are put into a temporary array, the 6 fields
	 * 	of information are stored, and each country with an Excellent, Very
	 * 	Good, and Good rating are pushed onto the stack. An Excellent rating
	 * 	is given to countries with a happiness index of >= 6.5, a Very Good
	 * 	rating is given to countries with a happiness index of no less than
	 * 	5.5 but less than 6.5, and the Good rating is a happiness index of no less
	 * 	than 4 but less than 5.5. Fair rated countries (happiness index score of no
	 * 	less than 0 but less than 4) are not included in the stack, nor in the priority
	 * 	queue.
	 * 
	 * @param fileName the name of the file to be read.
	 * @return The stack of countries with an Excellent, Very Good, or Good
	 * 			rating (based on Happiness Index).
	 */
	public static Stack readCountries(String fileName)
	{
		Stack countriesStack = new Stack();

		try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String l;
			br.readLine(); // Read in headers.

			while((l = br.readLine()) != null)
			{	
				String[] countries = l.split(","); // Temporary array to store countries.

				String countryName = countries[0];
				String capitol = countries[1];
				double population = Double.parseDouble(countries[2]);
				double gdp = Double.parseDouble(countries[3]);
				double area = Double.parseDouble(countries[4]);
				double happyIndex = Double.parseDouble(countries[5]);

				// Excellent countries.
				if(happyIndex >= 6.5)
				{
					Country c = new Country(countryName, capitol, population, gdp, area, happyIndex);
					countriesStack.push(c);
				}// End if.
				
				// Very Good countries.
				else if(happyIndex >= 5.5 && happyIndex < 6.5)
				{
					Country c = new Country(countryName, capitol, population, gdp, area, happyIndex);
					countriesStack.push(c);
				}// End else if.
				
				// Good countries.
				else if(happyIndex >= 4.0 && happyIndex < 5.5)
				{
					Country c = new Country(countryName, capitol, population, gdp, area, happyIndex);
					countriesStack.push(c);
				}// End else if.
			}// End while loop.
		}// End try.
			
			catch(IOException e)
			{
				e.printStackTrace();
			}// End catch.
		
		return countriesStack;

	}// End method readCountries.
	
	/**
	 * Method sortByHappinessIndex sorts a priority queue of countries by
	 * 	their happiness index score. It does so by storing the countries of
	 * 	the priority queue in a temporary array. Then, they are sorted and
	 * 	inserted back into the priority queue. This method supports other
	 * 	methods printPriorityQueue and method main.
	 * 
	 * @param countriesPQ the priority queue of country objects.
	 */
	public static void sortByHappinessIndex(PriorityQ countriesPQ)
	{
		// Temporary array to make sorting easier.
		Country[] countries = new Country[200];
		int numCountries = 0;
		
		while(!countriesPQ.isEmpty())
		{
			countries[numCountries++] = countriesPQ.remove();
		}// End while loop.
		
		for(int i = 1; i < numCountries; i++)
		{
			Country key = countries[i];
			int j = i - 1;
			
			while(j >= 0 && countries[j].getHappyIndex() < key.getHappyIndex())
			{
				countries[j + 1] = countries[j];
				j--;
			}// End inner while loop.
			
			countries[j + 1] = key;
		}// End for loop.

		for(int i = 0; i < numCountries; i++)
		{
			if(countries[i] != null)
			{
				countriesPQ.insert(countries[i]);
			}// End inner if.
		}// End for loop.

	}// End method sortByHappinessIndex.
	
	/**
	 * 	Method intervalDelete allows the user to enter an interval as [x, y]
	 * 	 and the method will delete countries with a happiness index between
	 * 	 x and y. Validity of the interval is checked, as x needs to be less
	 * 	 than y. A few if statements will let the user know if an error
	 * 	 occurs with their entered interval and will prompt them again to	
	 * 	 reenter a new interval, using the Scanner intervalScnr.
	 * 
	 * @param countriesPQ the priority queue of country objects.
	 */
	public static void intervalDelete(PriorityQ countriesPQ)
	{
		@SuppressWarnings("resource")
		Scanner intervalScnr = new Scanner(System.in);
		System.out.println("Enter happiness interval like [x,y]: ");

		while(true)
		{
			double x;
			double y;

			//System.out.println("Enter happiness interval like [x,y]: ");

			String interval = intervalScnr.nextLine().replaceAll("\\[", "").replaceAll("\\]", "");
			String[] intervalParts = interval.split(",");

			try
			{
				x = Double.parseDouble(intervalParts[0].trim());
				y = Double.parseDouble(intervalParts[1].trim());

				if(x <= y)
				{
					boolean deleted = countriesPQ.intervalDelete(x, y);
					
					if(deleted)
					{
						System.out.println("Countries of priority queue with happiness values in [" + x + ", " + y + "] are deleted");
						break;
					}// End inner if.
					else
					{
						System.out.println("Invalid interval, enter numbers: ");
					}// End inner else.
					
				}// End if.
				else
				{
					System.out.println("Invalid interval, first number must be no bigger than the second: ");
				}// End else.
			}// End try.
			catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Invalid interval, enter numbers: ");
			}// End catch.
		}// End while loop.
	}// End intervalDelete method.
	
	/**
	 * Method printPriorityQueue first sorts the priority queue by the countries
	 * 	happiness indexes through the helper method sortByHappinessIndex. Then,
	 * 	printPriorityQ() is called from the PriorityQ class to print the priority
	 * 	queue.
	 * 
	 * @param countriesPQ the priority queue of country objects.
	 */
	public static void printPriorityQueue(PriorityQ countriesPQ)
	{
		sortByHappinessIndex(countriesPQ);
		countriesPQ.printPriorityQ();
	}// End printPriorityQueue method.
	
}// End Project3 class.
