package model;

import java.util.Date;

public class SearchKeyword {
    private String keyword;
    private Date searchTime;
    private int resultCount;
    private String userId;
    
    public SearchKeyword(String keyword, String userId) {
        this.keyword = keyword;
        this.userId = userId;
        this.searchTime = new Date();
        this.resultCount = 0;
    }
    
    // Getters and Setters
    public String getKeyword() { return keyword; }
    public Date getSearchTime() { return searchTime; }
    public int getResultCount() { return resultCount; }
    public void setResultCount(int resultCount) { this.resultCount = resultCount; }
    public String getUserId() { return userId; }
    
    // Methods
    public boolean isRecent() {
        Date now = new Date();
        long diff = now.getTime() - searchTime.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        return diffMinutes < 5; // Kurang dari 5 menit yang lalu
    }
    
    public String getSearchSummary() {
        return String.format("'%s' - %d hasil (%s)", 
            keyword, resultCount, searchTime.toString());
    }
    
    @Override
    public String toString() {
        return String.format("Keyword: %s | User: %s | Results: %d | Time: %s",
            keyword, userId, resultCount, searchTime);
    }
}