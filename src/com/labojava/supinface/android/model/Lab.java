/**
 * 
 */
package com.labojava.supinface.android.model;

/**
 * @author boris
 *
 */
public class Lab {
	private long id;
	private String name;
	private User glm;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getGlm() {
		return glm;
	}
	public void setGlm(User glm) {
		this.glm = glm;
	}
}
