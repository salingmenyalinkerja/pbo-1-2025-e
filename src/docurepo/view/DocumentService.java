package service;

import model.Document;
import model.User;

import java.util.ArrayList;

public class DocumentService {

    private ArrayList<Document> documents = new ArrayList<>();

    // menambahkan dokumen ke sistem (bukan viewer)
    public void addDocument(String owner, String fileName) {
        documents.add(new Document(owner, fileName));
    }

    // viewer melihat dokumen miliknya
    public void showDocuments(User user) {
        System.out.println("\nDokumen milik " + user.getUsername() + ":");
        for (Document d : documents) {
            if (d.getOwner().equals(user.getUsername())) {
                System.out.println("- " + d.getName());
            }
        }
    }

    // viewer hanya bisa mencari dokumen
    public void search(String keyword) {
        System.out.println("\nHasil pencarian: " + keyword);
        for (Document d : documents) {
            if (d.getName().contains(keyword)) {
                System.out.println("- " + d.getName() + " (pemilik: " + d.getOwner() + ")");
            }
        }
    }
}