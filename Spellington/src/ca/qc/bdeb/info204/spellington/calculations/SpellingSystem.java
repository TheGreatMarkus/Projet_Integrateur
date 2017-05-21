package ca.qc.bdeb.info204.spellington.calculations;

import ca.qc.bdeb.info204.spellington.GameCore;
import ca.qc.bdeb.info204.spellington.gameentities.GameAnimation;
import ca.qc.bdeb.info204.spellington.spell.ProjectileSpell;
import ca.qc.bdeb.info204.spellington.spell.Spell;
import ca.qc.bdeb.info204.spellington.gameentities.Projectile;
import ca.qc.bdeb.info204.spellington.gameentities.Spellington;
import ca.qc.bdeb.info204.spellington.gameentities.GameEntity.ElementalType;
import ca.qc.bdeb.info204.spellington.gameentities.enemies.Enemy;
import ca.qc.bdeb.info204.spellington.spell.BurstSpell;
import ca.qc.bdeb.info204.spellington.spell.ExplosionSpell;
import ca.qc.bdeb.info204.spellington.spell.HealingSpell;
import ca.qc.bdeb.info204.spellington.spell.PassiveSpell;
import ca.qc.bdeb.info204.spellington.spell.Potion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Class that manages the spell incantations and spell activation for the game.
 *
 * @author Celtis
 */
public class SpellingSystem {

    private static Spell passiveSpell;
    private static Spell activeSpell;
    private static Spell pastSpell;
    private static int nbSpellUses = 0;

    private static int nbPotionAcid = 5;
    private static int nbPotionHeal = 5;
    private static int nbPotionTime = 5;
    private static int nbPotionPast = 5;

    private static String incantationText = "";

    public static ArrayList<Spell> potionList = new ArrayList<>();
    public static ArrayList<Spell> knownSpells = new ArrayList<>();
    private static ArrayList<Spell> allSpells = new ArrayList<>();
    private static ArrayList<Spell> noviceSpells = new ArrayList<>();
    private static ArrayList<Spell> adeptSpells = new ArrayList<>();
    private static ArrayList<Spell> masterSpells = new ArrayList<>();

    private static Animation animFireBall;
    private static Animation animIceSpike;
    private static Animation animSpark;
    private static Animation animHeal;
    private static Animation animAscendingCurrent;
    private static Animation animFireResistance;
    private static Animation animIceResistance;
    private static Animation animLightningResistance;
    private static Animation animExplosiveBall;
    private static Animation animFireBreath;
    private static Animation animGiantFireBall;
    private static Animation animLightningSwarm;
    private static Animation animTeleportation;
    private static Animation animLightningBouncingBall;
    private static Animation animIceBreath;
    private static Animation animIceSpikeBall;
    private static Animation animIceRune;
    private static Animation animFireImmunity;
    private static Animation animMeteorSwarm;
    private static Animation animLightningImmunity;
    private static Animation animLightningSpear;
    private static Animation animIceStorm;
    private static Animation animIceImmunity;
    private static Animation animGreatHeal;
    private static Animation animAcid;
    private static Animation animTemps;
    private static Animation animPast;

