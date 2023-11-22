import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    private static HashMap<String, String> linkStorage = new HashMap<>();
static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        while (true) {
            System.out.println("1. Shorten a link");
            System.out.println("2. Retrieve a link");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    shortenLink();
                    break;
                case 2:
                    retrieveLink();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void shortenLink() {
        System.out.print("Enter the long URL: ");
        String longUrl = scanner.nextLine();

        String shortUrl = generateShortUrl(longUrl);
        linkStorage.put(shortUrl, longUrl);

        System.out.println("Shortened URL: " + shortUrl);
    }

    private static void retrieveLink() {
        System.out.print("Enter the short URL: ");
        String shortUrl = scanner.nextLine();

        if (linkStorage.containsKey(shortUrl)) {
            String longUrl = linkStorage.get(shortUrl);
            System.out.println("Original URL: " + longUrl);
        } else {
            System.out.println("Short URL not found.");
        }
    }

    private static String generateShortUrl(String longUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(longUrl.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString().substring(0, 8); // Use first 8 characters for simplicity
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}