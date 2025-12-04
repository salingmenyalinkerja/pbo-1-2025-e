package docurepo.controller;

import docurepo.model.Document;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DocumentStorageController {

    private final String storagePath = "storage/";

    public DocumentStorageController() {
        File folder = new File(storagePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // Menyimpan dokumen
    public boolean saveDocument(Document doc) {
        try {
            FileOutputStream fos = new FileOutputStream(storagePath + doc.getFileName());
            fos.write(doc.getFileContent());
            fos.close();
            System.out.println("Dokumen berhasil disimpan.");
            return true;
        } catch (IOException e) {
            System.out.println("Gagal menyimpan: " + e.getMessage());
            return false;
        }
    }

    // Mengambil dokumen
    public Document loadDocument(String fileName) {
        try {
            File file = new File(storagePath + fileName);
            if (!file.exists()) {
                System.out.println("Dokumen tidak ditemukan.");
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] data = fis.readAllBytes();
            fis.close();
            return new Document(fileName, data);
        } catch (IOException e) {
            System.out.println("Gagal memuat: " + e.getMessage());
            return null;
        }
    }

    // Menghapus dokumen
    public boolean deleteDocument(String fileName) {
        File file = new File(storagePath + fileName);
        if (file.exists()) {
            boolean result = file.delete();
            if (result) System.out.println("Dokumen berhasil dihapus.");
            else System.out.println("Gagal menghapus dokumen.");
            return result;
        } else {
            System.out.println("Dokumen tidak ditemukan.");
            return false;
        }
    }

    // Update dokumen
    public boolean updateDocument(String fileName, Document newDoc) {
        deleteDocument(fileName);
        return saveDocument(newDoc);
    }
}
