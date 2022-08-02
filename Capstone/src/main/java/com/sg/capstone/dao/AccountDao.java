package com.sg.capstone.dao;

public interface AccountDao {
	public boolean hasPermissions(String username);//return true if account with associated username has blog post permissions
}
