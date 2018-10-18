package chiara.controller;


import static chiara.controller.Controller.DIM;

public class AIController {


    public void generateOpponentPosition( String m[][], String opponent ) {
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( m[ i ][ j ].equals( " " ) ) {
                    m[ i ][ j ] = opponent;
                    return;
                }
            }
        }
    }

    public void generateIAPosition( String m[][], String opponent, String player ) {
        //TODO
        //TODO manca il controllo X_X bloccare nel centro
        if ( horizontalCheck( m, opponent ) ||
                verticalCheck( m, opponent ) ||
                horizontalBlock( m, player, opponent ) ||
                verticalBlock( m, player, opponent ) ||
                middleCheck( m, opponent ) ||
                inverseMiddleCheck( m, opponent ) ||
                middleBlock( m, player, opponent ) ||
                inverseMiddleBlock( m, player, opponent ) )
            return;
        else
            generateOpponentPosition( m, opponent );
    }

    public Boolean horizontalCheck( String m[][], String p ) {
        int j = 0;
        for ( int i = 0; i < DIM; i++ ) {
            if ( m[ i ][ j ].equals( p ) ) {
                if ( m[ i ][ j + 1 ].equals( p ) ) {
                    if ( m[ i ][ j + 2 ].equals( " " ) ) {
                        m[ i ][ j + 2 ] = p;
                        System.out.println( "horizontalCheck" );
                        System.out.println( "opponent: " + i + "-" + j + 2 );
                        return true;
                    } else
                        j = 0;
                }
            }
        }
        return false;
    }

    public Boolean verticalCheck( String m[][], String p ) {
        int i = 0;
        for ( int j = 0; j < DIM; j++ ) {
            if ( m[ i ][ j ].equals( p ) ) {
                if ( m[ i + 1 ][ j ].equals( p ) ) {
                    if ( m[ i + 2 ][ j ].equals( " " ) ) {
                        m[ i + 2 ][ j ] = p;
                        System.out.println( "verticalCheck" );
                        System.out.println( "opponent: " + i + 2 + "-" + j );
                        return true;
                    } else
                        i = 0;
                }
            }
        }
        return false;
    }

    public Boolean middleCheck( String m[][], String p ) {
        int c = 0;
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( i == j ) {
                    if ( m[ i ][ j ].equals( p ) ) {
                        c++;
                        if ( c == DIM - 1 ) {
                            m[ DIM - 1 ][ DIM - 1 ] = p;
                            System.out.println( "middleCheck" );
                            System.out.println( "opponent: 2-2" );
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Boolean inverseMiddleCheck( String m[][], String p ) {
        int c = 0;
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( i + j == DIM - 1 ) {
                    if ( m[ i ][ j ].equals( p ) ) {
                        c++;
                        if ( c == DIM - 1 ) {
                            m[ 0 ][ 0 ] = p;
                            System.out.println( "inverseMiddleCheck" );
                            System.out.println( "opponent: 0-0" );
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    //TODO non funziona da dx verso sx
    public Boolean horizontalBlock( String m[][], String p, String o ) {
        int j = 0;
        for ( int i = 0; i < DIM; i++ ) {
            if ( m[ i ][ j ].equals( p ) ) {
                if ( m[ i ][ j + 1 ].equals( p ) ) {
                    if ( m[ i ][ j + 2 ].equals( " " ) ) {
                        m[ i ][ j + 2 ] = o;
                        System.out.println( "horizontalBlock" );
                        System.out.println( "opponent: " + i + "-" + j + 2 );
                        return true;
                    } else
                        j = 0;
                }
            }
        }
        return false;
    }

    public Boolean verticalBlock( String m[][], String p, String o ) {
        int i = 0;
        for ( int j = 0; j < DIM; j++ ) {
            if ( m[ i ][ j ].equals( p ) ) {
                if ( m[ i + 1 ][ j ].equals( p ) ) {
                    if ( m[ i + 2 ][ j ].equals( " " ) ) {
                        m[ i + 2 ][ j ] = o;
                        System.out.println( "verticalBlock" );
                        System.out.println( "opponent: " + i + 2 + "-" + j );
                        return true;
                    } else
                        i = 0;
                }
            }
        }
        return false;
    }

    public Boolean middleBlock( String m[][], String p, String o ) {
        int c = 0;
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( i == j ) {
                    if ( m[ i ][ j ].equals( p ) ) {
                        c++;
                        if ( c == DIM - 1 ) {
                            if ( m[ DIM - 1 ][ DIM - 1 ].equals( " " ) ) {
                                m[ DIM - 1 ][ DIM - 1 ] = o;
                                System.out.println( "middleBlock" );
                                System.out.println( "opponent: 2-2" );
                                return true;
                            }
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Boolean inverseMiddleBlock( String m[][], String p, String o ) {
        int c = 0;
        for ( int i = 0; i < DIM; i++ ) {
            for ( int j = 0; j < DIM; j++ ) {
                if ( i + j == DIM - 1 ) {
                    if ( m[ i ][ j ].equals( p ) ) {
                        c++;
                        if ( c == DIM - 1 ) {
                            if ( m[ 2 ][ 0 ].equals( " " ) ) {
                                m[ 2 ][ 0 ] = o;
                                System.out.println( "inverseMiddleBlock" );
                                System.out.println( "opponent: 2-0" );
                                return true;
                            }
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

}
