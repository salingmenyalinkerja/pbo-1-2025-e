package model;

public class DOCXDocument extends Document {

    public DOCXDocument(String title, String content){
        super(title, content);
    }

    @Override
    public void open(){
        System.out.println("Opening DOCX: " + title);
    }

    @Override
    public void save(){
        System.out.println("Saving DOCX file...");
    }
}
