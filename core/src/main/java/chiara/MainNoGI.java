package chiara;


import chiara.controller.AIController;
import chiara.controller.Controller;

import java.util.Scanner;

public class MainNoGI {

    private static Controller controller;
    private static String player;

    private static AIController aiController;
    private static String opponent;

    private static int x;
    private static int y;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static int round = 1;

    public static void main( String[] args ) throws Exception {

        Thread t = Thread.currentThread();

        controller = new Controller();
        aiController = new AIController();

        Scanner in = new Scanner( System.in );
        System.out.println( "Hi. Enter your name.." );
        String name = in.next();
        t.sleep( 700 );
        System.out.println();
        System.out.println( "Hi " + ANSI_GREEN + name + ANSI_RESET + ", and welcome to" + ANSI_BLUE + " Tic-Tac-Toe" + ANSI_RESET + "!" );
        System.out.println();
        t.sleep( 1500 );
        System.out.println( "Press " + ANSI_PURPLE + "1" + ANSI_RESET + " if you want to play with the " + ANSI_PURPLE + "x " +
                ANSI_RESET +
                "or press" + ANSI_CYAN + " 2" + ANSI_RESET + " if you want to play with the" + ANSI_CYAN + " o" + ANSI_RESET + "." );
        player = in.next();
        while ( !checkValidPlayerOption( player ) ) {
            System.out.println();
            System.out.println( "Uh-oh! The number you insert is not valid." );
            System.out.println();
            System.out.println( "Press " + ANSI_PURPLE + "1" + ANSI_RESET + " if you want to play with the " + ANSI_PURPLE + "x " +
                    ANSI_RESET +
                    "or press" + ANSI_CYAN + " 2" + ANSI_RESET + " if you want to play with the" + ANSI_CYAN + "o" + ANSI_RESET + "." );
            player = in.next();
        }
        if ( player.equals( "1" ) ) {
            player = "x";
            opponent = "o";
        } else if ( player.equals( "2" ) ) {
            player = "o";
            opponent = "x";
        }
        t.sleep( 500 );
        System.out.println();
        System.out.println( "Congratulation, you picked the " + ANSI_GREEN + player + ANSI_RESET + "!" +
                " Your opponent will play with the " + ANSI_RED + opponent + ANSI_RESET + "." );
        System.out.println();
        System.out.println();
        t.sleep( 950 );
        System.out.println( ANSI_CYAN + "~~~~~~~ Ready to play? ~~~~~~~" + ANSI_RESET );
        System.out.println();
        t.sleep( 2000 );

        while ( controller.arePositionsAvailable() ) {

            System.out.println( ANSI_PURPLE + "Round: " + round + ANSI_RESET );
            round += 1;
            System.out.println( "Here the positions available: ( X - Y )" );
            System.out.println();
            t.sleep( 500 );
            controller.printAvailablePositions( t );
            t.sleep( 500 );
            controller.printMatrix();
            System.out.println( "Enter your X position:" );
            x = in.nextInt();
            System.out.println( "Enter your Y position:" );
            y = in.nextInt();
            while ( !checkValidCoordinate( x ) || !checkValidCoordinate( y ) ) {
                System.out.println();
                System.out.println( "Uh-oh! The Coordinates you insert are not valid." );
                t.sleep( 600 );
                System.out.println( "Enter your X position:" );
                x = in.nextInt();
                System.out.println( "Enter your Y position:" );
                y = in.nextInt();
            }
            if ( controller.move( player, x, y ) ) {
                t.sleep( 700 );
                controller.printMatrix();

                if ( !controller.arePositionsAvailable() ) {
                    System.out.println( ANSI_PURPLE + "I guess this game ended in a draw!" + ANSI_RESET );
                    return;
                }

                if ( controller.win( player ) ) {
                    System.out.println( ANSI_GREEN + "Congratulation " + ANSI_YELLOW + name + ANSI_RESET + ANSI_GREEN + ", you won!" +
                            ANSI_RESET );
                    return;
                }
            } else {
                while ( !controller.move( player, x, y ) ) {
                    System.out.println();
                    System.out.println( "Uh-oh! The Coordinates you insert are not valid. That position is no long available." );
                    System.out.println();
                    t.sleep( 600 );
                    System.out.println( "Enter your X position:" );
                    x = in.nextInt();
                    System.out.println( "Enter your Y position:" );
                    y = in.nextInt();

                    if ( !controller.arePositionsAvailable() ) {
                        System.out.println( "I guess this game ended in a draw!" );
                        return;
                    }

                    if ( controller.win( player ) ) {
                        System.out.println( ANSI_GREEN + "Congratulation " + ANSI_YELLOW + name + ANSI_RESET + ANSI_GREEN + ", you won!" +
                                ANSI_RESET );
                        return;
                    }
                }
                t.sleep( 700 );
                controller.printMatrix();
            }

            System.out.println();
            t.sleep( 500 );
            if ( !controller.arePositionsAvailable() ) {
                System.out.println( ANSI_PURPLE + "I guess this game ended in a draw!" + ANSI_RESET );
                return;
            }
            System.out.println( ANSI_YELLOW + "--- opponent's move ---" + ANSI_RESET );
            System.out.println();
            t.sleep( 1500 );
            aiController.generateIAPosition( controller.getField(), opponent, player );
            if ( controller.win( opponent ) ) {
                controller.printMatrix();
                System.out.println( ANSI_RED + "Uh-oh.. You lost :(" + ANSI_RESET );
                return;
            }
            controller.printMatrix();
        }

        System.out.println( ANSI_PURPLE + "I guess this game ended in a draw!" + ANSI_RESET );
    }

    public static Boolean checkValidCoordinate( int x ) {
        if ( x < 0 || x > 2 )
            return false;
        return true;
    }

    public static Boolean checkValidPlayerOption( String p ) {
        if ( p.equals( "1" ) || p.equals( "2" ) )
            return true;
        return false;
    }


}
