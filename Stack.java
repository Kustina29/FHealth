class Stack {
    static class NodeStack {
        String rekomendasi;
        NodeStack next;

        NodeStack(String rekomendasi) {
            this.rekomendasi = rekomendasi;
            this.next = null;
        }
    }

    private NodeStack top;

    void push(String rekomendasi) {
        NodeStack baru = new NodeStack(rekomendasi);
        if (top == null) {
            top = baru;
        } else {
            baru.next = top;
            top = baru;
        }
    }

    String pop() {
        if (top == null) {
            return "Tidak ada riwayat.";
        }
        String rekomendasi = top.rekomendasi;
        top = top.next;
        return rekomendasi;
    }

    void display() {
        if (top == null) {
            System.out.println("Tidak ada riwayat rekomendasi.");
            return;
        }
        
        System.out.println("+==================================================================+");
        System.out.println("|                     RIWAYAT REKOMENDASI MAKANAN                  |");
        System.out.println("+==================================================================+");
        NodeStack temp = top;
        while (temp != null) {
            System.out.println("- " + temp.rekomendasi);
            temp = temp.next;
        }
    }
}