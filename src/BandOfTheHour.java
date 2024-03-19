// Imports scanner.
import java.util.Scanner;

/**
 * Class description.
 * @author Madeline Cole
 * @version Version 1
 * @see BandOfTheHour
 */
public class BandOfTheHour {
    // Scanner to read user input.
    private static Scanner keyboard = new Scanner(System.in);
    // Maximum number of rows.
    private static final int MAX_ROWS = 10;
    // Maximum number of positions.
    private static final int MAX_POSITIONS = 8;
    // Maximum weight.
    private static final double MAX_WEIGHT = 200.0;
    // Minimum weight.
    private static final double MIN_WEIGHT = 45.0;
    // Max Weight Per Row.
    private static final double MAX_WEIGHT_PER_POSITION = 100.0;

    /**
     Method description.
     * Main Method
     * @param args The command line arguments.
     * @see BandOfTheHour
     */
    public static void main(String[] args) { //beginning of main method
        // Array to hold the positions.
        double[][] positions = new double[MAX_ROWS][];
        // Creates a variable to hold letters for each given row.
        char rowLetter;
        // Creates a variable to hold position number.
        int positionNumber;
        // Creates a variable to hold the total weight of each row.
        double totalWeight = 0.0;
        // Creates a variable where true is showing the options and false is exitiing the program
        String option;
        // Creates a variable to hold total weight of each row.
        totalWeight = 0.0;
        // Creates a variable where true shows the option menu and false will not display the option menu.
        boolean showMenu = true;
        // Creates a variable that holds the max positions for each row.
        int maxPositionsInRow = 0;

        // Prints title of program.
        System.out.println("Welcome to the Band of the Hour");
        System.out.println("--------------------------------");

        // Prompt the user to enter the number of rows and stores it into numRows.
        System.out.print("Please enter number of rows              : ");
        int numRows = keyboard.nextInt();


        // Validates the input is within 1-10 rows.
        while (numRows < 1 || numRows > MAX_ROWS) {
            // Prompts the user for a valid input until one is given.
            System.out.print("ERROR: Out of range, try again           : ");
            numRows = keyboard.nextInt();
        } // End of while loop.

        // Loop through each row.
        for (int i = 0; i < numRows; i++) {
            // Creates letters for each row based on the number of rows given.
            rowLetter = (char) ('A' + i);


            // Prompt the user to enter the number of positions in the row.
            System.out.printf("Please enter number of positions in row %c: ", rowLetter);
            int numPositions = keyboard.nextInt();
            /**
             * Validates that the input is within 1-8 positions.
             * If it's out of range, ask the user to try again.
             */
            while (numPositions < 1 || numPositions > MAX_POSITIONS) {
                System.out.print("ERROR: Out of range, try again           : ");
                numPositions = keyboard.nextInt();
            } // End of while loop.


            // Initialize the positions in the row.
            positions[i] = new double[numPositions];
        } // End of for loop.

        /**
         * This is the main loop of the program.
         * It will keep running until the user chooses to exit.
         */
        do {
            // If showMenu is true, print the menu options.
            if(showMenu){
                System.out.print("\n(A)dd, (R)emove, (P)rint,         e(X)it : "); }
            // Reads the users option and converts it the uppercase.
            option = keyboard.next().toUpperCase();

            // Exit the loop immediately if the option is "X".
            if (option.equals("X")) {
                break;
            }

            /**
             * Switch statement to handle different user options.
             */
            switch (option) {
                case "A": // Add a musician option.
                    // Prompt user for the row letter.
                    System.out.print("Please enter row letter                  : ");
                    rowLetter = keyboard.next().toUpperCase().charAt(0);

                    // Validate the entered row letter.
                    while (rowLetter < 'A' || rowLetter >= 'A' + numRows) {
                        System.out.print("ERROR: Out of range, try again           : ");
                        rowLetter = keyboard.next().toUpperCase().charAt(0);
                    } // End of while loop.

                    // Prompts user for position number.
                    System.out.print("Please enter position number (1 to " + positions[rowLetter - 'A'].length + ")    : ");
                    positionNumber = keyboard.nextInt();

                    // Validates that the entered position number is within 1-total positions in row.
                    while (positionNumber < 1 || positionNumber > positions[rowLetter - 'A'].length) {
                        System.out.print("ERROR: Out of range, try again           : ");
                        positionNumber = keyboard.nextInt();
                    } // End of while loop.

                    // Check if there's already a musician at the entered position.
                    if (positions[rowLetter - 'A'][positionNumber - 1] > 0) {
                        System.out.println("ERROR: There is already a musician there.");
                        break;
                    } // End of if statement.

                    // Calculate the maximum total weight for the row.
                    double MAX_WEIGHT_PER_ROW = MAX_WEIGHT_PER_POSITION * positions[rowLetter - 'A'].length;

                    // Initialize newTotalWeight.
                    double newTotalWeight = 0;

                    // Calculate the current total weight for the row.
                    for (double positionWeight : positions[rowLetter - 'A']) {
                        newTotalWeight += positionWeight;
                    }

                    // Initialize weight.
                    double weight;

                    // Use a do-while loop to ask for the weight at least once and repeat until a valid weight is entered.
                    //do {
                    // Prompts User for weight.
                    System.out.print("Please enter weight (" + MIN_WEIGHT + " to " + MAX_WEIGHT + ")      : ");
                    weight = keyboard.nextDouble();


                    // Validates that the entered weight is within 45.0 & 200.0.
                    while (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
                        System.out.print("ERROR: Out of range, try again           : ");
                        weight = keyboard.nextDouble();
                    } // End of while loop.
                    
                    // Calculate the new total weight for the row if the musician is added.
                    newTotalWeight += weight;

                    // If the new total weight would exceed the limit, print an error message.
                    if (newTotalWeight > MAX_WEIGHT_PER_ROW) {
                        System.out.println("ERROR: That would exceed the average weight limit.");
                        break;
                        // newTotalWeight -= weight; // Subtract the weight so the loop continues.
                        // weight = 0; // Reset the weight to 0 so the loop continues.
                    } // End of if statememnt.
                    //} while (weight == 0); // Repeat the loop if the weight is 0.

                    // If not, add the musician to the position and update the total weight.
                    positions[rowLetter - 'A'][positionNumber - 1] = weight;
                    System.out.println("****** Musician added.");

                    // Set showMenu to true so the menu will be printed in the next iteration.
                    showMenu = true;
                    break; // end of case A


                case "R": // Remove a musician option.
                    System.out.print("Please enter row letter                  : ");
                    // Reads the user's row letter and converts it to uppercase.
                    rowLetter = keyboard.next().toUpperCase().charAt(0);

                    /**
                     * Validate the entered row letter.
                     * If it's out of range, ask the user to try again.
                     */
                    while (rowLetter < 'A' || rowLetter >= 'A' + numRows) {
                        System.out.print("ERROR: Out of range, try again           : ");
                        rowLetter = keyboard.next().toUpperCase().charAt(0);
                    }
                    // Prompts the user for the position number.
                    System.out.print("Please enter position number (1 to " + positions[rowLetter - 'A'].length + ")    : ");
                    positionNumber = keyboard.nextInt();

                    /**
                     * Validate the entered position number.
                     * If it's out of range, ask the user to try again.
                     */
                    while (positionNumber < 1 || positionNumber > positions[rowLetter - 'A'].length) {
                        System.out.print("ERROR: Out of range, try again           : ");
                        positionNumber = keyboard.nextInt();
                    }

                    /**
                     * Check if the position is vacant.
                     * If so, print an error message.
                     * If not, remove the musician and print a success message.
                     */
                    if (positions[rowLetter - 'A'][positionNumber - 1] == 0.0) {
                        System.out.println("ERROR: That position is vacant.");
                    } else {
                        positions[rowLetter - 'A'][positionNumber - 1] = 0.0;
                        System.out.println("****** Musician removed.");
                    }
                    // Set showMenu to true so the menu will be printed in the next iteration.
                    showMenu = true;
                    break; //end of case R


                case "P": //print positions of musicians option.
                    System.out.println();
                    // Find the maximum number of positions in any row.
                    for (int i = 0; i < numRows; i++) {
                        if (positions[i].length > maxPositionsInRow) {
                            maxPositionsInRow = positions[i].length;
                        }
                    }

                    /**
                     * Print the positions and weights of the musicians in each row.
                     */
                    for (int i = 0; i < numRows; i++) {
                        rowLetter = (char) ('A' + i);
                        System.out.print(rowLetter + ": ");
                        // Resets the totalWeight.
                        totalWeight = 0.0;
                        for (int j = 0; j < positions[i].length; j++) {
                            System.out.printf("%5.1f ", positions[i][j]);
                            totalWeight += positions[i][j];
                        } // End of inner for loop.
                        
                        // Print spaces to align the output.
                        for (int j = positions[i].length; j < maxPositionsInRow; j++) {
                            System.out.print("      "); // 6 spaces to align with "%5.1f "
                        } // End of inner for loop.

                        /**
                         * Calculate and print the total and average weight of the musicians in the row.
                         */
                        double averageWeight = totalWeight / positions[i].length;
                        System.out.printf(" [ %5.1f, %5.1f]\n", totalWeight, averageWeight);
                        // Set showMenu to true so the menu will be printed in the next iteration.
                        showMenu = true;
                    } // End of for loop.
                    break; // End of case P.

                /**
                 * If the user's option is not recognized, print an error message and set showMenu to false.
                 */
                default:
                    System.out.print("Invalid option, try again                : ");
                    showMenu = false;
                    break; // End of default case.
            } // End of switch statement.
        } while (true); // End of do-while loop.

    } // End of the main method.
} // End of the BandOfTheHour class.
