package model;

public class PDFDocument extends Document {

    public PDFDocument(String title, String content){
        super(title, content);
    }

    @Override
    public void open(){
        System.out.println("Opening PDF: " + title);
    }

    @Override
    public void save(){
        System.out.println("Exporting as PDF...");
    }
}
