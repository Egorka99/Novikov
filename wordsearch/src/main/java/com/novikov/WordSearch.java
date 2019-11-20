package com.novikov;

import java.util.*;

class CharCoordinates {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CharCoordinates(int x, int y) {
        this.x = x;
        this.y = y;

    }
    
    public char getCharInMatrix(char[][] matrix) {
        return matrix[x][y];
    }
    
}
enum Move {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class WordSearch {

    private char[][] board;
    private String word;

    private int N;
    public WordSearch(char[][] board,String word) throws Exception {
        if (board[0].length == board[1].length) {
            this.board = board;
        }
        else {
            throw new Exception("Матрица должна быть квадратичной");
        }
        this.word = word;
        N = board[0].length;
    }
    
    private List<CharCoordinates> listFirstSymbolPositionsOnBoard() {
        List<CharCoordinates> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result.add(new CharCoordinates(i,j));
                }
            }
        }
        return result;
    }

    private char moveOnBoard(Move move, CharCoordinates currentPosition) {
        CharCoordinates newCoordinates = new CharCoordinates(0,0);
        switch(move) {
            case UP:
                newCoordinates = new CharCoordinates(currentPosition.getX(),currentPosition.getY() + 1);
            case DOWN:
                newCoordinates = new CharCoordinates(currentPosition.getX(),currentPosition.getY() - 1);
            case LEFT:
                newCoordinates = new CharCoordinates(currentPosition.getX() + 1,currentPosition.getY());
            case RIGHT:
                newCoordinates = new CharCoordinates(currentPosition.getX() - 1,currentPosition.getY());
        }

        if (newCoordinates.getX() > N || newCoordinates.getY() > N) {
            return currentPosition.getCharInMatrix(board);
        }

        return newCoordinates.getCharInMatrix(board);
    }
    
    public boolean exist() {
        int matchesCount = 0;
        for (CharCoordinates item: listFirstSymbolPositionsOnBoard()) {
            for (int i = 1; i < word.length(); i++) {
                Iterator iterator = EnumSet.allOf(Move.class).iterator();
                while (iterator.hasNext()) {
                    if(moveOnBoard((Move)iterator.next(),item) == word.charAt(i)) {
                        matchesCount++;
                        break;
                    }
                }

            }
            if (matchesCount != word.length()) matchesCount = 0;
        }
        return matchesCount == word.length();
    }
}