    public static final int ID_FIRE_BALL = 1;
    public static final int ID_ICE_SPIKE = 2;
    public static final int ID_SPARK = 3;
    public static final int ID_HEAL = 4;
    public static final int ID_ASCENDING_CURRENT = 5;
    public static final int ID_FIRE_RES = 6;
    public static final int ID_ICE_RES = 7;
    public static final int ID_LIGHTNING_RES = 8;
    public static final int ID_EXPLOSIVE_BALL = 9;
    public static final int ID_FIRE_BREATH = 10;
    public static final int ID_GIANT_FIRE_BALL = 11;
    public static final int ID_LIGHTNING_SWARM = 12;
    public static final int ID_TELEPORTATION = 13;
    public static final int ID_LIGHTNING_BALL = 14;
    public static final int ID_ICE_BREATH = 15;
    public static final int ID_ICE_SPIKE_BALL = 16;
    public static final int ID_ICE_RUNE = 17;
    public static final int ID_FIRE_IMMUNITY = 18;
    public static final int ID_METEOR_SWARM = 19;
    public static final int ID_LIGHTNING_IMMUNITY = 20;
    public static final int ID_LIGHTNING_SPEAR = 21;
    public static final int ID_ICE_STORM = 22;
    public static final int ID_ICE_IMMUNITY = 23;
    public static final int ID_GREAT_HEAL = 24;
    public static final int ID_POTION_ACID = 25;
    public static final int ID_POTION_HEAL = 26;
    public static final int ID_POTION_TIME = 27;
    public static final int ID_POTION_PAST = 28;

    //À changer Tarik
    private static final String FIRE_BALL_DESC = "Boule en feu traditionnelle " + '\n' + "déployée à l'aide d'une trajectoire parabolique.";
    private static final String ICE_SPIKE_DESC = "Pic de glace suivant une " + '\n' + "trajectoire majoritairement linéaire.";
    private static final String SPARK_DESC = "Explosion d'étincelles " + '\n' + "engendrée par le clic de la souris.";
    private static final String HEAL_DESC = "Guérison de base.";
    private static final String ASCENDING_CURRENT_DESC = "Accorde un saut " + '\n' + "supplémentaire à Spellington.";
    private static final String FIRE_RES_DESC = "Réduit les dégats infligés " + '\n' + "par les attaques pyromanes.";
    private static final String ICE_RES_DESC = "Réduit les dégats infligés " + '\n' + "par les attaques glaciales.";
    private static final String LIGHTNING_RES_DESC = "Réduit les dégats infligés " + '\n' + "par les attaques électrocutantes.";
    private static final String EXPLOSIVE_BALL_DESC = "";
    private static final String FIRE_BREATH_DESC = "";
    private static final String GIANT_FIRE_BALL_DESC = "";
    private static final String LIGHTNING_SWARM_DESC = "";
    private static final String TELEPORTATION_DESC = "";
    private static final String LIGHTNING_BALL_DESC = "";
    private static final String ICE_BREATH_DESC = "";
    private static final String ICE_SPIKE_BALL_DESC = "";
    private static final String ICE_RUNE_DESC = "";
    private static final String FIRE_IMMUNITY_DESC = "";
    private static final String METEOR_SWARM_DESC = "";
    private static final String LIGHTNING_IMMUNITY_DESC = "";
    private static final String LIGHTNING_SPEAR_DESC = "";
    private static final String ICE_STORM_DESC = "";
    private static final String ICE_IMMUNITY_DESC = "";
    private static final String GREAT_HEAL_DESC = "";
    private static final String POTION_ACID_DESC = "";
    private static final String POTION_HEAL_DESC = "";
    private static final String POTION_TIME_DESC = "";
    private static final String POTION_PAST_DESC = "";

    static boolean cheatMode = true;

