package docurepo.controller;

import docurepo.model.Document;
import java.net.URL;
import java.util.*;

public class DocumentController {
    private List<Document> documents = new ArrayList<>();

    public boolean addDocument(String name, URL url) {
        // Hanya terima .pdf atau .docx
        String ext = url.toString().toLowerCase();
        if (!ext.endsWith(".pdf") && !ext.endsWith(".docx")) {
            return false;
        }

        // Cek duplikat nama
        for (Document doc : documents) {
            if (doc.getName().equals(name)) {
                doc.addVersion(url);
                return true;
            }
        }

        documents.add(new Document(name, url));
        return true;
    }

    public List<Document> searchDocuments(String keyword) {
        List<Document> result = new ArrayList<>();
        for (Document doc : documents) {
            if (doc.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(doc);
            }
        }
        return result;
    }

    public List<Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }

    public boolean deleteDocument(String name) {
        return documents.removeIf(doc -> doc.getName().equals(name));
    }
}