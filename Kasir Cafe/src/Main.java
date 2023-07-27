import java.util.ArrayList;
import java.util.Scanner;

class ItemMenu {
    private String nama;
    private double harga;

    public ItemMenu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }
}

class Pesanan {
    private ArrayList<ItemMenu> daftarPesanan;

    public Pesanan() {
        daftarPesanan = new ArrayList<>();
    }

    public void tambahPesanan(ItemMenu item) {
        daftarPesanan.add(item);
    }

    public ArrayList<ItemMenu> getDaftarPesanan() {
        return daftarPesanan;
    }

    public double hitungTotal() {
        double total = 0.0;
        for (ItemMenu item : daftarPesanan) {
            total += item.getHarga();
        }
        return total;
    }
}

class Pembayaran {
    private double jumlahPembayaran;
    private double kembalian;

    public Pembayaran(double jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
        this.kembalian = 0.0;
    }

    public double getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(double jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }

    public double getKembalian() {
        return kembalian;
    }

    public void hitungKembalian(double total) {
        kembalian = jumlahPembayaran - total;
    }
}

class Kasir {
    private Pesanan pesanan;
    private Pembayaran pembayaran;

    public Kasir() {
        pesanan = new Pesanan();
    }

    public void prosesPesanan(ItemMenu item) {
        pesanan.tambahPesanan(item);
    }

    public double hitungTotal() {
        return pesanan.hitungTotal();
    }

    public void setPembayaran(double jumlahPembayaran) {
        pembayaran = new Pembayaran(jumlahPembayaran);
    }

    public double hitungKembalian() {
        pembayaran.hitungKembalian(hitungTotal());
        return pembayaran.getKembalian();
    }
}

class SistemKasirTokoKopi {
    public static void main(String[] args) {
        ItemMenu menu1 = new ItemMenu("Kopi Hitam", 12000);
        ItemMenu menu2 = new ItemMenu("Cappuccino", 18000);
        ItemMenu menu3 = new ItemMenu("Latte", 15000);
        ItemMenu menu4 = new ItemMenu("Butter Coffee",18000);
        ItemMenu menu5 = new ItemMenu("Roti Bakar Keju",13000);
        ItemMenu menu6 = new ItemMenu("Kue Bolu Coklat", 12000);

        Kasir kasir = new Kasir();

        System.out.println("Selamat datang di Toko Kopi Moen!");
        System.out.println("Menu: ");
        System.out.println("1. " + menu1.getNama() + " - Rp " + menu1.getHarga());
        System.out.println("2. " + menu2.getNama() + " - Rp " + menu2.getHarga());
        System.out.println("3. " + menu3.getNama() + " - Rp " + menu3.getHarga());
        System.out.println("4. " + menu4.getNama() + " - Rp " + menu4.getHarga());
        System.out.println("5. " + menu5.getNama() + " - Rp " + menu5.getHarga());
        System.out.println("6. " + menu6.getNama() + " - Rp " + menu6.getHarga());

        Scanner scanner = new Scanner(System.in);
        char ulangi;
        do {
            System.out.print("Masukkan pilihan menu (1/2/3/5/6): ");
            int pilihan = scanner.nextInt();
            ItemMenu itemMenu = null;
            switch (pilihan) {
                case 1:
                    itemMenu = menu1;
                    break;
                case 2:
                    itemMenu = menu2;
                    break;
                case 3:
                    itemMenu = menu3;
                    break;
                case 4:
                    itemMenu = menu4;
                    break;
                case 5:
                    itemMenu = menu5;
                    break;
                case 6:
                    itemMenu = menu6;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }

            if (itemMenu != null) {
                kasir.prosesPesanan(itemMenu);
                System.out.println(itemMenu.getNama() + " telah ditambahkan ke dalam pesanan.");
            }

            System.out.print("Apakah ingin menambah pesanan lain? (y/n): ");
            ulangi = scanner.next().charAt(0);
        } while (ulangi == 'y' || ulangi == 'Y');

        System.out.println("Total pembayaran: Rp " + kasir.hitungTotal());

        System.out.print("Masukkan jumlah pembayaran: Rp ");
        double jumlahPembayaran = scanner.nextDouble();
        kasir.setPembayaran(jumlahPembayaran);

        double kembalian = kasir.hitungKembalian();
        if (kembalian >= 0) {
            System.out.println("Kembalian: Rp " + kembalian);
        } else {
            System.out.println("Uang pembayaran kurang!");
        }

        System.out.println("Terima kasih telah berkunjung!");
    }
}
