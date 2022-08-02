package com.sg.capstone.dao;

import java.util.List;
import com.sg.capstone.model.*;

public interface BlogDao {
	public void addBlog(String author,String title, String content, List tags);
	public List getAll();//returns a list of all blogs (from newest to oldest?)
	public Blog get(int id);//returns blog with the id
}
