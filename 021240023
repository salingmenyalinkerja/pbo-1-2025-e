package model;

public abstract class Document {
    protected String title;
    protected String content;

    public Document(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle(){ return title; }
    public String getContent(){ return content; }

    public abstract void open();
    public abstract void save();

    public String getInfo(){
        return "Title: " + title;
    }
}
