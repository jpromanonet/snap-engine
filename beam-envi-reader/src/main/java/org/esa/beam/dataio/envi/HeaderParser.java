package org.esa.beam.dataio.envi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses a ENVI HDR header in key value pairs.
 * see http://www.exelisvis.com/docs/ENVIHeaderFiles.html
 */
class HeaderParser {

    private final Map<String, Object> header;
    private final Map<String, Object> history;

    public HeaderParser(Map<String, Object> header, Map<String, Object> history) {
        this.header = header;
        this.history = history;
    }

    public static HeaderParser parse(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        Map<String, Object> header = new HashMap<>();
        Map<String, Object> history = new HashMap<>();
        Map<String, Object> currentMap = header;
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            line = line.trim();
            if (line.equals("history = begins")) {
                currentMap = history;
            } else if (line.equals("history = ends")) {
                currentMap = header;
            } else {
                if (line.contains("=")) {
                    String[] keyValue = line.split("=", 2);
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    if (value.startsWith("{")) {
                        value = value.substring(1).trim();
                        while (!value.endsWith("}")) {
                            String continuedLine = bufferedReader.readLine();
                            value = value + "\n" + continuedLine.trim();
                            value = value.trim();
                        }
                        value = value.substring(0, value.length() - 1);
                    }
                    if (!value.isEmpty()) {
                        currentMap.put(key, value);
                    }
                }
            }
        }
        return new HeaderParser(header, history);
    }

    boolean contains(String key) {
        return header.containsKey(key);
    }

    String getString(String key) {
        if (!contains(key)) {
            throw new IllegalArgumentException("Missing mandatory header key: " + key);
        }
        return (String) header.get(key);
    }

    String getString(String key, String defaultValue) {
        return contains(key) ? getString(key) : defaultValue;
    }

    String[] getStrings(String key) {
        Object v = header.get(key);
        if (v == null) {
            return new String[0];
        } else {
            String elem = (String) v;
            String[] splits = elem.split(",");
            String[] splitsTrimmed = new String[splits.length];
            for (int i = 0; i < splitsTrimmed.length; i++) {
                splitsTrimmed[i] = splits[i].trim();
            }
            return splitsTrimmed;
        }
    }

    int getInt(String key) {
        if (!contains(key)) {
            throw new IllegalArgumentException("Missing mandatory header key: " + key);
        }
        return Integer.parseInt(getString(key));
    }

    int getInt(String key, int defaultValue) {
        return contains(key) ? getInt(key) : defaultValue;
    }

    double[] getDoubles(String key) {
        String[] elems = getStrings(key);
        double[] doubles = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            doubles[i] = Double.parseDouble(elems[i]);
        }
        return doubles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Header\n");
        mapToString(sb, header);
        sb.append("History\n");
        mapToString(sb, history);
        return sb.toString();
    }

    private static void mapToString(StringBuilder sb, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        HeaderParser enviHeader = HeaderParser.parse(new FileReader(args[0]));
        System.out.println(enviHeader);
    }
}
