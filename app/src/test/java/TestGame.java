import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class TestGame {

    private String runningGame(String propertiesFile) {
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesFile);
        String logResult = new Pinochle(properties).runApp();
        return logResult;
    }

    @Test(timeout = 90000)
    public void testOriginal() {
        String testProperties = "properties/test1.properties";
        String logResult = runningGame(testProperties);
        assertTrue(logResult.contains("Bid:1-140"));
        assertTrue(logResult.contains("Trump: C"));
        assertTrue(logResult.contains("Melding Scores: 190-0"));
        assertTrue(logResult.contains("Trick Winning: P0:9D,10C,12H,13H,11S,12S,11H,12C,13D,12C,13C,12S,1C,1D,1C,13D-P1:9H,10H,12H,1H,11C,13C,11S,1S"));
        assertTrue(logResult.contains("Final Score: 268,0"));
        assertTrue(logResult.contains("Winners: 0"));
    }

    @Test(timeout = 90000)
    public void testNewMeldingPart1() {
        String testProperties = "properties/test2.properties";
        String logResult = runningGame(testProperties);
        System.out.println("logResult = " + logResult);
        assertTrue(logResult.contains("Bid:1-30"));
        assertTrue(logResult.contains("Trump: C"));
        assertTrue(logResult.contains("Melding Scores: 210-30"));
    }

    @Test(timeout = 90000)
    public void testNewMeldingPart2() {
        String testProperties = "properties/test3.properties";
        String logResult = runningGame(testProperties);
        System.out.println("logResult = " + logResult);
        assertTrue(logResult.contains("Bid:1-30"));
        assertTrue(logResult.contains("Trump: C"));
        assertTrue(logResult.contains("Melding Scores: 230-300"));
    }

    @Test(timeout = 90000)
    public void testSmartBidding() {
        String testProperties = "properties/test4.properties";
        String logResult = runningGame(testProperties);
        System.out.println("logResult = " + logResult);
        assertTrue(logResult.contains("Bid:0-220"));
        assertTrue(logResult.contains("Trump: C"));
        assertTrue(logResult.contains("Melding Scores: 190-0"));
    }

    public String findLine(String text, String needle) {
        try (Stream<String> lines = text.lines()) {// Java 11+ ✨
            return lines.filter(line -> line.contains(needle))    // keep matching lines
                    .toList().stream().findAny().orElse(null);
        }
    }


    @Test(timeout = 90000)
    public void testCutThroat() {
        String testProperties = "properties/test5.properties";
        String logResult = runningGame(testProperties);
        String initialCards = findLine(logResult, "Initial Cards:");
        assertTrue("log should contain initial cards line", initialCards != null && !initialCards.isEmpty());
        String[] cardElements = initialCards.split("-");
        assertTrue("log should contain 4 elements", cardElements.length >= 4);
        String[] computerCards = cardElements[1].split(",");
        List<String> cardsToContain = new ArrayList<>(Arrays.asList("9D", "9D", "9C", "10D", "10D", "10C", "11D",
                "11D", "11C", "12D", "12C", "13D"));

        for(String computerCard : computerCards) {
            cardsToContain.remove(computerCard);
        }
        assertTrue("Cards to contain should be empty", cardsToContain.isEmpty());
        System.out.println("logResult = " + logResult);
    }
}