    /**
     * Initiates the necessary components for the SpellingSystem.
     *
     * @author Celtis
     */
    public static void initSpellingSystem() {
        initAnimation();

        Spell fireBall = new ProjectileSpell(ID_FIRE_BALL, ElementalType.FIRE, "Boule de feu", FIRE_BALL_DESC, 5, animFireBall, 30, 1, 1, 5);
        Spell iceSpike = new ProjectileSpell(ID_ICE_SPIKE, ElementalType.ICE, "Pic de glace", ICE_SPIKE_DESC, 3, animIceSpike, 20, 1, 0, 5);
        Spell spark = new ExplosionSpell(ID_SPARK, ElementalType.LIGHTNING, "Etincelle", SPARK_DESC, 2, animSpark, 5, 10);
        Spell heal = new HealingSpell(ID_HEAL, "Soin", HEAL_DESC, 1, animHeal, 100, 100, 10);
        Spell ascendingCurrent = new PassiveSpell(ID_ASCENDING_CURRENT, ElementalType.NEUTRAL, "Courant ascendant", ASCENDING_CURRENT_DESC, animAscendingCurrent, 100, 100, 0);
        Spell fireResistance = new PassiveSpell(ID_FIRE_RES, ElementalType.FIRE, "Résistance feu", FIRE_RES_DESC, animFireResistance, 100, 30, -40);
        Spell iceResistance = new PassiveSpell(ID_ICE_RES, ElementalType.ICE, "Résistance glace", ICE_RES_DESC, animIceResistance, 100, 30, -40);
        Spell lightningResistance = new PassiveSpell(ID_LIGHTNING_RES, ElementalType.LIGHTNING, "Résistance electrique", LIGHTNING_RES_DESC, animLightningResistance, 100, 30, -40);
        Spell explosiveBall = new ProjectileSpell(ID_EXPLOSIVE_BALL, ElementalType.FIRE, "Boule explosive", EXPLOSIVE_BALL_DESC, 2, animExplosiveBall, 100, 1, 1, 10);
        Spell fireBreath = new BurstSpell(ID_FIRE_BREATH, ElementalType.FIRE, "Soufle de feu", FIRE_BREATH_DESC, 3, animFireBreath, 40, 1, 1, 10, 0.15f, 5);
        Spell giantFireBall = new ProjectileSpell(ID_GIANT_FIRE_BALL, ElementalType.FIRE, "Grosse boule de feu", GIANT_FIRE_BALL_DESC, 2, animGiantFireBall, 200, 1, 1, 20);
        Spell lightningSwarm = new ExplosionSpell(ID_LIGHTNING_SWARM, ElementalType.LIGHTNING, "Essain d'eclairs", LIGHTNING_SWARM_DESC, 1, animLightningSwarm, 3, 100);
        Spell teleportation = new ProjectileSpell(ID_TELEPORTATION, ElementalType.NEUTRAL, "Teleportation", TELEPORTATION_DESC, 1, animTeleportation, 100, 1, 1, 0);

        Spell lightningBouncingBall = new ProjectileSpell(ID_LIGHTNING_BALL, ElementalType.LIGHTNING, "Boule electrique rebondissante", LIGHTNING_BALL_DESC, 2, animLightningBouncingBall, 30, 1, 1, 10);
        Spell iceBreath = new BurstSpell(ID_ICE_BREATH, ElementalType.ICE, "Souffle de glace", ICE_BREATH_DESC, 2, animIceBreath, 100, 1, 0, 1, 0.35f, 15);
        Spell iceSpikeBall = new ProjectileSpell(ID_ICE_SPIKE_BALL, ElementalType.ICE, "Boule a pointes de glace", ICE_SPIKE_BALL_DESC, 2, animIceSpikeBall, 50, 1, 1, 10);
        Spell iceRune = new ExplosionSpell(ID_ICE_RUNE, ElementalType.ICE, "Rune de glace", ICE_RUNE_DESC, 1, animIceRune, 20, 10);

        Spell fireImmunity = new PassiveSpell(ID_FIRE_IMMUNITY, ElementalType.FIRE, "Immunite feu", FIRE_IMMUNITY_DESC, animFireImmunity, 100, 100, 0);
        Spell meteorSwarm = new ExplosionSpell(ID_METEOR_SWARM, ElementalType.FIRE, "Pluie de meteors", METEOR_SWARM_DESC, 1, animMeteorSwarm, 20, 9999);
        Spell lightningImmunity = new PassiveSpell(ID_LIGHTNING_IMMUNITY, ElementalType.LIGHTNING, "Immunite électrique", LIGHTNING_IMMUNITY_DESC, animLightningImmunity, 100, 100, 0);

        Spell lightningSpear = new ProjectileSpell(ID_LIGHTNING_SPEAR, ElementalType.LIGHTNING, "Lance de foudre", LIGHTNING_SPEAR_DESC, 1, animLightningSpear, 50, 1, 0, 60);
        Spell iceStorm = new ExplosionSpell(ID_ICE_STORM, ElementalType.ICE, "Tempete de glace", ICE_STORM_DESC, 1, animIceStorm, 20, 9999);

        Spell iceImmunity = new PassiveSpell(ID_ICE_IMMUNITY, ElementalType.ICE, "Immunite glace", ICE_IMMUNITY_DESC, animIceImmunity, 100, 100, 0);
        Spell greatHeal = new HealingSpell(ID_GREAT_HEAL, "Soin majeur", GREAT_HEAL_DESC, 1, animGreatHeal, 100, 200, 999);

        Spell PotionAcid = new ProjectileSpell(ID_POTION_ACID, ElementalType.NEUTRAL, "Potion d'acide", POTION_ACID_DESC, 1, animAcid, 100, 1, 1, 20);
        Spell PotionHeal = new HealingSpell(ID_POTION_HEAL, "Potion Soin", POTION_HEAL_DESC, 1, animHeal, 100, 100, 20);
        Spell PotionTime = new Potion(ID_POTION_TIME, "Potion de ralentissement du temps", POTION_TIME_DESC, 1, animTemps, 100, 100);
        Spell PotionPast = new Potion(ID_POTION_PAST, "Potion du Passé", POTION_PAST_DESC, 1, animPast, 100, 100);

        potionList.add(PotionAcid);
        potionList.add(PotionHeal);
        potionList.add(PotionTime);
        potionList.add(PotionPast);

        allSpells.add(fireBall);
        allSpells.add(iceSpike);
        allSpells.add(spark);
        allSpells.add(heal);
        allSpells.add(ascendingCurrent);
        allSpells.add(fireResistance);
        allSpells.add(iceResistance);
        allSpells.add(lightningResistance);
        allSpells.add(explosiveBall);
        allSpells.add(fireBreath);
        allSpells.add(giantFireBall);
        allSpells.add(lightningSwarm);
        allSpells.add(teleportation);
        allSpells.add(lightningBouncingBall);
        allSpells.add(iceBreath);
        allSpells.add(iceSpikeBall);
        allSpells.add(iceRune);
        allSpells.add(fireImmunity);
        allSpells.add(meteorSwarm);
        allSpells.add(lightningImmunity);
        allSpells.add(lightningSpear);
        allSpells.add(iceStorm);
        allSpells.add(iceImmunity);
        allSpells.add(greatHeal);

        noviceSpells.add(fireBall);
        noviceSpells.add(iceSpike);
        noviceSpells.add(spark);
        noviceSpells.add(heal);
        noviceSpells.add(ascendingCurrent);
        noviceSpells.add(fireResistance);
        noviceSpells.add(iceResistance);
        noviceSpells.add(lightningResistance);

        adeptSpells.add(explosiveBall);
        adeptSpells.add(fireBreath);
        adeptSpells.add(giantFireBall);
        adeptSpells.add(lightningSwarm);
        adeptSpells.add(teleportation);
        adeptSpells.add(lightningBouncingBall);
        adeptSpells.add(iceBreath);
        adeptSpells.add(iceSpikeBall);
        adeptSpells.add(iceRune);

        masterSpells.add(fireImmunity);
        masterSpells.add(meteorSwarm);
        masterSpells.add(lightningImmunity);
        masterSpells.add(lightningSpear);
        masterSpells.add(iceStorm);
        masterSpells.add(iceImmunity);
        masterSpells.add(greatHeal);

        knownSpells.add(fireBall);
        knownSpells.add(iceSpike);
        knownSpells.add(spark);
        knownSpells.add(heal);
        knownSpells.add(ascendingCurrent);
        knownSpells.add(fireResistance);
        knownSpells.add(iceResistance);
        knownSpells.add(lightningResistance);

        //-- test, ajout de sorts à la liste knownSpells pour les tester -----
        knownSpells.add(greatHeal);
        knownSpells.add(fireBreath);
        knownSpells.add(fireImmunity);
        knownSpells.add(iceBreath);
        knownSpells.add(iceSpikeBall);
        knownSpells.add(lightningSpear);
        knownSpells.add(lightningSwarm);

        try {
            initSpellsIncantations();
        } catch (IOException ioe) {
        }
    }

