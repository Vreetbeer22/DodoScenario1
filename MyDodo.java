import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

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
    
    /**
     * Checks if there is a fence ahead
     * if there is mimi jumps over the fence
     * if there is no fence nothing happens
     */
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
    
    /**
     * checks if there is a fence ahead
     * if there is mimi try's to jump over it but if there are multiple fence's she wil keep on going until there is no fance and she can go back down
     * if there is no fence nothing happens
     */
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
    
    /**
     * turns mimi 180 degrees by turning right 2 times
     */
    public void turn180(){
        turnRight();
        turnRight();
    }
    
    /**
     * checks if there is a grian ahead
     * does this by moving forwards and then checking if mimi is standing on a grain
     * then going back to the original space and returning if there is a grain
     */
    public boolean grainAhead(){
        move();
        boolean isGrain = onGrain();
        stepOneCellBackwards();
        return isGrain;
    }
    
    /**
     * mimi keeps on walking in a straight line until on a egg
     */
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
    
    /**
     * gets mimi back to the start of the row
     * does this bij turning aroud, walking untill she can't walk any further and then turn back around
     */
    public void goBackToStartOfRowAndFaceBack() {
         turn180();
         while( ! borderAhead() && canMove()){
            move();
        }
        turn180();
    }
    
    /**
     * walks mimi to the end of the world and climbs over fences in her way
     */
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
    
    /**
     * keeps on walking till the end of the row
     * every time mimi stands on a grain she picks it up and print the coordinates she standing on
     */
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
    
    /**
     * steps one cell backwards
     */
    public void stepOneCellBackwards() {
        turn180();
        move();
        turn180();
    }
    
    /**
     * walks to the end the world and every time mimi is on a empty nest she lays an egg
     */
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
    
    /**
     * keeps on walking along a block of fence's until mimi stands on a egg
     * does this by checking if there is a fence next to her and then move until there isn't
     */
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
    
    /**
     * keeps folowing a trail of eggs to a nest
     * checks if there is a egg infront of her and then steps towards the egg and pick it up
     */
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
    
    /**
     * walks in a simple maze until mimi is on a nest
     * if there is no fence ahead mimi takes a step
     * if there is a fence mimi turns to search for a way with no fence ahead and then takes a step
     */
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
    
    /**
     * checks if there is a fence to the right
     * does this by turning to the right and checking if there is a fence in front
     * then turns back and gives back the result
     */
    public boolean fenceOnRightSide() {
        turnRight();
        boolean result = fenceAhead();
        turnLeft();
        return result;
    }
    
    /**
     * does the same as fenceOnRightSide but for the left
     */
    public boolean fenceOnLeftSide() {
        turnLeft();
        boolean result = fenceAhead();
        turnRight();
        return result;
    }
    
    /**
     * walks in a bit more complex maze until on a nest
     * does this by looking if there is a fence on the right side and if not move to the right
     * if there is a fence on the right mimi checks in front of her to move
     * if there is also a fence in front of mimi she checks the left side
     * and if there is a fence to the left also she turns back and try's a different way
     */
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
    
    /**
     * faces the direction of the number you give
     */
    public void faceDirection(int direction) {
        if (direction < NORTH || direction > WEST) {
            return;
        }
        while (getDirection() !=direction) {
            turnRight();
        }
    }
    
    /**
     * mimi face's north
     */
    public void faceNorth() {
        while (getDirection() !=NORTH) {
            turnRight();
        }
    }
    
    /**
     * mimi face's east
     */
    public void faceEast() {
        while (getDirection() !=EAST) {
            turnRight();
        }
    }
    
    /**
     * mimi face's south
     */
    public void faceSouth() {
        while (getDirection() !=SOUTH) {
            turnRight();
        }
    }
    
    /**
     * mimi face's west
     */
    public void faceWest() {
        while (getDirection() !=WEST) {
            turnRight();
        }
    }
    
    /**
     * checks if the given coordinates are valid
     * does this by asking the outer cords of the world and seeing if they are greater or lesser than the world
     */
    public boolean validCoordinates(int x,int y) {
        if (x > getWorld().getWidth()-1 || y > getWorld().getHeight()-1){
            showError ("Those aren't valid coördinates");
            return false;
        }
        else{
            return true;
        }
    }
    
    /**
     * mimi goes to the given coordinates
     * first checks the coordinates and then keeps walking in either direction until the coordinates of mimi are even to the given cords
     */
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
    
    /**
     * walks to the end of the row and then back
     * every time mimi stands on a egg she counts it
     * at the end she gives the number of found eggs back
     */
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
    
    /**
     * user gives a number of eggs for mimi to lay
     * mimi lays eggs in every space until she reached the wanted amount of eggs or she can't move anymore
     */
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
    
    /**
     * goes to a row below and face the other way from wich mimi was facing first
     */
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
    
    /**
     * walks thruh the whole world counting every eggs she stands on and at the end she returns to her starting position
     */
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
    
    /**
     * counts the eggs in every row to see wich has the most eggs
     * after every row mimi looks if that row has more eggs than the previus and if that is true that row becomes the new row with the most eggs
     * at the end mimi gives back wich row has the most eggs in it
     */
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
     * makes an monument by increasing a count at the start of every row by 1 and filling that much spaces with eggs
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
     * makes a stronger monument by duplicating the count after every row and laying that much eggs
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
     * makes a pyramid of eggs by increasing the count off eggs with 1 for every row
     * and then lays eggs in both ways as much as the count is
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
     * counts the average amount off eggs by first counting the amount off eggs in the world
     * and then dividing that amount by the amount of rows to find the average amount per row
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
    
    /**
     * zorgt dat mimi naar 0.0 kan gaan en naar het oosten kijkt
     * dit gebeurt door dat je mimi eerst een stap neemt een een willekeurige richting
     * daarna kijkt ze welke kant dat op was aan de hand van coordinaten
     * en vanuit daar kan ze dan naar 0.0 gaan lopen
     */
    public void findStartPosition() {
        boolean findMoveDirection = false;
        int startX = getX();
        int startY = getY();
        int newX = 0;
        int newY = 0;
        while (!findMoveDirection){
            if (canMove()){
                move();
                newX = getX();
                newY = getY();
                stepOneCellBackwards();
                findMoveDirection = true;
            }
            else{
                turnLeft();
            }
        }
        if (newX > startX){
            turn180();
            while(canMove()){
                move();
            }
            turnRight();
            while(canMove()){
                move();
            }
            turnRight();
        }
        else if (newX < startX){
            while(canMove()){
                move();
            }
            turnRight();
            while(canMove()){
                move();
            }
            turnRight();
        }
        else if (newY > startY){
            turnRight();
            while(canMove()){
                move();
            }
            turnRight();
            while(canMove()){
                move();
            }
            turnRight();
        }
        else {
            turnLeft();
            while(canMove()){
                move();
            }
            turnRight();
            while(canMove()){
                move();
            }
            turnRight();
        }
    }
    
    /**
     * het zelfde als findMissingEggCell maar nu zonder richtingen
     * gebruikt de bovenstaade functie findStartPosition en turnLeft en turnRight
     */
    public void findMissingEggCellWithoutDirection() {
        int missingX = 0;
        int missingY = 0;
        int count = -1;
        int numberOffEggs;
        boolean endReached = false;
        boolean xReached = false;
        boolean yReached = false;
        int beginX = getX();
        int beginY = getY();
        findStartPosition();
        while (!endReached){
            while (!yReached){
                count++;
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
                    findStartPosition();
                    yReached = true;
                }
                numberOffEggs = 0;
            }
            count = -1;
            turnRight();
            while (!xReached){
                count++;
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
                    findStartPosition();
                    xReached = true;
                }
            }
            findStartPosition();
            while (missingX > getX()){
                move();
            }
            turnRight();
            while (missingY > getY()){
                move();
            }
            if (!onEgg() && missingX != 0 && missingY != 0){
               layEgg(); 
            }
            else if(onEgg()){
                pickUpEgg();
            }
            else{
              showCompliment("Er is niks mis");  
            }
            findStartPosition();
            while (beginX > getX()){
                move();
            }
            turnRight();
            while (beginY > getY()){
                move();
            }
            turnLeft();
            endReached = true;
        }
    }
    
    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }
    
    public void practiceWithListsOfSurpriseEggs(){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 10, getWorld() );
    }
    
    public List<SurpriseEgg> getListOfSurpriseEggsInWorld() {
        return getWorld().getObjects(SurpriseEgg.class);
    }
    
    public void findMostValuebleEgg() {
        int highestValue = 0;
        int highestX = 0;
        int highestY = 0;
        List<SurpriseEgg> listOfEgss= getListOfSurpriseEggsInWorld();
        for (SurpriseEgg egg : listOfEgss){
            System.out.println(egg.getX()+","+egg.getY()+" - "+egg.getValue());
            if (egg.getValue() > highestValue){
                highestValue = egg.getValue();
                highestX = egg.getX();
                highestY = egg.getY();
            }
        }
        System.out.println("De hoogste waarde is: "+highestValue);
        System.out.println("Op coördinaat: "+highestX+","+highestY);
    }
    
        public void calculateAverageEggWorth() {
        int totalEggValue = 0;
        int totalEggs = 0;
        double averageEggValue = 0.0;
        List<SurpriseEgg> listOfEgss= getListOfSurpriseEggsInWorld();
        for (SurpriseEgg egg : listOfEgss){
            totalEggValue = totalEggValue + egg.getValue();
            totalEggs++;
        }
        averageEggValue = (double)totalEggValue / totalEggs;
        System.out.println("Je gemidelde egg value is: "+averageEggValue);
    }
    
    /**
     * kijkt wel nummer er wordt terug gegeven vanuit randomdirection en kiest aan de hand daarvan de richting die mimi op moet kijken
     */
    public void faceRandomDirection(){
        if (randomDirection() == 0){
            faceNorth();
        }
        else if (randomDirection() == 1){
            faceEast();
        }
        else if (randomDirection() == 2){
            faceSouth();
        }        
        else {
            faceWest();
        }
    }
    
    /**
     * mimi zet net zolang stappen in een willekeurige richting totdat de stappen op zijn
     * voor de richting gebruikt ze de faceRandomDirection functie
     * daarna kijkt ze of ze kan lopen en als dat kan zet ze een stap
     */
    public void moveRandomly() {
        int myNrOfStepsTaken = Mauritius.MAXSTEPS;
        int score = 0;
        if (onBlueEgg()){
            pickUpEgg();
            score++;
        }
        else if (onGoldenEgg()){
            pickUpEgg();
            score =+ 5;
        }
        while (myNrOfStepsTaken != 0){
            faceRandomDirection();
            if(!canMove()){
                faceRandomDirection();
            }
            else{
                move();
                if (onBlueEgg()){
                    pickUpEgg();
                    score++;
                }
                else if (onGoldenEgg()){
                    pickUpEgg();
                    score += 5;
                }
                myNrOfStepsTaken--;
                ((Mauritius)getWorld()).updateScore(myNrOfStepsTaken, score);
            }
        }
        faceEast();
    }
    
    public void dodoRace() {
        int myNrOfStepsTaken = Mauritius.MAXSTEPS;
        int score = 0;
        if (onBlueEgg()){
            pickUpEgg();
            score++;
        }
        else if (onGoldenEgg()){
            pickUpEgg();
            score += 5;
        }
        while (myNrOfStepsTaken != 0){ 
            List<Egg> listOfEgss= getListOfEggsInWorld();
            double closestDistance = Double.MAX_VALUE;
            Egg closestEgg = null;
            for (Egg egg : listOfEgss){
                int x = egg.getX() - getX();
                int y = egg.getY() - getY();
                double distance = Math.sqrt(x * x + y * y);
                if (egg instanceof GoldenEgg){
                    distance = distance / 5;
                }
                if (distance < closestDistance){
                    closestDistance = distance;
                    closestEgg = egg;
                } 
            }
            
            if (closestEgg == null){
                break;
            }
            
            if (getX() < closestEgg.getX()) {
                faceEast();
                move();
            } else if (getX() > closestEgg.getX()) {
                faceWest();
                move();
            } else if (getY() < closestEgg.getY()) {
                faceSouth();
                move();
            } else if (getY() > closestEgg.getY()) {
                faceNorth();
                move();
            }
            
            if (onBlueEgg()){
                pickUpEgg();
                score++;
            }
            else if (onGoldenEgg()){
                pickUpEgg();
                score += 5;
            }
            
            myNrOfStepsTaken--;
            ((Mauritius)getWorld()).updateScore(myNrOfStepsTaken, score);
            faceEast();
        }
    }
}