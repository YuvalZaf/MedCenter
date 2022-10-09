import java.util.Vector;

public class Junior_Doctor extends Workers
{
	private Bounded_Queue <Patient> juniorQueue;
	private Unbounded_Queue <Patient> nurseQueue;
	private Unbounded_Queue<Patient> pharmQueue;
	private Vector<Note> managerNotes;
	
	
	public void run()
	{
		while(!dayEnded)
		{
			Patient cur = juniorQueue.extract();
			if(cur == null)//  if queue is empty
				break;
			if(cur.getNote().getCondition() == -1)// if patient condition is wrong send back to the nurses queue
			{
				nurseQueue.insert(cur);
			}
			else
			{
				try 
				{
				Thread.sleep(cur.getNote().getCondition()*1000);}
				catch(InterruptedException e) {};
				cur.getNote().setDocId(this.workerId);// set the doc id in the note
				int treatment = (1 + (int)(Math.random()*4)) + cur.getNote().getCondition();
				cur.getNote().setTreatment(treatment);// set the treatment num in the note
				if(Math.random()<= 0.5)
				{
					sendToPharm(treatment, cur);//send to pharmacist queue
				}
				else//send home and tell manger
				{
					sendHome(cur);
				}
			}
				
		}
			         	
	}
	
	
	public Junior_Doctor(int id, Bounded_Queue <Patient> juniorQueue,Unbounded_Queue <Patient> nurseQueue
			,Unbounded_Queue<Patient> pharmQueue,Vector<Note> managerNotes)//constructor
	{
		super(id);
		this.juniorQueue = juniorQueue;
		this.managerNotes = managerNotes;
		this.nurseQueue = nurseQueue;
		this.pharmQueue = pharmQueue;
	}
	
	public void sendToPharm(int treatment, Patient cur)//send to the pharmecist queue
	{
		int medicine;
		medicine = treatment + (1 + (int)(Math.random()*4));
		cur.setPrescription(medicine);
		pharmQueue.insert(cur);
	}
	
	public void sendHome(Patient cur)// send patient home
	{
		Note copy = cur.getNote();
		managerNotes.add(copy);
	}
	
	public String toString()
	{
		return "[" + workerId + "]";
	}
	
	
}
