package com.company;

import java.util.*;


class Pos {
    int c;
    int r;

    public Pos(int r, int c) {
        this.c = c;
        this.r = r;
    }

    public String toString() {
        return "(" + r + ", " + c + ") ";
    }

    public static boolean equal (Pos p1, Pos p2) {
        return p1.r == p2.r && p1.c == p2.c;
    }
}

enum Direction {
    NOT_VISITED,
    START,
    UP,
    DOWN,
    LEFT,
    RIGHT,
    PATH,
    END
}


public class Grid {
    int size = 0;
    int weight[][] = null;



    public Grid(int size) {
        this.size = size;
        weight = new int[size][size];
        initWeight();
    }

    void initWeight() {
        for (int r = 0; r < size; r++) {
            Arrays.fill(weight[r], 1);
        }

        for(int r = 1; r < size -1; r++) {
            weight[r][size/2 - 1] = 1000;
        }
    }

    public void print(int[][] m) {
        for(int r = 0; r < size; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < size; c++) {
                sb.append(m[r][c]);
                sb.append("    ");
            }
            System.out.println(sb);
        }
    }

    public void print(Direction[][] m) {
        for(int r = 0; r < size; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < size; c++) {
                String str = "";
                Direction d = m[r][c];
                switch (d) {
                    case UP:
                        str = "|";
                        break;

                    case DOWN:
                        str = "|";
                        break;

                    case LEFT:
                        str = "-";
                        break;

                    case RIGHT:
                        str = "-";
                        break;

                    case NOT_VISITED:
                        str = " ";
                        break;

                    case START:
                        str = "*";
                        break;

                    case PATH:
                        str = "+";
                        break;


                    case END:
                        str = "*";
                        break;

                }
                sb.append(str);
                sb.append("    ");
            }
            System.out.println(sb);
        }
    }

    public void print(ArrayList<Pos> nodes) {
        if(nodes == null)
            return;
        for(Pos p: nodes) {
            System.out.println(p);
//            System.out.println("(" + p.r + ", " + p.c + ") ");
        }
    }


    Direction[][] createDirectionMatrix() {
        Direction[][] cameFrom = new Direction[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j< size; j++) {
                cameFrom[i][j] = Direction.NOT_VISITED;
            }
        }
        return cameFrom;
    }

    Pos move(Pos p, Direction d) {
        int r = p.r;
        int c = p.c;
        switch(d) {
            case UP:
                if(--r < 0)
                    return null;
                break;
            case DOWN:
                if(++r >= size)
                    return null;
                break;
            case LEFT:
                if(--c < 0)
                    return null;
                break;
            case RIGHT:
                if(++c >= size)
                    return null;
                break;
        }
        return new Pos(r, c);
    }

    Pos move_back(Pos p, Direction d) {
        int r = p.r;
        int c = p.c;
        switch(d) {
            case UP:
                if(++r < 0)
                    return null;
                break;
            case DOWN:
                if(--r >= size)
                    return null;
                break;
            case LEFT:
                if(++c < 0)
                    return null;
                break;
            case RIGHT:
                if(--c >= size)
                    return null;
                break;
        }
        return new Pos(r, c);
    }

//----------------------------------------------------------------------------------------------------------------------
// breadth first search
    void explore(Pos p, Direction d, Queue<Pos> frontier, Direction[][] cameFrom) {
        Pos newPos = move(p, d);
        if(newPos == null)
            return;
        int r = newPos.r;
        int c = newPos.c;

        // do not step on barrier
        if(weight[r][c] == -1)
            return;

        // not explored before
        if(cameFrom[r][c] != Direction.NOT_VISITED)
            return;

        // now, let us explore it
        frontier.add(new Pos(r, c));
        cameFrom[r][c] = d;
    }

    public boolean bfs (Pos p1, Pos p2) {
        Queue<Pos> queue = new LinkedList<>();
        Direction[][] cameFrom = createDirectionMatrix();

        queue.add(p1);
        cameFrom[p1.r][p1.c] = Direction.START;

        while(!queue.isEmpty()) {
            Pos p = queue.remove();
            if(Pos.equal(p, p2)) {
                Direction d = cameFrom[p.r][p.c];
                while(d != Direction.START) {
                    cameFrom[p.r][p.c] = Direction.PATH;
                    p = move_back(p, d);
                    d = cameFrom[p.r][p.c];
                }
                print(cameFrom);
                return true;
            }

            explore(p, Direction.UP, queue, cameFrom);
            explore(p, Direction.DOWN, queue, cameFrom);
            explore(p, Direction.LEFT, queue, cameFrom);
            explore(p, Direction.RIGHT, queue, cameFrom);

        }
        return false;
    }

    class PosCost {
        Pos p;
        int cost;

        public PosCost(Pos p, int cost) {
            this.p = p;
            this.cost = cost;
        }
    }

