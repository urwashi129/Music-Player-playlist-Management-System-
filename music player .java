import java.util.*;

class Node {
    String song;
    Node prev, next;

    Node(String song) {
        this.song = song;
        this.prev = null;
        this.next = null;
    }
}

class MusicPlayer {

    Node head = null;
    Node current = null;

    Stack<String> history = new Stack<>();

    TreeSet<String> bst = new TreeSet<>();

    // Add Song
    void addSong(String song) {
        Node newNode = new Node(song);

        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;

            last.next = newNode;
            newNode.prev = last;

            newNode.next = head;
            head.prev = newNode;
        }

        bst.add(song);
        System.out.println("Song added: " + song);
    }

    // Play Song
    void playSong() {
        if (head == null) {
            System.out.println("Playlist is empty. Add songs first.");
            return;
        }

        if (current == null) {
            current = head;
        }

        playCurrent();
    }

    // Play Current Song
    void playCurrent() {
        System.out.println("Now playing: " + current.song);
        history.push(current.song);
    }

    // Next Song
    void nextSong() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        if (current == null) {
            current = head;
        } else {
            current = current.next;
        }

        playCurrent();
    }

    // Previous Song
    void previousSong() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        if (current == null) {
            current = head;
        } else {
            current = current.prev;
        }

        playCurrent();
    }

    // Search Song using BST
    void searchSong(String song) {
        if (bst.contains(song)) {
            System.out.println("Song found: " + song);
        } else {
            System.out.println("Song not found: " + song);
        }
    }

    // Recently Played Songs
    void recentlyPlayed() {
        if (history.empty()) {
            System.out.println("No recently played songs.");
            return;
        }

        System.out.print("Recently played: ");

        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.print(history.get(i));

            if (i > 0) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }

    // Display Playlist
    void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Node temp = head;

        System.out.print("Playlist: ");

        do {
            System.out.print(temp.song);
            temp = temp.next;

            if (temp != head) {
                System.out.print(", ");
            }

        } while (temp != head);

        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MusicPlayer player = new MusicPlayer();

        while (true) {

            System.out.println("\n========== MUSIC PLAYER ==========");
            System.out.println("1. Add Song");
            System.out.println("2. Play Song");
            System.out.println("3. Next Song");
            System.out.println("4. Previous Song");
            System.out.println("5. Search Song");
            System.out.println("6. Recently Played");
            System.out.println("7. Display Playlist");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter song name: ");
                    String song = sc.nextLine();
                    player.addSong(song);
                    break;

                case 2:
                    player.playSong();
                    break;

                case 3:
                    player.nextSong();
                    break;

                case 4:
                    player.previousSong();
                    break;

                case 5:
                    System.out.print("Enter song to search: ");
                    String search = sc.nextLine();
                    player.searchSong(search);
                    break;

                case 6:
                    player.recentlyPlayed();
                    break;

                case 7:
                    player.displayPlaylist();
                    break;

                case 8:
                    System.out.println("Exiting Music Player.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}