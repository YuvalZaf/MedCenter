import java.util.Vector;

public class Pharmacist extends Workers
{
	private Unbounded_Queue<Patient> pharmQueue;
	private Vector<Note> managerNotes;


	
	
	public void run()
	{
		while(!dayEnded)
		{
			Patient cur = pharmQueue.extract();
			if(cur == null)//  if queue is empty
				break;
			int sleepTime = 2 + (int)(Math.random()*2); 
			try {
			Thread.sleep(sleepTime*1000);}
			catch(InterruptedException e){};
			giveMedicine(cur);
			managerNotes.add(cur.getPrescription());//send home and send copy of note to manager
		}
	}
	
	
	public Pharmacist(int id, Unbounded_Queue<Patient> pharmQueue, 
			Vector<Note> managerNotes)//constructor
	{
		super(id);
		this.pharmQueue =pharmQueue;
		this.managerNotes = managerNotes;
	}
	
	public void giveMedicine(Patient p)//give medicine for patient
	{
		((Prescription)p.getPrescription()).giveMedicine();
	}
	
	
	
	

}
