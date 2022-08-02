package com.sg.capstone.dao;

import java.util.List;

public interface HashtagDao {
	public List getAll(); //returns a list of all hashtags
	public void add(String tags);//add all non duplicate hashtags to database in a string with hashtags delimited by #
	public List getTags(String tags);//returns list of hashtags in the string
}
