package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.List;
import java.util.Map;

public class PlayerImpl implements Player {

    public PlayerImpl() {
    }

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> directions = l.possibleMoves();

        if (directions.size() > 0) {
            int step = (int)(Math.random() * directions.size());
            return directions.get(step);
        }

        return null;
    }

}
