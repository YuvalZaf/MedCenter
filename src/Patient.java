
public class Patient implements Runnable, Comparable<Patient>
{
	private String firstName, lastName;
	private int age, id, arrivedSec;
	private int height, weight;
	private Note note;
	private Note prescription;
	private Unbounded_Queue <Patient> nurseQueue;
	private int Noteindex =1;
	

	public void run()
	{
		try {
		Thread.sleep(arrivedSec*1000);}
		catch(InterruptedException e){};
		nurseQueue.insert(this);
	}

	public Patient(String firstName, String lastName, int age, int id, int arrived, //Constructor
			int height, int weight, Unbounded_Queue <Patient> nurseQueue)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.id = id;
		this.arrivedSec = arrived;
		this.height = height;
		this.weight = weight;
		this.nurseQueue = nurseQueue;
	}

	public int getHeight()
	{
		return this.height;
	}
	public int getWeight()
	{
		return this.weight;
	}
	public Note getNote()
	{
		return this.note;
	}
	
	public void setNote(int bloodPressure,double bodyHeat,int condition, int nurseId)//create the note for the patient
	{
		note=new Note (this.id ,bloodPressure, bodyHeat, condition, nurseId, Noteindex);
		Noteindex++;
	}
	
	public void setPrescription(int med)// create prescription for the patient
	{
		prescription = new Prescription(med, this.note);
	}
	
	public Note getPrescription()
	{
		return this.prescription;
	}
	
	public int compareTo(Patient p)//compareTo
	{
		if(this.note.getCondition() > p.getNote().getCondition())
			return 1;
		else if(this.note.getCondition() < p.getNote().getCondition())
			return -1;
		else
			return 0;
	}
	
	public String toString()
	{
		return firstName + " " + lastName;
		
		
		
	}









}
