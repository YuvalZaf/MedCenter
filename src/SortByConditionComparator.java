import java.util.Comparator;

public class SortByConditionComparator implements Comparator<Patient>
{
	public int compare(Patient o1, Patient o2)
	{
		return (int)(o1.getNote().getCondition() - o2.getNote().getCondition());
	}
}
