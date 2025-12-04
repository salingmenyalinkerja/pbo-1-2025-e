package controller;

import model.EditorModel;
import model.Document;
import view.EditorView;

public class EditorController {

    private EditorModel model;
    private EditorView view;

    public EditorController(EditorModel model, EditorView view) {
        this.model = model;
        this.view = view;
    }

    // tampilkan daftar dokumen
    public void showDocuments() {
        view.displayDocuments(model.getAll());
    }

    // tampilkan detail dokumen
    public void openDocument(int id) {
        Document doc = model.getById(id);
        view.displayDetail(doc);
    }

    // edit dokumen melalui view
    public void editDocument(int id, String newTitle, String newContent) {
        model.update(id, newTitle, newContent);
        view.showUpdateSuccess();
    }
}