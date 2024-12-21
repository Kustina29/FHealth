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
        daftarDokter.tambahDokter("Sista", "123");
        daftarDokter.tambahDokter("Hyuna", "123");
        daftarDokter.tambahDokter("Fatur", "123");
        daftarDokter.tambahDokter("Mutia", "123");
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
        System.out.println("|           Selamat Datang di FHealth, Solusi Sehat Anda!            |");
        System.out.println("+====================================================================+");
        System.out.println("|1. Sign In                                                          |");
        System.out.println("|2. Log In                                                           |");
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
            System.out.println("\n+===============================================================+");
            System.out.println("|                            MENU USER                          |");
            System.out.println("+===============================================================+");
            System.out.println("1. Konsultasi Makanan");
            System.out.println("2. Cek Riwayat Rekomendasi");
            System.out.println("3. Cek Kategori Makanan");
            System.out.println("4. Cek Antrian");
            System.out.println("5. Daftar Dokter");
            System.out.println("6. Log Out");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    konsultasiMakanan(username);
                    break;
                case 2:
                    clearScreen();
                    riwayatRekomendasi.display();
                    input.nextLine();
                    break;
                case 3:
                    clearScreen();
                    kategoriMakanan.tampilkanKategori();
                    input.nextLine();
                    break;
                case 4:
                clearScreen();
                antrianKonsultasi.cekAntrian();
                input.nextLine();
                break;
                case 5:
                clearScreen();
                SortingDanSearch.bubbleSort(daftarDokter);
                SortingDanSearch.printDokter(daftarDokter);
                System.out.print("Masukkan nama dokter yang ingin dicari: ");
                String namaDicari = input.nextLine(); 
                SortingDanSearch.Search(daftarDokter, namaDicari);
                input.nextLine();
                break;
                case 6:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    static void konsultasiMakanan(String username) {
        clearScreen();
        System.out.println("\n+===============================================================+");
        System.out.println("|                    JADWAL KONSULTASI DOKTER                   |");
        System.out.println("+===============================================================+");
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
                dokter = "Andre";
                break;
            case 2:
                dokter = "Bromo";
                break;
            case 3:
                dokter = "Chely";
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }
    
        System.out.print("Masukkan keluhan Anda: ");
        String keluhan = input.nextLine();
    
        antrianKonsultasi.enqueue(username, dokter, keluhan);
        System.out.println("Anda telah berhasil mendaftar untuk konsultasi dengan Dr. " + dokter + ".");
        input.nextLine();
    }
    

    static void konsultasiDanRekomendasi(String namaDokter) {
        clearScreen();
        System.out.println("\n+===============================================================+");
        System.out.println("|                  KONSULTASI & REKOMENDASI                     |");
        System.out.println("+===============================================================+");
        System.out.print("Masukkan rekomendasi & saran untuk pasien: ");
        String rekomendasi = input.nextLine();
    
        riwayatRekomendasi.push("Dokter " + namaDokter + ": " + rekomendasi);
        System.out.println("Rekomendasi berhasil disimpan.");
        input.nextLine();
    }
    

    static void menuDokter(String namaDokter) {
        while (true) {
            clearScreen();
            System.out.println("\n+===============================================================+");
            System.out.println("|                            MENU DOKTER                        |");
            System.out.println("+===============================================================+");
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
                clearScreen();
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
        System.out.println("\n+===============================================================+");
        System.out.println("|                       INPUT SARAN NUTRISI                     |");
        System.out.println("+===============================================================+");
        while (lanjutMenambah) {
            System.out.print("Masukkan nama kategori baru: ");
            String kategoriBaru = input.nextLine();
            kategoriMakanan.tambahKategori(kategoriBaru);
            System.out.println("Kategori baru berhasil ditambahkan.");
    
            System.out.print("Apakah Anda ingin menambahkan makanan ke kategori ini? (y/n): ");
            String pilihan = input.nextLine();
    
            if (pilihan.equalsIgnoreCase("y")) {
                boolean lanjutMakanan = true;
                while (lanjutMakanan) {
                    System.out.print("Masukkan nama makanan: ");
                    String makanan = input.nextLine();
                    kategoriMakanan.tambahMakananKeKategori(kategoriBaru, makanan);
                    System.out.println("Makanan berhasil ditambahkan ke kategori " + kategoriBaru);
    
                    System.out.print("Apakah Anda ingin menambahkan makanan lagi ke kategori ini? (y/n): ");
                    String tambahLagiMakanan = input.nextLine();
                    if (tambahLagiMakanan.equalsIgnoreCase("n")) {
                        lanjutMakanan = false;
                        System.out.println("Proses penambahan makanan selesai.");
                    }
                }
            } else if (pilihan.equalsIgnoreCase("n")) {
                System.out.println("Tidak ada makanan yang ditambahkan ke kategori " + kategoriBaru);
            }
    
            System.out.print("Apakah Anda ingin menambahkan kategori lagi? (y/n): ");
            String tambahLagiKategori = input.nextLine();
    
            if (!tambahLagiKategori.equalsIgnoreCase("y")) {
                lanjutMenambah = false;
                System.out.println("Proses penambahan kategori selesai.");
            }
        }
    }
    
    
    static void ambilAntrianDokter(String namaDokter) {
        if (antrianKonsultasi.isEmpty()) {
            System.out.println("Tidak ada antrian untuk dokter " + namaDokter + ".");
        } else {
            AntrianQueue.NodeQueue pasien = antrianKonsultasi.dequeue();
        }
        input.nextLine();
    }
    
}
