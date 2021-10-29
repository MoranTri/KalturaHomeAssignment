import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Utilities {

    public static String randomString() {
        // create a string of all characters
        String alphabet = "abcdefghijkelmonpqrstuvwxyz1234567890";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 12;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static void generateNewUserDetails(Variables variables) {
        variables.setUserName(randomString());
        variables.setExternalId(randomString());
        variables.setPassword(randomString());
    }

    public static String parseMillisecondsDate(int dateInMilli) {
        DateFormat simple = new SimpleDateFormat("dd/MM/yy");
        return simple.format(dateInMilli);
    }
}
