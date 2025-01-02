import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class Sitose {
    /*
     * variabel input sebagai scanner yang akan digunakkan untuk menangkap data
     * inputan
     */
    Scanner input = new Scanner(System.in);

    /*
     * ArrayList<User> penggunaObject = new ArrayList<>(); mendefinisikan sebuah
     * ArrayList sebagai wadah dinamis yang digunakan untuk menyimpan objek-objek
     * yang dibentuk dari kelas User.
     */
    ArrayList<User> penggunaObject = new ArrayList<>();
    /*
     * variabel level untuk menyimpan kode level pengguna yang sedang login
     */
    String level;
    /*
     * variabel userCabang untuk menyimpan data cabang toko pengguna yang sedang
     * login
     */
    CabangToko userCabang;

    /*
     * ArrayList<Jenis> jenisProdukObject = new ArrayList<>();
     * mendefinisikan sebuah ArrayList sebagai wadah dinamis yang digunakan
     * untuk menyimpan objek-objek yang dibentuk dari kelas Jenis.
     */
    ArrayList<Jenis> jenisProdukObject = new ArrayList<>();

    /*
     * ArrayList<Kategori> kategoriProdukObject = new ArrayList<>();
     * mendefinisikan sebuah ArrayList sebagai wadah dinamis yang digunakan
     * untuk menyimpan objek-objek yang dibentuk dari kelas Kategori.
     */
    ArrayList<Kategori> kategoriProdukObject = new ArrayList<>();

    /*
     * ArrayList<Produk> produkObject = new ArrayList<>();
     * mendefinisikan sebuah ArrayList sebagai wadah dinamis yang digunakan
     * untuk menyimpan objek-objek yang dibentuk dari kelas Produk.
     */
    ArrayList<Produk> produkObject = new ArrayList<>();

    /*
     * ArrayList<CabangToko> cabangTokoObject = new ArrayList<>();
     * mendefinisikan sebuah ArrayList sebagai wadah dinamis yang digunakan
     * untuk menyimpan objek-objek yang dibentuk dari kelas CabangToko.
     */
    ArrayList<CabangToko> cabangTokoObject = new ArrayList<>();

    /*
     * ArrayList<Transaksi> transaksiObject = new ArrayList<>();
     * mendefinisikan sebuah ArrayList sebagai wadah dinamis yang digunakan
     * untuk menyimpan objek-objek yang dibentuk dari kelas Transaksi.
     */
    ArrayList<Transaksi> transaksiObject = new ArrayList<>();

    public static void main(String[] args) {
        // Membuat objek baru dari kelas Sitose dan menyimpannya di variabel func
        Sitose func = new Sitose();

        // Menjalankan metode run() pada objek func yang telah dibuat
        func.run();
    }

    /*
     * method run() menjalankan :
     * -> method initUser() untuk mengisi data pengguna yang akan login kedalam
     * aplikasi
     * -> method welcome() untuk menampilkan halaman depan dari program yang
     * dijalankan
     */
    void run() {
        initUser();
        welcome();
    }

    /*
     * method initUser() mengisi data pengguna yang akan masuk dan mengakses
     * aplikasi
     * Daftar role/level pengguna:
     * 1. admin
     * -> bisa mengakses semua menu kecuali menambah data transaksi
     * 2. gudang
     * -> mengatur semua manajemen produk yang ada di gudang utama
     * 3. manajer toko
     * -> mengatur data toko
     * 4. gudang toko
     * -> mengatur produk yang ada di toko tersebut
     * 5. kasir
     * -> melakukan transaksi
     */
    void initUser() {
        /*
         * melakukan insert data kedalam arraylist penggunaObject dalam bentuk objek
         * dari kelas User dengan parameter :
         * -> id : id pengguna
         * -> username : username atau nama pengguna
         * -> password : password pengguna
         * -> level : level atau role yang digunakkan untuk memvalidasi hak akses
         */
        penggunaObject.add(new User("admin", "password", "admin", null));
        penggunaObject.add(new User("gudang", "password", "gudang", null));
    }

    /*
     * method welcome() menampilkan pesan selamat datang di aplikasi, lalu memanggil
     * method login() agar pengguna bisa melakukan login
     */
    void welcome() {
        header("SELAMAT DATANG DI SIAPBANG");

        login();
    }

    /*
     * method login() memanggil input agar pengguna bisa melakukan login dengan
     * memasukkan username dan password
     */
    void login() {
        System.out.print("Masukkan Username : ");
        String username = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        /*
         * melakukan pengecekan pada fungsi auth() dengan username dan password sebagai
         * parameter.
         * -> jika kondisi benar, maka pengguna diizinkan untuk melanjutkan dan
         * mengakses
         * menu aplikasi sesuai dengan level atau role-nya.
         * -> jika kondisi salah, pengguna diarahkan untuk kembali mengisi username dan
         * password
         */
        if (auth(username, password)) {
            setup();
        } else {
            login();
        }
    }

    /*
     * fungsi auth() dengan parameter username dan password mengembalikan status
     * apakah username dan password yang dimasukkan benar atau salah dengan tipe
     * data boolean. pengecekan dilakukan dengan menggunakkan looping (foreach),
     * jika terdapat
     * username dan password yang sama dari objek yang diulang (loop), maka username
     * dan password yang dimasukkan bernilai benar, jika tidak bernilai salah.
     * 
     * -> jika username dan password bernilai benar, maka simpan level sebagai angka
     * didalam variabel global level yang sudah dibuat sebelumnya (variabel yang
     * dibuat untuk menyimpan level pengguna yang sedang login) dan kembalikan nilai
     * true
     * -> jika username dan password bernilai salah, maka tampilkan pesan
     * "Username dan password salah!" dan kembalikan nilai false
     */
    boolean auth(String usernameInput, String passwordInput) {
        for (User user : penggunaObject) {
            if (user.username.equals(usernameInput) && user.password.equals(passwordInput)) {
                if (user.level.equals("admin")) {
                    this.level = "UA";
                } else if (user.level.equals("gudang")) {
                    this.level = "UB";
                } else if (user.level.equals("manajer")) {
                    this.level = "UC";
                    this.userCabang = user.cabangtoko;
                } else if (user.level.equals("kasir")) {
                    this.level = "UD";
                    this.userCabang = user.cabangtoko;
                }

                return true;
            }
        }
        System.out.println("Username dan password salah!");
        return false;
    }

    /*
     * method setup() untuk memanggil method showMenu() yang akan menampilkan daftar
     * menu aplikasi sesuai dengan hak akses pengguna
     */
    void setup() {
        showMenu();
    }

    /*
     * Rakha Jelasin
     */
    static void header(String title) {

        int width = 29;
        int length = title.length();
        int sepasi = (width - length) / 2;

        System.out.println("=============================");
        System.out.println("");
        System.out.printf("%" + sepasi + "s%s%" + sepasi + "s\n", "", title, "");
        System.out.println("");
        System.out.println("=============================");
    }

    /*
     * Rakha Jelasin
     */
    void showMenu() {
        // 0 = nama menu, 1 = kode menu
        header("MENU APLIKASI");
        String[][] mainMenus = { { "Manajemen Jenis Produk", "A" }, { "Manajemen Kategori Produk", "B" },
                { "Data Produk", "C" }, { "Manajemen Cabang Toko", "D" },
                { "Transaksi", "E" }, { "Manajemen Pengguna", "F" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ABCDEF" }, { "UB", "BCD" }, { "UC", "CD" }, { "UD", "E" } };

        output(mainMenus, mainMenuAccess);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                menuJenis();
                break;
            case "B":
                menuKategori();
                break;
            case "C":
                menuProduk();
                break;
            case "D":
                menuCabangToko();
                break;
            case "E":
                menuTransaksi();
                break;
            case "F":
                menuManajemenUser();
                break;
            default:
                break;
        }

    }

    void output(String[][] mainMenus, String[][] mainMenuAccess) {
        String userAccess = findUserAccess(mainMenuAccess, this.level);
        // cek jika false brarti ga ktemu
        if (userAccess.equals("false")) {
            System.out.println("There is an error");
            return;
        }

        System.out.println("+--------------------------+");
        System.out.printf("| %-4s | %-16s |\n", "pilih", "Menu");
        System.out.println("+--------------------------+");
        for (String[] mainMenu : mainMenus) {
            if (!userAccess.contains(mainMenu[1]))
                continue;

            System.out.printf("| %-5s | %-16s |\n", mainMenu[1], mainMenu[0]);

        }
        System.out.println("+--------------------------+");
    }

    public static String findUserAccess(String[][] mainMenuAccess, String roleName) {
        for (String[] mainMenuAcc : mainMenuAccess) {
            // nyamain antara kode level yang di array sama level yang disimpan waktu user
            // login
            if (mainMenuAcc[0].equals(roleName)) {
                // kalau sama, return kode menu
                return mainMenuAcc[1];
            }
        }

        String err = "false";
        return err;
    }

    void menuJenis() {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "Lihat", "A" }, { "Tambah", "B" }, { "Edit", "C" }, { "Hapus", "D" },
                { "Kembali", "0" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ABCD0" }, { "UB", "ABCD0" }, { "UC", "A0" } };

        header("MENU JENIS PRODUK");

        output(mainMenus, mainMenuAccess);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewJenis(true);
                break;
            case "B":
                insertJenis();
                break;
            case "C":
                updateJenis();
                break;
            case "D":
                removeJenis();
                break;
            case "0":
                showMenu();
                break;

            default:
                break;
        }
    }

    void menuKategori() {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "Lihat", "A" }, { "Tambah", "B" }, { "Edit", "C" }, { "Hapus", "D" },
                { "Kembali", "0" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ABCD" }, { "UB", "ABCD" }, { "UC", "A" } };

        output(mainMenus, mainMenuAccess);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewKategori(true);
                break;
            case "B":
                insertKategori();
                break;
            case "C":
                updateKategori();
                break;
            case "D":
                removeKategori();
                break;
            case "0":
                showMenu();
                break;

            default:
                break;
        }
    }

    void menuProduk() {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "Lihat", "A" }, { "Tambah", "B" }, { "Edit", "C" }, { "Hapus", "D" },
                { "Kembali", "0" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ABCD" }, { "UB", "ABCD" }, { "UC", "A" } };

        output(mainMenus, mainMenuAccess);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewProduk(true);
                break;
            case "B":
                insertProduk();
                break;
            case "C":
                updateProduk();
                break;
            case "D":
                removeProduk();
                break;
            case "0":
                showMenu();
                break;

            default:
                break;
        }
    }

    void menuCabangToko() {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "Lihat", "A" }, { "Tambah", "B" }, { "Edit", "C" }, { "Hapus", "D" },
                { "Kembali", "0" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ABCD" }, { "UB", "ABCD" }, { "UC", "A" } };

        output(mainMenus, mainMenuAccess);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewProduk(true);
                break;
            case "B":
                insertProduk();
                break;
            case "C":
                updateProduk();
                break;
            case "D":
                removeProduk();
                break;
            case "0":
                showMenu();
                break;

            default:
                break;
        }
    }

    void menuTransaksi() {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "Lihat", "A" }, { "Tambah", "B" }, { "Hapus", "D" }, { "Kembali", "0" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ACD" }, { "UD", "ABD" } };

        output(mainMenus, mainMenuAccess);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewTransaksi(true);
                break;
            case "B":
                insertTransaksi();
                break;
            case "D":
                removeTransaksi();
                break;
            case "0":
                showMenu();
                break;

            default:
                break;
        }
    }

    void menuManajemenUser() {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "Lihat", "A" }, { "Tambah", "B" }, { "edit", "C" }, { "Hapus", "D" },
                { "Kembali", "0" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ABCD" }, { "UC", "ABCD" } };

        output(mainMenus, mainMenuAccess);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewUser(true);
                break;
            case "B":
                insertUser();
                break;
            case "C":
                updateUser();
                break;
            case "D":
                removeUser();
                break;
            case "0":
                showMenu();
                break;
            default:
                break;
        }
    }

    /*
     * method viewJenis() menampilkan data jenis produk, dengan parameter isShowMenu
     * dengan tipe data boolean yang berfungsi untuk memberikan kondisi apakah
     * method showMenu nggil atau tidak.
     * -> jika isShowMenu bernilai benar, maka akan memanggil method showMenu(dengan
     * parameter "submenu" sebagai tipe menu yang dipanggil dan "1" sebagai
     * submenu yang dipilih untuk ditampilkan
     * -> jika isShowMenu bernilai salah, maka tidak akan memanggil method
     * kondisi dibawah akan mengecek apakah data didalam jenisProdukObject kosong
     * atau tidak,
     * -> jika data kosong, maka tampilkan pesan "Tidak ada data untuk ditampilkan"
     * -> jika data tidak kosong, maka tampilkan data dalam bentuk tabel
     */
    void viewJenis(Boolean isShowMenu) {
        if (jenisProdukObject.isEmpty()) {

            System.out.println("|       Tidak ada data          |");
            System.out.println("+-------------------------------+");

        } else {
            /*
             * Rakha jelasin struktur table nya
             */

            // Isi
            int id = 0;
            for (Jenis item : jenisProdukObject) {
                System.out.printf("| %-2d | %-4s | %-17s | \n", (id++ + 1), item.kode, item.nama);
            }

            // bawah
            System.out.println("+----+------+-------------------+");
        }

        /*
         * kondisi isShowMenu untuk memanggil method showMenu
         */

        if (isShowMenu) {
            menuJenis();
        }
    }

    /*
     * method insertJenis() digunakkan untuk menyimpan jenis produk yang dimasukkan
     * oleh pengguna kedalam ArrayList jenisProdukObject dalam bentuk objek dari
     * kelas Jenis()
     */
    void insertJenis() {
        /*
         * try catch digunakkan untuk membungkus isi dari method insertJenis, untuk
         * mengantisipasi jika terjadi kesalahan pada program
         * -> jika sintak didalam try tidak terjadi kesalahan, maka program akan
         * berjalan dengan baik
         * -> jika sintak didalam try terjadi kesalahan, maka catch akan menampilkan
         * pesan "Data Gagal Disimpan!" dan menampilkan pesan error program dengan
         * variabel e sebagai parameter untuk menangkap error dan fungsi getMessage()
         * untuk mengambil pesan error program
         */
        try {
            header("TAMBAH JENIS PRODUK");

            System.out.print("Masukkan nama jenis produk : ");
            String nama = input.nextLine(); // input nama jenis produk
            System.out.print("Masukkan kode jenis produk (1 huruf alphabet) : ");
            String kode = input.nextLine(); // input kode jenis produk

            /*
             * kondisi !nama.isEmpty() && !kode.isEmpty() digunakkan untuk melakukan
             * validasi agar pengguna tidak memasukkan inputan kosong
             * -> jika input tidak kosong, maka data akan disimpan kedalam ArrayList
             * jenisProdukObject dalam bentuk objek dari kelas Jenis() dan
             * menampilkan pesan "Data Berhasil Disimpan"
             * -> jika input kosong, maka data tidak akan disimpan dan menampilkan pesan
             * "Kolom wajib diisi!"
             */
            if (!nama.isEmpty() && !kode.isEmpty()) {
                /*
                 * melakukan insert data kedalam arraylist jenisProdukObject dalam bentuk objek
                 * dari kelas Jenis dengan parameter :
                 * -> id : id data
                 * -> nama : nama jenis produk
                 * -> kode : kode jenis produk (berisi 1 huruf yang unik sebagai kode dari jenis
                 * produk)
                 */
                jenisProdukObject.add(new Jenis(nama, kode));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                /*
                 * memanggil method menuJenis(parameter "submenu" sebagai tipe menu yang
                 * dipanggil dan "1" sebagai submenu untuk menampilkan submenu jenis produk
                 */
                menuJenis();
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    /*
     * method updateJenis() digunakkan untuk memperbarui jenis produk yang
     * dimasukkan
     * oleh pengguna kedalam ArrayList jenisProdukObject dalam bentuk objek dari
     * kelas Jenis()
     */
    void updateJenis() {
        header("EDIT JENIS PRODUK");

        try {
            /*
             * memanggil method viewJenis() untuk menampilkan data jenis produk agar
             * pengguna bisa melihat id jenis produk, dengan parameter isShowMenu bernilai
             * false agar menu tidak ditampilkan
             */
            viewJenis(false);

            System.out.println("Masukkan id jenis produk : ");
            int id = input.nextInt();

            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();

            /*
             * namaTmp dan kodeTmp digunakkan sebagai temporary variable untuk menyimpan
             * nama dan kode yang lama, nantinya akan digunakkan jika pengguna tidak ingin
             * mengubah data lama mereka
             */
            String namaTmp = jenisProdukObject.get(id - 1).nama;
            String kodeTmp = jenisProdukObject.get(id - 1).kode;

            System.out.println("Masukkan nama baru (masukkan jika ingin mengganti) : ");
            String nama = input.nextLine();
            System.out.println("Masukkan kode baru (masukkan jika ingin mengganti) : ");
            String kode = input.nextLine();

            /*
             * jenisProdukObject.get(id - 1).name digunakan untuk mengambil nilai properti
             * nama dari objek pada indeks ke-id - 1 di dalam daftar jenisProdukObject.
             * Lalu, = (sama dengan) didepannya digunakkan untuk melakukan assignment
             * kedalam property yang sudah dipanggil
             * 
             * `nama.isEmpty() ? namaTmp : nama` adalah ternary operator,
             * fungsinya mirip seperti if hanya saja lebih ringkas.
             * -> `nama.isEmpty()` adalah kondisinya dan akan menghasilkan tipe data boolean
             * -> jika bernilai benar, maka value yang akan digunakkan diambil dari variable
             * namaTmp
             * -> jika bernilai salah, maka value yang akan digunakkan diambil dari variable
             * nama
             * jadi, singkatnya kondisi tersebut untuk mengecek, jika nama yang diinputkan
             * oleh pengguna kosong, maka property nama akan diisi oleh nama jenis produk
             * yang sudah disimpan pada variable namaTmp, jika tidak maka akan menggunakkan
             * nama yang baru, yang sudah dimasukkan oleh pengguna
             */
            jenisProdukObject.get(id - 1).nama = nama.isEmpty() ? namaTmp : nama;
            jenisProdukObject.get(id - 1).kode = kode.isEmpty() ? kodeTmp : kode;
            System.out.println(">>>>Data Berhasil Diubah<<<<");

            /*
            */
            menuJenis();
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    /*
     * method removeJenis() digunakkan untuk menghapus data jenis produk
     */
    void removeJenis() {
        header("HAPUS JENIS PRODUK");

        try {
            /*
             * memanggil method viewJenis() untuk menampilkan data jenis produk agar
             * pengguna bisa melihat id jenis produk, dengan parameter isShowMenu bernilai
             * false agar menu tidak ditampilkan
             */
            viewJenis(false);

            /*
             * pengguna memasukkan id jenis produk
             */
            System.out.println("Masukkan id jenis produk : ");
            int id = input.nextInt();

            /*
             * removeIf akan mencari ............. BELOMMMMMMMMMMMMMMMMM
             */
            jenisProdukObject.remove(id - 1);
            System.out.println(">>>>Data Berhasil Dihapus<<<<");

            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();
            menuJenis();
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    /*
     * method viewKategori() menampilkan data jenis produk, dengan parameter
     * isShowMenu
     * dengan tipe data boolean yang berfungsi untuk memberikan kondisi apakah
     * method showMenu nggil atau tidak.
     * -> jika isShowMenu bernilai benar, maka akan memanggil method showMenu(dengan
     * parameter "submenu" sebagai tipe menu yang dipanggil dan "2" sebagai
     * submenu yang dipilih untuk ditampilkan
     * -> jika isShowMenu bernilai salah, maka tidak akan memanggil method
     * showMenu(
     * void viewKategori(boolean isShowMenu) {
     * /*
     * kondisi dibawah akan mengecek apakah data didalam kategoriProdukObject kosong
     * atau tidak,
     * -> jika data kosong, maka tampilkan pesan "Tidak ada data untuk ditampilkan"
     * -> jika data tidak kosong, maka tampilkan data dalam bentuk tabel
     */
    void viewKategori(Boolean isShowMenu) {
        if (kategoriProdukObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            // atas
            System.out.println("+----+----+----------------------+");
            System.out.printf("| %-2s |%-2s| %-18s |\n", "ID", "Kode", "Nama Kategori Produk");
            System.out.println("+----+----+----------------------+");

            // Isi
            int id = 0;
            for (Kategori item : kategoriProdukObject) {
                System.out.printf("| %-2s | %-2s | %-20s |\n", (id++ + 1), item.kode, item.nama);
            }

            // bawah
            System.out.println("+----+----+----------------------+");
        }

        /*
         * kondisi isShowMenu untuk memanggil method showMenu
         */

        if (isShowMenu) {
            showMenu();
        }
    }

    /*
     * method insertKategori() digunakkan untuk menyimpan jenis produk yang
     * dimasukkan
     * oleh pengguna kedalam ArrayList kategoriProdukObject dalam bentuk objek dari
     * kelas Kategori()
     */
    void insertKategori() {
        /*
         * try catch digunakkan untuk membungkus isi dari method insertKategori, untuk
         * mengantisipasi jika terjadi kesalahan pada program
         * -> jika sintak didalam try tidak terjadi kesalahan, maka program akan
         * berjalan dengan baik
         * -> jika sintak didalam try terjadi kesalahan, maka catch akan menampilkan
         * pesan "Data Gagal Disimpan!" dan menampilkan pesan error program dengan
         * variabel e sebagai parameter untuk menangkap error dan fungsi getMessage()
         * untuk mengambil pesan error program
         */
        try {
            header("TAMBAH KATEGORI PRODUK");

            System.out.print("Masukkan nama kategori produk : ");
            String nama = input.nextLine(); // input nama kategori produk
            System.out.print("Masukkan kode kategori produk (2 huruf alphabet) : ");
            String kode = input.nextLine(); // input kode kategori produk

            /*
             * kondisi !nama.isEmpty() && !kode.isEmpty() digunakkan untuk melakukan
             * validasi agar pengguna tidak memasukkan inputan kosong
             * -> jika input tidak kosong, maka data akan disimpan kedalam ArrayList
             * kategoriProdukObject dalam bentuk objek dari kelas Kategori() dan
             * menampilkan pesan "Data Berhasil Disimpan"
             * -> jika input kosong, maka data tidak akan disimpan dan menampilkan pesan
             * "Kolom wajib diisi!"
             */
            if (!nama.isEmpty() && !kode.isEmpty()) {
                /*
                 * melakukan insert data kedalam arraylist jenisProdukObject dalam bentuk objek
                 * dari kelas Jenis dengan parameter :
                 * -> id : id data
                 * -> nama : nama kategori produk
                 * -> kode : kode kategori produk (berisi 2 huruf yang unik sebagai kode dari
                 * kategori
                 * produk)
                 */
                kategoriProdukObject.add(new Kategori(nama, kode));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                /*
                 * memanggil method showMenu(parameter "submenu" sebagai tipe menu yang
                 * dipanggil dan "2" sebagai submenu untuk menampilkan submenu kategori produk
                 */
                showMenu();
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());

        }
    }

    /*
     * method updateKategori() digunakkan untuk memperbarui kategori produk yang
     * dimasukkan
     * oleh pengguna kedalam ArrayList kategoriProdukObject dalam bentuk objek dari
     * kelas Kategori()
     */
    void updateKategori() {
        header("EDIT KATEGORI PRODUK");

        try {
            /*
             * memanggil method viewKategori() untuk menampilkan data kategori produk agar
             * pengguna bisa melihat id kategori produk, dengan parameter isShowMenu
             * bernilai
             * false agar menu tidak ditampilkan
             */
            viewKategori(false);

            System.out.println("Masukkan id kategori produk : ");
            int id = input.nextInt();

            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();

            /*
             * namaTmp dan kodeTmp digunakkan sebagai temporary variable untuk menyimpan
             * nama dan kode yang lama, nantinya akan digunakkan jika pengguna tidak ingin
             * mengubah data lama mereka
             */
            String namaTmp = kategoriProdukObject.get(id - 1).nama;
            String kodeTmp = kategoriProdukObject.get(id - 1).kode;

            System.out.println("Masukkan nama baru (masukkan jika ingin mengganti) : ");
            String nama = input.nextLine();
            System.out.println("Masukkan kode baru (masukkan jika ingin mengganti) : ");
            String kode = input.nextLine();

            /*
             * kategoriProdukObject.get(id - 1).name digunakan untuk mengambil nilai
             * properti
             * nama dari objek pada indeks ke-id - 1 di dalam daftar kategoriProdukObject.
             * Lalu, = (sama dengan) didepannya digunakkan untuk melakukan assignment
             * kedalam property yang sudah dipanggil
             * 
             * `nama.isEmpty() ? namaTmp : nama` adalah ternary operator,
             * fungsinya mirip seperti if hanya saja lebih ringkas.
             * -> `nama.isEmpty()` adalah kondisinya dan akan menghasilkan tipe data boolean
             * -> jika bernilai benar, maka value yang akan digunakkan diambil dari variable
             * namaTmp
             * -> jika bernilai salah, maka value yang akan digunakkan diambil dari variable
             * nama
             * jadi, singkatnya kondisi tersebut untuk mengecek, jika nama yang diinputkan
             * oleh pengguna kosong, maka property nama akan diisi oleh nama jenis produk
             * yang sudah disimpan pada variable namaTmp, jika tidak maka akan menggunakkan
             * nama yang baru, yang sudah dimasukkan oleh pengguna
             */
            kategoriProdukObject.get(id - 1).nama = nama.isEmpty() ? namaTmp : nama;
            kategoriProdukObject.get(id - 1).kode = kode.isEmpty() ? kodeTmp : kode;
            System.out.println(">>>>Data Berhasil Diubah<<<<");

            showMenu();
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    /*
     * method removeKategori() digunakkan untuk menghapus data kategori produk
     */
    void removeKategori() {
        header("HAPUS KATEGORI PRODUK");

        try {
            /*
             * pengguna memasukkan id jenis produk
             */
            System.out.println("Masukkan id kategori produk : ");
            int id = input.nextInt();

            /*
             * removeIf akan mencari ............. BELOMMMMMMMMMMMMMMMMM
             */
            kategoriProdukObject.remove(id - 1);
            System.out.println(">>>>Data Berhasil Dihapus<<<<");

            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();
            showMenu();
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void viewProduk(boolean isShowMenu) {
        if (kategoriProdukObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            int id = 0;
            for (Produk item : produkObject) {
                System.out.println((id++ + 1));
                System.out.println(item.nama);
                System.out.println(item.kode);
                System.out.println(item.jenis);
                System.out.println(item.kategori);
                System.out.println(item.stok);
                System.out.println(item.harga);
            }
        }

        if (isShowMenu) {
            menuProduk();
        }
    }

    void insertProduk() {
        try {
            header("TAMBAH PRODUK");

            System.out.print("Masukkan nama produk : ");
            String nama = input.nextLine();

            viewJenis(false);
            System.out.print("Pilih angka jenis produk : ");
            int id_jenis = input.nextInt();

            viewKategori(false);
            System.out.print("Pilih angka kategori produk : ");
            int id_kategori = input.nextInt();

            System.out.print("Masukkan jumlah stok masuk : ");
            int stok = input.nextInt();

            System.out.print("Masukkan harga satuan : ");
            int harga = input.nextInt();

            if (nama != "") {
                produkObject.add(new Produk(nama, jenisProdukObject.get(id_jenis - 1),
                        kategoriProdukObject.get(id_kategori - 1), stok,
                        harga));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                menuProduk();
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void updateProduk() {
        try {
            header("EDIT PRODUK");

            int id = input.nextInt();
            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();

            System.out.print("Masukkan nama produk baru : ");
            String nama = input.nextLine();

            viewJenis(false);
            System.out.print("Pilih angka jenis produk baru : ");
            int id_jenis = input.nextInt();

            viewKategori(false);
            System.out.print("Pilih angka kategori produk baru : ");
            int id_kategori = input.nextInt();

            System.out.print("Masukkan jumlah stok masuk baru : ");
            int stok = input.nextInt();

            System.out.print("Masukkan harga satuan baru : ");
            int harga = input.nextInt();

            if (nama != "") {
                produkObject.get(id - 1).nama = nama;
                produkObject.get(id - 1).jenis = jenisProdukObject.get(id_jenis - 1);
                produkObject.get(id - 1).kategori = kategoriProdukObject.get(id_kategori - 1);
                produkObject.get(id - 1).stok = stok;
                produkObject.get(id - 1).harga = harga;

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                menuProduk();
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void removeProduk() {
        header("HAPUS PRODUK");

        // tampilkan kategori produk
        viewProduk(false);

        System.out.println("Masukkan id produk : ");
        int id = input.nextInt();

        produkObject.remove(id - 1);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        /*
         * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
         * input sebelumnya, supaya saat kita membaca input berikutnya dengan
         * nextLine(), program tidak langsung melewatkannya.
         */
        input.nextLine();
        menuProduk();
    }

    void viewCabangToko(boolean isShowMenu) {
        if (cabangTokoObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            int id = 0;
            for (CabangToko item : cabangTokoObject) {
                System.out.println((id++ + 1));
                System.out.println(item.nama);
                System.out.println(item.kode);
                System.out.println(item.telepon);
                System.out.println(item.alamat);
            }

            // bawah
            System.out.println("+----+----------------------+");
        }

        if (isShowMenu) {
            menuCabangToko();
        }
    }

    void insertCabangToko() {
        try {
            header("TAMBAH CABANG PRODUK");

            System.out.print("Masukkan nama cabang : ");
            String nama = input.nextLine();
            System.out.print("Masukkan kode cabang : ");
            String kode = input.nextLine();
            System.out.print("Masukkan telepon cabang : ");
            String telepon = input.nextLine();
            System.out.print("Masukkan alamat cabang : ");
            String alamat = input.nextLine();

            if (nama != "" && telepon != "") {
                cabangTokoObject.add(new CabangToko(nama, kode, telepon, alamat));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                menuCabangToko();
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void updateCabangToko() {
        header("EDIT CABANG TOKO");

        // tampilkan cabang toko produk
        viewCabangToko(false);

        System.out.println("Masukkan id cabang toko : ");
        int id = input.nextInt();
        /*
         * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
         * input sebelumnya, supaya saat kita membaca input berikutnya dengan
         * nextLine(), program tidak langsung melewatkannya.
         */
        input.nextLine();

        System.out.println("Masukkan nama baru : ");
        String nama = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String kode = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String telepon = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String alamat = input.nextLine();

        // tambahkan if kosong maka tidak ada yang diubah ---------------------
        cabangTokoObject.get(id - 1).nama = nama;
        cabangTokoObject.get(id - 1).kode = kode;
        cabangTokoObject.get(id - 1).telepon = telepon;
        cabangTokoObject.get(id - 1).alamat = alamat;
        System.out.println(">>>>Data Berhasil Diubah<<<<");

        menuCabangToko();
    }

    void removeCabangToko() {
        header("HAPUS CABANG TOKO");

        // tampilkan cabang toko
        viewCabangToko(false);

        System.out.println("Masukkan id cabang toko : ");
        int id = input.nextInt();

        cabangTokoObject.remove(id - 1);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        /*
         * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
         * input sebelumnya, supaya saat kita membaca input berikutnya dengan
         * nextLine(), program tidak langsung melewatkannya.
         */
        input.nextLine();
        menuCabangToko();
    }

    void viewTransaksi(boolean isShowMenu) {
        if (transaksiObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            int id = 0;
            for (Transaksi item : transaksiObject) {
                System.out.println((id++ + 1));
                System.out.println(item.kode);
                System.out.println(item.waktu);
                System.out.println(item.total);
                System.out.println(item.transaksi_detail);
            }
        }
        if (isShowMenu) {
            menuTransaksi();
        }
    }

    void insertTransaksi() {
        try {
            header("TAMBAH TRANSAKSI");

            cabangTokoObject.add(new CabangToko("Cabang 1", "C1", "0000", "tes"));
            jenisProdukObject.add(new Jenis("jenis 1", "J"));
            kategoriProdukObject.add(new Kategori("kategori 1", "KA"));
            produkObject.add(new Produk("produk 1", jenisProdukObject.get(0),
                    kategoriProdukObject.get(0), 10, 10000));

            ArrayList<ArrayList<String>> tmp = new ArrayList<>();
            ArrayList<String> tmpItem;

            boolean isNewProduct = true;
            while (isNewProduct) {
                viewProduk(false);

                System.out.print("Pilih produk : ");
                String produk = input.nextLine();

                System.out.print("Masukkan Quantity : ");
                String qty = input.nextLine();

                tmpItem = new ArrayList<>();
                tmpItem.add(produk); // 0
                tmpItem.add(qty); // 1
                tmp.add(new ArrayList<>(tmpItem));

                System.out.print("Ingin menambahkan produk baru (y/n)?");
                String isnpr = input.nextLine();

                if (isnpr.equals("n")) {
                    isNewProduct = false;
                }
            }

            if (!isNewProduct) {
                int total = 0;
                CabangToko cabang = this.userCabang; // diganti jadi diambil dari data user
                Transaksi transaksiBaru = new Transaksi(total, cabang);

                for (ArrayList<String> item : tmp) {
                    int produkIndex = Integer.parseInt(item.get(0));
                    int kuantiti = Integer.parseInt(item.get(1));

                    Produk produkName = produkObject.get(produkIndex - 1);
                    int totalPerProduk = kuantiti * produkName.harga;
                    total += totalPerProduk;

                    ArrayList<String> detailItem = new ArrayList<>();
                    detailItem.add(produkName.nama); // Nama produk
                    detailItem.add(produkName.kode); // Kode produk
                    detailItem.add(String.valueOf(kuantiti)); // Kuantitas
                    detailItem.add(String.valueOf(totalPerProduk)); // Total harga
                    transaksiBaru.transaksi_detail.add(detailItem);

                    // viewNota(); <- tampilkan nota
                }

                menuTransaksi();
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void removeTransaksi() {
        header("HAPUS TRANSAKSI");

        // tampilkan transaksi
        viewUser(false);

        System.out.println("Masukkan id transaksi : ");
        int id = input.nextInt();

        transaksiObject.remove(id - 1);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        /*
         * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
         * input sebelumnya, supaya saat kita membaca input berikutnya dengan
         * nextLine(), program tidak langsung melewatkannya.
         */
        input.nextLine();
        menuTransaksi();
    }

    void viewUser(boolean isShowMenu) {
        if (penggunaObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            int id = 0;
            // jika levelnya adalah manajer, maka
            if (this.level.equals("UC")) {
                for (User item : penggunaObject) {
                    // menampilkan data user yang levelnya hanya kasir, yang terkait dengan toko si
                    // manajer ketika login
                    if (item.level.equals("kasir") && item.cabangtoko.kode.equals(this.userCabang.kode)) {
                        System.out.println((id++ + 1));
                        System.out.println(item.username);
                        System.out.println(item.password);
                        System.out.println(item.level);
                        System.out.println(item.cabangtoko.nama);
                    }
                }
            } else {
                for (User item : penggunaObject) {
                    System.out.println((id++ + 1));
                    System.out.println(item.username);
                    System.out.println(item.password);
                    System.out.println(item.level);
                    if (item.level.equals("manajer") || item.level.equals("kasir"))
                        System.out.println(item.cabangtoko.nama);
                }
            }
        }
        if (isShowMenu) {
            menuManajemenUser();
        }
    }

    void insertUser() {
        try {
            header("TAMBAH USER");

            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();
            System.out.print("Masukkan username : ");
            String username = input.nextLine();
            System.out.print("Masukkan password : ");
            String password = input.nextLine();
            System.out.print("Masukkan level (admin, gudang, manajer, kasir) : ");
            String level = input.nextLine();
            CabangToko cabangToko = null;
            if (level.equals("manajer") || level.equals("kasir")) {
                viewCabangToko(false);
                System.out.print("Pilih angka cabang toko : ");
                int cabangtoko = input.nextInt();

                cabangToko = cabangTokoObject.get(cabangtoko - 1);
            }

            if (username != "" && password != "") {
                penggunaObject.add(new User(username, password, level, cabangToko));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                menuManajemenUser();
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void updateUser() {
        header("EDIT USER");

        // tampilkan kategori produk
        viewUser(false);

        System.out.println("Masukkan id kategori produk : ");
        int id = input.nextInt();
        /*
         * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
         * input sebelumnya, supaya saat kita membaca input berikutnya dengan
         * nextLine(), program tidak langsung melewatkannya.
         */
        input.nextLine();

        System.out.println("Masukkan username baru : ");
        String username = input.nextLine();
        System.out.println("Masukkan password baru : ");
        String password = input.nextLine();

        // tambahkan if kosong maka tidak ada yang diubah ---------------------
        penggunaObject.get(id - 1).username = username;
        penggunaObject.get(id - 1).password = password;
        System.out.println(">>>>Data Berhasil Diubah<<<<");

        menuManajemenUser();
    }

    void removeUser() {
        header("HAPUS USER");

        // tampilkan kategori produk
        viewUser(false);

        System.out.println("Masukkan id kategori produk : ");
        int id = input.nextInt();

        penggunaObject.remove(id - 1);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        /*
         * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
         * input sebelumnya, supaya saat kita membaca input berikutnya dengan
         * nextLine(), program tidak langsung melewatkannya.
         */
        input.nextLine();
        menuManajemenUser();
    }
}

class User {
    String username, password, level;
    CabangToko cabangtoko;

    User(String username, String password, String level, CabangToko cabangtoko) {
        this.username = username;
        this.password = password;
        this.level = level;
        this.cabangtoko = cabangtoko;
    }
}

class Jenis {
    public String nama, kode;

    public Jenis(String nama, String kode) {
        this.nama = nama;
        this.kode = kode;
    }
}

class Kategori {
    String nama, kode;

    public Kategori(String nama, String kode) {
        this.nama = nama;
        this.kode = kode;
    }
}

class Produk {
    int stok, harga;
    String nama, kode;
    Jenis jenis;
    Kategori kategori;

    public Produk(String nama, Jenis jenis, Kategori kategori, int stok, int harga) {
        this.nama = nama;
        this.jenis = jenis;
        this.kategori = kategori;
        this.kode = this.jenis.kode + this.kategori.kode + String.format("%04d", 0);
        // (B-MA-0001 = BMA0001 dengan B (barang) adalah kode dari
        // jenis, MA (makanan) kode dari kategori dan 0001 didapat dari nomor id)
        this.stok = stok;
        this.harga = harga;
    }
}

class CabangToko {
    String kode, nama, telepon, alamat;

    public CabangToko(String nama, String kode, String telepon, String alamat) {
        this.nama = nama;
        this.kode = kode;
        this.telepon = telepon;
        this.alamat = alamat;
    }
}

class Transaksi {
    int total;
    String kode, waktu;
    ArrayList<ArrayList<String>> transaksi_detail = new ArrayList<>();
    ArrayList<String> transaksi_detail_item;

    public Transaksi(int total, CabangToko cabang) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        System.out.println("Tanggal dan waktu yang diformat: " + formattedDate);

        // this.id = id;
        this.kode = kode;
        this.waktu = formattedDate;
        this.total = total;
        // this.transaksi_detail_i;
    }
}