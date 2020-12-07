package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.List;

public class WallFollower extends PlayerImpl implements Player {

    @Override
    public Direction nextMove(Labyrinth l) {
        int pRow = l.getPlayerPosition().getRow();
        int pCol = l.getPlayerPosition().getCol();
        List<Direction> directions = l.possibleMoves();

        // bal keze a falon...
        // ha east - row - 1, col marad
        // west - row + 1, col marad
        // north - row marad, col - 1
        // south - row marad, col + 1

        /*for (Direction d : directions) {
            switch (d) {
                case EAST:
                    Coordinate cE = new Coordinate(pCol, pRow - 1);
                    try {
                        CellType type = l.getCellType(cE);
                        if (type == CellType.WALL) {
                            return Direction.EAST;
                        }
                    } catch (CellException ignored) {
                    }
                    break;
                case WEST:
                    Coordinate cW = new Coordinate(pCol, pRow + 1);
                    try {
                        CellType type = l.getCellType(cW);
                        if (type == CellType.WALL) {
                            return Direction.WEST;
                        }
                    } catch (CellException ignored) {
                    }
                    break;
                case NORTH:
                    Coordinate cN = new Coordinate(pCol - 1, pRow);
                    try {
                        CellType type = l.getCellType(cN);
                        if (type == CellType.WALL) {
                            return Direction.NORTH;
                        }
                    } catch (CellException ignored) {
                    }
                    break;
                case SOUTH:
                    Coordinate cS = new Coordinate(pCol + 1, pRow);
                    try {
                        CellType type = l.getCellType(cS);
                        if (type == CellType.WALL) {
                            return Direction.SOUTH;
                        }
                    } catch (CellException ignored) {
                    }
                    break;
            }
        }*/

        return null;
    }



}