    public static void newKnownSpell(Spell newSpell) {
        knownSpells.add(newSpell);
    }

    public static void update(Input input, Spellington spellington, ArrayList<Projectile> activeProjectiles, ArrayList<GameAnimation> activeAnimations, ArrayList<Enemy> activeEnemy) {
        boolean clickedLeftMouse = false;
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            clickedLeftMouse = true;
        }

        if (clickedLeftMouse) {
            boolean newSpell = false;
            for (int i = 0; i < knownSpells.size(); i++) {
                if (incantationText.equalsIgnoreCase(knownSpells.get(i).getIncantation())) {
                    if (knownSpells.get(i) instanceof PassiveSpell) {
                        if (passiveSpell != null) {
                            passiveSpell.endOfActivation(spellington, activeAnimations);
                        }
                        passiveSpell = knownSpells.get(i);
                        passiveSpell.spellActivation(spellington, input, activeAnimations, activeProjectiles, activeEnemy);
                        newSpell = true;
                    } else {
                        activeSpell = knownSpells.get(i);
                        nbSpellUses = knownSpells.get(i).getUses();
                        newSpell = true;
                    }
                }
            }
            if (!newSpell && activeSpell != null) {
                activeSpell.spellActivation(spellington, input, activeAnimations, activeProjectiles, activeEnemy);
                //pour tester
                if (!cheatMode) {
                    nbSpellUses--;
                }
            }
            if (nbSpellUses <= 0) {
                if (activeSpell != null) {
                    activeSpell.endOfActivation(spellington, activeAnimations);
                    pastSpell = activeSpell;
                }
                activeSpell = null;
                nbSpellUses = 0;
            }
            incantationText = "";
        }

