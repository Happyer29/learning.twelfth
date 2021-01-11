package ru.vsu.cs.yagodintsevNikitaAndreevich;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);
        String str = "YIY";
        int height = 1;
        int width = 1;

        char[] symbolsArr = stringToCharArr(str);

        height = getHeight(symbolsArr.length - 1, symbolsArr[symbolsArr.length - 1], height, symbolsArr);
        width = getWidth(symbolsArr.length - 1, symbolsArr[symbolsArr.length - 1], width, symbolsArr);


        char[][] mass = new char[height][width];
        mass = getMassToPaint(symbolsArr.length - 1, symbolsArr[symbolsArr.length - 1], width / 2, height - 1, symbolsArr, mass);

        paint(mass, height, width);
    }

    private static void paint(char[][] mass, int height, int width){
        for (int d = 0; d < height; d++)
        {
            for (int q = 0; q < width; q++)
            {
                if ((mass[d][q] != '\\')&&(mass[d][q] != '/')&&(mass[d][q] != '|'))
                    System.out.print(".");
                else System.out.print(mass[d][q]);
            }
            System.out.println();
        }
    }

    private static char[][] getMassToPaint(int num, char sym, int startx, int starty, char[] symbolsArr, char[][] mass){
        int i = 0;
        for(i = 0; i<Math.pow(2, num); i++)
            mass[starty-i][startx] = '|';
        //System.out.println(mass);
        if (sym == 'Y'){
            int z=1;
            for(; i< Math.pow(2, num+1); z++, i++)
            {
                mass[starty - i ][startx + z] = '/';
                mass[starty - i][startx - z] = '\\';
            }
            if (num > 0)
            {
                num--;
                getMassToPaint(num, symbolsArr[num], startx + (z-1), starty - i, symbolsArr, mass);
                getMassToPaint(num, symbolsArr[num], startx - (z-1), starty - i, symbolsArr, mass);
            }
        }
        if (sym == 'I'){
            for (; i < Math.pow(2, num + 1); i++)
                mass[starty - i][startx] = '|';
            if (num != 0)
            {
                num--;
                getMassToPaint(num, symbolsArr[num], startx, starty - i, symbolsArr, mass);
            }
        }
        return mass;
    }

    private static int getHeight(int num, char sym, int height, char[] symbolsArr) {
        height *= 2;
        if (num > 0) {
            num--;
            height += getHeight(num, symbolsArr[num], height, symbolsArr);
        }
        return height;
    }

    private static int getWidth(int num, char sym, int width, char[] symbolsArr) {
        if (symbolsArr[num] == 'Y'){
            width += Math.pow(2, num + 1);
        }
        if (num > 0) {
            num--;
            width = getWidth(num, symbolsArr[num], width, symbolsArr);
        }
        return width;
    }

    private static char[] stringToCharArr(String str){
        char[] ch = new char[str.length()];

        for (int i = str.length() - 1; i >= 0; i--) {
            ch[str.length() - 1 - i] = str.charAt(i);
        }

        return ch;
    }

}
