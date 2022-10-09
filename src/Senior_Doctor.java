import java.util.Vector;

public class Senior_Doctor extends Workers
{
	private Unbounded_QueueByPriority <Patient> priorityQueue;
	private Unbounded_Queue<Patient> pharmQueue;
	private Vector<Note> managerNotes;
	
	
	public void run()
	{
		while(!dayEnded)
		{
			Patient cur = priorityQueue.extract();
			if(cur == null)//  if queue is empty
				break;
			try {
				Thread.sleep(6*1000);}
			catch(InterruptedException e) {};
			cur.getNote().setDocId(this.workerId);
			int treatment = (1 + (int)(Math.random()*4)) + cur.getNote().getCondition();
			cur.getNote().setTreatment(treatment);
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

	public Senior_Doctor(int id, Unbounded_QueueByPriority <Patient> priorityQueue,
			Unbounded_Queue<Patient> pharmQueue, Vector<Note> managerNotes)//constructor
	{
		super(id);
		this.priorityQueue = priorityQueue;
		this.pharmQueue = pharmQueue;
		this.managerNotes = managerNotes;
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
	
	
}
