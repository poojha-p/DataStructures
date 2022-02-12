package a5;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int primary_diagonal = 0;
        for (int i=0; i<arr.size(); i++) {
            primary_diagonal += (arr.get(i).get(i));
        }

        int secondary_diagonal = 0;
        for (int i = 2; i>=0; i--) {
            secondary_diagonal += (arr.get(i).get(i));
        }

        int diagonal_difference = Math.abs(primary_diagonal - secondary_diagonal);
        return diagonal_difference;
    }

}

