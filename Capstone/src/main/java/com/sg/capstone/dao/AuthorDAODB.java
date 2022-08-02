package com.sg.capstone.dao;


import com.sg.capstone.model.Author;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sammychan
 */
@Repository
public class AuthorDAODB implements AuthorDAO{
    @Autowired
    JdbcTemplate jdbc;  
    
    @Override
    public Author getAuthor(String username) {
        try{
            final String sql = "SELECT * FROM authors WHERE username = ?;";
            Author author =  jdbc.queryForObject(sql, new AuthorMapper(), username);
            return author;
        }catch (DataAccessException e){
            return null;
        } 
    }

    @Override
    public List<Author> getAllAuthors() {
        final String sql = "SELECT * FROM authors;";
        List<Author> authors = jdbc.query(sql, new AuthorMapper());  
        return authors;           
    }
    
    @Override
    public Author addAuthor(Author author){
        final String sql = "INSERT INTO authors VALUES (?, ?, ?, ?);";
        jdbc.update(sql, author.getUsername(), author.getDisplayName(), author.getPass(), author.getPermissions());
        return author;         
    }
    
    @Override
    public void deleteAuthorByUsername(String username){
        final String sql = "DELETE FROM authors WHERE username = ?";
        jdbc.update(sql, username);
    }
    
    public static final class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int index) throws SQLException{
            Author author  = new Author();
            
            author.setUsername(rs.getString("username"));
            author.setDisplayName(rs.getString("display_name"));
            author.setPass(rs.getString("pass"));
            author.setPermissions(rs.getBoolean("permissions"));
               
            return author;
        }    
    }     
    
}
