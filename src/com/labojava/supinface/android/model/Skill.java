/**
 * 
 */
package com.labojava.supinface.android.model;

/**
 * @author boris
 *
 */
public class Skill {
	private long id;
	private String label;
	private int rate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
}
