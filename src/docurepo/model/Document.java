package docurepo.model;

public class Document {
    
    protected String owner;
    protected String name;
    protected int version;

    public Document(String owner, String name){
        SetOwner(owner);
        SetName(name);
        SetVersion(1);
    }

    public void SetOwner(String owner){
        this.owner = owner;
    }

    public void SetName(String name){
        this.name = name;
    }

    public void SetVersion(int version){
        this.version = version;
    }

    public String GetOwner(){
        return owner;
    }

    public String GetName(){
        return name;
    }

    public int GetVersion(){
        return version;
    }
}