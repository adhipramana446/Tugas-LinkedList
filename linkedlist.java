import java.util.Scanner;

class Node {
    String nim;
    String nama;
    String kelas;
    Node next;

    public Node(String nim, String nama, String kelas) {
        this.nim = nim;
        this.nama = nama;
        this.kelas = kelas;
        this.next = null;
    }
}

public class linkedlist {
    static Node head = null;
    static int count = 0;
    static Scanner scanner = new Scanner(System.in);

    // 1. Insert at Beginning
    public static void insertAtBeginning() {
        System.out.println("\n--- Insert at Beginning ---");
        System.out.print("Masukkan NIM: "); String nim = scanner.nextLine();
        System.out.print("Masukkan Nama: "); String nama = scanner.nextLine();
        System.out.print("Masukkan Kelas: "); String kelas = scanner.nextLine();

        Node newNode = new Node(nim, nama, kelas);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        count++;
        System.out.println("Data berhasil ditambahkan di awal.");
    }

    // 2. Insert at Given Position
    public static void insertAtPosition() {
        System.out.println("\n--- Insert at Given Position ---");
        System.out.println("Range posisi valid: 1 sampai " + (count + 1));
        System.out.print("Masukkan Posisi: ");
        int pos = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (pos < 1 || pos > count + 1) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (pos == 1) {
            insertAtBeginning(); // Logic sama jika posisi 1 (tetapi count nambah di dlm fungsi itu)
            // Karena fungsi insertAtBeginning menambah count, kita harus hati-hati agar tidak double count jika dipanggil langsung
            // Untuk simplifikasi tugas ini, kita copy logic insert manual saja agar count terkontrol disini
            // *Koreksi*: Di sini saya panggil fungsi input manual saja agar alur input konsisten
            return; 
        }

        System.out.print("Masukkan NIM: "); String nim = scanner.nextLine();
        System.out.print("Masukkan Nama: "); String nama = scanner.nextLine();
        System.out.print("Masukkan Kelas: "); String kelas = scanner.nextLine();
        Node newNode = new Node(nim, nama, kelas);

        Node temp = head;
        // Traverse sampai node sebelum posisi tujuan (pos-2 karena index mulai 0 secara logika pointer)
        for (int i = 1; i < pos - 1; i++) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        count++;
        System.out.println("Data berhasil ditambahkan di posisi " + pos);
    }

    // 3. Insert at End
    public static void insertAtEnd() {
        System.out.println("\n--- Insert at End ---");
        System.out.print("Masukkan NIM: "); String nim = scanner.nextLine();
        System.out.print("Masukkan Nama: "); String nama = scanner.nextLine();
        System.out.print("Masukkan Kelas: "); String kelas = scanner.nextLine();

        Node newNode = new Node(nim, nama, kelas);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        count++;
        System.out.println("Data berhasil ditambahkan di akhir.");
    }

    // 4. Delete from Beginning
    public static void deleteFromBeginning() {
        if (head == null) {
            System.out.println("List kosong, tidak ada yang dihapus.");
            return;
        }
        System.out.println("Menghapus: " + head.nama);
        head = head.next;
        count--;
    }

    // 5. Delete Given Position
    public static void deleteAtPosition() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }
        System.out.println("Range hapus valid: 1 sampai " + count);
        System.out.print("Masukkan Posisi hapus: ");
        int pos = scanner.nextInt();
        scanner.nextLine();

        if (pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        if (pos == 1) {
            deleteFromBeginning();
            return;
        }

        Node temp = head;
        for (int i = 1; i < pos - 1; i++) {
            temp = temp.next;
        }
        
        System.out.println("Menghapus: " + temp.next.nama);
        temp.next = temp.next.next;
        count--;
    }

    // 6. Delete from End
    public static void deleteFromEnd() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }
        
        if (head.next == null) { // Hanya 1 data
            deleteFromBeginning();
            return;
        }

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        System.out.println("Menghapus: " + temp.next.nama);
        temp.next = null;
        count--;
    }

    // 7. Delete First Occurrence (by NIM)
    public static void deleteByNim() {
        if (head == null) {
            System.out.println("List kosong.");
            return;
        }
        System.out.print("Masukkan NIM yang akan dihapus: ");
        String targetNim = scanner.nextLine();

        // Cek Head
        if (head.nim.equals(targetNim)) {
            deleteFromBeginning();
            return;
        }

        Node temp = head;
        boolean found = false;
        while (temp.next != null) {
            if (temp.next.nim.equals(targetNim)) {
                System.out.println("Menghapus mahasiswa: " + temp.next.nama);
                temp.next = temp.next.next;
                count--;
                found = true;
                break;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("Data dengan NIM " + targetNim + " tidak ditemukan.");
        }
    }

    // 8. Show Data
    public static void showData() {
        System.out.println("\n--- Data Mahasiswa (Total: " + count + ") ---");
        if (head == null) {
            System.out.println("List Kosong.");
            return;
        }
        Node temp = head;
        int i = 1;
        System.out.printf("%-5s %-15s %-20s %-10s\n", "No", "NIM", "Nama", "Kelas");
        System.out.println("-----------------------------------------------------");
        while (temp != null) {
            System.out.printf("%-5d %-15s %-20s %-10s\n", i, temp.nim, temp.nama, temp.kelas);
            temp = temp.next;
            i++;
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== MENU LINKED LIST MAHASISWA ===");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at given position");
            System.out.println("3. Insert at end");
            System.out.println("4. Delete from beginning");
            System.out.println("5. Delete given position");
            System.out.println("6. Delete from end");
            System.out.println("7. Delete first occurence (by NIM)");
            System.out.println("8. Show data");
            System.out.println("9. Exit");
            System.out.print("Pilihan Anda: ");
            
            while (!scanner.hasNextInt()) {
                System.out.print("Input harus angka! Pilihan Anda: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: insertAtBeginning(); break;
                case 2: insertAtPosition(); break;
                case 3: insertAtEnd(); break;
                case 4: deleteFromBeginning(); break;
                case 5: deleteAtPosition(); break;
                case 6: deleteFromEnd(); break;
                case 7: deleteByNim(); break;
                case 8: showData(); break;
                case 9: System.out.println("Program selesai."); break;
                default: System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 9);
    }
}