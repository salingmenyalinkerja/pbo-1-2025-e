// DocumentViewCLI.java
// Command Line Interface Document Viewer untuk berbagai format dokumen

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Document Viewer dengan Command Line Interface
 * Mendukung: TXT, HTML, Java, XML, JSON, CSV, MD, dan format teks lainnya
 * 
 * @author Developer
 * @version 1.0
 */
public class DocumentViewCLI {
    
    // Konstanta untuk warna (jika terminal mendukung)
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";
    
    // Variabel global
    private static boolean useColors = true;
    private static boolean pagination = true;
    private static int linesPerPage = 20;
    private static String currentFilePath = "";
    private static List<String> searchResults = new ArrayList<>();
    private static int currentSearchIndex = 0;
    
    /**
     * Main method - Entry point aplikasi
     */
    public static void main(String[] args) {
        // Cek apakah terminal mendukung warna
        checkColorSupport();
        
        // Tampilkan banner
        showBanner();
        
        // Proses argumen command line
        if (args.length > 0) {
            processCommandLineArgs(args);
        } else {
            // Mode interaktif
            startInteractiveMode();
        }
    }
    
    /**
     * Cek dukungan warna terminal
     */
    private static void checkColorSupport() {
        try {
            // Cek OS dan environment
            String os = System.getProperty("os.name").toLowerCase();
            String term = System.getenv("TERM");
            
            // Non-Windows atau Windows dengan ANSI support
            if (!os.contains("win") || (term != null && !term.isEmpty())) {
                useColors = true;
            } else {
                useColors = false;
            }
        } catch (Exception e) {
            useColors = false;
        }
    }
    
    /**
     * Tampilkan banner aplikasi
     */
    private static void showBanner() {
        if (useColors) {
            System.out.println(CYAN + "╔══════════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║" + YELLOW + "          DOCUMENT VIEWER CLI v1.0           " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + GREEN + "     Command Line Document Viewer         " + CYAN + "║" + RESET);
            System.out.println(CYAN + "╚══════════════════════════════════════════════════╝" + RESET);
        } else {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║          DOCUMENT VIEWER CLI v1.0           ║");
            System.out.println("║     Command Line Document Viewer         ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
        }
        System.out.println();
    }
    
    /**
     * Proses argumen command line
     */
    private static void processCommandLineArgs(String[] args) {
        Map<String, String> options = parseArgs(args);
        
        // Help command
        if (options.containsKey("help") || options.containsKey("h")) {
            showHelp();
            return;
        }
        
        // Version command
        if (options.containsKey("version") || options.containsKey("v")) {
            showVersion();
            return;
        }
        
        // File untuk dibuka
        String filePath = options.get("file");
        String url = options.get("url");
        
        // Mode pencarian
        if (options.containsKey("search") || options.containsKey("s")) {
            String searchTerm = options.get("search") != null ? options.get("search") : options.get("s");
            if (filePath != null) {
                searchInFile(filePath, searchTerm, options.containsKey("case") || options.containsKey("c"));
            } else {
                System.out.println(colorize(RED, "Error: File harus ditentukan untuk pencarian"));
                showHelp();
            }
            return;
        }
        
        // Mode statistik
        if (options.containsKey("stats") || options.containsKey("stat")) {
            if (filePath != null) {
                showFileStats(filePath);
            } else {
                System.out.println(colorize(RED, "Error: File harus ditentukan untuk statistik"));
                showHelp();
            }
            return;
        }
        
        // Mode compare
        if (options.containsKey("compare")) {
            String file2 = options.get("compare");
            if (filePath != null && file2 != null) {
                compareFiles(filePath, file2);
            } else {
                System.out.println(colorize(RED, "Error: Dua file harus ditentukan untuk perbandingan"));
                showHelp();
            }
            return;
        }
        
        // Buka file atau URL
        if (filePath != null) {
            viewFile(filePath, options);
        } else if (url != null) {
            viewURL(url, options);
        } else if (args.length > 0 && !args[0].startsWith("-")) {
            // Jika argumen pertama adalah file tanpa flag
            viewFile(args[0], options);
        } else {
            // Tidak ada file atau URL, tampilkan help
            showHelp();
        }
    }
    
    /**
     * Parse argumen command line
     */
    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> options = new HashMap<>();
        
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            
            if (arg.startsWith("--")) {
                // Long option
                String key = arg.substring(2);
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    options.put(key, args[i + 1]);
                    i++;
                } else {
                    options.put(key, "");
                }
            } else if (arg.startsWith("-")) {
                // Short option
                String key = arg.substring(1);
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    options.put(key, args[i + 1]);
                    i++;
                } else {
                    options.put(key, "");
                }
            } else if (i == 0 && !options.containsKey("file")) {
                // File path tanpa flag
                options.put("file", arg);
            }
        }
        
        return options;
    }
    
    /**
     * Tampilkan file di command line
     */
    private static void viewFile(String filePath, Map<String, String> options) {
        try {
            File file = new File(filePath);
            
            if (!file.exists()) {
                System.out.println(colorize(RED, "Error: File tidak ditemukan: " + filePath));
                return;
            }
            
            if (file.isDirectory()) {
                listDirectory(filePath, options);
                return;
            }
            
            currentFilePath = filePath;
            
            // Cek format file
            String fileName = file.getName().toLowerCase();
            String fileType = getFileType(fileName);
            
            // Tampilkan info file
            System.out.println(colorize(CYAN, "┌─ File Information ──────────────────────────────"));
            System.out.println(colorize(CYAN, "│ Nama: ") + colorize(YELLOW, file.getName()));
            System.out.println(colorize(CYAN, "│ Path: ") + file.getAbsolutePath());
            System.out.println(colorize(CYAN, "│ Ukuran: ") + formatFileSize(file.length()));
            System.out.println(colorize(CYAN, "│ Tipe: ") + fileType);
            System.out.println(colorize(CYAN