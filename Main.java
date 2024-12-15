import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static LinkedList daftarUser = new LinkedList();
    static AntrianQueue antrianKonsultasi = new AntrianQueue();

    public static void main(String[] args) {
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
        System.out.println("|2. Log In User                                                      |");
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

        if (daftarUser.validasiLogin(username, password)) {
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
            System.out.println("2. Cek Antrian");
            System.out.println("3. Log Out");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    konsultasiMakanan(username);
                    break;
                case 2:
                    clearScreen();
                    antrianKonsultasi.cekAntrian();
                    input.nextLine();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    static void konsultasiMakanan(String username) {
        clearScreen();
        System.out.println("+==============================+");
        System.out.println("|Pilih Dokter untuk Konsultasi |");
        System.out.println("+==============================+");
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
}

    
