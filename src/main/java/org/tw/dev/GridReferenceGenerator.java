package org.tw.dev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridReferenceGenerator {

    private static final int rowFrom = 1;
    private static final int rowTo = 22;
    private static final char columnFrom = 'J';
    private static final String columnTo = "AL";
    private static final List<String> excludedSquares = List.of("Z22-AL22", "AA21-AL21", "AA20-AL20", "AA19-AL19", "AB18-AL18", "AD17-AL17", "AF16-AL16",
            "AF15-AL15", "AG14-AL14", "AH13-AL13", "AJ12-AL12", "AJ11-AL11", "AH10-AL10", "AJ9-AL9", "AK8-AL8", "AK7-AL7", "AL6", "AK5-AL5", "AL4",
            "K20", "J19", "J18", "J14", "J13", "J12-K12", "J11-K11", "J10-M10", "J9-M9", "J8-M8", "J7-M7", "J6-M6", "J5-L5", "J4-L4");
    private static final List<String> excludedColumns = List.of("O", "AI");

    public static List<String> generateRandomGridReferences(int numberOfReferences) {
        List<String> validReferences = generateValidGridReferences();
        Random rand = new Random();
        List<String> randomReferences = new ArrayList<>();

        while (randomReferences.size() < numberOfReferences && !validReferences.isEmpty()) {
            String reference = validReferences.remove(rand.nextInt(validReferences.size()));
            if(!randomReferences.contains(reference)) {
                randomReferences.add(reference);
            }
        }

        return randomReferences;
    }

    private static List<String> generateValidGridReferences() {
        List<String> validReferences = new ArrayList<>();
        List<String> columns = generateColumns();
        List<String> expandedExcludedSquares = expandExcludedSquares();

        for (String column : columns) {
            if (!GridReferenceGenerator.excludedColumns.contains(column)) {
                for (int i = rowFrom; i <= rowTo; i++) {
                    String reference = column + i;
                    if(!expandedExcludedSquares.contains(reference)) {
                        validReferences.add(reference);
                    }
                }
            }
        }

        return validReferences;
    }

    private static List<String> expandExcludedSquares() {
        List<String> expandedExcludedSquares = new ArrayList<>();
        for (String item : GridReferenceGenerator.excludedSquares) {
            if(item.contains("-")) {
                String[] parts = item.split("-");
                String start = parts[0];
                String end = parts[1];
                expandedExcludedSquares.addAll(generateRange(start, end));
            }
            else {
                expandedExcludedSquares.add(item);
            }
        }

        return expandedExcludedSquares;
    }

    private static List<String> generateRange(String start, String end) {
        List<String> range = new ArrayList<>();
        String startColumn = start.replaceAll("\\d", "");
        String endColumn = end.replaceAll("\\d", "");
        int row = Integer.parseInt(start.replaceAll("\\D", ""));

        List<String> columns = generateColumns();
        int startIndex = columns.indexOf(startColumn);
        int endIndex = columns.indexOf(endColumn);

        for (int i = startIndex; i <= endIndex; i++) {
            range.add(columns.get(i) + row);
        }
        return range;
    }

    private static List<String> generateColumns() {
        List<String> columns = new ArrayList<>();
        for(char c = columnFrom; c <= 'Z'; c++) {
            columns.add(String.valueOf(c));
        }
        for (char c1 = 'A'; c1 <= columnTo.charAt(1); c1++) {
            columns.add("A" + c1);
        }
        return columns;
    }
}