package myPack;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Entering {
	static public UserData Enter(String login, String password) throws IOException
	{
		UserData b = null;
		ArrayList<UserData> userArray = GetUsers();
		for(int i = 0; i<userArray.size(); i++)
		{
			if(userArray.get(i).login.equals(login) && userArray.get(i).password.equals(password))
			{
				b = userArray.get(i);
				break;
			}
		}
		return b;
	}
	
	public static ArrayList<UserData> GetUsers() throws IOException
	{
		ArrayList<UserData> userArray = new ArrayList<UserData>();
		FileInputStream fis = new FileInputStream("C:\\Users\\Компьютер\\workspace\\TestServer\\logindata");
		ObjectInputStream ois = null;
		try { 
			ois = new ObjectInputStream(new BufferedInputStream(fis));
		} catch(EOFException e) {
		}
		if(ois != null)
		{
			try {
				UserData ud;
				while(true)
				{
					try { 
					ud = (UserData)ois.readObject();
					userArray.add(ud);
					} catch(EOFException e)
					{
						break;
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
			fis.close();
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Компьютер\\workspace\\TestServer\\logindata");
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));
			for(int i = 0; i < userArray.size(); i++)
			{
				oos.writeObject(userArray.get(i));
			}
			oos.close();
			fos.close();
		}
		return userArray;
	}
	
	public static void RemoveUser(int n) throws IOException
	{ArrayList<UserData> userArray = new ArrayList<UserData>();
	FileInputStream fis = new FileInputStream("C:\\Users\\Компьютер\\workspace\\TestServer\\logindata");
	ObjectInputStream ois = null;
	try { 
		ois = new ObjectInputStream(new BufferedInputStream(fis));
	} catch(EOFException e) {
	}
	if(ois != null)
	{
		try {
			UserData ud;
			while(true)
			{
				try { 
				ud = (UserData)ois.readObject();
				userArray.add(ud);
				} catch(EOFException e)
				{
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
		fis.close();
		userArray.remove(n);
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Компьютер\\workspace\\TestServer\\logindata");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));
		for(int i = 0; i < userArray.size(); i++)
		{
			oos.writeObject(userArray.get(i));
		}
		oos.close();
		fos.close();
	}
	}
}
