package docurepo.model;

public class User {
    private String nama;
    private String nim;
    private String role;

    public User() {
        // Contoh default user dengan NIM 021240005
        this.nama = "User Contoh";
        this.nim = "021240005";
        this.role = "Viewer";
    }

    public User(String nama, String nim, String role) {
        this.nama = nama;
        this.nim = nim;
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User {" +
                "nama='" + nama + '\'' +
                ", nim='" + nim + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
