package tfisher.entities;
// Generated Jun 4, 2014 12:06:28 PM by Hibernate Tools 3.6.0



/**
 * Media generated by hbm2java
 */
public class Media  implements java.io.Serializable {


     private String id;
     private Tweet tweet;
     private String indices;
     private String mediaUrl;
     private String mediaUrlHttps;
     private String url;
     private String displayUrl;
     private String expandedUrl;
     private String type;
     private String size;
     private String sourceStatusIdStr;

    public Media() {
    }

	
    public Media(String id) {
        this.id = id;
    }
    public Media(String id, Tweet tweet, String indices, String mediaUrl, String mediaUrlHttps, String url, String displayUrl, String expandedUrl, String type, String size, String sourceStatusIdStr) {
       this.id = id;
       this.tweet = tweet;
       this.indices = indices;
       this.mediaUrl = mediaUrl;
       this.mediaUrlHttps = mediaUrlHttps;
       this.url = url;
       this.displayUrl = displayUrl;
       this.expandedUrl = expandedUrl;
       this.type = type;
       this.size = size;
       this.sourceStatusIdStr = sourceStatusIdStr;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Tweet getTweet() {
        return this.tweet;
    }
    
    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
    public String getIndices() {
        return this.indices;
    }
    
    public void setIndices(String indices) {
        this.indices = indices;
    }
    public String getMediaUrl() {
        return this.mediaUrl;
    }
    
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
    public String getMediaUrlHttps() {
        return this.mediaUrlHttps;
    }
    
    public void setMediaUrlHttps(String mediaUrlHttps) {
        this.mediaUrlHttps = mediaUrlHttps;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
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
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getSize() {
        return this.size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    public String getSourceStatusIdStr() {
        return this.sourceStatusIdStr;
    }
    
    public void setSourceStatusIdStr(String sourceStatusIdStr) {
        this.sourceStatusIdStr = sourceStatusIdStr;
    }




}


