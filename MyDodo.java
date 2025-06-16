import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead()){
            return false;
        } else {
            return true;
        }
    }
    
    public void climbOverFence() {
        if (fenceAhead()){
            turnLeft();
            move();
            turnRight();
            move();
            move();
            turnRight();
            move();
            turnLeft();
        }
    }
    
        public void climbOverMultipleFences() {
        boolean voltooid = false;
        if (fenceAhead()){
            turnLeft();
            move();
            turnRight();
            move();
            move();
            turnRight();
            while(!voltooid){
            if (fenceAhead()){
                turnLeft();
                move();
                turnRight();
                }
            else{
                voltooid = true;
            }
            }
            move();
            turnLeft();
        }
    }
    
    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public String jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
        }
        return"you have moved "+nrStepsTaken+" steps";
    }

    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        while( ! borderAhead() && canMove()){
            System.out.println("X: " + getX() + " Y: " + getY());
            move();
        }
        System.out.println("X: " + getX() + " Y: " + getY());
        System.out.println("kloenk!");
    }
    
    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
       if( onEgg() ){
            return false;
        }else{
            return true;
       }
    }
    
    public void turn180(){
        turnRight();
        turnRight();
    }
    
    public boolean grainAhead(){
        move();
        boolean isGrain = onGrain();
        stepOneCellBackwards();
        return isGrain;
    }
    
    public void goToEgg(){
        boolean eggFound = false;
        while (!eggFound) {
        if(onEgg()) {
            eggFound = true;
            break;
        }else{
            move();
        }
        }
    }
    
    public void goBackToStartOfRowAndFaceBack() {
         turn180();
         while( ! borderAhead() && canMove()){
            move();
        }
        turn180();
    }
    
    public void walkToWorldEdgeClimbingOverFences() {
        while( ! borderAhead()){
            if (onNest()){
                break;
            }
            if (fenceAhead()){
                climbOverMultipleFences();
            }
            else{
            move();
            }
        }
    }
    
    public void pickUpGrainsAndPrintCoordinates() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        while(! borderAhead()){
            if (onGrain()){
                pickUpGrain();
                System.out.println("X: " + getX() + " Y: " + getY());
            }
            move();
            if (onGrain()){
                pickUpGrain();
                System.out.println("X: " + getX() + " Y: " + getY());
            }
        }
    }
    
    public void stepOneCellBackwards() {
        turn180();
        move();
        turn180();
    }
    
    public void walkToWorldEdgeFillingEmptyNests() {
        while(! borderAhead()){
            if (onNest() && !onEgg()){
                layEgg();
            }
            move();
            if (onNest() && !onEgg()){
                layEgg();
            }
        }
    }
    
    public void walkAroundFencedArea() {
        while ( ! onEgg()){
            turnRight();
            if(fenceAhead()){
                turnLeft();
                if(fenceAhead()){
                    turnLeft();
                }
                if(fenceAhead()){
                    turnLeft();
                }
                move();
            }
            else {
                move();
            }
        }
    }
    
    public void eggTrailToNest() {
        while ( !onNest()){
            if (eggAhead() && ! nestAhead()){
                move();
                pickUpEgg();
            }
            else if ( ! eggAhead()){
                turnRight();
                if (eggAhead()){
                    move();
                    pickUpEgg();
                }
            } 
            else if ( ! eggAhead()){
                turn180();
                if (eggAhead()){
                    move();
                    pickUpEgg();
                }
            } 
            else {
                move();
            }
            if (nestAhead()){
                move();
                break;
            }
        }
    }
    
    public void walkInSimpleMaze() {
        while ( ! onNest()){
            if( ! fenceAhead()){
                move();
            }
            else{
                turnRight();
                if( ! fenceAhead()){
                    move();
                }
                else{
                    turn180();
                    move();
                }
            }
        }
    }
    
    public boolean fenceOnRightSide() {
        turnRight();
        boolean result = fenceAhead();
        turnLeft();
        return result;
    }
    
    public boolean fenceOnLeftSide() {
        turnLeft();
        boolean result = fenceAhead();
        turnRight();
        return result;
    }
    
    public void walkInMaze() {
        while ( ! onNest()){
            boolean fenceOnRight = fenceOnRightSide();
            boolean fenceOnLeft = fenceOnLeftSide();
            if ( !fenceOnRight ){
                turnRight();
                move();
            }
            else if ( !fenceAhead() ){
                move();
            }
            else if ( !fenceOnLeft){
                turnLeft();
                move();
            }
            else{
                turn180();
            }
        }
    }
    
    public void faceDirection(int direction) {
        if (direction < NORTH || direction > WEST) {
            return;
        }
        while (getDirection() !=direction) {
            turnRight();
        }
    }
    public void faceNorth() {
        while (getDirection() !=NORTH) {
            turnRight();
        }
    }
    
    public void faceEast() {
        while (getDirection() !=EAST) {
            turnRight();
        }
    }
    
    public void faceSouth() {
        while (getDirection() !=SOUTH) {
            turnRight();
        }
    }
    
    public void faceWest() {
        while (getDirection() !=WEST) {
            turnRight();
        }
    }
    
    public boolean validCoordinates(int x,int y) {
        if (x > getWorld().getWidth()-1 || y > getWorld().getHeight()-1){
            showError ("Those aren't valid coördinates");
            return false;
        }
        else{
            return true;
        }
    }
    
    public void goToLocation(int coordx,int coordy) {
        if (validCoordinates(coordx, coordy)){
            boolean locationXReached = false;
            boolean locationYReached = false;
            while ( !locationXReached ){
                if (getX() < coordx){
                    faceEast();
                    move();
                }
                else if (getX() > coordx){
                    faceWest();
                    move();
                }
                else{
                    locationXReached = true;
                }
            }
            turnRight();
            while ( !locationYReached ){
                if (getY() < coordy){
                    faceSouth();
                    move();
                }
                else if (getY() > coordy){
                    faceNorth();
                    move();
                }
                else{
                    locationYReached = true;
                }
            }
            faceEast();
        }
    }
    
    public int countEggsInRow(){
        int numberOffEggs = 0;
        while( ! borderAhead()){
            if (onEgg()){
                numberOffEggs++;    
            }
            move();
        }
        if (onEgg()){
            numberOffEggs++;    
        }   
        goBackToStartOfRowAndFaceBack();
        return numberOffEggs;
    }
    
    public void layInputNumberOffEggs(int numberOfEggs) {
        int repeat = 0;
        while (numberOfEggs - 1 != repeat){
            if(borderAhead()){
                break;
            }
            if (! onEgg()){
               layEgg(); 
               move();
            }
            else{
                move();
            }
            repeat++;
        }
        if (! onEgg()){
               layEgg(); 
            }
    }
    
    public void goToRowBelowAndTurnAround() {
        if(getDirection() == EAST){
            turnRight();
            move();
            turnRight();
        }
        else if(getDirection() == WEST){
            turnLeft();
            move();
            turnLeft();
        }
    }
    
    public void countEggsInWorld() {
        int eggsInWorld = 0;
        boolean endReached = false;
        int beginX = getX();
        int beginY = getY();
        goToLocation(0,0);
        while (!endReached){
            while( ! borderAhead()){
            if (onEgg()){
                eggsInWorld++;    
            }
            move();
            }
            if (onEgg()){
                eggsInWorld++;    
            }
            if (getY() == getWorld().getHeight() -1){
                endReached = true;
            }
            else {
                goToRowBelowAndTurnAround();
            }
        }
        goToLocation(beginX, beginY);
        showCompliment("je hebt "+eggsInWorld+" gevonden in de wereld");
    }
    
    public void findRowWithMostEggs() {
        boolean endReached = false;
        boolean end = false;
        int mostEggs = 0;
        int numberOffEggs = 0;
        int row = 0;
        int mostInRow = 0;
        int beginX = getX();
        int beginY = getY();
        goToLocation(0,0);
        while( !endReached){
            numberOffEggs = 0;
            row++;
            while( ! borderAhead()){
                if (onEgg()){
                    numberOffEggs++;    
                }
                move();
            }
            if (onEgg()){
                numberOffEggs++;    
            }   
            goBackToStartOfRowAndFaceBack();
            if (numberOffEggs > mostEggs){
                mostEggs = numberOffEggs;
                mostInRow = row;
            }
            if (getY() == getWorld().getHeight() -1){
                endReached = true;
            }
            else {
            turnRight();
            move();
            turnLeft();
            }
        }
        goToLocation(beginX, beginY);
        showCompliment("Je hebt de meeste eieren in rij "+mostInRow);
    }
    
    /**
     * maakt een monument door aan het begin van elke rij de waarde met 1 te verhogen en dan steeds zoveel blokjes in te vullen voordat de dodo terug 
     * gaat naar zijn orginele positie
     */
    public void makeEggMonument(){
        int row = 0;
        int aantal = 0;
        boolean end = false;
        faceEast();
        if (canLayEgg()){
        layEgg();
        }
        turnRight();
        move();
        turnLeft();
        while (!end){
            row++;
            int beginX = getX();
            int beginY = getY();
            while (row >= aantal){
                if (canLayEgg()){
                    layEgg();
                    move();
                }
                else{
                    move();
                }
                aantal++;
            }
            aantal = 0;
            goToLocation(beginX, beginY);
            if (getY() == getWorld().getHeight() -1){
                end = true;
            }
            else {
            turnRight();
            move();
            turnLeft();
            }
        }
    }
    
    /**
     * maakt een stevig monument aan eieren door elke keer na een rij het aantal te verdubbelen
     */
    public void makeEggMonument2(){
        int row = 1;
        int aantal = 0;
        boolean end = false;
        faceEast();
        if (canLayEgg()){
        layEgg();
        }
        turnRight();
        move();
        turnLeft();
        while (!end){
            row = row * 2;
            int beginX = getX();
            int beginY = getY();
            while (row >= aantal){
                if (canLayEgg()){
                    layEgg();
                    move();
                }
                else{
                    move();
                }
                aantal++;
            }
            aantal = 0;
            goToLocation(beginX, beginY);
            if (getY() == getWorld().getHeight() -1){
                end = true;
            }
            else {
            turnRight();
            move();
            turnLeft();
            }
        }
    }
    
    /**
     * maakt een een piramide door voor elke rij de waarde met 1 te verhogen en vervolgens
     * bijde kanten op aan de hand van de hoeveelheid waarde steeds eieren te leggen
     */
    public void makeEggPyramid() {
        int row = 0;
        int aantal = 0;
        boolean end = false;
        faceEast();
        if (canLayEgg()){
        layEgg();
        }
        turnRight();
        move();
        turnLeft();
        while (!end){
            row++;
            int beginX = getX();
            int beginY = getY();
            while (row >= aantal){
                if (canLayEgg()){
                    layEgg();
                    if (canMove()){
                        move();
                    }
                    else{
                        break;
                    }
                }
                else{
                    if (canMove()){
                        move();
                    }
                    else{
                        break;
                    }
                }
                aantal++;
            }
            aantal = 0;
            goToLocation(beginX, beginY);
            faceWest();
            while (row >= aantal){
                if (canLayEgg()){
                    layEgg();
                    if (canMove()){
                        move();
                    }
                    else{
                        break;
                    }
                }
                else{
                    if (canMove()){
                        move();
                    }
                    else{
                        break;
                    }
                }
                aantal++;
            }
            aantal = 0;
            goToLocation(beginX, beginY);
            faceEast();
            if (getY() == getWorld().getHeight() -1){
                end = true;
            }
            else {
            turnRight();
            move();
            turnLeft();
            }
        }
    }
    
    /**
     * telt het gemiddelde aantal eieren per rij door eerst alles te tellen en dat aantal door het aantal rijen te delen
     */
    public void countAverageAmountOfEggsPerRow() {
        int eggsInWorld = 0;
        double averageAmountOffEggs = 0;
        int rowCount = 1;
        boolean endReached = false;
        int beginX = getX();
        int beginY = getY();
        goToLocation(0,0);
        while (!endReached){
            while( ! borderAhead()){
            if (onEgg()){
                eggsInWorld++;    
            }
            move();
            }
            if (onEgg()){
                eggsInWorld++;    
            }
            if (getY() == getWorld().getHeight() -1){
                endReached = true;
            }
            else {
                goToRowBelowAndTurnAround();
                rowCount++;
            }
        }
        averageAmountOffEggs = (double) eggsInWorld/rowCount;
        goToLocation(beginX, beginY);
        showCompliment("Je hebt gemiddeld "+averageAmountOffEggs+" per rij");
    }
    
    /**
    * vindt in je wereld de rij en colom met een missend ei. 
    * hierbij gaat mimi elke rij af totdat er een rij gevonden wordt waar er een oneven aantal eieren ligt.
    * vervolgens onthoud mimi dit coordinaat en doet hetzelfde voor de colomen.
    * daarna heb je 2 coordinaten waar er een ei moet komen en gaat mimi daar naartoe en legt een ei.
    */
    public void findMissingEggscCell() {
        int missingX = 0;
        int missingY = 0;
        int count = -1;
        int numberOffEggs;
        boolean endReached = false;
        boolean xReached = false;
        boolean yReached = false;
        int beginX = getX();
        int beginY = getY();
        goToLocation(0,0);
        while (!endReached){
            while (!yReached){
                count++;
                faceEast();
                numberOffEggs = countEggsInRow();
                if (getY() == getWorld().getHeight() -1){
                    yReached = true;
                }
                if ((numberOffEggs % 2) == 0){
                    turnRight();
                    if ( canMove() ) {
                        step();
                    }
                    turnLeft();
                }
                else{
                    missingY = count;
                    goToLocation(0,0);
                    yReached = true;
                }
                numberOffEggs = 0;
            }
            count = -1;
            while (!xReached){
                count++;
                faceSouth();
                numberOffEggs = countEggsInRow();
                if (getX() == getWorld().getWidth() -1){
                    xReached = true;
                }
                if ((numberOffEggs % 2) == 0){
                    turnLeft();
                    if ( canMove() ) {
                        step();
                    }
                    turnRight();
                }
                else{
                    missingX = count;
                    goToLocation(0,0);
                    xReached = true;
                }
            }
            goToLocation(missingX,missingY);
            if (!onEgg() && missingX != 0 && missingY != 0){
               layEgg(); 
            }
            else if(onEgg()){
                pickUpEgg();
            }
            else{
              showCompliment("Er is niks mis");  
            }
            goToLocation(beginX, beginY);
            endReached = true;
        }
    }
}