import java.util.Collections;

import java.util.Vector;

public class Unbounded_QueueByPriority<T extends Comparable<T>> extends Unbounded_Queue<T>
{
	private Vector<T> queue;
	private boolean end;

	public synchronized void setEndDay(boolean finish)
	{
		this.end = finish;
		notifyAll();
	}
	
	public Unbounded_QueueByPriority ()
	{
		queue = new Vector<T>();
		end = false;
	}

	public synchronized void insert(T item)
	{
		queue.add(item);
		//System.out.println("v");
		Collections.sort(queue);
		Collections.reverse(queue);
		this.notifyAll();
	}
	
	public synchronized T extract() 
	{
		while (queue.isEmpty())
		{
			try {
				this.wait();
			}
			catch(InterruptedException e){};
		}
		T t = queue.elementAt(0);
		queue.remove(t);
		return t;
	}

	
}
