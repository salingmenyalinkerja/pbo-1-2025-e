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

## Pembagian Tugas

### Controller

#### Kelas
- DocumentListingController
  - 021240117	SITI NURJANNAH MUTMAINNAH
  - 021240023	BERNADETE NUNU HERIN
- DocumentSearchController
  - 021240118	SUCI HANDAYANI
  - 021240010	ANGELIANA DINDA MORENA
- DocumentStorageController
  - 021240025	CICILIA ROSARI NONI
  - 021240071	KONSOLATA DUA KILAN
- DocumentVersioningController
  - 021240146	MARIO JUAN DANIELO
  - 021240035	EMANUEL DELON NARO
- AdminController
  - 021240005	ALEXANDRA YESICA YOHAN
  - 021240046	FRANSISKA BELA BALSOMANG
- EditorController
  - 021240114	RISKA ISTIQOMA
  - 021240009	ANASTASYA WINDA SEPTIANI
- ViewerController
  - 021240026	CLARITA DEWI FORTUNATA
  - 021240033	EFNER MARCHELO DARA

### Model

#### Kelas
- Document
  - 021240023	BERNADETE NUNU HERIN
- DOCXDocument
  - 021240010	ANGELIANA DINDA MORENA
- PDFDocument
  - 021240010	ANGELIANA DINDA MORENA
- User
  - 021240005	ALEXANDRA YESICA YOHAN
- Admin
  - 021240046	FRANSISKA BELA BALSOMANG
- Editor
  - 021240009	ANASTASYA WINDA SEPTIANI
- Viewer
  - 021240033	EFNER MARCHELO DARA
- SearchKeyword
  - 021240148	MARIA CHORMELIA IRENSIA DE
  - 021240075	MARIA FLORENTINA MONE

### View

#### Kelas
- CommandLineInterface
  - 021240124	WILHELMUS PEDE
  - 021240008	ANASTASIA NONA ICA
  - 021240147	FRIDOLIN SALFARES MADO
  - 021240134	YOLANDA GRASELA DUA BURA
  - 021240075	MARIA FLORENTINA MONE
- DocumentView
  - 021240124	WILHELMUS PEDE
  - 021240008	ANASTASIA NONA ICA
- SearchDocumentView
  - 021240147	FRIDOLIN SALFARES MADO
  - 021240134	YOLANDA GRASELA DUA BURA
- AdminView
  - 021240005	ALEXANDRA YESICA YOHAN
  - 021240046	FRANSISKA BELA BALSOMANG
- EditorView
  - 021240114	RISKA ISTIQOMA
  - 021240009	ANASTASYA WINDA SEPTIANI
- ViewerView
  - 021240026	CLARITA DEWI FORTUNATA
  - 021240033	EFNER MARCHELO DARA

## Berkas Utilitas

### src/build.ps1
- Menghapus semua hasil *compile java* dan menjalankan kembali *compile java*
- Dipakai dengan menjalankan `powershell -ExecutionPolicy Bypass -File build.ps1`

### src/clean.ps1
- Menghapus semua hasil *compile java*
- Dipakai dengan menjalankan `powershell -ExecutionPolicy Bypass -File clean.ps1`