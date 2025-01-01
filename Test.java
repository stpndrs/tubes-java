import java.util.Scanner;

class Test {
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        User currentUser = new User(1, "Stevan", "hehe", "UA");
        // level = admin manajer kasir
        /*
        * Menu
        * menu 1
        * -> lihat
        * -> tambah
        * -> edit
        * -> hapus
        * 
        * menu 2
        * -> lihat
        * -> tambah
        * -> edit
        * -> hapus
        * 
        * menu 3
        * -> lihat
        * -> tambah
        * -> hapus
        * 
        * ================================
        * 
        * Akses
        * admin
        * -> menu 1 = semua
        * -> menu 2 = semua
        * -> menu 3 = lihat hapus
        * 
        * manajer
        * -> menu 2 = lihat edit
         * -> menu 3 = lihat
         * 
         * kasir
         * -> menu 3 = lihat tambah
         */
        
        // 0 = nama menu, 1 = kode menu
        header("semabarang aja dulu");
        String[][] mainMenus = { { "menu 1", "A" }, { "menu 2", "B" }, { "menu 3", "C" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "ABC" }, { "UB", "BC" }, { "UC", "C" } };
        
        output(mainMenus, mainMenuAccess, currentUser);
      
        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                // TINGGAL DIGANTI JADI SUBMENU 1
                // subMenu3(currentUser);
                break;
            case "B":
                // TINGGAL DIGANTI JADI SUBMENU 2
                // subMenu3(currentUser);
                break;
            case "C":
                subMenu3(currentUser);
                break;

            default:
                break;
        }
    }
    static void header(String title){

        int width = 29;
        int length = title.length();
        int sepasi = (width - length) / 2;

        System.out.println("=============================");
        System.out.println("");
        System.out.printf("%" + sepasi + "s%s%" + sepasi + "s\n", "", title, "");
        System.out.println("");
        System.out.println("=============================");
    }

    static void output(String[][] mainMenus, String[][] mainMenuAccess, User currentUser){
        String userAccess = findUserAccess(mainMenuAccess, currentUser.level);
        // cek jika false brarti ga ktemu
        if (userAccess.equals("false")) {
            System.out.println("There is an error");
            return;
        }
    
        System.out.println("+--------------------------+");
        System.out.printf("| %-4s | %-16s |\n","pilih", "Menu");
        System.out.println("+--------------------------+");
        for (String[] mainMenu : mainMenus) {
            if (!userAccess.contains(mainMenu[1]))
                continue;
                
            System.out.printf("| %-5s | %-16s |\n", mainMenu[1], mainMenu[0] );
    
        }
        System.out.println("+--------------------------+");
    }

    // fungsi buat nyari array aksesnya
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

    public static void subMenu1(User currentUser) {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "lihat", "A" }, { "tambah", "B" }, { "edit", "C" }, { "hapus", "D" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "AD" }, { "UB", "A" }, { "UC", "AB" } };

        header("A dil A");
        
        output(mainMenus, mainMenuAccess, currentUser);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewMenu1();
                break;
            case "B":
                tambahMenu1();
                break;
            case "C":
                editMenu1();
                break;
            case "D":
                hapusMenu1();
                break;

            default:
                break;
        }
    }

    public static void subMenu2(User currentUser) {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "lihat", "A" }, { "tambah", "B" }, { "edit", "C" }, { "hapus", "D" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "AD" }, { "UB", "A" }, { "UC", "AB" } };
        
        output(mainMenus, mainMenuAccess, currentUser);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewMenu2();
                break;
            case "B":
                tambahMenu2();
                break;
            case "C":
                editMenu2();
                break;
            case "D":
                hapusMenu2();
                break;

            default:
                break;
        }
    }

    public static void subMenu3(User currentUser) {
        // 0 = nama menu, 1 = kode menu
        String[][] mainMenus = { { "lihat", "A" }, { "tambah", "B" }, { "edit", "C" }, { "hapus", "D" } };
        // 0 = kode user, 1 = string dari kode menu
        String[][] mainMenuAccess = { { "UA", "AD" }, { "UB", "A" }, { "UC", "AB" } };
        
        output(mainMenus, mainMenuAccess, currentUser);

        System.out.println("Masukkan kode menu");
        String inpmenu = input.nextLine();

        switch (inpmenu) {
            case "A":
                viewMenu3();
                break;
            case "B":
                tambahMenu3();
                break;
            case "C":
                editMenu3();
                break;
            case "D":
                hapusMenu3();
                break;

            default:
                break;
        }
    }

    public static void viewMenu1() {
        System.out.println("View Menu 1");
    }

    public static void tambahMenu1() {
        System.out.println("tambah Menu 1");
    }

    public static void editMenu1() {
        System.out.println("edit Menu 1");
    }

    public static void hapusMenu1() {
        System.out.println("hapus Menu 1");
    }

    public static void viewMenu2() {
        System.out.println("View Menu 2");
    }

    public static void tambahMenu2() {
        System.out.println("tambah Menu 2");
    }

    public static void editMenu2() {
        System.out.println("edit Menu 2");
    }

    public static void hapusMenu2() {
        System.out.println("hapus Menu 2");
    }

    public static void viewMenu3() {
        System.out.println("View Menu 3");
    }

    public static void tambahMenu3() {
        System.out.println("tambah Menu 3");
    }

    public static void editMenu3() {
        System.out.println("edit Menu 3");
    }

    public static void hapusMenu3() {
        System.out.println("hapus Menu 3");
    }
}

class User {
    int id;
    String username, password, level;

    User(int id, String username, String password, String level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
    }
}