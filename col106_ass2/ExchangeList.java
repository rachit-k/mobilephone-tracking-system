
public class ExchangeList
{
	node head;

	ExchangeList()
	{
		head=null;
	}
	ExchangeList(Exchange x)
	{
		head=new node(x);
		head.a=x;
		head.next=null;
	}
	class node
	{
		Exchange a;
		node next;
		node(Exchange x)
		{
			a=x;
			next=null;
		}
		node()
		{
			next=null;
		}
	}


	public Boolean isempty()
	{
		if (head.equals(null))
			return true;
		else
			return false;
	} 

	public Boolean ismember(Exchange x)
	{	

		node temp=head;
		while(!temp.equals(null))
		{
			temp=temp.next;
			if(temp.a.equals(x))
			{
				return true;
			}
		}		
			return false;		
	}

	public void insert(Exchange x)
	{
		node temp=new node();		
		if(head==null)
		{
			temp.a=x;
			temp.next=null;
			head=temp;
		}	
		else
		{
			temp=head;
			while(!(temp.next==null))
			{
				temp=temp.next;		
			}
		node t= new node();
		t.a=x;	
		temp.next=t;
		t.next=null;
		}
	}

	public void delete(Exchange x)
	{
		node temp=head;
		node t=head;
		if(temp.a.equals(x))
		{
			head=head.next;
		}
		else
		{
			while(!temp.equals(null))
			{
				temp=temp.next;
				if(temp.a.equals(x))
				{
					t.next=temp.next;
					break;
				}
				t=t.next;			
			}
		}		
	}

	public int numchildren()
	{
		node temp= head;
		int flag=0;
		if(temp==null)
		{
			return 0;	//
		}
		if(temp!=null)
		{
			flag++;
		}
		while(!(temp.next==null))
		{
			temp=temp.next;
			flag++;
		}
		return flag;
	}

	public Exchange achild(int x) throws switchoffexception
	{
		node temp= head;
		if(temp==null)
		{
			throw new switchoffexception();
		}
		else if(x>=numchildren())
		{
			throw new switchoffexception();
		}		
		else
		{
			for(int i=0;i<x;i++)
			{
				temp=temp.next;
			}
			return temp.a;
		}
	}

	public Exchange child(int x)
	{
		node temp= head;
		while(!temp.equals(null))
		{
			if(temp.a.eno==x)
			{
				return temp.a;
			}
			temp=temp.next;
		}
		return head.a;
	}

	public void addrouteB(Exchange bb,Exchange ee)
	{
		if((bb==ee)||(bb==null))
			return;
		else
		{
			addrouteB(bb.parent,ee);
			this.insert(bb);
		}
	}

	public String printelist()
	{
		String s="";
		node temp=head;
		if(temp==null)
			return "";
		else
		{
			s=s+String.valueOf(temp.a.eno);
			while(temp.next!=null)
			{
				temp=temp.next;
				s=s+", "+String.valueOf(temp.a.eno);				
			}
			return s;
		}		
	}

}