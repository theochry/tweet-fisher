package tfisher.entities;
// Generated Jun 4, 2014 12:06:28 PM by Hibernate Tools 3.6.0



/**
 * Url generated by hbm2java
 */
public class Url  implements java.io.Serializable {


     private long id;
     private Tweet tweet;
     private String displayUrl;
     private String expandedUrl;
     private String indices;
     private String url;

    public Url() {
    }

	
    public Url(long id) {
        this.id = id;
    }
    public Url(long id, Tweet tweet, String displayUrl, String expandedUrl, String indices, String url) {
       this.id = id;
       this.tweet = tweet;
       this.displayUrl = displayUrl;
       this.expandedUrl = expandedUrl;
       this.indices = indices;
       this.url = url;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Tweet getTweet() {
        return this.tweet;
    }
    
    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
    public String getDisplayUrl() {
        return this.displayUrl;
    }
    
    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }
    public String getExpandedUrl() {
        return this.expandedUrl;
    }
    
    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }
    public String getIndices() {
        return this.indices;
    }
    
    public void setIndices(String indices) {
        this.indices = indices;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }




}


