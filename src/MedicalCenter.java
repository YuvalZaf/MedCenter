import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

public class MedicalCenter 
{
	private int workersId = 1, numOfNurse; 
	private double closeTime;
	private Unbounded_Queue<Patient> nurseQueue;
	private Bounded_Queue <Patient> juniorQueue;
	private Unbounded_QueueByPriority <Patient> priorityQueue;
	private Unbounded_Queue<Patient> pharmQueue;
	private Vector<Note> managerNotes;
	private Vector<Thread> start;
	
	
	public MedicalCenter(int numOfNurse, double closeTime)// Constructor
	{
		this.closeTime = closeTime;
		this.numOfNurse = numOfNurse;
		setQueues();
		ReadFromFile("patients.txt");
		setManager();
		setNurses();
		setJuniorDoc();
		setSeniorDoc();
		setPharmacist();
		startTheCenter();
	}
	
	private void ReadFromFile(String s)// read the received patient file
	{
		BufferedReader PatientList=null;
		try
		{
			FileReader fr = new FileReader(s);
			PatientList = new BufferedReader (fr);
			String str = PatientList.readLine();
			str = PatientList.readLine();
			while(str!=null)
			{
				String [] Data = str.split("	");
				setPatient(Data);
				str = PatientList.readLine();
			}
		}
		catch (FileNotFoundException exception)
		{
			System.out.println ("The file " + s + " was not found.");
		}
		catch (IOException exception)
		{
			System.out.println(exception);
		}
		finally{
			try{
				PatientList.close();
			} catch(IOException exception){
				exception.printStackTrace();
			}
		}
	}
	
	private void setQueues()//start the queues for the hospital
	{
		nurseQueue = new Unbounded_Queue <Patient>(); 
		juniorQueue=new Bounded_Queue<Patient>(8);
		priorityQueue = new Unbounded_QueueByPriority<Patient>();
		pharmQueue = new Unbounded_Queue<Patient>();
		managerNotes = new Vector<Note>();
		start = new Vector<Thread>();
	}

	private void setPatient(String[] Data)//  make patients for the center
	{
		String firstName = Data[0];
		String lastName = Data[1];
		int age = Integer.parseInt(Data[2]);
		int height = Integer.parseInt(Data[3]);
		int weight = Integer.parseInt(Data[4]);
		int id = Integer.parseInt(Data[5]);
		int arrivel = Integer.parseInt(Data[6]);
		Patient t = new Patient(firstName, lastName, age, id, arrivel, height,  weight, nurseQueue);
		Thread n = new Thread(t);
		start.add(n);
		
	}
	private void setJuniorDoc()// create  3 junior doc's
	{
		for(int i =0; i<3; i++)
		{
			Workers j = new Junior_Doctor(workersId, juniorQueue, nurseQueue, pharmQueue,
					managerNotes);
			workersId++;
			Thread n = new Thread(j);
			start.add(n);
		}
	}
	
	private void setSeniorDoc()//create 1 senior doc
	{
		Workers s = new Senior_Doctor(workersId, priorityQueue, pharmQueue,managerNotes);
		workersId++;
		Thread n = new Thread(s);
		start.add(n);
	}

	private void setNurses()//create nurses as many as the user wants(max - 4)
	{
		for(int i = 0; i<numOfNurse; i++ )
		{
			Workers n = new Nurse(workersId,nurseQueue, juniorQueue, priorityQueue);
			workersId++;
			Thread t = new Thread(n);
			start.add(t);
		}
	}
	
	private void setManager()// create the manager for the hospital
	{
		Workers hospitalManager = new Manager(closeTime ,workersId ,managerNotes, this);
		Thread n = new Thread(hospitalManager);
		start.add(n);
	}
	
	private void setPharmacist()//create pharmacist's
	{
		for(int i = 0; i<2; i++)
		{
		Workers p = new Pharmacist(workersId,pharmQueue, managerNotes);
		workersId++;
		Thread n = new Thread(p);
		start.add(n);
		}
	}
	private void startTheCenter()// start the day at the hospital
	{
		for(Thread i : start)
		{
			i.start();
		}
	}
	
	public void notifyQueue()// notify all the persons in the queues that day is over
	{
		this.juniorQueue.setEndDay(true);
		this.nurseQueue.setEndDay(true);
		this.pharmQueue.setEndDay(true);
		this.priorityQueue.setEndDay(true);
	}
	
	
}
