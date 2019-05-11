
public class Exchange
{
	int eno;
	ExchangeList elist;
	Exchange parent;
	MobilePhoneSet mset;

	Exchange(int number)
	{
		eno=number;
		elist=new ExchangeList();
		mset=new MobilePhoneSet();
	}

	/*Exchange()
	{
		eno=0;
	}*/

	public void Updatetree()
	{
		if(this.parent==null)
		{
			return;
		}
		else if(!(this.mset==null))
		{
			this.parent.mset=this.parent.mset.Union(this.mset);
			this.parent.Updatetree();
		}
			
	}

	public void new_mset(MobilePhone x)
	{
		if(mset==null)
		{
			mset.Insert(x);
		}

	}


	public Exchange parent()
	{
		return parent;
	}

	public void addchild(Exchange cc)
	{
		if(elist!=null)
			elist.insert(cc);
		else
			elist=new ExchangeList(cc);
		cc.parent=this;
	}

	public int numChildren()
	{
		if(elist==null)
		{
			return 0;
		}
		else
		{
		return elist.numchildren();
		}
	}

	public Boolean isRoot()
	{
		if(parent.equals(null))
		{
			return true;
		}
		return false;
	}

	public boolean existExchange(int x)
	{
		try{
		int j=this.numChildren();
		if(this.eno==x)
		{
			return true;
		}
		for(int i=0;i<j;i++)			
		{
			if(this.elist.achild(i).existExchange(x))
			{
				return true;
			}
		}}
		catch(switchoffexception e){}
		return false;
	}

	public Exchange getExchange(int x) throws exchangenotfoundexception
	{
		
		if(existExchange(x)==false)
			{
				throw new exchangenotfoundexception();
			}	
		int n=numChildren();
		try
		{
			if(this.eno==x)
			{
				return this;
			}
			for(int i=0; i<n;i++)
			{
				if(this.elist.achild(i).existExchange(x)==true)
					return this.elist.achild(i).getExchange(x);
			}
		}
		catch(switchoffexception e)
		{}
		if(n<0)
			throw new exchangenotfoundexception();
		return null;
	}

	public RoutingMapTree subtree(int i)
	{		
		RoutingMapTree t = new RoutingMapTree();
		try
		{
		t.root= elist.achild(i);		
		}
		catch(switchoffexception e)
		{}
		return t;
	}

	public MobilePhoneSet residentSet()
	{
		return mset;
	}
	
}