
public class Note
{
	protected int bloodPressure, condition;
	protected double bodyHeat;
	protected int DocId;
	protected int treatment;
	protected int pId;
	protected int nurse;
	protected int index;
	
	public Note(int id, int bloodPressure,double bodyHeat, int condition, int nurseId, int index)//constructor
	{
		this.index = index;
		this.pId = id;
		this.nurse = nurseId;
		this.bloodPressure=bloodPressure;
		this.bodyHeat=bodyHeat;
		this.condition=condition;
	}
	public int getPatientID()
	{
		return this.pId;
	}
	
	public int getNurseId()
	{
		return this.nurse;
	}
	
	public int getBloodPressure()
	{
		return this.bloodPressure;
	}
	
	public double getBodyHeat()
	{
		return this.bodyHeat;
	}
	
	public int getCondition()
	{
		return this.condition;
	}
	
	public int getDocID()
	{
		return this.DocId;
	}
	
	public void setDocId(int id)
	{
		this.DocId = id;
	}
	
	public void setTreatment(int t)
	{
		this.treatment = t;
	}
	
	public int getTreatment()
	{
		return this.treatment;
	}
	
	public String toString()
	{
		return "[ " + "bloodPressure: " + bloodPressure + " "
				+ "bodyHeat: " + bodyHeat + " "
				+ "Condition: " + condition + " "
				+ "Treating Doc: " + DocId + " "
				+  "Treatment: " + treatment + " ]";
	}

}
