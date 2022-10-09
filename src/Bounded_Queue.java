import java.util.Vector;

public class Bounded_Queue <T> extends Vector<Patient>
{
	private Vector<T> queue;
	private int maxSize;
	private boolean end;

	public synchronized void setEndDay(boolean finish)
	{
		this.end = finish;
		notifyAll();
	}
	
	public Bounded_Queue(int maxSize)
	{
		queue= new Vector<T>();
		this.maxSize=maxSize;
		end =  false;
	}

	public synchronized void insert(T item) {
		while(queue.size()>=maxSize)
			try {
				this.wait();
			}
		catch(InterruptedException e){};

		queue.add(item);
		this.notifyAll(); }

	public synchronized T extract(){
		while (queue.isEmpty() && !this.end)
			try {
				this.wait();
				if(this.end)
					return null;
			}
		catch(InterruptedException e){};
		if(this.end && this.queue.isEmpty())
			return null;
		T item = queue.elementAt(0);
		queue.remove(item);
		this.notifyAll();
		return item;
	}
}


