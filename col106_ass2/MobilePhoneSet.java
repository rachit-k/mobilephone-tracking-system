
public class MobilePhoneSet extends Myset<MobilePhone>
{
	MobilePhone m;
	MobilePhoneSet()
	{
	}
	MobilePhoneSet(MobilePhone x)
	{
		m=x;
	}
	public Boolean IsMember(MobilePhone x)
	{
		return super.IsMember(x);
	}

	public int numChildren()
	{
		return super.numChildren();
	}

	public void Insert(MobilePhone x)
	{
		super.Insert(x);
	}

	public void Delete(MobilePhone x)
	{
		super.Delete(x);
		/*MobilePhoneSet s = this;
		while(!s.m.bstation.parent.equals(null))
		{	
			s=s.m.bstation.parent.mset;
			s.Delete(x);
		}*/
	}

	public MobilePhone getPhonexxx(int x) throws mobilenotfoundexception
	{
	 	MobilePhone m=super.getPhonexxx(x);
	 	if(m==null)
	 	{
	 		throw new mobilenotfoundexception();
	 	}
	 	else
	 	{
	 		return super.getPhonexxx(x);
	 	}
	}

	public MobilePhone getPhone(int x) throws mobilenotfoundexception
	{
	 	MobilePhone y=new MobilePhone(x);
	 	MobilePhone m=super.getPhonexx(y);
	 	if(m==null)
	 	{
	 		throw new mobilenotfoundexception();
	 	}
	 	else
	 	{
	 		return m;
	 	}
	}

	public MobilePhone aPhone(int x) throws mobilenotfoundexception
	{
	 	MobilePhone m=super.aPhone(x);

	 	if(m==null)
	 	{
	 		throw new mobilenotfoundexception();
	 	}
	 	else
	 	{
	 		return m;
	 	}
	}

	 public String querymset()
	 {
	 	try
	 	{
	 	String s="";
	 	int p=numChildren();
	 	if(p>0)
	 		{
	 			if(aPhone(0).status())
	 			s=s+ String.valueOf(aPhone(0).mno);
	 		}
	 	for(int i=2;i<p+1;i++)
	 	{
	 		if(aPhone(i).status())
	 		s=s+", "+ String.valueOf(aPhone(i).mno);
	 	}	 	
	 	return s;
	 	}
	 	catch(mobilenotfoundexception e)
	 	{
	 		System.out.println("Error- Mobile not found");
	 	}
	 	return "";
	 }

}