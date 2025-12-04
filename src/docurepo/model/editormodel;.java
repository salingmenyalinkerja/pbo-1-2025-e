package model;

import java.util.ArrayList;

public class EditorModel {

    private ArrayList<Document> docs = new ArrayList<>();

    public EditorModel() {
        docs.add(new Document(1, "Laporan Mingguan", "Isi laporan..."));
        docs.add(new Document(2, "Proposal", "Isi proposal..."));
    }

    public ArrayList<Document> getAll() {
        return docs;
    }

    public Document getById(int id) {
        for (Document d : docs) {
            if (d.id == id) return d;
        }
        return null;
    }

    public void update(int id, String newTitle, String newContent) {
        Document d = getById(id);
        if (d != null) {
            d.title = newTitle;
            d.content = newContent;
        }
    }
}