/**
 * Class PriorityQ implements a priority queue of country objects
 * 	using a sorted doubly linked list. The list is sorted and
 * 	created in respective methods contained in class Project3.java.
 * 	The links of the priority queue are popped out of the stack,
 * 	inserted, and finally sorted by their Happiness Index. The stack
 * 	is from class Stack.java. Various methods are used to support
 * 	the priority queue, which are a constructor method, insert, remove,
 * 	intervalDelete, a recursive print method, and an isEmpty boolean
 * 	method. Each of these has their own function to checking or
 * 	manipulating the data.
 * <p>
 * Overall, this priority queue utilizes a sorted doubly linked list and
 * 	sorts the Countries based on their Happiness Index, or more specifically,
 * 	the higher their Happiness Index, the higher their priority.
 * 
 * @author Emma Choi
 * @version 10/25/2023
 */
public class PriorityQ
{
	private static class Link
	{
		Country c;
		Link next;
		Link previous;
		
		public Link(Country c)
		{
			this.c = c;
			next = null;
			previous = null;
		}// End Link method.
	}// End Link class.
	
	private Link head;
	
	/**
	 * Method PriorityQ is the constructor method that creates the
	 * 	priority queue. The head is set to null for this reason.
	 * 
	 */
	public PriorityQ()
	{
		head = null;
	}// End PriorityQ method.
	
	/**
	 * Method insert takes in a Country object and inserts it into the
	 * 	priority queue. This O(N) method allows the user to add a Country
	 * 	object into the priority queue, based on happiness index.
	 * 
	 * @param c a Country object.
	 */
	public void insert(Country c)
	{
		Link newLink = new Link(c);
		
		if(head == null)
		{
			head = newLink;
		}// End if.
		else
		{
			Link current = head;
			
			while(current.next != null && c.getHappyIndex() < current.c.getHappyIndex())
			{
				current = current.next;
			}// End inner while loop.
			
			if(c.getHappyIndex() >= current.c.getHappyIndex())
			{
				// Insert at or after current link.
				
				newLink.next = current.next;
				newLink.previous = current;
				if(current.next != null)
				{
					current.next.previous = newLink;
				}// End inner inner if.
				current.next = newLink;
			}// End inner if.
			else
			{
				// Insert at beginning.
				
				newLink.next = head;
				head.previous = newLink;
				head = newLink;
			}// End inner else.
		}// End else.
	}// End insert method.
	
	/**
	 * Method remove returns a now removed Country object from the priority queue.
	 * 	This is an O(1) method.
	 * 
	 * @return the removed Country object.
	 */
	public Country remove()
	{
		if(head == null)
		{
			return null;
		}// End if.
		
		Country removedCountry = head.c;
		head = head.next;
		
		if(head != null)
		{
			head.previous = null;
		}// End if.
		
		return removedCountry;
	}// End remove method.
	
	/**
	 * Method printPriorityQ utilizes a private helper method
	 * 	printPriorityQRecursively to print the priority queue in a
	 * 	recursive manner. Formatting and organization take precedence,
	 * 	along with printing in order of the highest Happiness Index, to
	 * 	the lowest Happiness Index.
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
		
		printPriorityQRecursively(head);
	}// End printPriorityQ method.
	
	private void printPriorityQRecursively(Link current)
	{
		if(current == null)
		{
			return;
		}// End if.
		
		Country c = current.c;
		
		if(c != null)
		{
			printPriorityQRecursively(current.next);
		}

		System.out.printf("%-40s%-20s%-20s%-20s%-20s%n", c.getCountryName(), c.getCapitol(),
				String.format("%.3f", c.getGdp()/c.getPopulation()), String.format("%.6f", (c.getArea()/c.getPopulation())), String.format("%.3f", c.getHappyIndex()));
		
	}// End helper method printPriorityQRecursively.
	
	/**
	 * Method intervalDelete takes in two parameters, a lower bound and an upper
	 * 	bound. These numbers are used to delete a certain range of Country objects
	 * 	that have a Happiness Index that falls between or equal to set bounds. For
	 * 	example, if the lower bound were 5.5, and the upper bound were 7, all
	 * 	Country objects with a Happiness Index of 5.5 to 7 inclusive, would be 
	 * 	removed from the priority queue.
	 * 
	 * @param lowerBound the minimum or lower bound of the interval.
	 * @param upperBound the maximum or upper bound of the interval.
	 * @return true if anything was found and deleted, false otherwise.
	 */
	public boolean intervalDelete(double lowerBound, double upperBound)
	{
		boolean found = false;
		Link current = head;
		
		while(current != null)
		{
			if(current.c.getHappyIndex() >= lowerBound && current.c.getHappyIndex() <= upperBound)
			{
				// Delete it.
				Link previous = current.previous;
				Link next = current.next;
				
				if(previous != null)
				{
					previous.next = next;
				}// End first inner inner if.
				else
				{
					head = next;
				}// End inner inner else.
				if(next != null)
				{
					next.previous = previous;
				}// End second inner inner if.
				
				found = true;
			}// End inner if.
			
			current = current.next;
		}// End while loop.
		
		return found;
	}// End intervalDelete method.
	
	/**
	 * Method isEmpty checks to see if the priority queue is empty.
	 * 	It will return true if empty, false otherwise.
	 * 
	 * @return true if the PQ is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return head == null;
	}// End isEmpty method.

}// End class PriorityQ
