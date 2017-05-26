package myPack;


import java.io.Serializable;

public class UserData implements Serializable {
	public String login;
	public String password;
	public boolean isAdmin;

	public UserData(String l, String p)
	{
		login = l;
		password = p;
		isAdmin = false;
	}
	
	public UserData(String l, String p, boolean i)
	{
		login = l;
		password = p;
		isAdmin = i;
	}
}
