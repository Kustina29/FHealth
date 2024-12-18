class LinkedListDokter {
    Node head;

    void tambahDokter(String username, String password) {
        Node DokterBaru = new Node(username, password);
        if (head == null) {
            head = DokterBaru;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = DokterBaru;
        }
    }

    boolean validasiLogin(String username, String password) {
        Node temp = head;
        while (temp != null) {
            if (temp.username.equals(username) && temp.password.equals(password)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}