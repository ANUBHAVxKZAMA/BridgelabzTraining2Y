import java.util.*;

class Book {
    int bookId;
    String title;
    String author;
    double price;
    public Book(int bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
public class LibraryManagementSystem {
    public static int removeDuplicates(Book[] books, int n) {
        if (n == 0)
            return 0;
        int j = 0;
        for (int i = 1; i < n; i++) {
            if (books[i].bookId != books[j].bookId) {
                j++;
                books[j] = books[i];
            }
        }
        return j + 1;
    }
    public static void searchByTitle(Book[] books, int count, String query) {
        query = query.toLowerCase();
        boolean c = false;
        System.out.println("Search result(s) for '" + query + "':");
        for (int i = 0; i < count; i++) {
            if (books[i].title.toLowerCase().contains(query)) {
                System.out.println("- Found: [" + books[i].bookId + "] " + books[i].title + " (Rs. " + books[i].price + ")");
                c = true;
            }
        }
        if (!c) {
            System.out.println("No book found.");
        }
    }
    public static void sortByPrice(Book[] books, int count) {
        int swaps = 0;
        for (int i = 0; i < count - 1; i++) {
            int mi = i;
            for (int j = i + 1; j < count; j++) {
                if (books[j].price < books[mi].price) {
                    mi = j;
                }
            }
            if (mi != i) {
                Book temp = books[i];
                books[i] = books[mi];
                books[mi] = temp;
                swaps++;
            }
        }
        System.out.println("Total Swaps: " + swaps);
    }
    public static int searchByPrice(Book[] books, int count, double targetPrice) {
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (books[mid].price == targetPrice) {
                return mid;
            }
            if (books[mid].price < targetPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static int minBooksForTargetCost(Book[] books, int count, double targetCost) {
        int left = 0;
        double cs = 0;
        int minLength = Integer.MAX_VALUE;
        for (int right = 0; right < count; right++) {
            cs += books[right].price;
            while (cs >= targetCost) {
                int windowLength = right - left + 1;
                if (windowLength < minLength) {
                    minLength = windowLength;
                }
                cs -= books[left].price;
                left++;
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Book(s):");
        int n = sc.nextInt();
        Book[] books = new Book[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Book " + (i + 1));
            System.out.print("Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Author: ");
            String author = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            books[i] = new Book(id, title, author, price);
        }
        n = removeDuplicates(books, n);
        System.out.println("Unique book(s) count: " + n);
        for (int i = 0; i < n; i++) {
            System.out.println("[" + books[i].bookId + "] " + books[i].title + " - Rs. " + books[i].price);
        }
        System.out.println("Enter the searching key:");
        sc.nextLine();
        String q = sc.nextLine();
        searchByTitle(books, n, q);
        sortByPrice(books, n);
        System.out.println("Books Sorted by Price:");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". [" + books[i].bookId + "] " + books[i].title + " - Rs. " + books[i].price);
        }
        System.out.print("Enter price to search: ");
        double targetPrice = sc.nextDouble();
        System.out.println("Searching for Price Rs. " + targetPrice + "...");
        int index = searchByPrice(books, n, targetPrice);
        if (index != -1) {
            System.out.println("Result: Book found at index " + index + ": [" + books[index].bookId + "] " + books[index].title + " (Rs. " + books[index].price + ")");
        } else {
            System.out.println("Result: Book not found.");
        }
        System.out.print("Enter target cost: ");
        double targetCost = sc.nextDouble();
        System.out.println("Finding minimum consecutive books whose total price >= Rs. " + targetCost + "...");
        int result = minBooksForTargetCost(books, n, targetCost);
        System.out.println("Minimum Consecutive Books Needed: " + result);
        sc.close();
    }
}