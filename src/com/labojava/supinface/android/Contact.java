package com.labojava.supinface.android;

public class Contact {
	
	private int id;
	private String type;
	private String value;
	private int user_Id;
	
	//---------CONSTRUCTOR------------
	Contact()
	{
		
	}
	
	Contact(int aID)
	{
		this.id= aID;
	}
	//--------------------------------
	
	
	//--------Getter------------------
	public int GetId()
	{
		return this.id;
	}
	public String GetContactType()
	{
		return this.type;
	}
	public String GetValue()
	{
		return this.value;
	}
	public int GetUser_Id()
	{
		return this.user_Id;
	}	
	//--------------------------------
	
	
	//--------Setter------------------
	public void SetId(int aID)
	{
		this.id= aID;
	}
	public void SetContactType(String aContactType)
	{
		this.type= aContactType;
	}
	public void SetUser_Id(int aUser_Id)
	{
		this.user_Id= aUser_Id;
	}	
	//--------------------------------
	

}
