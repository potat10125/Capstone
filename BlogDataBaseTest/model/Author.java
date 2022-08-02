package SammyChan.BlogDataBaseTest.model;

import java.util.Objects;

/**
 *
 * @author sammychan
 */
public class Author {
    private String username;
    private String displayName;
    private boolean permissions;
    private String pass;
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getDisplayName(){
        return displayName;
    }
    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }
    public boolean getPermissions(){
        return permissions;
    }
    public void setPermissions(boolean permissions){
        this.permissions = permissions;
    }
    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.username);
        hash = 11 * hash + Objects.hashCode(this.displayName);
        hash = 11 * hash + Objects.hashCode(this.permissions);
        hash = 11 * hash + Objects.hashCode(this.pass);
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
        final Author other = (Author) obj;
        if (this.permissions != other.permissions) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.displayName, other.displayName)) {
            return false;
        }
        return Objects.equals(this.pass, other.pass);
    }
    
}
