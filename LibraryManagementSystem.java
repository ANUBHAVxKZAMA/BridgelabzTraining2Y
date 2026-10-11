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

    // Remove duplicate books based on Book ID
    public static int removeDuplicates(Book[] books, int n) {
        Set<Integer> ids = new HashSet<>();
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (ids.add(books[i].bookId)) {
                books[j] = books[i];
                j++;
            }
        }

        return j;
    }

    // Search books by title
    public static void searchByTitle(Book[] books, int count, String query) {
        query = query.toLowerCase(Locale.ROOT);
        boolean found = false;

        System.out.println("Search result(s) for '" + query + "':");

        for (int i = 0; i < count; i++) {
            if (books[i].title.toLowerCase(Locale.ROOT).contains(query)) {
                System.out.println("- Found: [" + books[i].bookId + "] "
                        + books[i].title + " (Rs. " + books[i].price + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No book found.");
        }
    }

    // Sort books by price using Selection Sort
    public static void sortByPrice(Book[] books, int count) {
        int swaps = 0;

        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < count; j++) {
                if (books[j].price < books[minIndex].price) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Book temp = books[i];
                books[i] = books[minIndex];
                books[minIndex] = temp;
                swaps++;
            }
        }

        System.out.println("Total Swaps: " + swaps);
    }

    // Binary Search by Price
    public static int searchByPrice(Book[] books, int count, double targetPrice) {
        int low = 0;
        int high = count - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (Double.compare(books[mid].price, targetPrice) == 0) {
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

    // Find minimum consecutive books whose total price >= target cost
    public static int minBooksForTargetCost(Book[] books, int count, double targetCost) {
        if (targetCost <= 0) {
            return 0;
        }

        int left = 0;
        double currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < count; right++) {
            currentSum += books[right].price;

            while (currentSum >= targetCost && left <= right) {
                int windowLength = right - left + 1;

                if (windowLength < minLength) {
                    minLength = windowLength;
                }

                currentSum -= books[left].price;
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of Book(s):");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Invalid number of books.");
            sc.close();
            return;
        }

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

            if (price < 0 || Double.isNaN(price) || Double.isInfinite(price)) {
                System.out.println("Invalid price. Price must be non-negative and finite.");
                sc.close();
                return;
            }

            books[i] = new Book(id, title, author, price);
        }

        // Remove duplicate IDs
        n = removeDuplicates(books, n);

        System.out.println("Unique book(s) count: " + n);

        for (int i = 0; i < n; i++) {
            System.out.println("[" + books[i].bookId + "] "
                    + books[i].title + " - Rs. " + books[i].price);
        }

        // Search by title
        System.out.println("Enter the searching key:");
        sc.nextLine();
        String query = sc.nextLine();

        searchByTitle(books, n, query);

        // Sort by price
        sortByPrice(books, n);

        System.out.println("Books Sorted by Price:");

        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". [" + books[i].bookId + "] "
                    + books[i].title + " - Rs. " + books[i].price);
        }

        // Search by price
        System.out.print("Enter price to search: ");
        double targetPrice = sc.nextDouble();

        if (Double.isNaN(targetPrice) || Double.isInfinite(targetPrice)) {
            System.out.println("Invalid search price.");
            sc.close();
            return;
        }

        System.out.println("Searching for Price Rs. " + targetPrice + "...");

        int index = searchByPrice(books, n, targetPrice);

        if (index != -1) {
            System.out.println("Result: Book found at index " + index + ": ["
                    + books[index].bookId + "] " + books[index].title
                    + " (Rs. " + books[index].price + ")");
        } else {
            System.out.println("Result: Book not found.");
        }

        // Find minimum consecutive books for target cost
        System.out.print("Enter target cost: ");
        double targetCost = sc.nextDouble();

        if (Double.isNaN(targetCost) || Double.isInfinite(targetCost)) {
            System.out.println("Invalid target cost.");
            sc.close();
            return;
        }

        System.out.println("Finding minimum consecutive books whose total price >= Rs. "
                + targetCost + "...");

        int result = minBooksForTargetCost(books, n, targetCost);

        System.out.println("Minimum Consecutive Books Needed: " + result);

        sc.close();
    }
}

