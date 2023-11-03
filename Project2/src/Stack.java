/**
 * The Stack class implements a stack of Country objects using an array.
 * 	There is a constructor that creates the stack array (Country[] countries)
 * 	and then various methods titled push, pop, printStack, isEmpty, and
 * 	isFull. Each method has it's own function to editing or checking the
 * 	stack array.
 * 
 * @author Emma Choi
 * @version 10/05/2023
 */
public class Stack
{
	// Variable declarations.
	private Country[] countries;
	private int top;
	private int maxSize;
	
	/**
	 * The Stack method is a constructor method that creates the stack
	 * 	array based on a size provided.
	 * 
	 * @param s maximum size of the stack array.
	 */
	public Stack(int s)
	{
		maxSize = s;
		countries = new Country[maxSize];
		top = -1;
	}
	
	/**
	 * The push method checks whether the stack array is full and, if it
	 * 	is, will return a statement that says it is full. Otherwise, the
	 * 	method will increment top, pushing Country c onto the stack. When
	 * 	printed, the new country should appear at the top of the output.
	 * 
	 * @param c Country variable that will be pushed.
	 */
	public void push(Country c)
	{
		if(isFull())
		{
			System.out.println("Stack is full!");
		}
		else
		{
			countries[++top] = c;
		}
	}// End push method.
	
	/**
	 * The pop method checks whether the stack array is empty and, if it
	 * 	is, will return null and inform the user. Otherwise, the country
	 * 	at top will be removed (popped).
	 * 
	 * @return the Country that was popped.
	 */
	public Country pop()
	{
		if(isEmpty())
		{
			System.out.println("Stack is empty!");
			return null;
		}
		else
		{	
			return countries[top--];
		}
	}// End pop method.
	
	/**
	 * Method printStack prints the stack from the top down to the bottom using a
	 * 	for loop.
	 */
	public void printStack()
	{		
		String headers = String.format("%-40s%-20s%-20s%-20s%-20s", "Name", "Capitol", "GDPPC", "APC", "HappinessIndex");
		int headersLength = headers.length();
		System.out.println(headers);
		
		for(int i = 0; i < headersLength - 5; i++)
		{
			System.out.print("-");
		}
		
		System.out.println();
		
		for(int i = top; i >= 0; i--)
		{
			Country c = countries[i];
			if(c != null)
				System.out.printf("%-40s%-20s%-20s%-20s%-20s%n", c.getCountryName(), c.getCapitol(),
						String.format("%.3f", c.getGdp()/c.getPopulation()), String.format("%.6f", (c.getArea()/c.getPopulation())), String.format("%.3f", c.getHappyIndex()));
		}
		
		System.out.println();
	}// End printStack method.
	
	/**
	 * Method isEmpty checks to see if the stack array is empty.
	 *
	 * @return true if the stack is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return (top == -1);
	}// End isEmpty method.
	
	/**
	 * Method isFull checks to see if the stack array is full.
	 * 
	 * @return true if the stack is full, false otherwise.
	 */
	public boolean isFull()
	{
		return (top == countries.length - 1);
	}// End isFull method.

}// End Stack class.
