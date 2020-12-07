package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.exceptions.CellException;

public class Testing {

    public static void main(String[] args) throws CellException {

        LabyrinthImpl l = new LabyrinthImpl();
        String fileName1 = "/home/progmatic/github/progmatic-labyrinth/labyrinth1.txt";
        String fileName2 = "/home/progmatic/github/progmatic-labyrinth/labyrinth2.txt";
        String fileName3 = "/home/progmatic/github/progmatic-labyrinth/labyrinth3.txt";
        l.loadLabyrinthFile(fileName1);
        l.outputLabyrinth();

        System.out.println(l.getPlayerPosition().getRow() + " " + l.getPlayerPosition().getCol());


    }

}
