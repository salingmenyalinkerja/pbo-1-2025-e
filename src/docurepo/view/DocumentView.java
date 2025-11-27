package docurepo.view;

import docurepo.model.Document;

import java.util.List;

public class DocumentView {

    public void showList(List<Document> docs) {
        System.out.println("=== List Dokumen ===");
        for (Document d : docs) {
            System.out.println(d);
        }
    }
}
