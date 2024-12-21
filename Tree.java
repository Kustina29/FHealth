class Tree {
    static class TreeNode {
        String kategori;
        TreeNode next; 
        TreeNode foodList; 

        TreeNode(String kategori) {
            this.kategori = kategori;
            this.next = null;
            this.foodList = null;
        }
    }

    private TreeNode root;

    void tambahKategori(String kategori) {
        TreeNode newKategori = new TreeNode(kategori);
        if (root == null) {
            root = newKategori;
        } else {
            TreeNode temp = root;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newKategori;
        }
    }

    void tambahMakananKeKategori(String kategori, String makanan) {
        TreeNode kategoriNode = cariKategori(root, kategori);
        if (kategoriNode != null) {
            TreeNode newMakanan = new TreeNode(makanan);
            if (kategoriNode.foodList == null) {
                kategoriNode.foodList = newMakanan;
            } else {
                TreeNode temp = kategoriNode.foodList;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newMakanan;
            }
        } else {
            System.out.println("Kategori " + kategori + " tidak ditemukan.");
        }
    }
    private TreeNode cariKategori(TreeNode node, String kategori) {
        while (node != null) {
            if (node.kategori.equals(kategori)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
    void tampilkanKategori() {
        if (root == null) {
            System.out.println("Belum ada kategori.");
        } else {
            System.out.println("\n+==============================+");
            System.out.println("|        SARAN NUTRISI         |");
            System.out.println("+==============================+");
            tampilkanKategoriRekursif(root);
        }
    }
    private void tampilkanKategoriRekursif(TreeNode node) {
        if (node != null) {
            System.out.println("Kategori: " + node.kategori);
            TreeNode makananNode = node.foodList;
            while (makananNode != null) {
                System.out.println("   - " + makananNode.kategori);
                makananNode = makananNode.next;
            }
            tampilkanKategoriRekursif(node.next);
        }
    }
}
