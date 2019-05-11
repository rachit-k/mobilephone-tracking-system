
public class RoutingMapTree
{
	Exchange root;

	RoutingMapTree()
	{
		root=new Exchange(0);
		root.parent=null;
	}

	public void switchOn(MobilePhone a, Exchange b)
	{
		a.switchOn();	
	}

	public void switchOff(MobilePhone a)
	{
		a.switchOff();	
	}

	public Exchange findPhone(MobilePhone m) throws mobilenotfoundexception, switchoffexception
	{
		try
		{
			if(m.status())
				return m.location();
			else
				throw new mobilenotfoundexception();
		}
		catch(switchoffexception e)
		{
			throw new switchoffexception();
		}
	}

	public Exchange lowestRouter(Exchange a, Exchange b) throws exchangenotfoundexception
	{
		if((a==null)||(b==null))
		{
			throw new exchangenotfoundexception(); 
		}
		else				
		{
			if(a==b)
			{
				return a;
			}
			else if(a.parent()==b.parent())
			{
				return a.parent();
			}
			else
			{
				return lowestRouter(a.parent(),b.parent());
			}
		}
	}

	public ExchangeList routeCall(MobilePhone a, MobilePhone b) throws switchoffexception, exchangenotfoundexception
	{
		
		try
		{
			Exchange aa = a.location();
			ExchangeList el = new ExchangeList(aa);		
			Exchange bb=b.location();
			Exchange ee = lowestRouter(aa,bb);	
			while(!(aa.equals(ee)))
			{				
				aa=aa.parent;
				el.insert(aa);
			}
			el.addrouteB(bb,ee);
			return el;
		}
		catch(switchoffexception e)
		{
			throw new switchoffexception();
		}	
		catch(exchangenotfoundexception e)
		{
			throw new exchangenotfoundexception();
		}

	}

	public void movePhone(MobilePhone a, Exchange b) throws switchoffexception, exchangenotfoundexception
	{
		if(!(a.status()))
			throw new switchoffexception();	//switchoff
		else
		{
			if(b.mset==null)
				throw new exchangenotfoundexception();	//b not a base station
			else
			{
				Exchange ee=a.bstation;
				ee.mset.Delete(a);
				ee.Updatetree();
				a.bstation=b;
			}
		}
	}

