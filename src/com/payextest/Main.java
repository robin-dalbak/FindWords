package com.payextest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


// Her har jeg laget en løsning som søker etter ord i en grid og printer de ut. Ordene den søker etter henter den fra filen resources/words.txt
public class Main {


    static class FindWords {
        public static void main(String[] args) throws FileNotFoundException {
            ArrayList<String> words = loadWords("resources/words.txt");
            ArrayList<String> grid = loadWords("resources/grid3x3.txt");
            ArrayList<String> hits = findWords(words, grid);


            // her printes resultatet ut i alfabetisk rekkefølge.
            Collections.sort(hits);
            for (String s : hits) {
                System.out.println(s);
            }
        }

        // Denne søker gjennom griden og legger til ordene den finner i "hits". Den søker horisontalt og vertikalt, forlengs og baklengs.
        public static ArrayList<String> findWords(ArrayList<String> words, ArrayList<String> horizontalGrid) {
            ArrayList<String> verticalGrid = transpose(horizontalGrid);
            ArrayList<String> backwardVerticalGrid = transposeOpposite(horizontalGrid);
            ArrayList<String> backwardHorizontalGrid = transposeOpposite(verticalGrid);
            ArrayList<String> hits = new ArrayList<>();

            for (String row : verticalGrid) {
                for (String word : words) {
                    if (row.contains(word)) {
                        hits.add(word);
                    }
                }
            }

            for (String row : horizontalGrid) {
                for (String word : words) {
                    if (row.contains(word)) {
                        hits.add(word);
                    }
                }
            }


            for (String row : backwardVerticalGrid) {
                for (String word : words) {
                    if (row.contains(word)) {
                        hits.add(word);
                    }
                }
            }


            for (String row : backwardHorizontalGrid) {
                for (String word : words) {
                    if (row.contains(word)) {
                        hits.add(word);
                    }
                }
            }


            return hits;

        }

        // Denne setter opp griden slik at metoden ovenfor kan søke gjennom.
        public static ArrayList<String> transpose(ArrayList<String> grid) {
            ArrayList<String> transposed = new ArrayList<>();
            for (int row = 0; row < grid.size(); row++) {
                StringBuilder verticalWordBuilder = new StringBuilder();
                for (int col = 0; col < grid.size(); col++) {
                    verticalWordBuilder.append(grid.get(col).charAt(row));

                }
                transposed.add(verticalWordBuilder.toString());
            }
            return transposed;
        }


        public static ArrayList<String> transposeOpposite(ArrayList<String> oppositeGrid) {
            ArrayList<String> transposedOpposite = new ArrayList<>();
            for (int oRow = oppositeGrid.size() - 1; oRow >= 0; oRow--) {
                StringBuilder verticalWordBuilder = new StringBuilder();
                for (int oCol = oppositeGrid.size() - 1; oCol >= 0; oCol--) {
                    verticalWordBuilder.append(oppositeGrid.get(oCol).charAt(oRow));

                }

                transposedOpposite.add(verticalWordBuilder.toString());
            }

            return transposedOpposite;
        }


        // Denne metoden søker gjennom tekstfilene og legger den opp i en arraylist som strings.
        public static ArrayList<String> loadWords(String filename) throws FileNotFoundException {
            ArrayList<String> words = new ArrayList<>();
            Scanner scanner = new Scanner(new FileInputStream(filename));
            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }

            return words;
        }

    }
}