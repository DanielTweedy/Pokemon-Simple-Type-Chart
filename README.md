# SimpleTypeChart
## Description
Program tries to simplify looking up type advantages of Pokemon by taking Pokemon input types and prints out the multipliers am attack type has on the defending Pokemon based on the options used on the command line.
## Usage
SimpleTypeChart [options] \<type1\> [type2] [options]
### Example
`java SimpleTypeChart water #type is case insensitive and can accept WaTer, Water, WATER, etc.`
```
          water
  NORMAL: 1.00
FIGHTING: 1.00
  FLYING: 1.00
  POISON: 1.00
  GROUND: 1.00
    ROCK: 1.00
     BUG: 1.00
   GHOST: 1.00
   STEEL: 0.50
    FIRE: 0.50
   WATER: 0.50
   GRASS: 2.00
ELECTRIC: 2.00
 PSYCHIC: 1.00
     ICE: 0.50
  DRAGON: 1.00
    DARK: 1.00
```
## Required Software
Java 7.0 or higher

## How to Build
Program does not currently have a JAR or other way to distribute it.
Program only requires that both the PokeTypes.java and SimpleTypeChart.java are in the same folder and the standard Java Compiler.

`javac SimpleTypeChart.java`
