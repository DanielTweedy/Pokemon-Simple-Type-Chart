/* SimpleTypeChart.java
 *
 * Author: Daniel "MelBrooksKa" Tweedy
 *
 * Usage: SimpleTypeChart [options] <type1> [type2]
 *
 * Program works to simplify looking up Pokemon type advantages by taking a
 * variety of inputs and outputing information about the Pokemon types. By
 * default, the program takes one or two Pokemon types and prints out all
 * possible attack types and their type advantage against the input Pokemon
 * types from the generation II through V chart. With different options,
 * program can print out a specific attack type against the input, print out
 * how type advantage works, a different Pokemon generation type chart, or
 * information on how the program works.
 *
 * Errors: Invalid type name
 *         Too many arguments after the type
 *         Too few arguments
 *
 * Language: Java 1.7 or later
 */
public class SimpleTypeChart {
    // Used for printing out how to use the program on the command-line
    private static final String USAGE = "Usage: SimpleTypeChart [options] <type1> [type2]";
    // Return value for when errors are thrown
    private static final int ERROR_THROWN = 1;

    // input types for the pokemon user in checking
    private static PokeTypes.type type1, type2;
    // attack type and flag for when to print only one type
    private static boolean attackFlag = false;
    private static PokeTypes.type attackType;

    public static void main(String args[]) {
        if(args.length < 1) {
            System.err.println(USAGE);
            System.exit(ERROR_THROWN);
        }

        parseArgs(args);
        if(type1 == null) {
            System.err.println("Error: Needs at least one Pokemon defender type.");
            System.exit(ERROR_THROWN);
        }

        if(type2 == type1) {
            type2 = null;
        }

        if(! attackFlag) {
            printAllEffects();
        } else {
            printEffects();
        }
    }

    /* printAllEffects()
     * Default behavior of the program that prints out all the effectiveness
     * levels of attacks to the input Pokemon type.
     * Assumes that type1 and type2 global variables have been set and checked
     * for errors and that there was no input attack option from the command-line.
     * Returns void.
     */
    private static void printAllEffects() {
        String tempHolder;

        for(PokeTypes.type traveler : PokeTypes.type.values()) {
            tempHolder = traveler.toString().toLowerCase();
            if(traveler.ordinal() == 0) {
                printTypes();
            }

            System.out.printf("%8s: %.2f\n", traveler.toString(), effectLevel(traveler));
        }
    }

    /* effectLevel(PokeTypes.type)
     * Takes an input attack type and returns the effectiveness in the form of
     * a double representing the modifier of the damage the Pokemon will take
     * due to type advantage form that attack.
     * Assumes that the input attack type is a valid type.
     */
    private static double effectLevel(PokeTypes.type atkType) {
        if(type2 == null) {
            return PokeTypes.getTypeAdvantage(type1, atkType).getEffect();
        } else {
            return PokeTypes.getTypeAdvantage(type1, atkType).getEffect() *
                   PokeTypes.getTypeAdvantage(type2, atkType).getEffect();
        }
    }

    /* printTypes()
     * Helper function that prints out a formated output of the input deffense
     * types.
     * Assumes that the type1 and type2 variables have been set and checked for
     * errors.
     * Returns void.
     */
    private static void printTypes() {
        System.out.printf("%15s", type1.toString().toLowerCase());
        if(type2 != null) {
            System.out.print(" " + type2.toString().toLowerCase());
        }

        System.out.println();
    }

    /* printEffects()
     * Prints out a formatted output of the a single input attack rather than
     * all a defending Pokemon's weaknesses.
     * Assumes that inputs have been checked for errors and the type1, type2,
     * and attackType have been set.
     * Returns void.
     */
    private static void printEffects() {
        printTypes();
        System.out.printf("%8s: %.2f\n", attackType.toString(), effectLevel(attackType));
    }

    /* parseArgs(String[])
     * Takes command-line arguments and parses them out for options, option
     * arguments, and pokemon types. Method checks for errors within the
     * arguments while it processes them.
     * Assumes that there is at least one argument.
     */
    private static void parseArgs(String args[]) {
        String tempString;
        int nextItem;

        for(int i = 0; i < args.length; i++) {
            tempString = args[i];
            nextItem = i + 1;

            if(tempString.charAt(0) == '-') {
//                System.out.println("Option: " + tempString);
                i += parseOptions(args, nextItem, tempString);
            } else if(isType(tempString)) {
                if(type1 == null) {
                    type1 = PokeTypes.type.valueOf(tempString.toUpperCase());
                } else if(type2 == null) {
                    type2 = PokeTypes.type.valueOf(tempString.toUpperCase());
                } else {
                    System.err.println("Error: Too many argumetns for the defending Pokemon's type");
                    System.exit(ERROR_THROWN);
                }
            } else {
                System.err.printf("Error: \"%s\" is not a Pokemon Type.\n", tempString);
                System.exit(ERROR_THROWN);
            }
        }
    }