        //potions start-----------
        if (input.isKeyPressed(Input.KEY_1)) {
            if (nbPotionAcid > 0) {
                potionList.get(0).spellActivation(spellington, input, activeAnimations, activeProjectiles, activeEnemy);
                nbPotionAcid--;
            }
        }

        if (input.isKeyPressed(Input.KEY_2)) {
            if (nbPotionHeal > 0) {
                potionList.get(1).spellActivation(spellington, input, activeAnimations, activeProjectiles, activeEnemy);
                nbPotionHeal--;
            }
        }

        if (input.isKeyPressed(Input.KEY_3)) {
            if (nbPotionTime > 0) {
                potionList.get(2).spellActivation(spellington, input, activeAnimations, activeProjectiles, activeEnemy);
                nbPotionTime--;
            }
        }

        if (input.isKeyPressed(Input.KEY_4)) {
            if (nbPotionPast > 0) {
                potionList.get(3).spellActivation(spellington, input, activeAnimations, activeProjectiles, activeEnemy);
                nbPotionPast--;
            }
        }

        //potions end-----------
        //test start........................................................
        if (input.isKeyPressed(Input.KEY_EQUALS)) {
            incantationText = allSpells.get(ID_FIRE_BREATH - 1).getIncantation();
        }

        if (input.isKeyPressed(Input.KEY_F2)) {
            incantationText = allSpells.get(ID_HEAL - 1).getIncantation();
        }

        if (input.isKeyPressed(Input.KEY_F9)) {
            incantationText = allSpells.get(ID_ASCENDING_CURRENT - 1).getIncantation();
        }

        if (input.isKeyPressed(Input.KEY_F8)) {
            incantationText = allSpells.get(ID_FIRE_RES - 1).getIncantation();
        }

