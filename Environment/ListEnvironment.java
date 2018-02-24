//I suppressed some "unchecked" warnings because they are unavoidable and annoying when looking for actual warnings.

package Environment;
public class ListEnvironment<K,V> implements Environment<K,V>
{
	ListNode<K,V> head;
	int	 scopeLevel;

	public ListEnvironment()
	{
		scopeLevel = 0;
		head = new ListNode<K,V>(null, null, scopeLevel, null);		
	}

	public void beginScope()
	{
		add(null, null);
		scopeLevel++;
	}
	@SuppressWarnings("unchecked")
	public void endScope()
	{
		ListNode n = head.next;
		while (n.key != null){
			n = n.next;
		}
		n = n.next;
		head.next = n;
		scopeLevel--;
	}

	public boolean inCurrentScope(K key)
	{
		ListNode n = head.next;
		while (n.key != null){
			if (n.key.equals(key)){
				return true;
			}
			n = n.next;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public void add(K key, V value)
	{
		ListNode n = new ListNode<K,V>(key, value, scopeLevel, head.next);
		head.next = n;
	}
	@SuppressWarnings("unchecked")
	public V lookup(K key)
	{
		ListNode n = head.next;
		while (n.key != key){
			n = n.next;
			if (n == null){
				return null;
			}
		}
		return (V)n.value;
	}

	public String toString()
	{
		ListNode<K,V> n = head.next;
		String s = "lvl: " + scopeLevel + " {";
		while (n != null)
		{
			s += "(";
			if ( n.key == null )
			{
				s += "<sm>";
			}
			else
			{
				s += n.key + "," + n.value;
			}
			s += ")";
			if (n.next != null)
			{
				s += ",";
			}
			n = n.next;
		}
		s += "}";
		return s;
	}
	/*
	public static void main (String[] args)
	{
		Environment<String, Integer> e1 = new ListEnvironment<String,Integer>();
		System.out.println(e1);
		e1.beginScope();
		System.out.println(e1);
		e1.add("ABC", 1);
		System.out.println(e1);
		e1.add("DEF", 2);
		e1.add("XYZ", 3);
		System.out.println(e1);
		if (e1.inCurrentScope("ABC") )
			System.out.println("Passed inCurrentScope 1");
		else
			System.out.println("Failed inCurrentScope 1");

		if (e1.inCurrentScope("DEF") )
			System.out.println("Passed inCurrentScope 2");
		else
			System.out.println("Failed inCurrentScope 2");

		if (e1.inCurrentScope("XYZ") )
			System.out.println("Passed inCurrentScope 3");
		else
			System.out.println("Failed inCurrentScope 3");

		if (!e1.inCurrentScope("DDD") )
			System.out.println("Passed inCurrentScope 4");
		else
			System.out.println("Failed inCurrentScope 4");
		e1.beginScope();

		e1.add("ABC", 123);
		e1.add("EER", 777);
		if (e1.inCurrentScope("ABC") )
			System.out.println("Passed inCurrentScope 5");
		else
			System.out.println("Failed inCurrentScope 5");

		if (!e1.inCurrentScope("DEF") )
			System.out.println("Passed inCurrentScope 6");
		else
			System.out.println("Failed inCurrentScope 6");

		if (!e1.inCurrentScope("XYZ") )
			System.out.println("Passed inCurrentScope 7");
		else
			System.out.println("Failed inCurrentScope 7");
		System.out.println(e1);

		if (e1.lookup("DEF").equals(2))
			System.out.println("Passed lookup 1");
		else
			System.out.println("Failed lookup 1");
		e1.beginScope();
		e1.add("BOBWAS", 999);
		System.out.println(e1);
		e1.endScope();
		System.out.println(e1);
		e1.endScope();
		System.out.println(e1);
		e1.endScope();
		System.out.println(e1);
	}*/
}
