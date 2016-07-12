/* PokeTypes.java
 * Contains all the information for the pokemon types.
 */

public class PokeTypes {
    public enum type {
        NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, BUG, GHOST, STEEL, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON, DARK
    }

    private enum genIType {
        NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, BUG, GHOST, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON
    }

    private enum genII_VType {
        NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, BUG, GHOST, STEEL, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON, DARK
    }

    private enum genVIType {
        NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, BUG, GHOST, STEEL, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON, DARK
    }

    // Contains the values for what type advantage will deal in extra damage 
    public enum EffectLevel {
        INEFFECTIVE(0.0), WEAK(0.5), NORMAL(1.0), STRONG(2.0);

        private double value;

        EffectLevel(final double newValue) {
            value = newValue;
        }

        public double getEffect() {
            return value;
        }
    }

    // Type charts are inverted from Bulbapedia type charts

    // norm fight fly pois grnd rock bug ghst stel fire wter grss elec psyc ice drag dark
    private static final EffectLevel norm = EffectLevel.NORMAL;
    private static final EffectLevel weak = EffectLevel.WEAK;
    private static final EffectLevel str  = EffectLevel.STRONG;
    private static final EffectLevel inef = EffectLevel.INEFFECTIVE;
    private static final EffectLevel genII_V[][] =
    {
             // norm  fght  fly   pois  grnd  rock  bug   ghst  stel  fire  wter  grss  elec  psyc  ice   drag  dark
    /* norm */ {norm, str , norm, norm, norm, norm, norm, inef, norm, norm, norm, norm, norm, norm, norm, norm, norm},
    /* fght */ {norm, norm, str , norm, norm, norm, norm, inef, norm, norm, norm, norm, norm, norm, norm, norm, norm},
    /* fly  */ {norm, weak, norm, norm, inef, str , weak, norm, norm, norm, norm, weak, str , norm, str , norm, norm},
    /* pois */ {norm, weak, norm, weak, str , norm, weak, norm, norm, norm, norm, weak, norm, str , norm, norm, norm},
    /* grnd */ {norm, norm, norm, weak, norm, weak, norm, norm, norm, norm, str , str , inef, norm, str , norm, norm},
    /* rock */ {weak, str , weak, weak, str , norm, norm, norm, norm, norm, str , str , norm, norm, norm, norm, norm},
    /* bug  */ {norm, weak, str , norm, weak, str , norm, norm, norm, str , norm, weak, norm, norm, norm, norm, norm},
    /* ghst */ {inef, inef, norm, weak, norm, norm, weak, norm, norm, norm, norm, norm, norm, norm, norm, norm, str },
    /* stel */ {weak, str , weak, inef, str , weak, weak, weak, weak, str , norm, weak, norm, weak, weak, weak, weak},
    /* fire */ {norm, norm, norm, norm, str , str , weak, norm, weak, weak, str , weak, norm, norm, weak, norm, norm},
    /* wter */ {norm, norm, norm, norm, norm, norm, norm, norm, weak, weak, weak, str , str , norm, weak, norm, norm},
    /* grss */ {norm, norm, str , str , weak, norm, str , norm, norm, str , weak, weak, weak, norm, str , norm, norm},
    /* elec */ {norm, norm, weak, norm, str , norm, norm, norm, weak, norm, norm, norm, weak, norm, norm, norm, norm},
    /* psyc */ {norm, weak, norm, norm, norm, norm, str , str , norm, norm, norm, norm, norm, weak, norm, norm, str },
    /* ice  */ {norm, str , norm, norm, norm, str , norm, norm, str , str , norm, norm, norm, norm, weak, norm, norm},
    /* drag */ {norm, norm, norm, norm, norm, norm, norm, norm, norm, weak, weak, weak, weak, norm, str , str , norm},
    /* dark */ {norm, str , norm, norm, norm, str , weak, norm, norm, norm, norm, norm, norm, inef, norm, norm, weak}
             // norm  fght  fly   pois  grnd  rock  bug   ghst  stel  fire  wter  grss  elec  psyc  ice   drag  dark
    };

    private static final EffectLevel genI[][] =
    {
             // norm  fght  fly   pois  grnd  rock  bug   ghst  fire  wter  grss  elec  psyc  ice   drag
    /* norm */ {norm, str , norm, norm, norm, norm, norm, inef, norm, norm, norm, norm, norm, norm, norm},
    /* fght */ {norm, norm, str , norm, norm, norm, norm, inef, norm, norm, norm, norm, norm, norm, norm},
    /* fly  */ {norm, weak, norm, norm, inef, str , weak, norm, norm, norm, weak, str , norm, str , norm},
    /* pois */ {norm, weak, norm, weak, str , norm, str , norm, norm, norm, weak, norm, str , norm, norm},
    /* grnd */ {norm, norm, norm, weak, norm, weak, norm, norm, norm, str , str , inef, norm, str , norm},
    /* rock */ {weak, str , weak, weak, str , norm, norm, norm, norm, str , str , norm, norm, norm, norm},
    /* bug  */ {norm, weak, str , norm, weak, str , norm, norm, str , norm, weak, norm, norm, norm, norm},
    /* ghst */ {inef, inef, norm, weak, norm, norm, weak, norm, norm, norm, norm, norm, norm, norm, norm},
    /* fire */ {norm, norm, norm, norm, str , str , weak, norm, weak, str , weak, norm, norm, norm, norm},
    /* wter */ {norm, norm, norm, norm, norm, norm, norm, norm, weak, weak, str , str , norm, weak, norm},
    /* grss */ {norm, norm, str , str , weak, norm, str , norm, str , weak, weak, weak, norm, str , norm},
    /* elec */ {norm, norm, weak, norm, str , norm, norm, norm, norm, norm, norm, weak, norm, norm, norm},
    /* psyc */ {norm, weak, norm, norm, norm, norm, str , inef, norm, norm, norm, norm, weak, norm, norm},
    /* ice  */ {norm, str , norm, norm, norm, str , norm, norm, str , norm, norm, norm, norm, weak, norm},
    /* drag */ {norm, norm, norm, norm, norm, norm, norm, norm, weak, weak, weak, weak, norm, str , str },
             // norm  fght  fly   pois  grnd  rock  bug   ghst  fire  wter  grss  elec  psyc  ice   drag
    };

    public static EffectLevel getTypeAdvantage(type defenseType, type attackType) {
        return genII_V[defenseType.ordinal()][attackType.ordinal()];
    }
}
