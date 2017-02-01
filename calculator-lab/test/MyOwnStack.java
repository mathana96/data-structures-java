/**
 * 
 * @author Mathana Sreedaran
 *
 *Attempt at implementstion of my own stack using an ArrayList and an interface
 *based on the actual Stack interface of Java
 */
public class MyOwnStack implements MyOwnStackInterface
{

	@Override
	public boolean empty()
	{
		if (stackArray.isEmpty())
			return true;
		return false;
	}

	@Override
	public <E> Object peek()
	{
		if (!empty())
			return stackArray.get(stackArray.size()-1);
		return null;
	}

	@Override
	public <E> Object pop()
	{
		Object pop;
		if (!empty())
		{
			pop = stackArray.get(stackArray.size()-1);
			stackArray.remove(stackArray.size()-1);
			return pop;
		}


		return null;
	}

	@Override
	public void push(Object item)
	{
		stackArray.add(item) ;

	}

	@Override
	public int search(Object o)
	{
		for (int i=stackArray.size()-1; i>=0; i--)
		{
			if (stackArray.get(i).equals(o))
				return stackArray.size() - i;
		}
		return -1;
	}

	@Override
	public int length()
	{
		return stackArray.size();
	}

	@Override
	public String toString()
	{
		return stackArray.toString();
	}

	@Override
	public void clear()
	{
		if (!empty())
		{
			stackArray.clear();
		}
	}
}
