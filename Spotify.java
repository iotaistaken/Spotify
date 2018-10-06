
import java.util.ArrayList;
import java.util.Scanner;

public class Spotify
{
	private  ArrayList<Customer> customers;
	private  ArrayList<Artist> artists;
	
	public Spotify()
	{
		this.customers = new ArrayList<Customer>();
		this.artists = new ArrayList<Artist>();
	}
	// Return Money to be spent on Subscription
	private  int Subs_Money(int x)
	{
		if (x==1)
		{
			return 0;
		}
		else if (x==2)
		{
			return 100;
		}
		else if (x==3)
		{
			return 500;
		}
		else if (x==4)
		{
			return 1000;
		}
		else
		{
			System.out.println("Invalid Plan Choseen");
			return 911;
		}
	}
	// Creates new Customer Here
	public Customer return_new_Customer(String[] x)
	{
		int icus = (int) Math.ceil(Math.random()*100);
		Customer cud = new Customer(icus,x[2]);
		return cud;
	}
	// Creates new Artist Here
	public Artist return_new_Artist(String[] x)
	{
		int iart = (int) Math.ceil(Math.random()*100);
		Artist bn = new Artist(iart, x[2]);		
		return bn;
	}
	
	public static void main(String[] args) 
	{
		Scanner jk = new Scanner(System.in);
		int q = jk.nextInt();
		Spotify obj = new Spotify(); // This is assigned to person whenever he opens application.
		for (int i=0; i<q; i++)
		{
			int check_Cus;
			int check_Art;
			String[] x = jk.nextLine().split(" ");
			if (x[0].compareTo("1")==0)
			{
				if (x[1].compareTo("C")==0)
				{
					check_Cus = (int) Math.ceil(Math.random()*100);
					Customer cus = new Customer(check_Cus, x[2]);
					obj.customers.add(cus);
					//System.out.println(check_Cus);
				}
				else if (x[1].compareTo("A")==0)
				{
					check_Art = (int) Math.ceil(Math.random()*100);
					Artist art = new Artist(check_Art, x[2]);
					obj.artists.add(art);
					//System.out.println(check_Art);
				}
			}
			if (x[0].compareTo("2")==0)
			{
				for (Customer cuc:obj.customers)
				{
					if (cuc.id==Integer.parseInt(x[1]))
					{
						cuc.subSet(Integer.parseInt(x[2]));
					}
				}
			}
			if (x[0].compareTo("3")==0)
			{
				for (Artist ary:obj.artists) 				// Extracting each Artist
				{
					if (ary.id==Integer.parseInt(x[1]))   // Checking Artist Id
					{
						for (int kl=0; kl<Integer.parseInt(x[2]); kl++)		// Adding the songs to that artist
						{
							Song df = new Song((int) Math.ceil(Math.random()*100),x[kl+3]);
							ary.song_list.add(df);
						}
						for (Song iu:ary.song_list)//Printing the ID's of the songs generated and added to Artist
						{
							System.out.print(iu.id + " ");
						}
						System.out.println();
					}
				}
			}
			if (x[0].compareTo("4")==0)
			{
				for (Artist arv: obj.artists)
				{
					System.out.println(arv.getEarn());
					arv.setEarn();
				}
			}
			if (x[0].compareTo("5")==0)
			{
				boolean customer = false;  // Means he is artist
				Customer pos = null;
				for (Customer cuv:obj.customers)         // This loop checks of he customer or artist.
				{
					
					if (cuv.id==Integer.parseInt(x[1]))
					{
						customer = true;      // He is a customer.
						pos = cuv;
						break;         			// Match found
					}
				}
				if (customer)
				{
					for (int po=0; po<2*Integer.parseInt(x[2]); po+=2)
					{
						for (Artist arj:obj.artists)
						{
							for (Song song:arj.song_list)
							{
								if (song.id==Integer.parseInt(x[3+po]))
								{
									if (x[4+po].compareTo("D")==0)
									{
										arj.earnIncr(20);
										song.setdown();
										pos.setdown();
										
									}
									else if (x[4+po].compareTo("P")==0)
									{
										arj.earnIncr(5);
										song.setplay();
										pos.setplay();
									}
								}
							}
						}
					}
				}
				else
				{
					for (int po=0; po<2*Integer.parseInt(x[2]); po+=2)
					{
						for (Artist arj:obj.artists)
						{
							for (Song song:arj.song_list)
							{
								if (song.id==Integer.parseInt(x[3+po]))
								{
									if (x[4+po].compareTo("D")==0)
									{
										arj.dueIncr(20);
										song.downloads+=1;
									}
									else if (x[4+po].compareTo("P")==0)
									{
										arj.dueIncr(5);
										song.plays+=1;
									}
								}
							}
						}
					}
				}
			}
			if (x[0].compareTo("6")==0)
			{
				for (Customer op:obj.customers)
				{
					if (op.id==Integer.parseInt(x[1]))
					{
						System.out.println(obj.Subs_Money(op.Sub_Option()));
					}
				}
			}
			if (x[0].compareTo("7")==0)
			{
				boolean customer = false;  // Means he is artist
				for (Customer cuv:obj.customers)         // This loop checks of he customer or artist.
				{
					
					if (cuv.id==Integer.parseInt(x[1]))
					{
						customer = true;      // He is a customer.
						break;         			// Match found
					}
				}
				if (customer)
				{
					for (Customer cub:obj.customers)
					{
						if (cub.id==Integer.parseInt(x[1]))
						{
							System.out.println(cub.getName() + " " + cub.getID() + " " + cub.Sub_Option());
						}
					}
				}
				else
				{
					for (Artist arc:obj.artists)
					{
						if (arc.id==Integer.parseInt(x[1]))
						{
							System.out.println(arc.getName() + " " + arc.getID()+";");
							for (Song io:arc.song_list)
							{
								System.out.print(io.id + ", ");
							}
							System.out.println();
						}
					}
				}
			}
		}
	}
}

