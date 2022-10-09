
public class Prescription extends Note
{
	private int medicine;
	private boolean medicineGiven;
	
	
	
	public Prescription(int medicine, Note n)//constructor
	{
		super(n.getPatientID(), n.getBloodPressure(), n.getBodyHeat(), n.getCondition(), n.getNurseId(), n.index);
		this.medicine = medicine;
		DocId = n.getDocID();
		treatment = n.getTreatment();
		medicineGiven = false;
	}
	
	
	public void giveMedicine()//give medicine for the patient
	{
		medicineGiven = true;
	}
	
	public int getMedicine()
	{
		return this.medicine;
	}
	
	
	
	
	

	
	
	
}
