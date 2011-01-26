package com.labojava.supinface.android;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String classe;
	private String pict_Path;
	private String labo;
	private String info_Sup;
	private String campus;	
	List<Skill> mySkills;
	List<Group> myGroups;
	private Contact myContact;
	
	//---------CONSTRUCTOR------------
	User()
	{

	}
	
	User(int aID)
	{
		this.SetId(aID);
		this.mySkills = new ArrayList<Skill>();
		this.myGroups = new ArrayList<Group>();
	}
	
	//--------------------------------
	
	//--------Getter------------------
	public int GetId(){
		return this.id; 
	}
	public String GetfirstName(){
		return this.firstName; 
	}
	public String GetlastName(){
		return this.lastName;
	}
	public String GetEmail(){
		return this.email;
	}
	public String GetClasse(){
		return this.classe;
	}
	public String GetPict_Path(){
		return this.pict_Path;
	}
	public String GetLabo(){
		return this.labo;
	}
	public String GetInfo_Sup(){
		return this.info_Sup;
	}
	public String GetCampus(){
		return this.campus;
	}
	
	//--------------------------------
	
	//--------Setter------------------
	public void SetId(int aID){
		this.id=aID;
	}
	public void SetfirstName(String aFirstName){
		this.firstName=aFirstName;
	}
	public void SetlastName(String aLastName){
		this.lastName= aLastName;
	}
	public void SetEmail( String aEmail){
		this.email=aEmail;
	}
	public void SetClasse( String aClasse){
		this.classe=aClasse;
	}
	public void SetPict_Path( String aPict_Path){
		this.pict_Path=aPict_Path;
	}
	public void SetLabo(String aLabo){
		this.labo= aLabo;
	}
	public void SetInfo_Sup( String aInfo_Sup){
		this.info_Sup= aInfo_Sup;
	}
	public void SetCampus(String aCampus){
		this.campus= aCampus;
	}
	//--------------------------------
	
	//--------METHODE-----------------
	//add a existing Skill
	public void AddSkill(int aSkill_ID)
	{
		Skill aSkill= new Skill(aSkill_ID);
		aSkill.user_Id=this.id;
		mySkills.add(aSkill);
	}
	
	// add a New Skill (not available in DB)
	public void AddNewSkill()
	{
		
	}
	//--------------------------------
}
