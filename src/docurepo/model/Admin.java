package roles;

import java.util.Scanner;
import services.DocumentService;
import services.UserService;

public class Admin {

    public void menu(DocumentService ds, UserService us, Scanner sc) {

        while (true) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Upload Dokumen");
            System.out.println("2. Rename Dokumen");
            System.out.println("3. Ganti Isi Dokumen");
            System.out.println("4. Hapus Dokumen");
            System.out.println("5. Lihat Semua Dokumen");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");

            String pilih = sc.nextLine();

            switch (pilih) {

                case "1":
                    System.out.print("Masukkan nama dokumen: ");
                    String nama = sc.nextLine();
                    System.out.print("Masukkan isi dokumen: ");
                    String isi = sc.nextLine();
                    ds.uploadDocument(nama, isi);
                    break;

                case "2":
                    System.out.print("Masukkan nama dokumen yang ingin diubah: ");
                    String oldName = sc.nextLine();
                    System.out.print("Masukkan nama baru: ");
                    String newName = sc.nextLine();
                    ds.renameDocument(oldName, newName);
                    break;

                case "3":
                    System.out.print("Masukkan nama dokumen: ");
                    String docName = sc.nextLine();
                    System.out.print("Masukkan isi baru: ");
                    String newContent = sc.nextLine();
                    ds.updateDocument(docName, newContent);
                    break;

                case "4":
                    System.out.print("Masukkan nama dokumen yang ingin dihapus: ");
                    String deleteName = sc.nextLine();
                    ds.deleteDocument(deleteName);
                    break;

                case "5":
                    ds.showAllDocuments();
                    break;

                case "0":
                    System.out.println("Logout berhasil...\n");
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}