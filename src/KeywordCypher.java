import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by alexgreco on 9/15/17.
 */
public class KeywordCypher {
    public static void main(String[] args) {
        KeywordCypher keyCyph = new KeywordCypher();

        String keyCyphIn = "kryptos";

        char[] result = keyCyph.getKeywordArray(keyCyphIn);

        char[] result2 = keyCyph.getEncryptionAlphabet(result);

        System.out.println(keyCyph.encryptString("knowledge is power!", result2));

        for (int x = 0; x < result.length; x++) {
            System.out.println(result[x]);
        }

    }

    /**
     * Function to put the input in a char array removing duplicates.
     */
    public char[] getKeywordArray(String input) {
        //array we will return
        char[] result;
        //hashmap we can use to put chars into map
        HashMap map = new HashMap();
        //Queue to keep order
        Queue<Character> queue = new ArrayBlockingQueue<>(input.length());

        //loop through input by char
        for (int x = 0; x < input.length(); x++) {
            //Get the curr char and make it lowercase
            char currChar = input.charAt(x);
            currChar = Character.toLowerCase(currChar);

            //if the character is not in the map, put it in
            if (!map.containsKey(currChar)) {
                map.put(currChar, 1);
                queue.add(currChar);
            }
            //if not, we have seen that char
        }

        result = new char[map.size()];

        //go through map and put chars into array
        for (int y = 0; y < map.size(); y++) {
            result[y] = queue.remove();
        }

        return result;
    }

    /**
     * function that creates the encryption alphabet.
     */
    public char[] getEncryptionAlphabet(char[] keywordArr) {
        //regular alphabet array
        char alphabet[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z'};
        char encrypAlphabet[] = new char[26];
        //To track where in encrypAlphabet chars should go
        int nextIndex = keywordArr.length;

        //copy keywordArr into encrypAlphabet
        for(int x = 0; x < keywordArr.length; x++ ){
            encrypAlphabet[x] = keywordArr[x];
        }

        //now copy over the rest of the alphabet
        for(int y = 0; y < alphabet.length; y++){
            //get currChar in alphabet
            char currChar = alphabet[y];
            //turn encrypAlphabet into a string to be able to find currChar
            String currEncrypAlphabet = new String(encrypAlphabet);
            if(!currEncrypAlphabet.contains(Character.toString(currChar))){
                encrypAlphabet[nextIndex] = currChar;
                nextIndex++;
            }
        }

        return encrypAlphabet;

    }

    /**
     * This function takes the encrypted alphabet, and the input string. For
     * every character of the input, get the corresponding character from
     * encryption alphabet and add that character to the output string
     * @param inputString
     * @param encrypAlphabet
     * @return
     */
    public String encryptString(String inputString, char[] encrypAlphabet){
        String output = "";

        //regular alphabet array
        char alphabet[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z'};

        //get a string of encrypAlphabet for index grabs
        String regAlphabetString = new String(alphabet);

        for(int x = 0; x < inputString.length(); x++){
            //get the current char
            char currChar = inputString.charAt(x);

            currChar = Character.toLowerCase(currChar);
            //make sure currChar is a letter
            if(Character.isAlphabetic(currChar)){
                //get the regular Alphabet index of currChar
                int currCharIndex = regAlphabetString.indexOf(currChar);
                output += encrypAlphabet[currCharIndex];
            }else{
                output += currChar;
            }
        }

        return output;
    }

    public String decryptString(String inputString, char[] encrypAlphabet){
        String output = "";

        //regular alphabet array
        char alphabet[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z'};

        //get a string of encrypAlphabet for index grabs
        String encrypAlphabetString = new String(encrypAlphabet);

        for(int x = 0; x < inputString.length(); x++){
            //get the current char
            char currChar = inputString.charAt(x);

            currChar = Character.toLowerCase(currChar);
            //make sure currChar is a letter
            if(Character.isAlphabetic(currChar)){
                //get the regular Alphabet index of currChar
                int currCharIndex = encrypAlphabetString.indexOf(currChar);
                output += alphabet[currCharIndex];
            }else{
                output += currChar;
            }
        }

        return output;
    }
}


















