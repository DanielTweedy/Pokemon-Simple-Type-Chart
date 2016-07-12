Program: simpleTypeChart

Author: Daniel "MelBrooksKA" Tweedy

Program trys to simplify the rules within the Pokemon type chart for quick
access to what an input Pokemon type is weak against. Takes arguments on the
command-line of one or two Pokemon types and different options for the output
[To Be Determined]. With no options, the program will simply look up the input
types and display what types of attacks are Strong, Weak, and Ineffective
(if any) against it. with the -a option, program will take an input type of
attack and tell whether the Pokemon of input type is weak, strong, or uneffected
by it and the multiplier. [other options to be determined, may add an option for
looking up Pokemon types by the Pokemon's name].

Usage: simpleTypeChart [options] <type1> [type2] [options]

Options: -a, --attack <attackType>
            Out only the effectiveness of the input attack type.

         --info
            Prints out how Pokemon type advantage works.

         --help
            Prints out how the program works.


Versions:
    It's Pre-Alpha:
    - Basic functionallity implemented for type chart generation of pokemon 2
      through 5.
    - Probably contains bugs.
    - Implements options for attack, info, and help.
    - Basic documentation, probably full of typos.