class Album
{
	private  ArrayList<Song> album;
	public Album(int x)
	{
		this.album = new ArrayList<Song>();
	}
}

class Song
{
	private int plays;
	private int downloads;
	private int id;
	private String name;
	public Song(int x, String nm)
	{
		this.id = x;
		this.name = nm;
		this.plays=0;
		this.downloads=0;
	}
	public void setdown()
	{
		this.downloads++;
	}
	public void setplay()
	{
		this.plays++;
	}
}
class Artist
{
	private int id;
	private String name;
	private int earnings;
	private int dues=0;
	private ArrayList<Song> song_list;
	public Artist(int x, String nm)
	{
		this.id = x;
		this.name = nm;
		this.dues=0;
		this.earnings=0;
		song_list = new ArrayList<Song>();
	}
	public int getID()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}
	public int getEarn()
	{
		return this.earnings;
	}
	public void setEarn()
	{
		this.earnings=0;
	}
	public void dueIncr(int x)
	{
		this.dues+=x;
	}
	public void earnIncr(int x)
	{
		this.earnings+=x;
	}
}

class Customer
{
	private int id;
	private int plays;
	private int downloads;
	private String name;
	private int dues;
	private int subscrption=1;
	public Customer(int x, String nm)
	{
		int plays = 0;
		int downloads = 0;
		this.id = x;
		this.name = nm;
		this.dues=0;
	}
	public String getName()
	{
		return this.name;
	}
	public int getID()
	{
		return this.id;
	}
	public void subSet(int x)
	{
		this.subscrption=x;
	}
	public void dueIncr(int x)
	{
		this.dues+=x;
	}
	public void setdown()
	{
		this.downloads++;
	}
	public void setplay()
	{
		this.plays++;
	}
	// Return Sub Option
	public int Sub_Option()
	{
		return this.subscrption;
	}
	public int ToPay()
	{
		return (this.downloads*20) + (this.plays*5);
	}
}

