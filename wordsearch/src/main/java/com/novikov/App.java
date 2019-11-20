package com.novikov;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        char[][] board = {
                {'A','E','D'},
                {'D','J','B'},
                {'A','B','C'}
        };
        String word = "AED";

        WordSearch wordSearch = new WordSearch();
        try {
            wordSearch = new WordSearch(board,"AED");
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(wordSearch.exist());


    }
}
