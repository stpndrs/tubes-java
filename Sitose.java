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
     * variabel level untuk menyimpan level pengguna yang sedang login
     */
    int level;

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
        penggunaObject.add(new User(1, "admin", "password", "admin", null));
        penggunaObject.add(new User(2, "gudang", "password", "gudang", null));
    }

    /*
     * method welcome() menampilkan pesan selamat datang di aplikasi, lalu memanggil
     * method login() agar pengguna bisa melakukan login
     */
    void welcome() {
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("        SELAMAT DATANG DI SITOSE        ");
        System.out.println(" ");
        System.out.println("========================================");

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
                    this.level = 1;
                } else if (user.level.equals("gudang")) {
                    this.level = 2;
                } else if (user.level.equals("manajer")) {
                    this.level = 3;
                } else if (user.level.equals("kasir")) {
                    this.level = 4;
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
        showMenu("menu", 0);
    }

    /*
     * Rakha Jelasin
     */
    void headermenu(String judul){
         int lineLength = 40; // Panjang garis
        String line = "=".repeat(lineLength);

        System.out.println(line);
        System.out.println();
        System.out.printf("%" + (lineLength / 2 + judul.length() / 2) + "s%n", judul);
        System.out.println();
        System.out.println(line);

    }
    void menutemplate() {
        headermenu("MENU APLIKASI");
        System.out.println(" ");
        System.out.println("+-----+--------------------------------+");
        System.out.println("| No  |           Pilihan Menu         |");
        System.out.println("+-----+--------------------------------+");
    }

    /*
     * Rakha Jelasin
     */
    void showMenu(String type, int choosedMenu) {

        String[] menuItems = {
                "Jenis Produk", "Kategori Produk", "Data Produk", "Manajemen Toko Cabang", "Transaksi", "Pengguna",
                "Logout"
        };

        if (type == "menu") {
            if (this.level == 1) {
                menutemplate();

                // menu
                for (int i = 0; i < menuItems.length; i++) {

                    System.out.printf("| %-3d | %-30s |\n", i + 1, menuItems[i]);
                }

                System.out.println("+-----+--------------------------------+");
                System.out.println(" ");
                System.out.println("======== PILIH UNTUK MENGAKSES MENU ========");
                int cm = chooseMenu();
                showMenu("submenu", cm);
            }
            if (this.level == 2) {

                menutemplate();

                for (int i = 0; i <= 3; i++) {
                    System.out.printf("| %-3d | %-30s |\n", i + 1, menuItems[i]);
                    System.out.println("+-----+--------------------------------+");
                    System.out.println(" ");
                    System.out.println("======== PILIH UNTUK MENGAKSES MENU ========");
                }

                int cm = chooseMenu();
                showMenu("submenu", cm);
            }
            if (this.level == 3) {
                menutemplate();

                System.out.printf("| %-3d | %-30s |\n", 1, menuItems[4]);
                System.out.println("+-----+--------------------------------+");
                System.out.println(" ");
                System.out.println("======== PILIH UNTUK MENGAKSES MENU ========");

                int cm = chooseMenu();
                showMenu("submenu", cm);
            }
            if (this.level == 4) {
                menutemplate();

                System.out.printf("| %-3d | %-30s |\n", 1, menuItems[5]);
                System.out.println("+-----+--------------------------------+");
                System.out.println(" ");
                System.out.println("======== PILIH UNTUK MENGAKSES MENU ========");

                int cm = chooseMenu();
                showMenu("submenu", cm);
            }
        } else if (type == "submenu") {
            String[] menuJenis = { "Lihat Data", "Tambah Data", "Edit Data", "Hapus Data", "Kembali" };
            String[] menuKategori = { "Lihat Data", "Tambah Data", "Edit Data", "Hapus Data", "Kembali" };
            String[] menuProduk = { "Lihat Data", "Tambah Data", "Edit Data", "Hapus Data", "kembali" };
            String[] menuCabangToko = { "Lihat Data", "Tambah Data", "Edit Data", "Hapus Data", "kembali" };
            String[] menuUser = { "Lihat Data", "Tambah Data", "Edit Data", "Hapus Data", "kembali" };

            if (choosedMenu == 0) {
                System.out.println("Anda telah logout");
                input.nextLine(); // Clear Buffer
                welcome();
            }
            if (choosedMenu == 1) {
                if (this.level == 1 || this.level == 2) {
                    System.out.println("+-------+----------------------+");
                    System.out.printf("| %-4s | %-20s |\n", "Pilih", " Menu Jenis Produk");
                    System.out.println("+------------------------------+");
                    int i = 0;
                    for (String mj : menuJenis) {
                        System.out.printf("| %6s| %-21s| \n", " " + (i++ + 1) + ". ", "  " + mj);
                    }
                    System.out.println("+------------------------------+");

                    System.out.println("========Pilih Untuk Mengakses Menu========");
                    int csm = chooseSubMenu();

                    switch (csm) {
                        case 1:
                            viewJenis(true);
                            break;
                        case 2:
                            insertJenis();
                            break;
                        case 3:
                            updateJenis();
                            break;
                        case 4:
                            removeJenis();
                            break;
                        case 5:
                            showMenu("menu", 0);
                            break;

                        default:
                            break;
                    }
                }
            } else if (choosedMenu == 2) {
                if (this.level == 1 || this.level == 2) {
                    System.out.println("+-------+----------------------+");
                    System.out.printf("| %-4s | %-20s |\n", "Pilih", " Menu Kategori Produk");
                    System.out.println("+------------------------------+");
                    int i = 0;
                    for (String mk : menuKategori) {
                        System.out.printf("| %6s| %-21s| \n", " " + (i++ + 1) + ". ", "  " + mk);
                    }
                    System.out.println("+------------------------------+");

                    System.out.println("========Pilih Untuk Mengakses Menu========");
                    int csm = chooseSubMenu();

                    switch (csm) {
                        case 1:
                            viewKategori(true);
                            break;
                        case 2:
                            insertKategori();
                            break;
                        case 3:
                            updateKategori();
                            break;
                        case 4:
                            removeKategori();
                            break;
                        case 5:
                            showMenu("menu", 0);
                            break;

                        default:
                            break;
                    }
                }
            } else if (choosedMenu == 3) {
                System.out.println("+------------------------------+");
                int i = 0;
                for (String mj : menuProduk) {
                    System.out.printf("| %6s| %-21s| \n", " " + (i++ + 1) + ". ", "  " + mj);
                }
                System.out.println("+------------------------------+");

                System.out.println("========Pilih Untuk Mengakses Menu========");
                int csm = chooseSubMenu();

                switch (csm) {
                    case 1:
                        viewProduk(true);
                        break;
                    case 2:
                        insertProduk();
                        break;
                    case 3:
                        updateProduk();
                        break;
                    case 4:
                        removeProduk();
                        break;
                    case 5:
                        showMenu("menu", 0);
                        break;
                    default:
                        break;

                }
            } else if (choosedMenu == 4) {
                System.out.println("+------------------------------+");
                int i = 0;
                for (String mj : menuCabangToko) {
                    System.out.printf("| %6s| %-21s| \n", " " + (i++ + 1) + ". ", "  " + mj);
                }
                System.out.println("+------------------------------+");

                System.out.println("========Pilih Untuk Mengakses Menu========");
                int csm = chooseSubMenu();

                switch (csm) {
                    case 1:
                        viewCabangToko(true);
                        break;
                    case 2:
                        insertCabangToko();
                        break;
                    case 3:
                        updateCabangToko();
                        break;
                    case 4:
                        removeCabangToko();
                        break;
                    case 5:
                        showMenu("menu", 0);
                        break;
                    default:
                        break;

                }
            } else if (choosedMenu == 6) {
                System.out.println("+------------------------------+");
                int i = 0;
                for (String mj : menuUser) {
                    System.out.printf("| %6s| %-21s| \n", " " + (i++ + 1) + ". ", "  " + mj);
                }
                System.out.println("+------------------------------+");

                System.out.println("========Pilih Untuk Mengakses Menu========");
                int csm = chooseSubMenu();

                switch (csm) {
                    case 1:
                        viewUser(true);
                        break;
                    case 2:
                        insertUser();
                        break;
                    case 3:
                        updateUser();
                        break;
                    case 4:
                        removeUser();
                        break;
                    case 5:
                        showMenu("menu", 0);
                        break;
                    default:
                        break;

                }
            }
        }
    }

    /*
     * fungsi chooseMenu() akan mengembalikan angka yang sudah dimasukkan oleh
     * pengguna untuk memilih menu apa yang akan di akses
     */
    int chooseMenu() {
        int choosedMenu;
        System.out.print("Masukkan Angka Menu : ");
        choosedMenu = input.nextInt();

        return choosedMenu;
    }

    /*
     * fungsi chooseSubMenu() akan mengembalikan angka yang sudah dimasukkan oleh
     * pengguna untuk memilih submenu apa yang akan di akses
     */
    int chooseSubMenu() {
        int choosedSubMenu;
        System.out.println("Masukkan Angka Menu : ");
        choosedSubMenu = input.nextInt();

        return choosedSubMenu;
    }

    /*
     * method viewJenis() menampilkan data jenis produk, dengan parameter isShowMenu
     * dengan tipe data boolean yang berfungsi untuk memberikan kondisi apakah
     * method showMenu akan dipanggil atau tidak.
     * -> jika isShowMenu bernilai benar, maka akan memanggil method showMenu()
     * dengan parameter "submenu" sebagai tipe menu yang dipanggil dan "1" sebagai
     * submenu yang dipilih untuk ditampilkan
     * -> jika isShowMenu bernilai salah, maka tidak akan memanggil method
     * showMenu()
     */
    // checkpoint
    void viewJenis(boolean isShowMenu) {
        // atas
        System.out.println("+----+------+-------------------+");
        System.out.printf("| %-2s | %-4s | %-17s |\n", "ID", "Kode", "Nama Jenis Produk");
        System.out.println("+----+------+-------------------+");
        /*
         * kondisi dibawah akan mengecek apakah data didalam jenisProdukObject kosong
         * atau tidak,
         * -> jika data kosong, maka tampilkan pesan "Tidak ada data untuk ditampilkan"
         * -> jika data tidak kosong, maka tampilkan data dalam bentuk tabel
         */
        if (jenisProdukObject.isEmpty()) {

            System.out.println("|       Tidak ada data          |");
            System.out.println("+-------------------------------+");

        } else {
            /*
             * Rakha jelasin struktur table nya
             */

            // Isi
            for (Jenis item : jenisProdukObject) {
                System.out.printf("| %-2d | %-4s | %-17s | \n", item.id, item.kode, item.nama);

            }

            // bawah
            System.out.println("+----+------+-------------------+");
        }

        /*
         * kondisi isShowMenu untuk memanggil method showMenu
         */

        if (isShowMenu) {
            showMenu("submenu", 1);
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
<<<<<<< HEAD
            headermenu("TAMBAH JENIS PRODUK");
            
            
=======
            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("             TAMBAH JENIS PRODUK");
            System.out.println(" ");
            System.out.println("========================================");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

            int id = 1;
            if (!jenisProdukObject.isEmpty()) {
                id = jenisProdukObject.get(jenisProdukObject.size() - 1).id + 1;
            }

            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();

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
                jenisProdukObject.add(new Jenis(id, nama, kode));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                /*
                 * memanggil method showMenu() dengan parameter "submenu" sebagai tipe menu yang
                 * dipanggil dan "1" sebagai submenu untuk menampilkan submenu jenis produk
                 */
                showMenu("submenu", 1);
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
<<<<<<< HEAD
        headermenu("EDIT JENIS PRODUK");
        
=======
        try {
            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("             EDIT JENIS PRODUK");
            System.out.println(" ");
            System.out.println("========================================");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

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
             * memanggil method showMenu() dengan parameter "submenu" sebagai tipe menu yang
             * dipanggil dan "1" sebagai submenu untuk menampilkan submenu jenis produk
             */
            showMenu("submenu", 1);
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    /*
     * method removeJenis() digunakkan untuk menghapus data jenis produk
     */
    void removeJenis() {
<<<<<<< HEAD
        headermenu("HAPUS JENIS PRODUK");
        
=======
        try {
            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("          HAPUS JENIS PRODUK");
            System.out.println(" ");
            System.out.println("========================================");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

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
            jenisProdukObject.removeIf(n -> n.id == id);
            System.out.println(">>>>Data Berhasil Dihapus<<<<");

            /*
             * memanggil method showMenu() dengan parameter "submenu" sebagai tipe menu yang
             * dipanggil dan "1" sebagai submenu untuk menampilkan submenu jenis produk
             */
            showMenu("submenu", 1);
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    /*
     * method viewKategori() menampilkan data jenis produk, dengan parameter
     * isShowMenu
     * dengan tipe data boolean yang berfungsi untuk memberikan kondisi apakah
     * method showMenu akan dipanggil atau tidak.
     * -> jika isShowMenu bernilai benar, maka akan memanggil method showMenu()
     * dengan parameter "submenu" sebagai tipe menu yang dipanggil dan "2" sebagai
     * submenu yang dipilih untuk ditampilkan
     * -> jika isShowMenu bernilai salah, maka tidak akan memanggil method
     * showMenu()
     */
    void viewKategori(boolean isShowMenu) {
        /*
         * kondisi dibawah akan mengecek apakah data didalam kategoriProdukObject kosong
         * atau tidak,
         * -> jika data kosong, maka tampilkan pesan "Tidak ada data untuk ditampilkan"
         * -> jika data tidak kosong, maka tampilkan data dalam bentuk tabel
         */
        if (kategoriProdukObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            // atas
<<<<<<< HEAD
            System.out.println("+----+----+----------------------+");
            System.out.printf("| %-2s |%-2s| %-18s |\n", "ID", "Kode", "Nama Kategori Produk" );
            System.out.println("+----+----+----------------------+");
=======
            System.out.println("+----+----------------------+");
            System.out.printf("| %-2s |%-2| %-18s |\n", "ID", "Kode", "Nama Kategori Produk");
            System.out.println("+----+----------------------+");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

            // Isi
            for (Kategori item : kategoriProdukObject) {
                System.out.printf("| %-2s | %-2s | %-20s |\n", item.id, item.kode, item.nama);
                
            }

            // bawah
            System.out.println("+----+----+----------------------+");
        }

        /*
         * kondisi isShowMenu untuk memanggil method showMenu
         */

        if (isShowMenu) {
            showMenu("submenu", 2);
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
<<<<<<< HEAD
            headermenu("TAMBAH KATEGORI PRODUK");
            
=======
            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("         TAMBAH KATEGORI PRODUK");
            System.out.println(" ");
            System.out.println("========================================");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

            int id = 1;
            if (!kategoriProdukObject.isEmpty()) {
                id = kategoriProdukObject.get(kategoriProdukObject.size() - 1).id + 1;
            }
            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();

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
                kategoriProdukObject.add(new Kategori(id, nama, kode));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                /*
                 * memanggil method showMenu() dengan parameter "submenu" sebagai tipe menu yang
                 * dipanggil dan "2" sebagai submenu untuk menampilkan submenu kategori produk
                 */
                showMenu("submenu", 2);
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
<<<<<<< HEAD
        headermenu("EDIT KATEGORI PRODUK");
        
=======
        try {
            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("        EDIT KATEGORI PRODUK");
            System.out.println(" ");
            System.out.println("========================================");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

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

            /*
             * memanggil method showMenu() dengan parameter "submenu" sebagai tipe menu yang
             * dipanggil dan "1" sebagai submenu untuk menampilkan submenu jenis produk
             */
            showMenu("submenu", 2);
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    /*
     * method removeKategori() digunakkan untuk menghapus data kategori produk
     */
    void removeKategori() {
<<<<<<< HEAD
        headermenu("HAPUS KATEGORI PRODUK");

       
=======
        try {
            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("            HAPUS KATEGORI PRODUK");
            System.out.println(" ");
            System.out.println("========================================");

            /*
             * memanggil method viewKategori() untuk menampilkan data kategori produk agar
             * pengguna bisa melihat id kategori produk, dengan parameter isShowMenu
             * bernilai
             * false agar menu tidak ditampilkan
             */
            viewKategori(false);
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

            /*
             * pengguna memasukkan id jenis produk
             */
            System.out.println("Masukkan id kategori produk : ");
            int id = input.nextInt();

            /*
             * removeIf akan mencari ............. BELOMMMMMMMMMMMMMMMMM
             */
            kategoriProdukObject.removeIf(n -> n.id == id);
            System.out.println(">>>>Data Berhasil Dihapus<<<<");

            /*
             * memanggil method showMenu() dengan parameter "submenu" sebagai tipe menu yang
             * dipanggil dan "2" sebagai submenu untuk menampilkan submenu kategori produk
             */
            showMenu("submenu", 2);
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void viewProduk(boolean isShowMenu) {
        if (kategoriProdukObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            for (Produk item : produkObject) {
                System.out.println(item.id);
                System.out.println(item.nama);
                System.out.println(item.kode);
                System.out.println(item.jenis);
                System.out.println(item.kategori);
                System.out.println(item.stok);
                System.out.println(item.harga);
            }
        }

        if (isShowMenu) {
            showMenu("submenu", 3);
        }
    }

    void insertProduk() {
        try {
            headermenu("TAMBAH PRODUK");

            int id = 1;
            if (!produkObject.isEmpty()) {
                id = produkObject.get(produkObject.size() - 1).id + 1;
            }
            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();

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
                produkObject.add(new Produk(id, nama, jenisProdukObject.get(id_jenis - 1),
                        kategoriProdukObject.get(id_kategori - 1), stok,
                        harga));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                showMenu("submenu", 3);
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
            headermenu("EDIT PRODUK");

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

                showMenu("submenu", 3);
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void removeProduk() {
        headermenu("HAPUS PRODUK");

        // tampilkan kategori produk
        viewProduk(false);

        System.out.println("Masukkan id produk : ");
        int id = input.nextInt();

        produkObject.removeIf(n -> n.id == id);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 3);
    }

    void viewCabangToko(boolean isShowMenu) {
        if (cabangTokoObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            for (CabangToko item : cabangTokoObject) {
                System.out.println(item.id);
                System.out.println(item.nama);
                System.out.println(item.kode);
                System.out.println(item.telepon);
                System.out.println(item.alamat);
            }

            // bawah
            System.out.println("+----+----------------------+");
        }

        if (isShowMenu) {
            showMenu("submenu", 4);
        }
    }

    void insertCabangToko() {
        try {
<<<<<<< HEAD
            headermenu("TAMBAH CABANG PRODUK");
            
=======

            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("           TAMBAH KATEGORI PRODUK");
            System.out.println(" ");
            System.out.println("========================================");

>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd
            int id = 1;
            if (!cabangTokoObject.isEmpty()) {
                id = cabangTokoObject.get(cabangTokoObject.size() - 1).id + 1;
            }
            /*
             * input.nextLine(); digunakan untuk menghapus sisa enter yang tertinggal dari
             * input sebelumnya, supaya saat kita membaca input berikutnya dengan
             * nextLine(), program tidak langsung melewatkannya.
             */
            input.nextLine();
            System.out.print("Masukkan nama cabang : ");
            String nama = input.nextLine();
            System.out.print("Masukkan kode cabang : ");
            String kode = input.nextLine();
            System.out.print("Masukkan telepon cabang : ");
            String telepon = input.nextLine();
            System.out.print("Masukkan alamat cabang : ");
            String alamat = input.nextLine();

            if (nama != "" && telepon != "") {
                cabangTokoObject.add(new CabangToko(id, nama, kode, telepon, alamat));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                showMenu("submenu", 4);
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void updateCabangToko() {
        headermenu("EDIT CABANG TOKO");


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

        showMenu("submenu", 4);
    }

    void removeCabangToko() {
<<<<<<< HEAD
        headermenu("HAPUS CABANG TOKO");
		
=======
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("          HAPUS CABANG TOKO");
        System.out.println(" ");
        System.out.println("========================================");

>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd
        // tampilkan cabang toko
        viewCabangToko(false);

        System.out.println("Masukkan id cabang toko : ");
        int id = input.nextInt();

        cabangTokoObject.removeIf(n -> n.id == id);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 4);
    }

    void viewUser(boolean isShowMenu) {
        if (penggunaObject.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            for (User item : penggunaObject) {
                System.out.println(item.id);
                System.out.println(item.username);
                System.out.println(item.password);
                System.out.println(item.level);
                if (item.level.equals("manajer") || item.level.equals("kasir"))
                    System.out.println(item.cabangtoko.toString());
            }
        }
        if (isShowMenu) {
            showMenu("submenu", 6);
        }
    }

    void insertUser() {
        try {
<<<<<<< HEAD
            headermenu("TAMBAH USER");
			
=======
            System.out.println("========================================");
            System.out.println(" ");
            System.out.println("          TAMBAH USER");
            System.out.println(" ");
            System.out.println("========================================");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

            int id = 1;
            if (!penggunaObject.isEmpty()) {
                id = penggunaObject.get(penggunaObject.size() - 1).id + 1;
            }
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
                penggunaObject.add(new User(id, username, password, level, cabangToko));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                showMenu("submenu", 6);
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void updateUser() {
<<<<<<< HEAD
        headermenu("EDIT USER");
=======
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("          EDIT USER");
        System.out.println(" ");
        System.out.println("========================================");
>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd

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

        showMenu("submenu", 6);
    }

    void removeUser() {
<<<<<<< HEAD
        headermenu("HAPUS USER");
			
=======
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("          HAPUS USER");
        System.out.println(" ");
        System.out.println("========================================");

>>>>>>> 62d78111ce040201ac0da79141b7999ec0357dcd
        // tampilkan kategori produk
        viewUser(false);

        System.out.println("Masukkan id kategori produk : ");
        int id = input.nextInt();

        penggunaObject.removeIf(n -> n.id == id);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 6);
    }
}

class User {
    int id;
    String username, password, level;
    CabangToko cabangtoko;

    User(int id, String username, String password, String level, CabangToko cabangtoko) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
        this.cabangtoko = cabangtoko;
    }
}

class Jenis {
    public int id;
    public String nama, kode;

    public Jenis(int id, String nama, String kode) {
        this.id = id;
        this.nama = nama;
        this.kode = kode;
    }
}

class Kategori {
    int id;
    String nama, kode;

    public Kategori(int id, String nama, String kode) {
        this.id = id;
        this.nama = nama;
        this.kode = kode;
    }
}

class Produk {
    int id, stok, harga;
    String nama, kode;
    Jenis jenis;
    Kategori kategori;

    public Produk(int id, String nama, Jenis jenis, Kategori kategori, int stok, int harga) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.kategori = kategori;
        this.kode = this.jenis.kode + this.kategori.kode + String.format("%04d", id);
        // (B-MA-0001 = BMA0001 dengan B (barang) adalah kode dari
        // jenis, MA (makanan) kode dari kategori dan 0001 didapat dari nomor id)
        this.stok = stok;
        this.harga = harga;
    }
}

class CabangToko {
    int id;
    String kode, nama, telepon, alamat;

    public CabangToko(int id, String nama, String kode, String telepon, String alamat) {
        this.id = id;
        this.nama = nama;
        this.kode = kode;
        this.telepon = telepon;
        this.alamat = alamat;
    }
}

class Transaksi {
    int id;
    String kode, waktu, total;
    ArrayList<String> transaksi_detail;

    public Transaksi(int id, String kode, String waktu, String total) {
        this.id = id;
        this.kode = kode;
        this.waktu = waktu;
        this.total = total;
        this.transaksi_detail = new ArrayList<>();
    }
}