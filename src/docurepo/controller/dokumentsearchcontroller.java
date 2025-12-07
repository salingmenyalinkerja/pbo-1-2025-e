package controller;

import model;
import java.util;

public class DocumentSearchController {
    private DocumentListingController listingController;
    private List<SearchKeyword> searchHistory;
    
    public DocumentSearchController(DocumentListingController listingController) {
        this.listingController = listingController;
        this.searchHistory = new ArrayList<>();
    }
    
    public List<Document> searchByKeyword(String keyword, String userId) {
        List<Document> allDocs = listingController.getAllDocuments();
        List<Document> results = new ArrayList<>();
        
        for (Document doc : allDocs) {
            if (doc.containsKeyword(keyword)) {
                results.add(doc);
            }
        }
        
        // Simpan ke history
        SearchKeyword searchRecord = new SearchKeyword(keyword, userId);
        searchRecord.setResultCount(results.size());
        searchHistory.add(searchRecord);
        
        System.out.println("Pencarian '" + keyword + "' menemukan " + results.size() + " dokumen");
        return results;
    }
    
    public List<Document> searchByTitle(String title) {
        List<Document> allDocs = listingController.getAllDocuments();
        List<Document> results = new ArrayList<>();
        
        for (Document doc : allDocs) {
            if (doc.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(doc);
            }
        }
        
        return results;
    }
    
    public List<Document> searchByContent(String content) {
        List<Document> allDocs = listingController.getAllDocuments();
        List<Document> results = new ArrayList<>();
        
        for (Document doc : allDocs) {
            if (doc.getContent().toLowerCase().contains(content.toLowerCase())) {
                results.add(doc);
            }
        }
        
        return results;
    }
    
    public List<Document> searchAdvanced(String keyword, String author, String type, String tag) {
        List<Document> results = listingController.getAllDocuments();
        
        // Filter by keyword
        if (keyword != null && !keyword.isEmpty()) {
            results.removeIf(doc -> !doc.containsKeyword(keyword));
        }
        
        // Filter by author
        if (author != null && !author.isEmpty()) {
            results.removeIf(doc -> !doc.getAuthor().equalsIgnoreCase(author));
        }
        
        // Filter by type
        if (type != null && !type.isEmpty()) {
            results.removeIf(doc -> !doc.getDocumentType().equalsIgnoreCase(type));
        }
        
        // Filter by tag
        if (tag != null && !tag.isEmpty()) {
            results.removeIf(doc -> !doc.getTags().contains(tag));
        }
        
        return results;
    }
    
    public List<SearchKeyword> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }
    
    public List<SearchKeyword> getRecentSearches() {
        List<SearchKeyword> recent = new ArrayList<>();
        for (SearchKeyword search : searchHistory) {
            if (search.isRecent()) {
                recent.add(search);
            }
        }
        return recent;
    }
    
    public void displaySearchHistory(String userId) {
        System.out.println("\n=== RIWAYAT PENCARIAN ===");
        boolean found = false;
        
        for (SearchKeyword search : searchHistory) {
            if (search.getUserId().equals(userId)) {
                System.out.println(search.getSearchSummary());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Belum ada riwayat pencarian.");
        }
        System.out.println("========================\n");
    }
}