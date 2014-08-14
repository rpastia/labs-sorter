package ro.pastia.labs.sorter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SorterApp {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Usage: SorterApp quick|merge input_file");
            System.exit(1);
        }

        ArrayList<Comparable> arrayList = new ArrayList<>();
        try (
            BufferedReader br = new BufferedReader(new FileReader(args[1]));
        ) {
            String line;
            line = br.readLine();
            if (line == null) {
                throw new RuntimeException("Empty line inside file!");
            }
            StringCaster caster = getStringCaster(getType(line));
            //no need to surround with try-catch here because on this same line we have
            //determined what caster we need
            arrayList.add(caster.cast(line));

            while ((line = br.readLine()) != null) {
                try {
                    arrayList.add(caster.cast(line));
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file!", e);
        }


        Comparable[] array = new Comparable[arrayList.size()];
        arrayList.toArray(array);

        SortStats sortStats = new SortStats();
        Sorter sorter = new Sorter(sortStats);

        switch (args[0]) {
            case "quick":
                sorter.doQuicksort(array, 0, array.length - 1);
                break;
            case "merge":
                sorter.doMergesort(array, 0, array.length - 1);
                break;
            default:
                System.out.println("Usage: SorterApp quick|merge array(items separated by '|')");
                System.exit(101);
        }

        for (Comparable item : array) {
            System.out.println(item);
        }

        System.err.println("Sorting took " + sortStats.getDuration() + " milliseconds!");
        System.err.println("Total number of comparisons " + sortStats.getNumComparisons());
    }

    public static Type getType(String s) {
        try {
            Integer.parseInt(s);
            return Type.INTEGER;
        } catch (NumberFormatException ignored) {
        }

        try {
            Float.parseFloat(s);
            return Type.FLOAT;
        } catch (NumberFormatException ignored) {
        }

        return Type.STRING;
    }

    public static StringCaster getStringCaster(Type t) {
        switch (t) {
            case FLOAT:
                return new StringCaster() {
                    public Comparable cast(String s) {
                        return Float.parseFloat(s);
                    }
                };
            case INTEGER:
                return new StringCaster() {
                    public Comparable cast(String s) {
                        return Integer.parseInt(s);
                    }
                };
            case STRING:
                return new StringCaster() {
                    public Comparable cast(String s) {
                        return s;
                    }
                };
            default:
                return null;
        }
    }


    enum Type {INTEGER, FLOAT, STRING}

    private interface StringCaster {
        public Comparable cast(String s);
    }
}