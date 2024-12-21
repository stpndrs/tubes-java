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
        if (this.level == 1) {

        }
        menu();
    }

    void menu() {
        
    }
}