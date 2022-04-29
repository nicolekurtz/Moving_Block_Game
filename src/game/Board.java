package game;
import java.util.ArrayList;

public class Board implements Comparable<Board> {
    //public int board[][] = new int[3][3]; 
    public int goal[][] ={ {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
    public int start[][] ={ {1, 4, 7}, {2, 5, 8}, {0, 3, 6} };
    public int board[][] = new int[3][3];
    public Integer fscore; 
    Board myparent; 
    int height;
    int mismatches;
    int blank[] = {0, 0};
    ArrayList <Board> children = new ArrayList<>(); 

    public Boolean calculate(Board toCalc){
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                if(toCalc.board[i][j] != goal[i][j])
                {
                    ++toCalc.mismatches;
                }
            }
        }
        toCalc.fscore = toCalc.height + toCalc.mismatches;
        if(toCalc.mismatches == 0)
            return true;
        return false;
    }

    public Board(Board parent)
    {
         for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) { 
                board[i][j] = parent.board[i][j];
                if(board[i][j] == 0)
                {
                    blank[0] = i;
                    blank[1] = j;
                }
            }
         }
         myparent = parent; 
        height = myparent.height + 1; 
    }
    public Board()
    {
        for(int i = 0; i < 3; ++i) 
        {
                for(int j = 0; j < 3; ++j) { 
                    board[i][j] = start[j][i];
                    if(board[i][j] == 0)
                    {
                        blank[0] = i;
                        blank[1] = j;
                    }
                }
        }
            calculate(this);
            height = 0;
    }

    public void Print(Board found)
    {
        for(int i = 0; i < 3; ++i) 
        {
                for(int j = 0; j < 3; ++j) { 
                    System.out.print(found.board[i][j]);
                }
                System.out.println();
        }
        System.out.println();
    }

    public int print_found(Board found)
    {
        if(found == null) return 1; 
        int count = print_found(found.myparent) + 1; 
        Print(found);
        return count;
    }

    public Boolean Children()
    {
        int grid[][] = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        int x = blank[0];
        int y = blank[1];

        for(int[] each: grid)
        {
            int xc = x + each[0];
            int yc = y + each[1];
            if( (xc >= 0) && (xc < 3))
            {
                if( (yc >= 0) && (yc < 3) )
                {
                    children.add(new Board(this));
                    children.get(children.size() - 1).board[x][y] = board[xc][yc]; 
            
                    children.get(children.size() - 1).board[xc][yc] = 0;

                    children.get(children.size() - 1).blank[0] = xc;
                    children.get(children.size() - 1).blank[1] = yc;
                    if(calculate(children.get(children.size() - 1)))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

  @Override
  public int compareTo(Board u) {
    return fscore.compareTo(u.fscore);
  }
}

