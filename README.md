# Tugas Akhir PBO I Kelas E, Tahun 2025

## Struktur Repository
```
pbo-1-2025-e
├── src/
│   ├── docurepo/
│   │   ├── controller/
│   │   ├── model/
│   │   └── view/
│   └── main/
├── docs/
│   └── UML_Diagram.png
├── .gitignore
└── README.md
```

## Fitur Aplikasi
- Program memiliki fungsi login, dimana pengguna perlu memasukkan nama dan password untuk menggunakan aplikasi.
- Program membagi pengguna ke dalam beberapa role, yaitu:
  - Admin
  - Editor
  - Viewer
- Admin program dapat menambahkan pengguna, mengatur hak akses pengguna, dan memanipulasi dokumen program.
- Editor program hanya dapat memanipulasi dokumen program.
- Viewer program hanya dapat mencari dokumen. 
- Program dapat menerima dokumen yang di-upload pengguna menggunakan url.
- Program hanya dapat menerima berkas .docx dan .pdf.
- Program akan memperbaharui dokumen miliknya bila nama berkas yang ingin di-upload sama dengan dokumen tersebut.
- Program mampu mengubah dokumen yang ada menjadi versi terdahulu dokumen tersebut.
- Program dapat menghapus dokumen yang dimilikinya.
- Program dapat memperlihatkan berkas-berkas dokumen yang dimilikinya.
- Program dapat mencarikan dokumen dengan kata kunci yang dimasukkan pengguna.