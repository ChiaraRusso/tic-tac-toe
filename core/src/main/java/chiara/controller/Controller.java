package chiara.controller;


public class Controller {

    public static final Integer DIM = 3;

    private String[][] field;

    public Controller() {
        this.field = new String[ DIM ][ DIM ];

        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ )
                field[ i ][ j ] = " ";
        }
    }

    // x and y are the position you want to move to
    public Boolean move( final String player, final int x, final int y ) {

        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( i == x ) {
                    if ( j == y ) {
                        if ( field[ x ][ y ].equals( " " ) ) {
                            field[ x ][ y ] = player;
                            return true;
                        }
                        return false;

                    }
                }
            }
        }
        return false;
    }

    public void printMatrix() {

        System.out.println();
        String fs = String.format(
                "\t  0   1   2  \n"+
                "\t0 %s | %s | %s  \n" +
                        "\t-------------\n" +
                        "\t1 %s | %s | %s  \n" +
                        "\t-------------\n" +
                        "\t2 %s | %s | %s  \n\n",
                field[ 0 ][ 0 ], field[ 0 ][ 1 ], field[ 0 ][ 2 ],
                field[ 1 ][ 0 ], field[ 1 ][ 1 ], field[ 1 ][ 2 ],
                field[ 2 ][ 0 ], field[ 2 ][ 1 ], field[ 2 ][ 2 ]
        );
        String fsNoNumb = String.format(
                        "\t  %s | %s | %s  \n" +
                        "\t-------------\n" +
                        "\t  %s | %s | %s  \n" +
                        "\t-------------\n" +
                        "\t  %s | %s | %s  \n\n",
                field[ 0 ][ 0 ], field[ 0 ][ 1 ], field[ 0 ][ 2 ],
                field[ 1 ][ 0 ], field[ 1 ][ 1 ], field[ 1 ][ 2 ],
                field[ 2 ][ 0 ], field[ 2 ][ 1 ], field[ 2 ][ 2 ]
        );

        System.out.println( fsNoNumb );

    }

    public void printAvailablePositions( Thread t ) throws InterruptedException {
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( field[ i ][ j ].equals( " " ) ) {
                    t.sleep( 500 );
                    System.out.print( " (" + i + "-" + j + ") " );
                }
            }
            System.out.println();
        }
    }

    public Boolean win( String p ) {

        if ( horizontalCheck( p ) || verticalCheck( p ) || middleCheck( p ) || middleCheck( p ) || inverseMiddleCheck( p ))
            return true;
        return false;
    }

    public Boolean horizontalCheck( String p ) {
        int j = 0;
        for ( int i = 0; i < DIM; i++ ) {
            if ( field[ i ][ j ].equals( p ) ) {
                if ( field[ i ][ j + 1 ].equals( p ) ) {
                    if ( field[ i ][ j + 2 ].equals( p ) )
                        return true;
                    else
                        j = 0;
                }
            }
        }
        return false;
    }


    public Boolean verticalCheck( String p ) {
        int i = 0;
        for ( int j = 0; j < DIM; j++ ) {
            if ( field[ i ][ j ].equals( p ) ) {
                if ( field[ i + 1 ][ j ].equals( p ) ) {
                    if ( field[ i + 2 ][ j ].equals( p ) )
                        return true;
                    else
                        i = 0;
                }
            }
        }
        return false;
    }

    public Boolean middleCheck( String p ) {
        int c = 0;
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( i == j ) {
                    if ( field[ i ][ j ].equals( p ) ) {
                        c++;
                    }
                }
            }
        }
        if ( c == DIM )
            return true;
        return false;
    }

    public Boolean inverseMiddleCheck( String p ) {
        int c = 0;
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( i + j == DIM - 1 ) {
                    if ( field[ i ][ j ].equals( p ) ) {
                        c++;
                    }
                }
            }
        }
        if ( c == DIM )
            return true;
        return false;
    }

    public Boolean arePositionsAvailable(){
        for(int i=0; i<DIM; i++){
            for(int j=0; j<DIM; j++){
                if(field[i][j].equals( " " ))
                    return true;
            }
        }
        return false;
    }

    public String[][] getField() {
        return field;
    }
}
