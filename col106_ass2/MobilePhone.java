
public class MobilePhone
{
	int mno;
	Boolean mode; //on=1, off=0
	Exchange bstation;

	MobilePhone(int number)
	{
		mno=number;
		mode=true;
	}

		MobilePhone(int number, Exchange ee)
	{
		mno=number;
		mode=true;
		bstation=ee;
	}
	/*public Boolean equals(MobilePhone x)
	{
		if(mno==x.number())
		{
			return true;
		}
		else
		{
			return false;
		}
	}*/

	public int number()
	{
		return mno;
	}

	public Boolean status()
	{
		return mode;
	}

	public void switchOn()
	{
		mode = true;
	}

	public void switchOff()
	{
		mode = false;
	}

	public Exchange location() throws switchoffexception
	{
		if(!mode)
		{
			throw new switchoffexception();
		}
			return bstation;
		
	}	

}