package Lab_Assignment_03;

public abstract class Floor {
    protected void give_point(Game game, int dscore){
        game.add_points(dscore);
    }
    protected void displaymessage(Player player, String floor_name){
        System.out.println(
            player.getName()
            .concat(" has reached ")
            .concat(floor_name)
        );
    }
    abstract void jump(Game game, Player player);
}

class Empty extends Floor {
    private final String floor_name = "An Empty Floor";
    @Override
    protected void jump(Game game, Player player){
        give_point(game, 1);
        displaymessage(player, floor_name);
    }
}

abstract class Snake extends Floor{
    void set_position(){//maybe play demote music

    }
}

class NormalSnake extends Snake{

    @Override
    void jump(Game game, Player player) {
        // TODO Auto-generated method stub
        
    }
    
}

class KingKobra extends Snake{

    @Override
    void jump(Game game, Player player) {
        // TODO Auto-generated method stub
        
    }
    
}

abstract class Ladder extends Floor{
    void set_position(){//maybe play promote

    }

}

class NormalLadder extends Ladder{

    @Override
    void jump(Game game, Player player) {
        // TODO Auto-generated method stub
        
    }
    
}

class Elevator extends Ladder{

    @Override
    void jump(Game game, Player player) {
        // TODO Auto-generated method stub
        
    }
    
}
