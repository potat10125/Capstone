package com.sg.capstone.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sammychan
 */
public class Blog {
    private int id;
    private String content;
    private String title;
    private String user;
    private boolean approved;
    private String publishDate; 
    private List<String> hashtags = new ArrayList<>();
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }
    public boolean getApproved(){
        return approved;
    }
    public void setApproved(boolean approved){
        this.approved = approved;
    }
    public String getPublishDate(){
        return publishDate;
    }
    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }
    public List<String> getHashtags(){
        return hashtags;
    }
    public void setHashtags(List<String> hashtags){
        this.hashtags = hashtags;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.content);
        hash = 61 * hash + Objects.hashCode(this.title);
        hash = 61 * hash + Objects.hashCode(this.user);
        hash = 61 * hash + Objects.hashCode(this.approved);
        hash = 61 * hash + Objects.hashCode(this.publishDate);
        hash = 61 * hash + Objects.hashCode(this.hashtags);
        return hash;
    }     

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.approved != other.approved) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.publishDate, other.publishDate)) {
            return false;
        }
        return Objects.equals(this.hashtags, other.hashtags);
    }
    
}
