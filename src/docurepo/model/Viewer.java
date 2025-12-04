package docurepo.model;

public class Viewer extends User {
    public static void main(String[] args){
        System.out.println("=====DEBUGGING=====");
    }
    public Viewer(String username, String password) {
        super(username, password);
    }

    // public void menu(DocumentService ds, Scanner sc) {

    //     while (true) {
    //         System.out.println("\n=== MENU VIEWER ===");
    //         System.out.println("1. Lihat Semua Dokumen");
    //         System.out.println("2. Cari Dokumen");
    //         System.out.println("0. Logout");
    //         System.out.print("Pilih: ");
    //         String pilih = sc.nextLine();

    //         switch (pilih) {

    //             case "1":
    //                 System.out.println("\n--- DAFTAR SEMUA DOKUMEN ---");
    //                 ds.showAllDocuments();
    //                 break;

    //             case "2":
    //                 System.out.print("Masukkan kata kunci: ");
    //                 String keyword = sc.nextLine();
    //                 ds.searchDocument(keyword);
    //                 break;

    //             case "0":
    //                 System.out.println("Logout berhasil...\n");
    //                 return;

    //             default:
    //                 System.out.println("Pilihan tidak valid!");
    //         }
    //     }
    // }
}