import model.User;
import service.DocumentService;

public class Main {
    public static void main(String[] args) {

        User viewer = new User();
        DocumentService ds = new DocumentService();

        // dokumen dimasukkan oleh sistem (viewer tidak boleh upload)
        ds.addDocument("viewer", "laporan_akhir.pdf");
        ds.addDocument("viewer", "catatan.docx");
        ds.addDocument("editor", "draft_editor.pdf"); // viewer tidak boleh lihat ini

        // viewer melihat dokumen miliknya
        ds.showDocuments(viewer);

        // viewer mencari dokumen
        ds.search("lap");
    }
}