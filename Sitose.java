import java.util.ArrayList;
import java.util.Scanner;

class Sitose {
    Scanner input = new Scanner(System.in);

    ArrayList<ArrayList<String>> users = new ArrayList<>();
    ArrayList<String> user;
    boolean isLogin = false;
    ArrayList<String> userLoggedin = new ArrayList<>();
    int level;

    ArrayList<String> menus = new ArrayList<>();
    ArrayList<String> selectedMenus = new ArrayList<>();
    
    ArrayList<String> jenis = new ArrayList<>();
    ArrayList<jenis> jenisObj = new ArrayList<jenis>();
    
    public static void main(String[] args) {
        Sitose func = new Sitose();
        
        func.run();
    }
    
    void run() {
        welcome();
    }

    void welcome() {
        System.out.println("========SELAMAT DATANG DI SITOSE========");
        System.out.println("===========Sistem Toko Sembako==========");
        System.out.println("==SILAHKAN LOGIN UNTUK MENGAKSES APLIKASI==");
        login();
    }
    
    void login() {
        user = new ArrayList<>();
        user.add("admin"); // username
        user.add("password"); // password
        user.add("admin"); // level
        users.add(new ArrayList<>(user));

        user = new ArrayList<>();
        user.add("gudang"); // username
        user.add("password"); // password
        user.add("gudang"); // level
        users.add(new ArrayList<>(user));

        user = new ArrayList<>();
        user.add("manajer"); // username
        user.add("password"); // password
        user.add("manajer"); // level
        users.add(new ArrayList<>(user));

        user = new ArrayList<>();
        user.add("kasir"); // username
        user.add("password"); // password
        user.add("kasir"); // level
        users.add(new ArrayList<>(user));

        System.out.print("Masukkan Username : ");
        String username = input.nextLine();
        System.out.print("Masukkan Password : ");
        String password = input.nextLine();

        auth(username, password);
    }

    void auth(String usernameInput, String passwordInput) {
        for (int idx = 0; idx < users.size(); idx++) {
            ArrayList<String> user = users.get(idx);

            String username = user.get(0);
            String password = user.get(1);
            String level = user.get(2);

            if (username.equals(usernameInput) && password.equals(passwordInput)) {
                this.isLogin = true;

                if (level == "admin") {
                    this.level = 1;
                } else if (level == "gudang") {
                    this.level = 2;
                } else if (level == "manajer") {
                    this.level = 3;
                } else if (level == "kasir") {
                    this.level = 4;
                }

                this.userLoggedin.add(username);
                this.userLoggedin.add(level);

                setup();
            }
            
            if (!this.isLogin) {
                System.out.println("Username dan password salah!");
                login();
            }
        }
    }

    void setup() {
        showMenu("menu", 0);
    }

    void showMenu(String type, int choosedMenu) {
        if (type == "menu") {
            if (this.level == 1) {
                System.out.println("========Menu Aplikasi========");
                System.out.println("----------MANAJEMEN PRODUK--------");
                System.out.println("1. Jenis Produk");
                System.out.println("2. Kategori Produk");
                System.out.println("3. Data Produk");
                System.out.println("----------MANAJEMEN TOKO CABANG--------");
                System.out.println("4. Manajemen Toko Cabang");
                System.out.println("----------MANAJEMEN TRANSAKSI--------");
                System.out.println("5. Transaksi");
                System.out.println("------------MANAJEMEN PENGGUNA----------");
                System.out.println("6. Pengguna");
                System.out.println("========Pilih Untuk Mengakses Menu========");
                int cm = chooseMenu();
                showMenu("submenu", cm);
            }
        } else if (type == "submenu") {
            if (choosedMenu == 1) {
                if (this.level == 1 || this.level == 2) {
					System.out.println("+-------+----------------------+");
        System.out.printf("| %-4s | %-20s |\n", "Pilih", " Menu Jenisa Produk");
        System.out.println("+------------------------------+");
                    
				 System.out.printf("| %4s| %-21s| \n","  1.  ","  Lihat Data");
				 System.out.printf("| %4s| %-21s| \n","  2.  ","  Tambah Data");
				 System.out.printf("| %4s| %-21s| \n","  3.  ","  Edit Data");
				 System.out.printf("| %4s| %-21s| \n","  4.  ","  Hapus Data");
				 System.out.printf("| %4s| %-21s| \n","  5.  ","  Kembali");
				System.out.println("+------------------------------+");
                    
                    System.out.println("========Pilih Untuk Mengakses Menu========");
                    int csm = chooseSubMenu();

                    switch (csm) {
                        case 1:
                            viewJenis();
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
            }
        }
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

    void viewJenis() {
        System.out.println("====DATA JENIS PRODUK====");

    if (jenisObj.isEmpty()) {
        System.out.println("Tidak ada data untuk ditampilkan.");
    } else {
        // atas
        System.out.println("+----+----------------------+");
        System.out.printf("| %-2s | %-20s |\n", "ID", "Nama Jenisa Produk");
        System.out.println("+----+----------------------+");

        // Isi 
        for (jenis item : jenisObj) {
            System.out.printf("| %-2d | %-20s |\n", item.id, item.name);
        }

        // bawah
        System.out.println("+----+----------------------+");
    }

    showMenu("submenu", 1);
}

    void insertJenis() {
        try {
            System.out.println(">>>>TAMBAH JENIS PRODUK<<<<");
    
            int id = jenisObj.size() + 1;
            input.nextLine(); // wait
            System.out.print("Masukkan nama jenis produk : ");
            String name = input.nextLine();
            
            if (name != "") {
                jenis jn = new jenis(id, name);
                jenisObj.add(jn);
        
                System.out.println(">>>>Data Berhasil Disimpan<<<<");
                
                showMenu("submenu", 1);
            } else {
                System.out.println("Kolom wajib diisi!");
            }
            
        } catch (Exception e) {
            System.out.println("!!!!Data Gagal Disimpan!!!!");
        }
    }

    void updateJenis() {
        System.out.println(">>>>EDIT JENIS PRODUK");

        jenisObj.forEach((item) -> {
            System.out.print("> id : ");
            System.out.print(item.id);
            System.out.print(" - name : ");
            System.out.println(item.name);
        });

        System.out.println("Masukkan id jenis produk : ");
        int id = input.nextInt();
        input.nextLine(); // wait

        System.out.println("Masukkan nama baru : ");
        String name = input.nextLine();
        
        jenisObj.get(id - 1).name = name;
        System.out.println(">>>>Data Berhasil Diubah<<<<");

        showMenu("submenu", 1);
    }

    void removeJenis() {
        System.out.println(">>>>HAPUS JENIS PRODUK<<<<");
        
        jenisObj.forEach((item) -> {
            System.out.print("> id : ");
            System.out.print(item.id);
            System.out.print(" - name : ");
            System.out.println(item.name);
        });

        System.out.println("Masukkan id jenis produk : ");
        int id = input.nextInt();

        jenisObj.remove(id - 1);
        System.out.println(">>>>Data Berhasil Dihapus<<<<");

        showMenu("submenu", 1);
    }
}

class jenis {
    public int id;
    public String name;

    public jenis(int id, String name) {
        this.id = id;
        this.name = name;
    }
}