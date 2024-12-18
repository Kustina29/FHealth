class Tree {
    static class TreeNode {
        String kategori;
        TreeNode left, right; 
        TreeNode child; 

        TreeNode(String kategori) {
            this.kategori = kategori;
            this.left = null;
            this.right = null;
            this.child = null;
        }
    }

    private TreeNode root;

    void tambahKategori(String kategori) {
        if (root == null) {
            root = new TreeNode(kategori);
        } else {
            tambahRekursif(root, kategori);
        }
    }

    private void tambahRekursif(TreeNode node, String kategori) {
        if (node == null) {
            return;
        }

        if (kategori.compareTo(node.kategori) < 0) {
            if (node.left == null) {
                node.left = new TreeNode(kategori);
            } else {
                tambahRekursif(node.left, kategori); 
            }
        } else if (kategori.compareTo(node.kategori) > 0) {
            if (node.right == null) {
                node.right = new TreeNode(kategori);
            } else {
                tambahRekursif(node.right, kategori); 
            }
        }
    }
    void tambahSubKategori(String kategoriInduk, String subKategori) {
        TreeNode nodeInduk = cariKategori(root, kategoriInduk);
        if (nodeInduk != null) {
            TreeNode subKategoriNode = new TreeNode(subKategori);
            if (nodeInduk.child == null) {
                nodeInduk.child = subKategoriNode;
            } else {
                TreeNode temp = nodeInduk.child;
                while (temp.right != null) {
                    temp = temp.right; 
                }
                temp.right = subKategoriNode; 
            }
            System.out.println("Subkategori " + subKategori + " berhasil ditambahkan ke kategori " + kategoriInduk);
        } else {
            System.out.println("Kategori induk " + kategoriInduk + " tidak ditemukan.");
        }
    }
    private TreeNode cariKategori(TreeNode node, String kategori) {
        if (node == null) {
            return null;
        }

        if (node.kategori.equals(kategori)) {
            return node;
        }

        if (kategori.compareTo(node.kategori) < 0) {
            return cariKategori(node.left, kategori); 
        } else {
            return cariKategori(node.right, kategori); 
        }
    }
    void tampilkanKategori() {
        if (root == null) {
            System.out.println("Belum ada kategori.");
        } else {
            tampilkanKategoriRekursif(root);
        }
    }

    private void tampilkanKategoriRekursif(TreeNode node) {
        if (node != null) {
            tampilkanKategoriRekursif(node.left);
            System.out.println("Kategori " + node.kategori);
            if (node.child != null) {
                tampilkanSubKategori(node.child);
            }
            tampilkanKategoriRekursif(node.right);
        }
    }
    private void tampilkanSubKategori(TreeNode node) {
        while (node != null) {
            System.out.println("   - " + node.kategori);
            node = node.right; 
        }
    }  
}