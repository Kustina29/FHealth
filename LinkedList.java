class LinkedList {
    Node head;

    void tambahUser(String username, String password) {
        Node userBaru = new Node(username, password);
        if (head == null) {
            head = userBaru;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = userBaru;
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