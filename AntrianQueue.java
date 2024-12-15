class AntrianQueue {
    class NodeQueue {
        String username, dokter;
        NodeQueue next;

        NodeQueue(String username, String dokter) {
            this.username = username;
            this.dokter = dokter;
            this.next = null;
        }
    }

    private NodeQueue front, rear;

    void enqueue(String username, String dokter) {
        NodeQueue baru = new NodeQueue(username, dokter);
        if (rear == null) {
            front = rear = baru;
        } else {
            rear.next = baru;
            rear = baru;
        }
        System.out.println("Berhasil masuk antrian untuk  " + dokter + ": " + username);
    }

    String dequeue() {
        if (front == null) {
            return "Antrian kosong!";
        }
        String nama = front.username;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return nama;
    }

    void cekAntrian() {
        if (front == null) {
            System.out.println("Tidak ada antrian saat ini.");
            return;
        }
        NodeQueue temp = front;
        System.out.println("Daftar Antrian:");
        while (temp != null) {
            System.out.println("- " + temp.username + " (Dokter: " + temp.dokter + ")");
            temp = temp.next;
        }
    }

    boolean isEmpty() {
        return front == null;
    }
}