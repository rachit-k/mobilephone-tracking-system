
public class linkedlist<E>
{
	
	node<E> head;

	linkedlist()
	{
		head=null;
	}

	public Boolean isempty()
	{
		if (head.equals(null))
			return true;
		else
			return false;
	} 

	public Boolean ismember(E x)
	{	
		node<E> temp;
		temp=head;

		while(temp!=null)
		{
			if(temp.a.equals(x))
			{
				return true;
			}
			temp=temp.next;
			
		}		
		return false;		
	}

	public void insert(E x)
	{
		node<E> temp=head;		
		if(temp==null)
		{
			head=new node(x);
		}	
		else
		{
			while(temp.next!=null)
			{
				temp=temp.next;		
			}
		node<E> t= new node<E>();
		t.a=x;	
		temp.next=t;
		t.next=null;
		}
	}

	public void delete(E x)	//exception
	{
		node<E> temp=head;
		node<E> t=head;
		if(temp.a.equals(x))
		{
			head=head.next;
		}
		else
		{
			while(!temp.next.equals(null))
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
			return 0;
		}
		if(!temp.equals(null))
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

	public E achild(int x) throws mobilenotfoundexception
	{
		node<E> temp= head;
		for(int i=1;i<x;i++)
		{
			temp=temp.next;
			//System.out.println("ccc ++++");
			if(temp.equals(null))
			{
				throw new mobilenotfoundexception();
			}
		}
		return temp.a;
	}

	/*public E child(int x)
	{
		node<E> temp= head;
		while(!(temp==null))
		{
			if(temp.a.equals(x))
			{
				return temp.a;
			}
			temp=temp.next;
		}
		return head.a;
	}*/

	public E childxxxx(int x)	
	{
		node<E> temp= head;
		if(head==null)
		{
			return null;
		}
		else
		{
		while(temp!=null)
		{
			
			if(x==((MobilePhone)temp.a).number())
			{
				//System.out.println("ccccccccc++++");
				return temp.a;
			}
			temp=temp.next;
		}
		}
		return null;
	}


	public E childxxx(MobilePhone x)	//
	{
		node<E> temp= head;
		if(head==null)
		{
			return null;
		}
		else
		{
		while(temp!=null)
		{
			
			if(x.number()==((MobilePhone)temp.a).number())
			{
				//System.out.println("ccccccccc++++");
				return temp.a;
			}
			temp=temp.next;
		}
		}
		return null;
	}

}