        if (input.isKeyPressed(Input.KEY_F7)) {
            incantationText = allSpells.get(ID_LIGHTNING_SWARM - 1).getIncantation();
        }

        if (input.isKeyPressed(Input.KEY_F6)) {
            incantationText = allSpells.get(ID_FIRE_IMMUNITY - 1).getIncantation();
        }

        if (input.isKeyPressed(Input.KEY_F1)) {
            spellington.subLifePoint(30, ElementalType.FIRE);
        }
        //test fin..........................................................
    }

    private static void initAnimation() {
        try {
            Image[] tempImgFireBall = new Image[31];
            for (int i = 0; i < tempImgFireBall.length; i++) {
                tempImgFireBall[i] = new Image("res/image/animation/spells/fireBall/(" + (i + 1) + ").png");
            }
            animFireBall = new Animation(tempImgFireBall, 30);

            Image[] tempImgUpStream = new Image[19];
            for (int i = 18; i >= 0; i--) {
                tempImgUpStream[18 - i] = new Image("res/image/animation/spells/upStream/(" + (i + 1) + ").png");
            }
            animAscendingCurrent = new Animation(tempImgUpStream, 40);

            Image[] tempImgFireRes = new Image[19];
            for (int i = 0; i < tempImgFireRes.length; i++) {
                tempImgFireRes[i] = new Image("res/image/animation/spells/fireRes/(" + (i + 1) + ").png");
            }
            animFireResistance = new Animation(tempImgFireRes, 30);

            Image[] tempImgIceRes = new Image[19];
            for (int i = 0; i < tempImgIceRes.length; i++) {
                tempImgIceRes[i] = new Image("res/image/animation/spells/iceRes/(" + (i + 1) + ").png");
            }
            animIceResistance = new Animation(tempImgIceRes, 30);

            Image[] tempImgeElectricRes = new Image[19];
            for (int i = 0; i < tempImgeElectricRes.length; i++) {
                tempImgeElectricRes[i] = new Image("res/image/animation/spells/electricRes/(" + (i + 1) + ").png");
            }
            animLightningResistance = new Animation(tempImgeElectricRes, 30);

            Image[] tempImgeElectricBolt = new Image[19];
            for (int i = 0; i < tempImgeElectricBolt.length; i++) {
                tempImgeElectricBolt[i] = new Image("res/image/animation/spells/lightningBolt/(" + (i + 1) + ").png");
            }
            animLightningBouncingBall = new Animation(tempImgeElectricBolt, 15);

            Image[] tempImgeHealSmall = new Image[19];
            for (int i = 0; i < tempImgeHealSmall.length; i++) {
                tempImgeHealSmall[i] = new Image("res/image/animation/spells/healSmall/(" + (i + 1) + ").png");
            }
            animHeal = new Animation(tempImgeHealSmall, 30);

            Image[] tempImgeHealBig = new Image[19];
            for (int i = 0; i < tempImgeHealBig.length; i++) {
                tempImgeHealBig[i] = new Image("res/image/animation/spells/healBig/(" + (i + 1) + ").png");
            }
            animGreatHeal = new Animation(tempImgeHealBig, 30);

            Image[] tempImgeFireBreath = new Image[30];
            for (int i = 0; i < tempImgeFireBreath.length; i++) {

                tempImgeFireBreath[i] = new Image("res/image/animation/spells/fireBall/(" + (i + 1) + ").png");//animation temporaire

            }
            animFireBreath = new Animation(tempImgeFireBreath, 30);

            Image[] tempImgeIceBreath = new Image[19];
            for (int i = 0; i < tempImgeIceBreath.length; i++) {

                tempImgeIceBreath[i] = new Image("res/image/animation/spells/iceSpike/(" + (i + 1) + ").png");

            }
            animIceBreath = new Animation(tempImgeIceBreath, 30);

            Image[] tempImgFireImmu = new Image[19];
            for (int i = 0; i < tempImgFireImmu.length; i++) {
                tempImgFireImmu[i] = new Image("res/image/animation/spells/fireImmu/(" + (i + 1) + ").png");
            }
            animFireImmunity = new Animation(tempImgFireImmu, 30);

            Image[] tempImgIceImmu = new Image[19];
            for (int i = 0; i < tempImgIceImmu.length; i++) {
                tempImgIceImmu[i] = new Image("res/image/animation/spells/iceImmu/(" + (i + 1) + ").png");
            }
            animIceImmunity = new Animation(tempImgIceImmu, 30);

            Image[] tempImgeElectricImmu = new Image[19];
            for (int i = 0; i < tempImgeElectricImmu.length; i++) {
                tempImgeElectricImmu[i] = new Image("res/image/animation/spells/electricImmu/(" + (i + 1) + ").png");
            }
            animLightningImmunity = new Animation(tempImgeElectricImmu, 30);

            Image[] tempImgeElectricSpark = new Image[19];
            for (int i = 0; i < tempImgeElectricSpark.length; i++) {
                tempImgeElectricSpark[i] = new Image("res/image/animation/spells/lightningSpark/(" + (i + 1) + ").png");
            }
            animSpark = new Animation(tempImgeElectricSpark, 30);

            Image[] tempImgeElectricSwarm = new Image[19];
            for (int i = 0; i < tempImgeElectricSwarm.length; i++) {
                tempImgeElectricSwarm[i] = new Image("res/image/animation/spells/lightningSwarm/(" + (i + 1) + ").png");
            }
            animLightningSwarm = new Animation(tempImgeElectricSwarm, 30);

            Image[] tempImgeElectricSpear = new Image[19];
            for (int i = 0; i < tempImgeElectricSpear.length; i++) {
                tempImgeElectricSpear[i] = new Image("res/image/animation/spells/lightningSpear/(" + (i + 1) + ").png");
            }
            animLightningSpear = new Animation(tempImgeElectricSpear, 30);

            Image[] tempImgeIceSpikeBall = new Image[19];
            for (int i = 0; i < tempImgeIceSpikeBall.length; i++) {
                tempImgeIceSpikeBall[i] = new Image("res/image/animation/spells/iceSpikeBall/(" + (i + 1) + ").png");
            }
            animIceSpikeBall = new Animation(tempImgeIceSpikeBall, 30);

            Image[] tempImgeExplosiveBall = new Image[20];
            for (int i = 0; i < tempImgeExplosiveBall.length; i++) {
                tempImgeExplosiveBall[i] = new Image("res/image/animation/spells/explosiveBall/(" + (i + 1) + ").png");
            }
            animExplosiveBall = new Animation(tempImgeExplosiveBall, 30);

            Image[] tempImgeIceSpike = new Image[19];
            for (int i = 0; i < tempImgeIceSpike.length; i++) {
                tempImgeIceSpike[i] = new Image("res/image/animation/spells/iceSpike/(" + (i + 1) + ").png");
            }
            animIceSpike = new Animation(tempImgeIceSpike, 30);

            Image[] tempImgeIceStorm = new Image[40];
            for (int i = 0; i < tempImgeIceStorm.length; i++) {
                tempImgeIceStorm[i] = new Image("res/image/animation/spells/iceStorm/(" + (i + 1) + ").png");
            }
            animIceStorm = new Animation(tempImgeIceStorm, 30);

            Image[] tempImgeMeteorSwarm = new Image[38];
            for (int i = 0; i < tempImgeMeteorSwarm.length; i++) {
                tempImgeMeteorSwarm[i] = new Image("res/image/animation/spells/meteorSwarm/(" + (i + 1) + ").png");
            }
            animMeteorSwarm = new Animation(tempImgeMeteorSwarm, 30);

            Image[] tempImgeteleportBall = new Image[1];
            for (int i = 0; i < tempImgeteleportBall.length; i++) {
                tempImgeteleportBall[i] = new Image("res/image/animation/spells/teleportBall.png");
            }
            animTeleportation = new Animation(tempImgeteleportBall, 30);

        } catch (SlickException ex) {
        }
    }

    private static void initSpellsIncantations() throws IOException {
        ArrayList<String> tempWord = new ArrayList<>();

        BufferedReader readerBuffer;
        String line;
        String filePath = new File("").getAbsolutePath();
        try {
            readerBuffer = new BufferedReader(new FileReader(filePath + "\\src\\res\\wordbank\\noviceWord.txt"));
            while ((line = readerBuffer.readLine()) != null) {
                tempWord.add(line);
            }
            readerBuffer.close();
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }

        for (int i = 0; i < noviceSpells.size(); i++) {
            int tempdice = GameCore.rand.nextInt(tempWord.size());
            noviceSpells.get(i).setIncantation(tempWord.get(tempdice));
            if (tempWord.size() <= 1) {
                System.out.print("Erreur: il n'y a pas assez de mots dans noviceWord.txt");
            }
            tempWord.remove(tempdice);
        }
        tempWord.clear();

        BufferedReader readerBuffer2;
        String line2 = null;
        String filePath2 = new File("").getAbsolutePath();
        try {
            readerBuffer2 = new BufferedReader(new FileReader(filePath2 + "\\src\\res\\wordbank\\adepteWord.txt"));
            while ((line2 = readerBuffer2.readLine()) != null) {
                tempWord.add(line2);
            }
            readerBuffer2.close();
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }

        for (int i = 0; i < adeptSpells.size(); i++) {
            int tempdice = GameCore.rand.nextInt(tempWord.size());
            adeptSpells.get(i).setIncantation(tempWord.get(tempdice));
            if (tempWord.size() <= 1) {
                System.out.print("Erreur: il n'y a pas assez de mots dans adepteWord.txt");
            }
            tempWord.remove(tempdice);
        }
        tempWord.clear();

        BufferedReader readerBuffer3 = null;
        String line3 = null;
        String filePath3 = new File("").getAbsolutePath();
        try {
            readerBuffer3 = new BufferedReader(new FileReader(filePath3 + "\\src\\res\\wordbank\\masterWord.txt"));
            while ((line3 = readerBuffer3.readLine()) != null) {
                tempWord.add(line3);
            }
            readerBuffer3.close();
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        }

        for (int i = 0; i < masterSpells.size(); i++) {
            int tempdice = GameCore.rand.nextInt(tempWord.size());
            masterSpells.get(i).setIncantation(tempWord.get(tempdice));
            if (tempWord.size() <= 1) {
                System.out.print("Erreur: il n'y a pas assez de mots dans masterWord.txt");
            }
            tempWord.remove(tempdice);
        }
        tempWord.clear();
    }

    public static void pastSpellPotion(Spellington spellington, ArrayList<GameAnimation> activeAnimations) {
        if (pastSpell != null) {
            if (activeSpell != null) {
                activeSpell.endOfActivation(spellington, activeAnimations);
            }
            activeSpell = pastSpell;
            nbSpellUses = pastSpell.getUses();

        }
    }

    public static Spell getActiveSpell() {
        return activeSpell;
    }

    public static Spell getPassiveSpell() {
        return passiveSpell;
    }

    public static String getIncantationText() {
        return incantationText;
    }

    public static void setIncantationText(String incantationText) {
        SpellingSystem.incantationText = incantationText;
    }

    public static Animation getAnimFireBall() {
        return animFireBall;
    }

    public static ArrayList<Spell> getKnownSpells() {
        return knownSpells;
    }

    public static ArrayList<Spell> getNoviceSpells() {
        return noviceSpells;
    }

    public static ArrayList<Spell> getAdeptSpells() {
        return adeptSpells;
    }

}