    /* isType(String)
     * Helper function that takes a String input and checks to see if the
     * String matches any pokemon types.
     * Returns True if it is a type, false otherwise.
     * Assume that String is not a possible option.
     */
    private static boolean isType(String inType) {
        for(PokeTypes.type t : PokeTypes.type.values()) {
            if(t.toString().equals(inType.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    /* parseOptions(String[], int, String)
     * Helper function that takes the heavy lifting of parsing out an input
     * option, any arguments, and checks for errors within the input.
     * Assumes that input o is a long or short option format but has not been
     * validated.
     * Returns an integer 0 or 1 for whether or not the NextItem has been used
     * for an argument and the counter in the previous caller is incremented.
     * Exits the program if a an error input occures.
     */
    private static int parseOptions(String args[], int nextItem, String option) {
        final String ATTACK_SHORT = "-a", ATTACK_LONG = "--attack"; // attack option
        final String HELP_OPTION = "--help"; // Option that displays help message
        // Displays how pokemon type advantage works
        final String INFO_OPTION = "--info";
        final int MOVED = 1;
        final int STAYED = 0;

        if(option.equals(ATTACK_SHORT) || option.equals(ATTACK_LONG)) {
//            System.out.println(option);
            if(attackFlag) {
                System.err.printf("Error: Attack already set.\n");
                System.exit(ERROR_THROWN);
            }
            
            if(args.length <= nextItem) {
                System.err.printf("Error: Option \"%s\" requires an argument of a Pokemon type.\n", option);
                System.exit(ERROR_THROWN);
            } else if(! isType(args[nextItem])) {
                System.err.printf("Error: Option \"%s\" is not a valid Pokemon Type for option \"%s\".\n", args[nextItem], option);
                System.exit(ERROR_THROWN);
            }

            attackFlag = true;
            attackType = PokeTypes.type.valueOf(args[nextItem].toUpperCase());

            return MOVED;
        } else if(option.equals(INFO_OPTION)) {
            printInfo();
        } else if(option.equals(HELP_OPTION)) {
            printHelp();
        } else {
            System.err.printf("Error: \"%s\" is not a valid option.\n");
            System.exit(ERROR_THROWN);
        }

        return STAYED;
    }

    private static void printInfo() {
        System.out.println("Info credited to bulbapedia wiki article on types at http://bulbapedia.bulbagarden.net/wiki/Type.");
        System.out.println("A move that is super effective against a Pokemon's type does double damage.\n" + 
                           "A move that is not very effective does 1/2 damage.\n" +
                           "A move that is ineffecive does no damage");
        System.out.println("Pokemon do not generally have just one type, so the interaction between an attack and the\n" +
                           "defending Pokemon's types changes based on the effectivness of the attack to both of the defending\n" + 
                           "Pokemon's types.");
        System.out.println("When the attack move is ineffective against either the defending Pokemon's types, it will deal no damage.");
        System.out.println("If one of the defending Pokemon's types takes normal damage from the attack and the other is super\n" +
                           "weak or strong, the damage will be applied the same way as if the Pokemon had only one type that\n" +
                           "was weak to the attack type.");
        System.out.println("If the attack is super effective against both the defending Pokemon's types, it takes 4 times the damage.");
        System.out.println("If the attack is not very effective against both the defeder's types, it will take 1/4 damage from the attack.");
        System.exit(0);
    }

    private static void printHelp() {
        final String BUFFER = "  ";
        final String DESCRIPTION = "Takes Pokemon Type(s) and prints out information of the type advantages that Pokemon experiences.";
        final String DASH_A = BUFFER.concat("-a, --attack  Takes an input attack type can prints out only the type advantage of the input.");
        final String DASH_INFO = BUFFER.concat("--info        Prints out info on how Pokemon Type advantege works.");
        final String DASH_HELP = BUFFER.concat("--help        Prints out info on how this program works.");

        System.out.println(USAGE);
        System.out.println(DESCRIPTION);
        System.out.println();

        System.out.println(DASH_A);
        System.out.println(DASH_INFO);
        System.out.println(DASH_HELP);
        System.exit(0);
    }
}
