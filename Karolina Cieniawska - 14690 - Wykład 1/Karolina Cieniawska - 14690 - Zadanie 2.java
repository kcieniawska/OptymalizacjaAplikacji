// ============================================
//
// ZADANIE 2
//
// ============================================

import java.util.ArrayList;
import java.util.List;

public class TextDataProcessor {
    private static final int MINIMUM_STRING_LENGTH = 3;
    private static final String EXCLUDED_PREFIX = "X";
    public String[] processTextArray(String[] rawData) {
        if (rawData == null) {
            return new String[0];
        }

        List<String> filteredTextList = new ArrayList<>();

        for (String textItem : rawData) {
            if (isValidAndEligible(textItem)) {
                String formattedText = textItem.trim().toUpperCase();
                
                if (!hasExcludedPrefix(formattedText)) {
                    filteredTextList.add(formattedText);
                }
            }
        }
        return filteredTextList.toArray(new String[0]);
    }
    private boolean isValidAndEligible(String text) {
        return text != null && text.length() > MINIMUM_STRING_LENGTH;
    }

    private boolean hasExcludedPrefix(String text) {
        return text.startsWith(EXCLUDED_PREFIX);
    }
}