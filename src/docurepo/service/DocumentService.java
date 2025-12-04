package docurepo.service;

import docurepo.model.Document;
import docurepo.model.User;
import java.util.ArrayList;

public class DocumentService {

    private ArrayList<Document> documents = new ArrayList<>();

    // menambahkan dokumen ke sistem (bukan viewer)
    public void addDocument(String owner, String fileName) {
        documents.add(new Document(owner, fileName));
    }

    // viewer melihat dokumen miliknya
    public void showDocuments(User user) {
        System.out.println("\nDokumen milik " + user.GetUsername() + ":");
        for (Document d : documents) {
            if (d.GetOwner().equals(user.GetUsername())) {
                System.out.println("- " + d.GetName());
            }
        }
    }

    // viewer hanya bisa mencari dokumen
    public void search(String keyword) {
        System.out.println("\nHasil pencarian: " + keyword);
        for (Document d : documents) {
            if (d.GetName().contains(keyword)) {
                System.out.println("- " + d.GetName() + " (pemilik: " + d.GetOwner() + ")");
            }
        }
    }
}