//----------------------------------------------------------------------------------------------------------------------
// DJK
    class PosCost_Comparator implements Comparator<PosCost> {
        public int compare(PosCost pc1, PosCost pc2)
        {
            return pc1.cost - pc2.cost;
        }
    }

    void explore(Pos p, Direction d, PriorityQueue<PosCost> frontier, Direction[][] history, int[][] cost) {
        Pos newPos = move(p, d);
        if(newPos == null)
            return;
        int r = newPos.r;
        int c = newPos.c;

        int pc = cost[p.r][p.c];
        int new_pc = weight[r][c] + pc;

        // do not step on barrier
        if(weight[r][c] == -1)
            return;

        // not explored before
        if(history[r][c] != Direction.NOT_VISITED && new_pc > cost[r][c])
            return;

        // now, let us explore it
        frontier.add(new PosCost(new Pos(r, c), new_pc));
        history[r][c] = d;
        cost[r][c] = new_pc;
    }

    public boolean djk (Pos p1, Pos p2) {
        PriorityQueue<PosCost> frontier = new PriorityQueue<>(new PosCost_Comparator());
        Direction[][] history = createDirectionMatrix();
        int[][] cost = new int[size][size];

        PosCost p1c = new PosCost(p1, 0);
        cost[p1.r][p1.c] = 0;
        frontier.add(p1c);
        history[p1.r][p1.c] = Direction.START;

        while(!frontier.isEmpty()) {
            Pos p = frontier.remove().p;
            if(Pos.equal(p, p2)) {
                Direction d = history[p.r][p.c];
                while(d != Direction.START) {
                    history[p.r][p.c] = Direction.PATH;
                    p = move_back(p, d);
                    d = history[p.r][p.c];
                }
                print(history);
                return true;
            }

            explore(p, Direction.UP, frontier, history, cost);
            explore(p, Direction.DOWN, frontier, history, cost);
            explore(p, Direction.LEFT, frontier, history, cost);
            explore(p, Direction.RIGHT, frontier, history, cost);

        }
        return false;
    }

//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//Greedy

    int getDistance(Pos p1, Pos p2) {
        int sum = (p2.c - p1.c) * (p2.c - p1.c);
        sum += (p2.r - p1.r) * (p2.r - p1.r);
        return sum;

    }

    void explore_greedy(Pos p, Direction d, PriorityQueue<PosCost> frontier, Direction[][] history, Pos p2) {
        Pos newPos = move(p, d);
        if(newPos == null)
            return;
        int r = newPos.r;
        int c = newPos.c;

        // do not step on barrier
        if(weight[r][c] == -1)
            return;

        // not explored before
        if(history[r][c] != Direction.NOT_VISITED)
            return;

        // now, let us explore it
        frontier.add(new PosCost(new Pos(r, c), getDistance(newPos, p2)));
        history[r][c] = d;
    }

    public boolean greedy (Pos p1, Pos p2) {
        PriorityQueue<PosCost> frontier = new PriorityQueue<>(new PosCost_Comparator());
        Direction[][] history = createDirectionMatrix();

        PosCost p1c = new PosCost(p1, getDistance(p1, p2));
        frontier.add(p1c);
        history[p1.r][p1.c] = Direction.START;

        while(!frontier.isEmpty()) {
            Pos p = frontier.remove().p;
            if(Pos.equal(p, p2)) {
                Direction d = history[p.r][p.c];
                while(d != Direction.START) {
                    history[p.r][p.c] = Direction.PATH;
                    p = move_back(p, d);
                    d = history[p.r][p.c];
                }
                print(history);
                return true;
            }

            explore_greedy(p, Direction.UP, frontier, history, p2);
            explore_greedy(p, Direction.DOWN, frontier, history, p2);
            explore_greedy(p, Direction.LEFT, frontier, history, p2);
            explore_greedy(p, Direction.RIGHT, frontier, history, p2);

        }
        return false;
    }
//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------
//AStar
void explore_AStar(Pos p, Direction d, PriorityQueue<PosCost> frontier, Direction[][] history, int[][] cost, Pos p2) {
    Pos newPos = move(p, d);
    if(newPos == null)
        return;
    int r = newPos.r;
    int c = newPos.c;

    int pc = cost[p.r][p.c];
    int new_pc = weight[r][c] + pc + getDistance(newPos, p2);

    // do not step on barrier
    if(weight[r][c] == -1)
        return;

    // not explored before
    if(history[r][c] != Direction.NOT_VISITED && new_pc > cost[r][c])
        return;

    // now, let us explore it
    frontier.add(new PosCost(new Pos(r, c), new_pc));
    history[r][c] = d;
    cost[r][c] = new_pc;
}

    public boolean AStar (Pos p1, Pos p2) {
        PriorityQueue<PosCost> frontier = new PriorityQueue<>(new PosCost_Comparator());
        Direction[][] history = createDirectionMatrix();
        int[][] cost = new int[size][size];

        PosCost p1c = new PosCost(p1, getDistance(p1, p2));
        cost[p1.r][p1.c] = 0;
        frontier.add(p1c);
        history[p1.r][p1.c] = Direction.START;

        while(!frontier.isEmpty()) {
            Pos p = frontier.remove().p;
            if(Pos.equal(p, p2)) {
                Direction d = history[p.r][p.c];
                while(d != Direction.START) {
                    history[p.r][p.c] = Direction.PATH;
                    p = move_back(p, d);
                    d = history[p.r][p.c];
                }
                print(history);
                return true;
            }

            explore_AStar(p, Direction.UP, frontier, history, cost, p2);
            explore_AStar(p, Direction.DOWN, frontier, history, cost, p2);
            explore_AStar(p, Direction.LEFT, frontier, history, cost, p2);
            explore_AStar(p, Direction.RIGHT, frontier, history, cost, p2);

        }
        return false;
    }


//----------------------------------------------------------------------------------------------------------------------

    public void test() {
        print(weight);
        System.out.println(AStar(new Pos(2,2), new Pos(5, 5)));
        System.out.println(AStar(new Pos(0,0), new Pos(9, 9)));


    }


}
