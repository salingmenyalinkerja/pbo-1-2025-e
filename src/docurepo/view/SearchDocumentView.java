import java.util.List;
import java.util.Scanner;

public class SearchDocumentView {
    private DocumentRepository repository;

    public SearchDocumentView(DocumentRepository repository) {
        this.repository = repository;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan kata kunci pencarian: ");
        String keyword = scanner.nextLine();

        List<Document> results = repository.searchDocuments(keyword);

        System.out.println("\n=== Hasil Pencarian ===");
        if (results.isEmpty()) {
            System.out.println("Tidak ada dokumen ditemukan.");
        } else {
            for (Document doc : results) {
                doc.display();
            }
        }
    }
}

   