	public String performAction(String actionMessage)	
	{
		String[] s=actionMessage.split(" ");
		int aa = Integer.parseInt(s[1]);
		int bb=0;
		if(s.length==3)
		{
			bb = Integer.parseInt(s[2]);
		}
		else  if(s.length>3)
		{
			return "Error - Incorrect String Input";
		}	

		if(s[0].equals("addExchange"))
		{
			try
			{
				Exchange temp = root.getExchange(aa);
				if(temp==null)
				{
					return actionMessage+": Error - No exchange with identifier "+ aa;
				}
				Exchange b=new Exchange(bb);
				temp.addchild(b);
				temp.Updatetree();
				return actionMessage+": ";
				
			}
			catch(exchangenotfoundexception e)
			{
			return actionMessage+": Error - No exchange with identifier "+ aa;
			}

		}

		else if(s[0].equals("switchOnMobile"))
		{
 			try
			{
				try
				{
					Exchange temp=root.getExchange(bb);
					MobilePhone m=new MobilePhone(aa,temp);
					if(temp!=null)
					{
						if((temp.mset!=null))	
						{	if(temp.mset.IsMember(m))
							{
								m=temp.mset.getPhone(aa);
								m.switchOn();
							} 
							else if(!root.mset.IsMember(m))
							{
								temp.mset.Insert(m);		
							}
							temp.Updatetree();
							return actionMessage+": ";
						}
						else
						{
						m.switchOn();
						temp.new_mset(m);
						temp.Updatetree();

						}	
					}

				
				}
				catch(mobilenotfoundexception e)	
				{
					return actionMessage+": Error - No mobile phone with identifier "+ aa;					
				}	


			}
			catch(exchangenotfoundexception e)
			{
				return actionMessage+": Error - No Exchange with identifier "+ bb;
			}

		}

		else if(s[0].equals("switchOffMobile"))
		{
			try
			{
				if(root.mset==null)
				{
					return actionMessage+": Error - No mobile phone with identifier "+ aa;
				}
				else
				{


				MobilePhone m=root.mset.getPhone(aa);
				m.switchOff();
			/*try
			{
				m.location().Updatetree();
			}
			catch(switchoffexception e)
			{}*/
				return actionMessage+": ";
				}
			}
			catch(mobilenotfoundexception e)
			{
				return actionMessage+": Error -  No mobile phone with identifier "+ aa;
			}

		}

		else if(s[0].equals("queryNthChild"))
		{
			try
			{
			Exchange temp=root.getExchange(aa);
			Exchange t=temp.elist.achild(bb);
			String z=String.valueOf(t.eno);
			z=actionMessage + ": " + z;
			return z;	
			}
			catch(exchangenotfoundexception e)
			{
				return  actionMessage+": Error - No Exchange with identifier "+ aa;
			}
			catch(switchoffexception e)	
			{
				return actionMessage+": Error - No mobile phone with identifier "+bb;					
			}
		}

		else if(s[0].equals("queryMobilePhoneSet"))
		{
			try
			{
			Exchange temp=root.getExchange(aa);
			if(temp.mset==null)
			{
				return actionMessage+": Error - No Exchange with identifier "+ aa;
			}
			else
			{	
				String z=actionMessage + ": " + temp.mset.querymset();
				return z;
			}	
			}
			catch(exchangenotfoundexception e)
			{
				return actionMessage+": Error - No Exchange with identifier "+ aa;
			}
		}

		else if(s[0].equals("findPhone"))
		{
			try
			{
				String z="queryFindPhone "+ aa+ ": " + findPhone(root.mset.getPhonexxx(aa)).eno;
				return z;
			}
			catch(mobilenotfoundexception e)
			{
				return "queryFindPhone "+ aa+": Error - No mobile phone with identifier " + aa+" found in the network" ;
			}
			catch(switchoffexception e)
			{
				return "queryFindPhone "+ aa+": Error - Mobile phone with identifier " + aa+" is currently switched off";
			}
		}

		else if(s[0].equals("lowestRouter"))
		{
			try
			{
				String z="queryLowestRouter "+ aa +" "+ bb + ": "+ String.valueOf(lowestRouter(root.getExchange(aa),root.getExchange(bb)).eno);
				return z;
			}
			catch(exchangenotfoundexception e)
			{
				return "queryLowestRouter "+ aa +" "+ bb + ": Error - not a base Exchange";
			}
		}	

		else if(s[0].equals("findCallPath"))
		{
			try
			{
				Exchange eee=root.mset.getPhonexxx(aa).location();
			}
			catch(switchoffexception e)
			{
				return "queryFindCallPath "+aa+" "+bb +": Error - Mobile phone with identifier "+ aa+ " is currently switched off";
			}
			catch(mobilenotfoundexception e)
			{
				return "queryFindCallPath "+aa+" "+bb + ": Error - No Mobile Phone with identifier "+ aa+" currently exists";
			}
			try
			{
				ExchangeList l=routeCall(root.mset.getPhonexxx(aa),root.mset.getPhonexxx(bb));
				String z="queryFindCallPath "+aa+" "+bb +": "+ l.printelist();
				return z;
			}
			catch(switchoffexception e)
			{
				return "queryFindCallPath "+aa+" "+bb +": Error - Mobile phone with identifier "+ bb+ " is currently switched off";
			}
			catch(exchangenotfoundexception e)
			{
				return "queryFindCallPath "+aa+" "+bb +": Error - No path exists";
			}
			catch(mobilenotfoundexception e)
			{
				return "queryFindCallPath "+aa+" "+bb + ": Error - No Mobile phone with identifier "+bb+" currently exists";
			}
			
		}

		else if(s[0].equals("movePhone"))
		{
			try
			{
				movePhone(root.mset.getPhonexxx(aa),root.getExchange(bb));
				return actionMessage + ": ";
			}
			catch(switchoffexception e)
			{
				return actionMessage + ": Error - Mobile phone with identifier"+ aa+" is currently switched off";
			}
			catch(exchangenotfoundexception e)
			{
				return actionMessage + ": Error - No Exchange with identifier "+ bb;
			}
			catch(mobilenotfoundexception e)
			{
				return actionMessage + ": Error - No Mobile Phone with identifier "+ aa+" currently exists";
			}
		}

		else
		{
			return "Error- No such query can be handled";
		}
		return "";
	}
}