import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static LinkedList daftarUser = new LinkedList();
    static LinkedListDokter daftarDokter = new LinkedListDokter();
    static AntrianQueue antrianKonsultasi = new AntrianQueue();
    static Stack riwayatRekomendasi = new Stack();
    static Tree kategoriMakanan = new Tree();

    public static void main(String[] args) {
        daftarDokter.tambahDokter("Andre", "123");
        daftarDokter.tambahDokter("Bromo", "123");
        daftarDokter.tambahDokter("Chely", "123");
        while (true) {
            clearScreen();
            header();
            tampilkanMenuUtama();
            int pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1:
                    signIn();
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan FHealth. Keluar...");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    

    static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void header() {
        String blue = "\033[1;36m"; 
        String reset = "\033[0m";   
        
        System.out.println(blue + 
            "######## ##       ## ########  #########  ##   ############ ##      ##\n" +
            "##       ##       ## ##       ##       ## ##        ##      ##      ##\n" +
            "##       ##       ## ##       ##       ## ##        ##      ##      ##\n" +
            "#######  ########### #######  ########### ##        ##      ##########\n" +
            "##       ##       ## ##       ##       ## ##        ##      ##      ##\n" +
            "##       ##       ## ##       ##       ## ##        ##      ##      ##\n" +
            "##       ##       ## ######## ##       ## ########  ##      ##      ##  " 
        + reset);
    }

    static void tampilkanMenuUtama() {
        System.out.println("\n+====================================================================+");
        System.out.println("|                SELAMAT DATANG PADA APLIKASI FHEALTH                |");
        System.out.println("+====================================================================+");
        System.out.println("|1. Sign In                                                          |");
        System.out.println("|2. Log In                                                       |");
        System.out.println("|3. Exit                                                             |");
        System.out.println("+====================================================================+");
        System.out.print("Pilih menu: ");
    }

    static void signIn() {
        clearScreen();
        System.out.println("\n+==============================+");
        System.out.println("|          SIGN IN             |");
        System.out.println("+==============================+");
        System.out.print("Masukkan Username: ");
        String username = input.nextLine();
        System.out.print("Masukkan Password: ");
        String password = input.nextLine();

        daftarUser.tambahUser(username, password);
        System.out.println("Akun berhasil dibuat!");
        input.nextLine();
    }

    static void logIn() {
        clearScreen();
        System.out.println("\n+==============================+");
        System.out.println("|          LOG IN              |");
        System.out.println("+==============================+");
        System.out.print("Masukkan Username: ");
        String username = input.nextLine();
        System.out.print("Masukkan Password: ");
        String password = input.nextLine();
    
        if ((username.equals("Andre") || username.equals("Bromo") || username.equals("Chely")) && password.equals("123")) {
            System.out.println("Login Berhasil sebagai Dokter: " + username);
            input.nextLine();
            menuDokter(username);
        }

        else if (daftarUser.validasiLogin(username, password)) {
            System.out.println("Login Berhasil! Selamat datang, " + username + ".");
            input.nextLine();
            menuUser(username); 
        } else {
            System.out.println("Login Gagal! Username atau Password salah.");
            input.nextLine();
        }
    }
    

    static void menuUser(String username) {
        while (true) {
            clearScreen();
            System.out.println("\n+==============================+");
            System.out.println("|          MENU USER           |");
            System.out.println("+==============================+");
            System.out.println("1. Konsultasi Makanan");
            System.out.println("2. Cek Riwayat Rekomendasi");
            System.out.println("3. Cek Kategori Makanan");
            System.out.println("4. Cek Antrian");
            System.out.println("5. Log Out");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    konsultasiMakanan(username);
                    break;
                case 2:
                    riwayatRekomendasi.display();
                    input.nextLine();
                    break;
                case 3:
                    kategoriMakanan.tampilkanKategori();
                    input.nextLine();
                    break;
                case 4:
                clearScreen();
                antrianKonsultasi.cekAntrian();
                input.nextLine();
                break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    static void konsultasiMakanan(String username) {
        clearScreen();
        System.out.println("\n+==============================+");
        System.out.println("|   JADWAL KONSULTASI DOKTER   |");
        System.out.println("+==============================+");
        System.out.println("Pilih Dokter untuk Konsultasi:");
        System.out.println("1. Dr. Andre (08:00 - 10:00)");
        System.out.println("2. Dr. Bromo (10:00 - 12:00)");
        System.out.println("3. Dr. Chely (13:00 - 15:00)");
        System.out.print("Masukkan pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine();

        String dokter = "";
        switch (pilihan) {
            case 1:
                dokter = "Dr. Andre";
                break;
            case 2:
                dokter = "Dr. Bromo";
                break;
            case 3:
                dokter = "Dr. Chely";
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        antrianKonsultasi.enqueue(username, dokter);
        input.nextLine();
    }

    static void konsultasiDanRekomendasi(String namaDokter) {
        clearScreen();
        System.out.println("\n+==============================+");
        System.out.println("|     KONSULTASI & REKOMENDASI |");
        System.out.println("+==============================+");
        System.out.print("Masukkan rekomendasi makanan untuk pasien: ");
        String rekomendasi = input.nextLine();
    
        riwayatRekomendasi.push("Dokter " + namaDokter + ": " + rekomendasi);
        System.out.println("Rekomendasi berhasil disimpan.");
        input.nextLine();
    }
    

    static void menuDokter(String namaDokter) {
        while (true) {
            clearScreen();
            System.out.println("\n+==============================+");
            System.out.println("|         MENU DOKTER          |");
            System.out.println("+==============================+");
            System.out.println("1. Ambil Antrian");
            System.out.println("2. Konsultasi ");
            System.out.println("3. Tambah Kategori Makanan");
            System.out.println("4. Log Out");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();
    
            switch (pilihan) {
                case 1:
                    ambilAntrianDokter(namaDokter);
                    break;
                case 2:
                    konsultasiDanRekomendasi(namaDokter);
                    break;
                case 3:
                tambahKategoriMakanan(kategoriMakanan, input);
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }


    static void tambahKategoriMakanan(Tree kategoriMakanan, Scanner input) {
        boolean lanjutMenambah = true; 
        
        while (lanjutMenambah) {
            System.out.print("Masukkan nama kategori baru: ");
            String kategoriBaru = input.nextLine(); 
            kategoriMakanan.tambahKategori(kategoriBaru); 
            System.out.println("Kategori baru berhasil ditambahkan.");
        
            System.out.print("Apakah Anda ingin menambahkan subkategori? (y/n): ");
            String pilihan = input.nextLine(); 
        
            if (pilihan.equalsIgnoreCase("y||Y")) {
                System.out.print("Masukkan kategori induk untuk subkategori: ");
                String parentKategori = input.nextLine(); 
        
                System.out.print("Masukkan nama subkategori: ");
                String subKategori = input.nextLine();
        
                kategoriMakanan.tambahSubKategori(parentKategori, subKategori); 
        
                System.out.println("Subkategori berhasil ditambahkan.");
            } else {
                System.out.println("Subkategori tidak ditambahkan.");
            }
            input.nextLine();
            System.out.print("Apakah Anda ingin menambahkan kategori lagi? (y/n): ");
            String tambahLagi = input.nextLine();
            
            if (!tambahLagi.equalsIgnoreCase("y || Y")) {
                lanjutMenambah = false; 
                System.out.println("Proses penambahan kategori selesai.");
            }
        }
    }
    
    static void ambilAntrianDokter(String namaDokter) {
        System.out.println("\n+==============================+");
        System.out.println("|         AMBIL ANTRIAN        |");
        System.out.println("+==============================+");
        if (antrianKonsultasi.isEmpty()) {
            System.out.println("Tidak ada antrian untuk dokter " + namaDokter + ".");
        } else {
            String pengguna = antrianKonsultasi.dequeue();
            System.out.println("Pengguna " + pengguna + " sedang dilayani oleh dokter " + namaDokter + ".");
        }
        input.nextLine();
    }
}
