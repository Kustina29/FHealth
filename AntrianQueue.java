class AntrianQueue {
    class NodeQueue {
        String username, dokter, keluhan;
        NodeQueue next;

        NodeQueue(String username, String dokter, String keluhan) {
            this.username = username;
            this.dokter = dokter;
            this.keluhan = keluhan;
            this.next = null;
        }
    }

    private NodeQueue front, rear;

    void enqueue(String username, String dokter, String keluhan) {
        NodeQueue newNode = new NodeQueue(username, dokter, keluhan);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        
    }

    NodeQueue dequeue() {
        if (front == null) {
            System.out.println("Antrian kosong.");
            return null;
        }
        NodeQueue temp = front;
        front = front.next;
        if (front == null) rear = null;
        System.out.println("Pasien " + temp.username + " dengan keluhan '" + temp.keluhan + "' sedang dilayani oleh Dr. " + temp.dokter + ".");
        return temp;
    }

    boolean isEmpty() {
        return front == null;
    }

    void cekAntrian() {
        if (front == null) {
            System.out.println("Antrian kosong.");
            return;
        }
        NodeQueue current = front;
        int nomor = 1; 
        System.out.println("+==================================================================+");
        System.out.println("|                        DAFTAR ANTRIAN PASIEN                     |");
        System.out.println("+==================================================================+");
        while (current != null) {
            System.out.println(nomor + ". Pasien: " + current.username);
            System.out.println("   Dokter: " + current.dokter);
            System.out.println("   Keluhan: " + current.keluhan);
            System.out.println("--------------------------------------------------------------------");
            current = current.next;
            nomor++;
        }
    }
}
