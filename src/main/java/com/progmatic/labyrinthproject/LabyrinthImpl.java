package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    private int width;
    private int height;
    private CellType[][] cells;
    private Coordinate playerCoordinate;

    public LabyrinthImpl() {
        width = 0;
        height = 0;
        cells = new CellType[width][height];
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());

            this.width = width;
            this.height = height;
            this.cells = new CellType[width][height];

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            cells[hh][ww] = CellType.WALL;
                            break;
                        case 'E':
                            cells[hh][ww] = CellType.END;
                            break;
                        case 'S':
                            cells[hh][ww] = CellType.START;
                            playerCoordinate = new Coordinate(ww, hh);
                            break;
                        default:
                            cells[hh][ww] = CellType.EMPTY;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int getWidth() {
        if (cells.length == 0) {
            return -1;
        }
        return width;
    }

    @Override
    public int getHeight() {
        if (cells.length == 0) {
            return -1;
        }
        return height;
    }

    /**
     * Only for testing, because I love to see what I'm doin.
     */
    public void outputLabyrinth() {
        for (CellType[] cell : cells) {
            for (int j = 0; j < cell.length - 1; j++) {
                System.out.print(cell[j].toString() + " ");
            }
            System.out.print(cell[height - 1]);
            System.out.println();
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        int row = c.getRow();
        int col = c.getCol();

        if (row < 0 || row >= cells.length || col < 0 || col >= cells[0].length) {
            throw new CellException(c, "not valid coordinate");
        } else {
            return cells[row][col];
        }
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new CellType[width][height];
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        int row = c.getRow();
        int col = c.getCol();

        if (row < 0 || row >= cells.length || col < 0 || col >= cells[0].length) {
            throw new CellException(c, "not valid coordinate");
        } else {
            cells[row][col] = type;
            if (type == CellType.START) {
                playerCoordinate = new Coordinate(col, row);
            }
        }

    }

    @Override
    public Coordinate getPlayerPosition() {
        return playerCoordinate;
    }

    @Override
    public boolean hasPlayerFinished() {
        int pRow = playerCoordinate.getRow();
        int pCol = playerCoordinate.getCol();

        return cells[pRow][pCol] == CellType.END;
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> directions = new ArrayList<>();
        int pRow = playerCoordinate.getRow();
        int pCol = playerCoordinate.getCol();

        // west - row marad, col - 1
        // east - row marad, col + 1
        // south - row + 1, col marad
        // north - row - 1, col marad

        if (pCol > 0) {
            if (cells[pRow][pCol - 1] != CellType.WALL) {
                directions.add(Direction.WEST);
            }
        }
        if (pCol < width) {
            if (cells[pRow][pCol + 1] != CellType.WALL) {
                directions.add(Direction.EAST);
            }
        }

        if (pRow > 0) {
            if (cells[pRow - 1][pCol] != CellType.WALL) {
                directions.add(Direction.NORTH);
            }
        }

        if (pRow < height) {
            if (cells[pRow + 1][pCol] != CellType.WALL) {
                directions.add(Direction.SOUTH);
            }
        }

        return directions;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        int pRow = playerCoordinate.getRow();
        int pCol = playerCoordinate.getCol();
        // west - row marad, col - 1
        // east - row marad, col + 1
        // south - row + 1, col marad
        // north - row - 1, col marad

        List<Direction> directions = possibleMoves();

        if (!directions.contains(direction)) {
            throw new InvalidMoveException();
        } else {
            switch (direction) {
                case NORTH:
                    playerCoordinate = new Coordinate(pCol, pRow - 1);
                    break;
                case SOUTH:
                    playerCoordinate = new Coordinate(pCol, pRow + 1);
                    break;
                case EAST:
                    playerCoordinate = new Coordinate(pCol + 1, pRow);
                    break;
                case WEST:
                    playerCoordinate = new Coordinate(pCol - 1, pRow);
                    break;
            }
        }
    }

}
