import java.sql.SQLException;
import java.util.Vector;

public class Manager extends Workers
{
	private double CLOSING_EMERGENCY_MEDICAL_CENTER_TIME;
	private Vector<Note> managerNotes;
	private MedicalCenter myMD;
	private Database myDB;
	
	
	public void run()
	{
		try 
		{
			Thread.sleep((int)CLOSING_EMERGENCY_MEDICAL_CENTER_TIME*1000);
		}
		catch(InterruptedException e) {};
		notifyDayEnded();// notify the workers that day is over
		notifyQueue();
		updateDatabase();
	}
	
	public Manager(double closeTime, int id ,Vector<Note> managerNotes, MedicalCenter m )//constructor
	{
		super(id);
		this.myMD = m;
		myDB = new Database();
		CLOSING_EMERGENCY_MEDICAL_CENTER_TIME = closeTime;
		this.managerNotes = managerNotes;
		dayEnded = false;
	}
	
	public void updateDatabase()//create new database for the med center and update it
	{
		myDB.createTable("patients");
		for(Note i : managerNotes)
		{
			try {
			myDB.insertIntoTable("patients", i);}
			catch (SQLException ex) {
				ex.getMessage();
			}
		}
	}
	
	public void notifyQueue()// notify the day is other for the workers and patients that waits in the queues
	{
		this.myMD.notifyQueue();
	}
	
	
	
	
	
	
	 
	
	

	
	
	
	
	
	
	
}
