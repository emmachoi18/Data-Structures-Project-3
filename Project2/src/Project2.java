import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * COP3530: Project 2 - Stacks and PriorityQ Queues
 * <p>
 * Class Project2 utilizes Stacks and Priority Queues. Input of a
 * 	file is needed, which is then converted into an array of countries,
 * 	and then pushed and inserted into a stack and priority queue using
 * 	the respective methods of the Stack and PriorityQ classes. These
 * 	are then used to perform various methods, which consist of printing
 * 	either the stack or priority queue, pushing and popping the stack,
 * 	and inserting/removing from the priority queue.
 * <p>
 * Project2 requires a file as input, which contains countries and the
 * 	elements of them, which are their name, capitol, population, GDP
 * 	(gross domestic product), area, and Happiness Index score. Output
 * 	generated from this information is a report of the countries from
 * 	its stack or priority queue (PriorityQ class will have the highest
 * 	Happiness Index scoring countries at the highest priority). The
 * 	output may also contain a new report of the countries, with a pop,
 * 	push, insert, or removal based on the method called.
 * 
 * @author Emma Choi
 * @version 10/05/2023
 */

public class Project2
{
	/**
	 * Method main takes in a file, reads the file, and converts each
	 * 	element into a Country object. These objects are added into a
	 * 	stack and priority queue. Main includes a switch case to allow
	 * 	the user to choose from a menu of options (that can print or
	 * 	manipulate the countries), and respective methods are called.
	 * 
	 * @param args an array of command-line arguments.
	 */
	public static void main(String[] args)
	{
		System.out.println("COP3530 Project 2");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		System.out.println("Stacks and Priority Queues");
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter the file name: ");
		String fileName = scnr.next();// Gets the file name from the user.
		
		Country[] countriesArray = new Country[200];
		Stack countriesStack = new Stack(200); // Create an empty stack.
		PriorityQ countriesPQ = new PriorityQ(200); // Create an empty priority queue.
		
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
				
			} // End while loop.
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// Add countries to the stack.
		int stackCounter = 0;
		for(int i = 0; i < countriesArray.length; i++)
		{
			if(countriesArray[i] != null)
			{
				countriesStack.push(countriesArray[i]);
				stackCounter++;
			}
		}
		
		 // Add countries to the priority queue.
		int pqCounter = 0;
		for(Country c : countriesArray)
		{
			if(c != null)
			{
				countriesPQ.insert(c);
				pqCounter++;
			}
		}
		
		// Print number of countries read in.
		System.out.println();
		System.out.printf("Stack created of %d countries.", stackCounter);
		System.out.println();
		System.out.printf("Priority Queue created of %d countries.", pqCounter);
		System.out.println();
		System.out.println();
		
		// Switch case.
		boolean flag = true;
		do
		{
			System.out.println("1. Print stack");
			System.out.println("2. Pop a country object from stack");
			System.out.println("3. Push a country object onto stack");
			System.out.println("4. Print a priority queue");
			System.out.println("5. Remove a country object from priority queue");
			System.out.println("6. Insert a country object into priority queue");
			System.out.println("7. Exit");
			
			System.out.println("Enter your choice: ");
			if(scnr.hasNextInt())
			{
				int choice = scnr.nextInt();
				
				switch(choice)
				{
				case 1:
					printStack(countriesStack);
					break;
				case 2:
					popStack(countriesStack);
					break;
				case 3:
					pushStack(countriesStack);
					break;
				case 4:
					printPQ(countriesPQ);
					break;
				case 5:
					removePQ(countriesPQ);
					break;
				case 6:
					insertPQ(countriesPQ);
					break;
				case 7:
					System.out.println("Have a good day!");
					flag = false;
					break;
				default:
					System.out.println("Invalid choice! Enter 1-7: ");
					break;
				}// End switch.
			}// End if.
				
			else
			{
				System.out.println("Invalid choice! Enter 1-7: ");
				scnr.next();
			}// End else.
			
		}while(flag);
		
