/**
 * The Stack class implements a stack of Country objects using a
 * 	double-ended singly linked list.  A double-ended list maintains
 * 	a pointer to the last and first links, and allows insertion at
 * 	the end of the list. Class Stack contains a constructor that creates
 * 	the stack, a push, pop, and isEmpty method, and finally, a recursive
 * 	printStack method that recursively prints from the top to the bottom
 * 	of the stack. Various private methods are used to create the double-
 * 	ended singly linked list. Each method in class Stack has its own
 * 	function to checking or manipulating the data.
 * 
 * @author Emma Choi
 * @version 10/25/2023
 */
public class Stack
{
	private static class Link
	{
		Country c;
		Link next;
		@SuppressWarnings("unused")
		Link previous;
		
		public Link(Country c)
		{
			this.c = c;
			next = null;
			previous = null;
		}// End Link method.	
	}// End Link class.
	
	private Link top;
	@SuppressWarnings("unused")
	private Link bottom;
	
	/**
	 * Method Stack is the constructor method that creates the stack.
	 * 	Top and bottom are set to null. This represents the double-
	 * 	ended singly linked list.
	 * 
	 */
	public Stack()
	{
		top = null;
		bottom = null;
	}// End Stack method.
	
	/**
	 * Method push allows the user to push a new Country, or link, onto
	 * 	the stack. The method will first check for an empty stack, but
	 * 	otherwise will push the Country onto the top.
	 * 
	 * @param c a Country object.
	 */
	public void push(Country c)
	{
		Link newLink = new Link(c);
		
		if(isEmpty())
		{
			top = newLink;
			bottom = newLink;
		}// End if.
		else
		{
			newLink.next = top;
			top.previous = newLink;
			top = newLink;
		}// End else.
	}// End push method.
	
	/**
	 * Method pop allows the user to remove a Country, or link, from the
	 * 	double-ended singly linked list. First, method pop will check for
	 * 	an empty stack, but otherwise will proceed with popping. It returns
	 * 	the popped country.
	 * 
	 * @return the popped Country object.
	 */
	public Country pop()
	{
		if(isEmpty())
		{
			return null;
		}// End if.
		else
		{
			Link temp = top;
			top = top.next;
			
			if(top != null)
			{
				top.previous = null;
			}// End inner if.
			else
			{
				bottom = null;
			}// End inner else.

			return temp.c;
			
		}// End else.
	}// End pop method.
	
	/**
	 * Method printStack uses a private helper method, printStackRecursively,
	 * 	to print the stack in a recursive manner. Besides formatting the stack
	 * 	correctly and in an organized fashion, method printStack takes advantage
	 * 	of the current link and the next link to easily print the stack from the
	 * 	top to the bottom.
	 * 
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
		
		printStackRecursively(top);
	}// End printStack() method.

	private void printStackRecursively(Link current)
	{
		if(current == null)
		{
			return;
		}// End if.
		
		Country c = current.c;
		System.out.printf("%-40s%-20s%-20s%-20s%-20s%n", c.getCountryName(), c.getCapitol(),
					String.format("%.3f", c.getGdp()/c.getPopulation()), String.format("%.6f", (c.getArea()/c.getPopulation())), String.format("%.3f", c.getHappyIndex()));
		
		printStackRecursively(current.next);
	}// End helper method printStackRecursively.
	
	/**
	 * Method isEmpty checks to see if the stack is empty. It will
	 * 	return true if the stack is empty, false otherwise.
	 * 
	 * @return true if the stack is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return top == null;
	}// End isEmpty method.

}// End Stack class.
