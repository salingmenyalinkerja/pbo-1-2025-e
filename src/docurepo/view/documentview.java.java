import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DocumentView extends JFrame {
    
    // Komponen UI
    private JTextArea textArea;
    private JEditorPane htmlPane;
    private JLabel imageLabel;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private CardLayout cardLayout;
    
    // Menu bar components
    private JMenuBar menuBar;
    private JMenu fileMenu, viewMenu, helpMenu;
    private JMenuItem openItem, saveItem, exitItem;
    private JMenuItem zoomInItem, zoomOutItem, resetZoomItem;
    private JMenuItem aboutItem;
    
    // Status bar
    private JLabel statusLabel;
    
    // Variabel untuk file saat ini
    private File currentFile;
    private String currentFilePath;
    private double zoomLevel = 1.0;
    
    // Konstanta
    private static final String APP_NAME = "Document Viewer";
    private static final String VERSION = "1.0";
    
    /**
     * Konstruktor utama
     */
    public DocumentView() {
        initializeUI();
        setupMenuBar();
        setupEventListeners();
        showWelcomeMessage();
    }
    
    /**
     * Inisialisasi antarmuka pengguna
     */
    private void initializeUI() {
        setTitle(APP_NAME + " v" + VERSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center window
        
        // Setup card layout untuk berbagai jenis viewer
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Text area untuk file teks biasa
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane textScrollPane = new JScrollPane(textArea);
        mainPanel.add(textScrollPane, "TEXT");
        
        // Editor pane untuk HTML
        htmlPane = new JEditorPane();
        htmlPane.setContentType("text/html");
        htmlPane.setEditable(false);
        
        JScrollPane htmlScrollPane = new JScrollPane(htmlPane);
        mainPanel.add(htmlScrollPane, "HTML");
        
        // Label untuk gambar
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JScrollPane imageScrollPane = new JScrollPane(imageLabel);
        mainPanel.add(imageScrollPane, "IMAGE");
        
        // Panel untuk format tidak didukung
        JPanel unsupportedPanel = new JPanel(new BorderLayout());
        JLabel unsupportedLabel = new JLabel("Format file tidak didukung untuk preview", JLabel.CENTER);
        unsupportedLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        unsupportedPanel.add(unsupportedLabel, BorderLayout.CENTER);
        mainPanel.add(unsupportedPanel, "UNSUPPORTED");
        
        // Status bar
        statusLabel = new JLabel("Siap");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        
        // Setup layout utama
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        // Tabbed pane untuk multiple documents (opsional)
        tabbedPane = new JTabbedPane();
        // add(tabbedPane, BorderLayout.CENTER); // Uncomment untuk multi-tab
        
        // Apply look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting look and feel: " + e.getMessage());
        }
    }
    
    /**
     * Setup menu bar
     */
    private void setupMenuBar() {
        menuBar = new JMenuBar();
        
        // File Menu
        fileMenu = new JMenu("File");
        
        openItem = new JMenuItem("Buka...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        
        saveItem = new JMenuItem("Simpan");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        
        exitItem = new JMenuItem("Keluar");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // View Menu
        viewMenu = new JMenu("View");
        
        zoomInItem = new JMenuItem("Zoom In");
        zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_DOWN_MASK));
        
        zoomOutItem = new JMenuItem("Zoom Out");
        zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK));
        
        resetZoomItem = new JMenuItem("Reset Zoom");
        resetZoomItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.CTRL_DOWN_MASK));
        
        viewMenu.add(zoomInItem);
        viewMenu.add(zoomOutItem);
        viewMenu.add(resetZoomItem);
        
        // Help Menu
        helpMenu = new JMenu("Bantuan");
        
        aboutItem = new JMenuItem("Tentang");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    /**
     * Setup event listeners
     */
    private void setupEventListeners() {
        // File menu events
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> System.exit(0));
        
        // View menu events
        zoomInItem.addActionListener(e -> zoomIn());
        zoomOutItem.addActionListener(e -> zoomOut());
        resetZoomItem.addActionListener(e -> resetZoom());
        
        // Help menu events
        aboutItem.addActionListener(e -> showAboutDialog());
        
        // Window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }
    
    /**
     * Tampilkan pesan selamat datang
     */
    private void showWelcomeMessage() {
        textArea.setText("Selamat datang di Document Viewer v" + VERSION + "\n\n" +
                        "Fitur:\n" +
                        "1. Membuka file teks (.txt, .java, .html, dll)\n" +
                        "2. Preview HTML dasar\n" +
                        "3. Menampilkan gambar (.jpg, .png, .gif)\n" +
                        "4. Zoom in/out untuk teks\n" +
                        "5. Simpan perubahan\n\n" +
                        "Gunakan menu File -> Buka untuk membuka file.");
        cardLayout.show(mainPanel, "TEXT");
        statusLabel.setText("Aplikasi siap digunakan");
    }
    
    /**
     * Buka file dialog
     */
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih file untuk dibuka");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Filter file
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                
                String name = f.getName().toLowerCase();
                return name.endsWith(".txt") || name.endsWith(".java") || 
                       name.endsWith(".html") || name.endsWith(".htm") ||
                       name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                       name.endsWith(".png") || name.endsWith(".gif") ||
                       name.endsWith(".xml") || name.endsWith(".json") ||
                       name.endsWith(".csv") || name.endsWith(".md");
            }
            
            @Override
            public String getDescription() {
                return "File dokumen (*.txt, *.java, *.html, *.jpg, *.png, *.gif, *.xml, *.json, *.csv, *.md)";
            }
        });
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            currentFilePath = currentFile.getAbsolutePath();
            loadFile(currentFile);
        }
    }
    
    /**
     * Muat file ke viewer
     */
    private void loadFile(File file) {
        try {
            String fileName = file.getName().toLowerCase();
            
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || 
                fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                loadImage(file);
            } else if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
                loadHTML(file);
            } else if (fileName.endsWith(".txt") || fileName.endsWith(".java") ||
                      fileName.endsWith(".xml") || fileName.endsWith(".json") ||
                      fileName.endsWith(".csv") || fileName.endsWith(".md")) {
                loadTextFile(file);
            } else {
                // Format tidak didukung
                cardLayout.show(mainPanel, "UNSUPPORTED");
                statusLabel.setText("Format file tidak didukung untuk preview: " + file.getName());
            }
            
            setTitle(APP_NAME + " v" + VERSION + " - " + file.getName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error membaca file: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error membaca file: " + e.getMessage());
        }
    }
    
    /**
     * Muat file teks
     */
    private void loadTextFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        
        textArea.setText(content.toString());
        cardLayout.show(mainPanel, "TEXT");
        statusLabel.setText("File teks dimuat: " + file.getName() + " (" + file.length() + " bytes)");
    }
    
    /**
     * Muat file HTML
     */
    private void loadHTML(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        
        htmlPane.setText(content.toString());
        cardLayout.show(mainPanel, "HTML");
        statusLabel.setText("File HTML dimuat: " + file.getName());
    }
    
    /**
     * Muat gambar
     */
    private void loadImage(File file) {
        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
        
        // Scale image jika terlalu besar
        Image image = imageIcon.getImage();
        if (image.getWidth(null) > 800 || image.getHeight(null) > 600) {
            Image scaledImage = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
        }
        
        imageLabel.setIcon(imageIcon);
        cardLayout.show(mainPanel, "IMAGE");
        statusLabel.setText("Gambar dimuat: " + file.getName() + 
                          " (" + image.getWidth(null) + "x" + image.getHeight(null) + ")");
    }
    
    /**
     * Simpan file
     */
    private void saveFile() {
        if (currentFile == null) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada file yang aktif untuk disimpan", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            // Tergantung viewer mana yang aktif
            String currentView = getCurrentView();
            
            if ("TEXT".equals(currentView)) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
                writer.write(textArea.getText());
                writer.close();
                statusLabel.setText("File berhasil disimpan: " + currentFile.getName());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else if ("HTML".equals(currentView)) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
                writer.write(htmlPane.getText());
                writer.close();
                statusLabel.setText("File berhasil disimpan: " + currentFile.getName());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "File ini tidak dapat disimpan (hanya teks dan HTML yang dapat diedit)", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error menyimpan file: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error menyimpan file: " + e.getMessage());
        }
    }
    
    /**
     * Dapatkan viewer yang sedang aktif
     */
    private String getCurrentView() {
        // Cara sederhana untuk mendapatkan viewer aktif
        Component current = ((CardLayout) mainPanel.getLayout()).getCurrentCard(mainPanel);
        
        if (current instanceof JScrollPane) {
            Component view = ((JScrollPane) current).getViewport().getView();
            if (view instanceof JTextArea) return "TEXT";
            if (view instanceof JEditorPane) return "HTML";
            if (view instanceof JLabel) return "IMAGE";
        }
        
        return "UNKNOWN";
    }
    
    /**
     * Zoom in untuk teks
     */
    private void zoomIn() {
        if ("TEXT".equals(getCurrentView())) {
            zoomLevel += 0.1;
            Font currentFont = textArea.getFont();
            Font newFont = currentFont.deriveFont((float)(currentFont.getSize() * zoomLevel));
            textArea.setFont(newFont);
            statusLabel.setText("Zoom: " + String.format("%.0f%%", zoomLevel * 100));
        }
    }
    
    /**
     * Zoom out untuk teks
     */
    private void zoomOut() {
        if ("TEXT".equals(getCurrentView()) && zoomLevel > 0.2) {
            zoomLevel -= 0.1;
            Font currentFont = textArea.getFont();
            Font newFont = currentFont.deriveFont((float)(currentFont.getSize() * zoomLevel));
            textArea.setFont(newFont);
            statusLabel.setText("Zoom: " + String.format("%.0f%%", zoomLevel * 100));
        }
    }
    
    /**
     * Reset zoom
     */
    private void resetZoom() {
        if ("TEXT".equals(getCurrentView())) {
            zoomLevel = 1.0;
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            statusLabel.setText("Zoom direset ke 100%");
        }
    }
    
    /**
     * Tampilkan dialog tentang aplikasi
     */
    private void showAboutDialog() {
        String aboutMessage = APP_NAME + " v" + VERSION + "\n\n" +
                            "Aplikasi penampil dokumen sederhana\n" +
                            "Dibuat dengan Java Swing\n\n" +
                            "Fitur:\n" +
                            "• Membuka file teks, HTML, gambar\n" +
                            "• Edit dan simpan file teks\n" +
                            "• Zoom in/out untuk teks\n" +
                            "• Antarmuka yang user-friendly\n\n" +
                            "© 2023 Document Viewer";
        
        JOptionPane.showMessageDialog(this, aboutMessage, "Tentang " + APP_NAME, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Konfirmasi keluar
     */
    private void confirmExit() {
        int result = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin keluar?",
            "Konfirmasi Keluar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Metode utama untuk menjalankan aplikasi
     */
    public static void main(String[] args) {
        // Jalankan di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            DocumentView viewer = new DocumentView();
            viewer.setVisible(true);
            
            // Jika ada argumen command line, buka file tersebut
            if (args.length > 0) {
                File file = new File(args[0]);
                if (file.exists()) {
                    viewer.loadFile(file);
                }
            }
        });
    }
    
    // Method tambahan untuk GitHub compatibility
    /**
     * Baca konten dari URL (untuk file di GitHub)
     */
    public String readFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        
        return content.toString();
    }
    
    /**
     * Tampilkan konten dari URL GitHub
     */
    public void displayGitHubContent(String url) {
        try {
            String content = readFromURL(url);
            textArea.setText(content);
            cardLayout.show(mainPanel, "TEXT");
            statusLabel.setText("Konten dari GitHub dimuat: " + url);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error membaca dari URL: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error membaca dari URL: " + e.getMessage());
        }
    }
}

/**
 * Kelas utilitas tambahan untuk DocumentView
 */
class DocumentUtils {
    
    /**
     * Dapatkan ekstensi file
     */
    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
    
    /**
     * Cek apakah file adalah gambar
     */
    public static boolean isImageFile(String fileName) {
        String ext = getFileExtension(fileName);
        return ext.equals("jpg") || ext.equals("jpeg") || 
               ext.equals("png") || ext.equals("gif") || 
               ext.equals("bmp");
    }
    
    /**
     * Cek apakah file adalah HTML
     */
    public static boolean isHTMLFile(String fileName) {
        String ext = getFileExtension(fileName);
        return ext.equals("html") || ext.equals("htm");
    }
    
    /**
     * Cek apakah file adalah teks
     */
    public static boolean isTextFile(String fileName) {
        String ext = getFileExtension(fileName);
        return ext.equals("txt") || ext.equals("java") || 
               ext.equals("xml") || ext.equals("json") || 
               ext.equals("csv") || ext.equals("md");
    }
}