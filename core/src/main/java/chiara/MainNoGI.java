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

    public static void main( String[] args ) throws Exception {

        Thread t = Thread.currentThread();

        controller = new Controller();
        aiController = new AIController();

        Scanner in = new Scanner( System.in );
        System.out.println( "Hi. Enter your name.." );
        String name = in.next();
        t.sleep( 700 );
        System.out.println();
        System.out.println( "Hi " + name + ", and welcome to Tic-Tac-Toe!" );
        System.out.println();
        t.sleep( 1500 );
        System.out.println( "Press 1 if you want to play with the x item or press 2 if you want to play with the o item" );
        player = in.next();
        while ( !checkValidPlayerOption( player ) ) {
            System.out.println();
            System.out.println( "Uh-oh! The number you insert is not valid." );
            System.out.println();
            System.out.println( "Press 1 if you want to play with the x item or press 2 if you want to play with the o item" );
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
        System.out.println( "Congratulation, you picked the " + player + "!" +
                " Your opponent will play with the " + opponent );
        System.out.println();
        System.out.println();
        t.sleep( 950 );
        System.out.println( "~~~~~~~ Ready to play? ~~~~~~~" );
        System.out.println();
        t.sleep( 2000 );

        //TODO quando le posizioni libere sono finite, il gioco termina

        while ( controller.arePositionsAvailable() ) {

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

                if ( controller.win( player ) ) {
                    System.out.println( "Congratulation " + name + ", you won!" );
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

                    if ( controller.win( player ) ) {
                        System.out.println( "Congratulation " + name + ", you won!" );
                        return;
                    }
                }
                t.sleep( 700 );
                controller.printMatrix();
            }

            System.out.println();
            t.sleep( 500 );
            System.out.println( "--- opponent's move ---" );
            System.out.println();
            t.sleep( 1500 );
            aiController.generateIAPosition( controller.getField(),opponent,player );
            if ( controller.win( opponent ) ) {
                controller.printMatrix();
                System.out.println( "Uh-oh.. You lost :(" );
                return;
            }
            controller.printMatrix();
        }

        System.out.println( "I guess this game ended in a draw!" );
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
