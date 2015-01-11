package tfisher.entities;
// Generated 13 ??? 2014 8:05:22 ?? by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * Tweet generated by hbm2java
 */
@Component
public class Tweet extends Observable implements java.io.Serializable {


     private String idStr;
     private User user;
     private String createdAt;
     private String text;
     private String source;
     private Integer retweetCount;
     private String NLang;
     private Set medias = new HashSet(0);

    public Tweet() {
    }

	
    public Tweet(String idStr) {
        this.idStr = idStr;
    }
    public Tweet(String idStr, User user, String createdAt, String text, String source, Integer retweetCount, String NLang, Set medias) {
       this.idStr = idStr;
       this.user = user;
       this.createdAt = createdAt;
       this.text = text;
       this.source = source;
       this.retweetCount = retweetCount;
       this.NLang = NLang;
       this.medias = medias;
    }
   
    public String getIdStr() {
        return this.idStr;
    }
    
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
        this.user.setIdStr(user.getIdStr());
    }
    public String getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    public Integer getRetweetCount() {
        return this.retweetCount;
    }
    
    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }
    public String getNLang() {
        return this.NLang;
    }
    
    public void setNLang(String NLang) {
        this.NLang = NLang;
    }
    public Set getMedias() {
        return this.medias;        
    }
    
    public void setMedias(Set medias) {
        this.medias = medias;       
    }

/**
     * Notify all observers that the state changed
     */
    public void changeState() 
    {      
        setChanged();
        notifyObservers();
    }


}