		scnr.close();
	}// End main.
	
	/**
	 * Method printStack uses countriesStack to print each Country
	 * 	object in the stack. The method printStack in the Stack class
	 * 	is called to do so.
	 * 
	 * @param countriesStack a stack of Country objects.
	 */
	public static void printStack(Stack countriesStack)
	{
		countriesStack.printStack();
	}// End printStack method.
	
	/**
	 * Method popStack uses countriesStack and the respective pop
	 * 	method in class Stack to pop a country out of the stack.
	 * 
	 * @param countriesStack a stack of Country objects.
	 */
	public static void popStack(Stack countriesStack)
	{
		countriesStack.pop();
		System.out.println();
		System.out.println("One country is popped from the stack.");
		System.out.println();
	}// End popStack method.
	
	/**
	 * Method pushStack uses countriesStack and the respective push
	 * 	method in class Stack to push a new country onto the stack.
	 * 	Input is needed from the user which will be the new country's
	 * 	name, capitol, population, GDP, area, and Happiness Index.
	 * 	These elements of the country are then constructed into a
	 * 	Country object and pushed.
	 * 
	 * @param countriesStack a stack of Country objects.
	 */
	public static void pushStack(Stack countriesStack)
	{
		@SuppressWarnings("resource") // SupressWarnings here to avoid error occurring when switch case runs again.
		Scanner pushScanner = new Scanner(System.in);
		
		System.out.println("Enter name: ");
		String newCountry = pushScanner.nextLine();
		
		System.out.println("Enter capitol: ");
		String newCapitol = pushScanner.nextLine();
		
		System.out.println("Enter population: ");
		double newPopulation = pushScanner.nextDouble();
		pushScanner.nextLine();
		
		System.out.println("Enter GDP (USD): ");
		double newGdp = pushScanner.nextDouble();
		pushScanner.nextLine();
		
		System.out.println("Enter Area (km2): ");
		double newArea = pushScanner.nextDouble();
		pushScanner.nextLine();
		
		System.out.println("Enter Happiness Index: ");
		double newHappyIndex = pushScanner.nextDouble();
		pushScanner.nextLine();
		
		Country c = new Country(newCountry, newCapitol, newPopulation, newGdp, newArea, newHappyIndex);
		countriesStack.push(c);
		
		System.out.printf("\nOne country \"%s\" is pushed onto stack.\n", newCountry);
		System.out.println();
		
		// pushScanner.close();
	}// End pushStack method.
	
	/**
	 * Method printPQ uses countriesPQ and the method printPriorityQ
	 * 	in the class PriorityQ to print the countries.
	 * 
	 * @param countriesPQ a priority queue of Country objects.
	 */
	public static void printPQ(PriorityQ countriesPQ)
	{
		countriesPQ.printPriorityQ();
	}// End printPQ method.
	
	/**
	 * Method removePQ uses countriesPQ and the remove method in
	 * 	class PriorityQ to remove a country out of the priority
	 * 	queue.
	 * 
	 * @param countriesPQ a priority queue of Country objects.
	 */
	public static void removePQ(PriorityQ countriesPQ)
	{
		countriesPQ.remove();
		System.out.println();
		System.out.println("One country is removed from the priority queue.");
		System.out.println();
	}// End removePQ method.

	/**
	 * Method insertPQ uses countriesPQ and the respective insert
	 * 	method in class PriorityQ to insert a new country into the
	 * 	priority queue. Input from the user contains the name, capitol,
	 * 	population, GDP, area, and Happiness Index of the new country.
	 * 	Those elements are then constructed into a Country object and
	 * 	inserted into the priority queue.
	 * 
	 * @param countriesPQ a priority queue of Country objects.
	 */
	public static void insertPQ(PriorityQ countriesPQ)
	{
		@SuppressWarnings("resource") // SupressWarnings here to avoid error occurring when switch case runs again.
		Scanner pqScanner = new Scanner(System.in);
		
		System.out.println("Enter name: ");
		String newCountry = pqScanner.nextLine();
		
		System.out.println("Enter capitol: ");
		String newCapitol = pqScanner.nextLine();
		
		System.out.println("Enter population: ");
		double newPopulation = pqScanner.nextDouble();
		pqScanner.nextLine();
		
		System.out.println("Enter GDP (USD): ");
		double newGdp = pqScanner.nextDouble();
		pqScanner.nextLine();
		
		System.out.println("Enter Area (km2): ");
		double newArea = pqScanner.nextDouble();
		pqScanner.nextLine();
		
		System.out.println("Enter Happiness Index: ");
		double newHappyIndex = pqScanner.nextDouble();
		pqScanner.nextLine();
		
		Country c = new Country(newCountry, newCapitol, newPopulation, newGdp, newArea, newHappyIndex);
		countriesPQ.insert(c);
		
		System.out.printf("\nOne country \"%s\" is pushed onto priority queue.\n", newCountry);
		System.out.println();
		
		// pqScanner.close();
	}// End insertPQ method.
	
}// End Project2 Class.
