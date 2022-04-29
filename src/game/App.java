package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class App{
    
    Board startBoard = new Board();
    public static void main(String[] args) throws Exception {
        App play = new App();
        play.play();
    }

    public void play (){
        ArrayList<Board> queue = new ArrayList<>();
        queue.add(startBoard);
        // for(int i = 0; i < 3; ++i)
        // {
        //     for(int j = 0; j < 3; ++j)
        //     {
        //         System.out.println(startBoard.start[i][j]);
        //     }
        // }

        Boolean notFound = true;
        while(notFound)
        {
            if(queue.get(0) == null)
            {
                System.out.println("Not found");
                return;
            }
            Board popped = queue.remove(0);

            if(popped.Children())
            {
                popped.print_found(popped.children.get(popped.children.size() - 1));
                notFound = false;
            }
            else {
                for(Board child: popped.children)
                    queue.add(child);
                Collections.sort(queue); 
            }
        }
        
    }
}
