class Node:
    def __init__(self, nim, nama, kelas):
        self.nim = nim
        self.nama = nama
        self.kelas = kelas
        self.next = None

class LinkedList:
    def __init__(self):
        self.head = None
        self.count = 0

    def get_input_data(self):
        nim = input("Masukkan NIM: ")
        nama = input("Masukkan Nama: ")
        kelas = input("Masukkan Kelas: ")
        return Node(nim, nama, kelas)

    # 1. Insert at Beginning
    def insert_at_beginning(self):
        print("\n--- Insert at Beginning ---")
        new_node = self.get_input_data()
        
        if self.head is None:
            self.head = new_node
        else:
            new_node.next = self.head
            self.head = new_node
        
        self.count += 1
        print("Data berhasil ditambahkan di awal.")

    # 2. Insert at Given Position
    def insert_at_position(self):
        print("\n--- Insert at Given Position ---")
        print(f"Range posisi valid: 1 sampai {self.count + 1}")
        try:
            pos = int(input("Masukkan Posisi: "))
        except ValueError:
            print("Input posisi harus angka.")
            return

        if pos < 1 or pos > self.count + 1:
            print("Posisi tidak valid!")
            return

        if pos == 1:
            # Panggil logika insert beginning manual agar tidak double prompt
            new_node = self.get_input_data()
            new_node.next = self.head
            self.head = new_node
            self.count += 1
            print("Data berhasil ditambahkan di posisi 1.")
            return

        new_node = self.get_input_data()
        temp = self.head
        # Traverse ke posisi sebelum target (pos-2)
        for _ in range(1, pos - 1):
            temp = temp.next
        
        new_node.next = temp.next
        temp.next = new_node
        self.count += 1
        print(f"Data berhasil ditambahkan di posisi {pos}.")

    # 3. Insert at End
    def insert_at_end(self):
        print("\n--- Insert at End ---")
        new_node = self.get_input_data()

        if self.head is None:
            self.head = new_node
        else:
            temp = self.head
            while temp.next is not None:
                temp = temp.next
            temp.next = new_node
        
        self.count += 1
        print("Data berhasil ditambahkan di akhir.")

    # 4. Delete from Beginning
    def delete_from_beginning(self):
        if self.head is None:
            print("List kosong.")
            return
        
        print(f"Menghapus: {self.head.nama}")
        self.head = self.head.next
        self.count -= 1

    # 5. Delete Given Position
    def delete_at_position(self):
        if self.head is None:
            print("List kosong.")
            return

        print(f"Range hapus valid: 1 sampai {self.count}")
        try:
            pos = int(input("Masukkan Posisi hapus: "))
        except ValueError:
            print("Posisi harus angka.")
            return

        if pos < 1 or pos > self.count:
            print("Posisi tidak valid!")
            return

        if pos == 1:
            self.delete_from_beginning()
            return

        temp = self.head
        for _ in range(1, pos - 1):
            temp = temp.next
        
        print(f"Menghapus: {temp.next.nama}")
        temp.next = temp.next.next
        self.count -= 1

    # 6. Delete from End
    def delete_from_end(self):
        if self.head is None:
            print("List kosong.")
            return
        
        if self.head.next is None:
            self.delete_from_beginning()
            return

        temp = self.head
        while temp.next.next is not None:
            temp = temp.next
        
        print(f"Menghapus: {temp.next.nama}")
        temp.next = None
        self.count -= 1

    # 7. Delete First Occurrence (by NIM)
    def delete_by_nim(self):
        if self.head is None:
            print("List kosong.")
            return
        
        target_nim = input("Masukkan NIM yang akan dihapus: ")

        # Cek head
        if self.head.nim == target_nim:
            self.delete_from_beginning()
            return

        temp = self.head
        found = False
        while temp.next is not None:
            if temp.next.nim == target_nim:
                print(f"Menghapus mahasiswa: {temp.next.nama}")
                temp.next = temp.next.next
                self.count -= 1
                found = True
                break
            temp = temp.next
        
        if not found:
            print(f"Data dengan NIM {target_nim} tidak ditemukan.")

    # 8. Show Data
    def show_data(self):
        print(f"\n--- Data Mahasiswa (Total: {self.count}) ---")
        if self.head is None:
            print("List Kosong.")
            return
        
        temp = self.head
        idx = 1
        print(f"{'No':<5} {'NIM':<15} {'Nama':<20} {'Kelas':<10}")
        print("-" * 50)
        while temp is not None:
            print(f"{idx:<5} {temp.nim:<15} {temp.nama:<20} {temp.kelas:<10}")
            temp = temp.next
            idx += 1

# Main Program Loop
if __name__ == "__main__":
    ll = LinkedList()
    
    while True:
        print("\n=== MENU LINKED LIST MAHASISWA (PYTHON) ===")
        print("1. Insert at beginning")
        print("2. Insert at given position")
        print("3. Insert at end")
        print("4. Delete from beginning")
        print("5. Delete given position")
        print("6. Delete from end")
        print("7. Delete first occurence (by NIM)")
        print("8. Show data")
        print("9. Exit")
        
        try:
            choice = int(input("Pilihan Anda: "))
        except ValueError:
            print("Input harus berupa angka!")
            continue

        if choice == 1: ll.insert_at_beginning()
        elif choice == 2: ll.insert_at_position()
        elif choice == 3: ll.insert_at_end()
        elif choice == 4: ll.delete_from_beginning()
        elif choice == 5: ll.delete_at_position()
        elif choice == 6: ll.delete_from_end()
        elif choice == 7: ll.delete_by_nim()
        elif choice == 8: ll.show_data()
        elif choice == 9: 
            print("Program selesai.")
            break
        else:
            print("Pilihan tidak valid.")