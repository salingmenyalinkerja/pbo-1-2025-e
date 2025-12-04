package view;

import java.util.ArrayList;
import model.Document;

public class EditorView {

    // Tampilkan semua dokumen
    public void displayDocuments(ArrayList<Document> docs) {
        System.out.println("=== Daftar Dokumen (Editor) ===");
        for (Document d : docs) {
            System.out.println(d.id + ". " + d.title);
        }
        System.out.println("================================");
    }

    // Tampilkan isi dokumen
    public void displayDetail(Document doc) {
        if (doc == null) {
            System.out.println("Dokumen tidak ditemukan.");
            return;
        }

        System.out.println("=== Detail Dokumen ===");
        System.out.println("Judul  : " + doc.title);
        System.out.println("Isi    : " + doc.content);
        System.out.println("========================");
    }

    // Notifikasi setelah update
    public void showUpdateSuccess() {
        System.out.println("✔ Dokumen berhasil diperbarui!");
    }
}