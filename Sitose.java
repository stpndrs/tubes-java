import java.util.ArrayList;
import java.util.Scanner;

class Sitose {
    Scanner input = new Scanner(System.in);

    ArrayList<User> users = new ArrayList<>();

    int level;

    ArrayList<String> menus = new ArrayList<>();
    ArrayList<String> selectedMenus = new ArrayList<>();

    ArrayList<Jenis> jenisObj = new ArrayList<Jenis>();
    ArrayList<Kategori> kategoriObj = new ArrayList<Kategori>();
    ArrayList<Produk> produkObj = new ArrayList<Produk>();
    ArrayList<CabangToko> cabangtokoObj = new ArrayList<CabangToko>();

    public static void main(String[] args) {
        Sitose func = new Sitose();

        func.run();
    }

    void run() {
        initUser();
        welcome();
    }

    void initUser() {
        users.add(new User(1, "admin", "password", "admin", null));
        users.add(new User(2, "gudang", "password", "gudang", null));
    }

    void welcome() {
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("        SELAMAT DATANG DI SITOSE        ");
        System.out.println(" ");
        System.out.println("========================================");

        login();
    }

    void login() {
        System.out.print("Masukkan Username : ");

        String username = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        if (auth(username, password)) {
            setup();
        } else {
            login();
        }
    }

