
public class Myset<E>
{
	public linkedlist<E> ll;
	
	Myset()
	{
		ll=new linkedlist<E>();
	}

	public Boolean IsEmpty()
	{
		return ll.isempty();
	}

	public int numChildren()
	{
		return ll.numchildren();
	}

	public Boolean IsMember(E a)
	{
		if(ll==null)
			return false;
		return ll.ismember(a);	
	}

	public void Insert(E a)
	{
		ll.insert(a);
	}

	public void Delete(E a)
	{
		ll.delete(a);
	}

	public E getPhone(int a) throws mobilenotfoundexception
	{
		MobilePhone m=new MobilePhone(a);
		return ll.childxxx(m);
	}

	public E getPhonexxx(int x) throws mobilenotfoundexception
	{
		return ll.childxxxx(x);
	}

	public E getPhonexx(MobilePhone m) throws mobilenotfoundexception
	{
		return ll.childxxx(m);
	}


	public E aPhone(int a) throws mobilenotfoundexception
	{
		return ll.achild(a);
	}

	public MobilePhoneSet Union(Myset<E> a)
	{
		Myset<E> b=this;
		node<E> t=a.ll.head;
		if(t==null)
		{
			return (MobilePhoneSet)b;

		}
		if((t!=null)&&(!b.IsMember(t.a)))
		{
			b.Insert(t.a);
		}
		
			while(t.next!=null)
		{
			t=t.next;
			if(!b.IsMember(t.a))
			{
				b.Insert(t.a);
			}
		}
		return (MobilePhoneSet)b;

	}

	public Myset Intersection(Myset a)
	{
		Myset b = this;
		node t=a.ll.head;
		if((!t.equals(null))&&(!b.IsMember(t.a)))
		{
			b.Delete(t.a);
		}
		while(!t.next.equals(null))
		{
			t=t.next;
			if(!b.IsMember(t.a))
			{
				b.Delete(t.a);
			}
		}
		return b;
	}

}