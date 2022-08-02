package SammyChan.BlogDataBaseTest.model;

/**
 *
 * @author sammychan
 */
public class Author {
    private String username;
    private String displayName;
    private boolean permissions;
    private String password;
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getDisplayName(){
        return displayName;
    }
    public boolean getPersmissions(){
        return permissions;
    }
    public void setPermssions(boolean permissions){
        this.permissions = permissions;
    }
    
}