    boolean auth(String usernameInput, String passwordInput) {
        for (User user : users) {
            if (user.username.equals(usernameInput) && user.password.equals(passwordInput)) {
                // userLoggedin = user
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

    void setup() {
        showMenu("menu", 0);
    }

    void menutemplate() {
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("             MENU APLIKASI");
        System.out.println(" ");
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("+-----+--------------------------------+");
        System.out.println("| No  |           Pilihan Menu         |");
        System.out.println("+-----+--------------------------------+");

    }

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

        // int a = 0;
        // a++;
        // if (choosedMenu == 1) {
        // if (this.level == 1 || this.level == 2) {
        // System.out.println(a + ". Lihat Data");
        // System.out.println(a + ". Tambah Data");
        // System.out.println(a + ". Edit Data");
        // System.out.println(a + ". Hapus Data");
        // System.out.println(a + ". Kembali");
        // int csm = 1;
        // switch (csm) {
        // case 1:

        // break;

        // default:
        // break;
        // }
        // } else if (choosedMenu == 2) {
        // System.out.println(a + ". Lihat Data");
        // System.out.println(a + ". Tambah Data");
        // System.out.println(a + ". Edit Data");
        // System.out.println(a + ". Hapus Data");
        // System.out.println(a + ". Kembali");
        // } else if (choosedMenu == 3) {
        // System.out.println(a + ". Lihat Data");
        // System.out.println(a + ". Tambah Data");
        // System.out.println(a + ". Edit Data");
        // System.out.println(a + ". Hapus Data");
        // System.out.println(a + ". Kembali");
        // }
        // }
    }

    int chooseMenu() {
        int choosedMenu;
        System.out.print("Masukkan Angka Menu : ");
        choosedMenu = input.nextInt();

        return choosedMenu;
    }

    int chooseSubMenu() {
        int choosedSubMenu;
        System.out.println("Masukkan Angka Menu : ");
        choosedSubMenu = input.nextInt();

        return choosedSubMenu;
    }

    // checkpoint
    void viewJenis(boolean isShowMenu) {
        if (jenisObj.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            // atas
            System.out.println("+----+------+-------------------+");
            System.out.printf("| %-2s | %-4s | %-17s |\n", "ID", "Kode", "Nama Jenis Produk");
            System.out.println("+----+------+-------------------+");

            // Isi
            for (Jenis item : jenisObj) {
                System.out.printf("| %-2d | %-4s | %-17s | \n", item.id, item.kode, item.name);
                // System.out.println(item.kode);
            }

            // bawah
            System.out.println("+----+------+-------------------+");
        }

        if (isShowMenu) {
            showMenu("submenu", 1);
        }
    }

    void insertJenis() {
        try {
            System.out.println(">>>>TAMBAH JENIS PRODUK<<<<");

            int id = 1;
            if (!jenisObj.isEmpty()) {
                id = jenisObj.get(jenisObj.size() - 1).id + 1;
            }
            input.nextLine(); // wait
            System.out.print("Masukkan nama jenis produk : ");
            String name = input.nextLine();
            System.out.print("Masukkan kode jenis produk : ");
            String kode = input.nextLine();

            if (name != "" && kode != "") {
                jenisObj.add(new Jenis(id, name, kode));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                showMenu("submenu", 1);
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void updateJenis() {
        System.out.println("========================================");
        System.out.println(" ");
        System.out.println("             EDIT JENIS PRODUK");
        System.out.println(" ");
        System.out.println("========================================");

        // tampilkan jenis produk
        viewJenis(false);
        // jika tidak ada yg harus di tampilkan harus tampilkan menu produk
        if (jenisObj.isEmpty()) {
            System.out.println("Tidak ada data yang dapat diubah. Kembali ke menu utama.");
            showMenu("submenu", 1);
            return;
        }
        System.out.println("Masukkan id jenis produk : ");
        int id = input.nextInt();
        input.nextLine(); // wait

        System.out.println("Masukkan nama baru : ");
        String name = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String kode = input.nextLine();

        // tambahkan if kosong maka tidak berubah ---------------------
        jenisObj.get(id - 1).name = name;
        jenisObj.get(id - 1).kode = kode;
        System.out.println(">>>>Data Berhasil Diubah<<<<");

        showMenu("submenu", 1);
    }

    void removeJenis() {
        System.out.println(">>>>HAPUS JENIS PRODUK<<<<");

        // tampilkan jenis produk
        viewJenis(false);

        System.out.println("Masukkan id jenis produk : ");
        int id = input.nextInt();

        jenisObj.removeIf(n -> n.id == id);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 1);
    }

    void viewKategori(boolean isShowMenu) {
        if (kategoriObj.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            // atas
            System.out.println("+----+----------------------+");
            System.out.printf("| %-2s | %-20s |\n", "ID", "Nama Kategori Produk", "Kode Kategori");
            System.out.println("+----+----------------------+");

            // Isi
            for (Kategori item : kategoriObj) {
                System.out.printf("| %-2d | %-20s |\n", item.id, item.name, item.kode);
                System.out.println(item.kode);
            }

            // bawah
            System.out.println("+----+----------------------+");
        }

        if (isShowMenu) {
            showMenu("submenu", 2);
        }
    }

    void insertKategori() {
        try {
            System.out.println(">>>>TAMBAH KATEGORI PRODUK<<<<");

            int id = 1;
            if (!kategoriObj.isEmpty()) {
                id = kategoriObj.get(kategoriObj.size() - 1).id + 1;
            }
            input.nextLine(); // wait
            System.out.print("Masukkan nama kategori produk : ");
            String name = input.nextLine();
            System.out.print("Masukkan kode kategori produk : ");
            String kode = input.nextLine();

            if (name != "" && kode != "") {
                kategoriObj.add(new Kategori(id, name, kode));

                System.out.println(">>>>Data Berhasil Disimpan<<<<");

                showMenu("submenu", 2);
            } else {
                System.out.println("Kolom wajib diisi!");
            }

        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
            System.out.println("error : " + e.getMessage());
        }
    }

    void updateKategori() {
        System.out.println(">>>>EDIT KATEGORI PRODUK");

        // tampilkan kategori produk
        viewKategori(false);

        System.out.println("Masukkan id kategori produk : ");
        int id = input.nextInt();
        input.nextLine(); // wait

        System.out.println("Masukkan nama baru : ");
        String name = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String kode = input.nextLine();

        // tambahkan if kosong maka tidak ada yang diubah ---------------------
        kategoriObj.get(id - 1).name = name;
        kategoriObj.get(id - 1).kode = kode;
        System.out.println(">>>>Data Berhasil Diubah<<<<");

        showMenu("submenu", 2);
    }

    void removeKategori() {
        System.out.println(">>>>HAPUS KATEGORI PRODUK<<<<");

        // tampilkan kategori produk
        viewKategori(false);

        System.out.println("Masukkan id kategori produk : ");
        int id = input.nextInt();

        kategoriObj.removeIf(n -> n.id == id);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 2);
    }

    void viewProduk(boolean isShowMenu) {
        if (kategoriObj.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            for (Produk item : produkObj) {
                System.out.println(item.id);
                System.out.println(item.name);
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
            System.out.println(">>>>TAMBAH PRODUK<<<<");

            int id = 1;
            if (!produkObj.isEmpty()) {
                id = produkObj.get(produkObj.size() - 1).id + 1;
            }
            input.nextLine(); // wait

            System.out.print("Masukkan nama produk : ");
            String name = input.nextLine();

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

            if (name != "") {
                produkObj.add(new Produk(id, name, jenisObj.get(id_jenis - 1), kategoriObj.get(id_kategori - 1), stok,
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
            System.out.println(">>>>EDIT PRODUK<<<<");

            int id = input.nextInt();
            input.nextLine(); // wait

            System.out.print("Masukkan nama produk baru : ");
            String name = input.nextLine();

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

            if (name != "") {
                produkObj.get(id - 1).name = name;
                produkObj.get(id - 1).jenis = jenisObj.get(id_jenis - 1);
                produkObj.get(id - 1).kategori = kategoriObj.get(id_kategori - 1);
                produkObj.get(id - 1).stok = stok;
                produkObj.get(id - 1).harga = harga;

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
        System.out.println(">>>>HAPUS PRODUK<<<<");

        // tampilkan kategori produk
        viewProduk(false);

        System.out.println("Masukkan id produk : ");
        int id = input.nextInt();

        produkObj.removeIf(n -> n.id == id);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 3);
    }

    void viewCabangToko(boolean isShowMenu) {
        if (cabangtokoObj.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            for (CabangToko item : cabangtokoObj) {
                System.out.println(item.id);
                System.out.println(item.name);
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
            System.out.println(">>>>TAMBAH KATEGORI PRODUK<<<<");

            int id = 1;
            if (!cabangtokoObj.isEmpty()) {
                id = cabangtokoObj.get(cabangtokoObj.size() - 1).id + 1;
            }
            input.nextLine(); // wait
            System.out.print("Masukkan nama cabang : ");
            String name = input.nextLine();
            System.out.print("Masukkan kode cabang : ");
            String kode = input.nextLine();
            System.out.print("Masukkan telepon cabang : ");
            String telepon = input.nextLine();
            System.out.print("Masukkan alamat cabang : ");
            String alamat = input.nextLine();

            if (name != "" && telepon != "") {
                cabangtokoObj.add(new CabangToko(id, name, kode, telepon, alamat));

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
        System.out.println(">>>>EDIT CABANG TOKO");

        // tampilkan cabang toko produk
        viewCabangToko(false);

        System.out.println("Masukkan id cabang toko : ");
        int id = input.nextInt();
        input.nextLine(); // wait

        System.out.println("Masukkan nama baru : ");
        String name = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String kode = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String telepon = input.nextLine();
        System.out.println("Masukkan kode baru : ");
        String alamat = input.nextLine();

        // tambahkan if kosong maka tidak ada yang diubah ---------------------
        cabangtokoObj.get(id - 1).name = name;
        cabangtokoObj.get(id - 1).kode = kode;
        cabangtokoObj.get(id - 1).telepon = telepon;
        cabangtokoObj.get(id - 1).alamat = alamat;
        System.out.println(">>>>Data Berhasil Diubah<<<<");

        showMenu("submenu", 4);
    }

    void removeCabangToko() {
        System.out.println(">>>>HAPUS CABANG TOKO<<<<");

        // tampilkan cabang toko
        viewCabangToko(false);

        System.out.println("Masukkan id cabang toko : ");
        int id = input.nextInt();

        cabangtokoObj.removeIf(n -> n.id == id);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 4);
    }

    void viewUser(boolean isShowMenu) {
        if (users.isEmpty()) {
            System.out.println("Tidak ada data untuk ditampilkan.");
        } else {
            for (User item : users) {
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

    // BELOM SELESAIII
    void insertUser() {
        try {
            System.out.println(">>>>TAMBAH USER<<<<");

            int id = 1;
            if (!users.isEmpty()) {
                id = users.get(users.size() - 1).id + 1;
            }
            input.nextLine(); // wait
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

                cabangToko = cabangtokoObj.get(cabangtoko - 1);
            }

            if (username != "" && password != "") {
                users.add(new User(id, username, password, level, cabangToko));

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
        System.out.println(">>>>EDIT USER");

        // tampilkan kategori produk
        viewUser(false);

        System.out.println("Masukkan id kategori produk : ");
        int id = input.nextInt();
        input.nextLine(); // wait

        System.out.println("Masukkan username baru : ");
        String username = input.nextLine();
        System.out.println("Masukkan password baru : ");
        String password = input.nextLine();

        // tambahkan if kosong maka tidak ada yang diubah ---------------------
        users.get(id - 1).username = username;
        users.get(id - 1).password = password;
        System.out.println(">>>>Data Berhasil Diubah<<<<");

        showMenu("submenu", 6);
    }

    void removeUser() {
        System.out.println(">>>>HAPUS USER<<<<");

        // tampilkan kategori produk
        viewUser(false);

        System.out.println("Masukkan id kategori produk : ");
        int id = input.nextInt();

        users.removeIf(n -> n.id == id);
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
    public String name, kode;

    public Jenis(int id, String name, String kode) {
        this.id = id;
        this.name = name;
        this.kode = kode;
    }
}

class Kategori {
    int id;
    String name, kode;

    public Kategori(int id, String name, String kode) {
        this.id = id;
        this.name = name;
        this.kode = kode;
    }
}

class Produk {
    int id, stok, harga;
    String name, kode;
    Jenis jenis;
    Kategori kategori;

    public Produk(int id, String name, Jenis jenis, Kategori kategori, int stok, int harga) {
        this.id = id;
        this.name = name;
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
    String kode, name, telepon, alamat;

    public CabangToko(int id, String name, String kode, String telepon, String alamat) {
        this.id = id;
        this.name = name;
        this.kode = kode;
        this.telepon = telepon;
        this.alamat = alamat;
    }
}