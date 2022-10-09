import java.util.Vector;

public class Unbounded_Queue<T> extends Vector<Patient>
{
	private Vector<T> queue; 
	private boolean end;

	public Unbounded_Queue ()
	{
		queue = new Vector<T>();
		end = false;
	}
	
	public synchronized void setEndDay(boolean finish)
	{
		this.end = finish;
		notifyAll();
	}

	public synchronized void insert(T item)
	{
		queue.add(item);
		this.notifyAll();
	}

	public synchronized T extract() 
	{
		while (queue.isEmpty() && !this.end)
		{
			try {
				this.wait();
				if(this.end)
					return null;
			}
			catch(InterruptedException e){};
			if(this.end && this.queue.isEmpty())
				return null;
		}
		T t = queue.elementAt(0);
		queue.remove(t);
		return t;
	}
}
