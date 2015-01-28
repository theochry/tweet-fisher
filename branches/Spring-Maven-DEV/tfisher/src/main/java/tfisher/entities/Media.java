package tfisher.entities;
import java.util.Observable;
import org.springframework.stereotype.Component;
// Generated 13 ??? 2014 8:05:22 ?? by Hibernate Tools 3.6.0



/**
 * Media generated by hbm2java
 */
@Component
public class Media  extends Observable implements java.io.Serializable {


     private String idStr;
     private Tweet tweet;
     private String mediaUrl;
     private String type;

    public Media() {
    }

	
    public Media(String idStr) {
        this.idStr = idStr;
    }
    public Media(String idStr, Tweet tweet, String mediaUrl, String type) {
       this.idStr = idStr;
       this.tweet = tweet;
       this.mediaUrl = mediaUrl;
       this.type = type;
    }
   
    public String getIdStr() {
        return this.idStr;
    }
    
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
    public Tweet getTweet() {
        return this.tweet;
    }
    
    public void setTweet(Tweet tweet) {        
        this.tweet = tweet;
        this.tweet.setIdStr(tweet.getIdStr());
    }
    public String getMediaUrl() {
        return this.mediaUrl;
    }
    
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

public void changeState() 
    {      
        setChanged();
        notifyObservers();
    }


}


