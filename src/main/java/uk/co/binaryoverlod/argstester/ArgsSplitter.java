package uk.co.binaryoverlod.argstester;

import java.util.ArrayList;

public class ArgsSplitter {

    public static String[] split(String input) {
        boolean inQuotes = false; // Whether the current position is surrounded by quotes or not
        int splitFrom = 0; // We initially start the first split from 0 to the first space
        var args = new ArrayList<String>();
        for (int pos = 0; pos < input.length(); pos++) {
            char charAtPos = input.charAt(pos);
            if (charAtPos == ' ') {
                int splitTo = pos;
                // If there is a quote to close this
                if (inQuotes && (input.substring(pos).contains("\""))) {
                    continue;
                }
                if (input.charAt(pos - 1) == '"') {
                    splitTo = pos - 1; // If we are splitting after a quote, don't include the quote in the split
                }
                args.add(input.substring(splitFrom, splitTo));
                splitFrom = pos + 1; // Set the next split start to be after
            } else if (pos == input.length() - 1) {
                int splitTo = input.length();
                // If the end character is a quote, we want to "split" before the quote to no include it.
                if (inQuotes && input.charAt(pos) == '"') {
                    splitTo = pos;
                }
                args.add(input.substring(splitFrom, splitTo));
                // End of string so do nothing else
            } else if (charAtPos == '"') {
                if (!inQuotes && (pos == 0 || input.charAt(pos - 1) == ' ')) {
                    splitFrom += 1; // Start the split after the first quote
                    inQuotes = true;
                } else if (inQuotes) {
                    inQuotes = false;
                }
            }
        }
        System.out.println(args.toString());
        return args.toArray(String[]::new);
    }

}
