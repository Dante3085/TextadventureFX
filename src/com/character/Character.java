package com.character;

import com.exceptions.ItemNotRegisteredException;
import com.exceptions.MagicNotRegisteredException;
import com.exceptions.RevengeMoveNotRegisteredException;
import com.exceptions.SkillNotRegisteredException;

import java.util.HashMap;

/**
 * The Character class represents playable Characters in the Textadventure.
 * A Character has certain Attributes that describe his role in Combat or other aspects of the game.
 * A Character is able to perform 6 different types of actions in Combat.
 *  - Use a skill
 *  - Just attack
 *  - Defend
 *  - Use Magic
 *  - Use Item
 *  - End Turn
 *  @author mjsch
 */
public class Character
{
    private String name;
    private int hp;
    private int mp;
    private int strength;
    private int defence;
    private int wit;
    private int agility;
    private int speed;

    private int revengeValue = 0;

    private int DMG;      // Math.random() * DMGRANGE + DMGBASE.
    private int DMGRANGE; // UNDEFINED
    private int DMGBASE;  // Attacker's strength (or whatever is adequate to that) - Defender's defense (or whatever is adequate to that).

    private HashMap<Item, ICallback> items = new HashMap<Item, ICallback>();
    private HashMap<Magic, ICallback> magics = new HashMap<Magic, ICallback>();
    private HashMap<Skill, ICallback> skills = new HashMap<Skill, ICallback>();
    private HashMap<RevengeMove, ICallback> revengeMoves = new HashMap<RevengeMove, ICallback>();

    /**
     * Default Constructor
     */
    public Character()
    {
        this.name = "NO_NAME";
        this.hp = 0;
        this.mp = 0;
        this.strength = 0;
        this. defence = 0;
        this.wit = 0;
        this.agility = 0;
        this.speed = 0;

        setupSkills();
        setupRevengeMoves();
    }

    /**
     *
     * @param name
     * @param hp
     * @param mp
     * @param strength
     * @param defence
     * @param wit
     * @param agility
     * @param speed
     */
    public Character(String name, int hp, int mp, int strength, int defence, int wit, int agility, int speed)
    {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.strength = strength;
        this. defence = defence;
        this.wit = wit;
        this.agility = agility;
        this.speed = speed;

        setupSkills();
        setupRevengeMoves();
    }

    /**
     * Increases revengeValue by revengeModifier value of a Skill or Magic.
     * @param revengeModifier
     */
    private void updateRevengeValue(int revengeModifier)
    {
        revengeValue += revengeModifier;
    }

    /**
     * Checks if one of several revengeValueThresholds has been reached or surpassed.
     * If that is the case, a corresponding RevengeMove will be executed on either the passed 'target'
     * or a friendly Character. Defensive, healing, ... RevengeMoves will most likely be executed on
     * friendly Characters.
     * @param target
     * @return
     */
    private boolean reachedRevengeThreshold(Character target)
    {
        System.out.println("REACHED_REVENGE_THRESHOLD\n\tRevengeValue: " + revengeValue);
        if (revengeValue >= 20)
        {
            action_revengeMove(target, RevengeMove.A);
            revengeValue = 0;
            return true;
        }
        if (revengeValue >= 10)
        {
            action_revengeMove(target, RevengeMove.B);
            revengeValue = 0;
            return true;
        }
        if (revengeValue >= 5)
        {
            action_revengeMove(target, RevengeMove.C);
            revengeValue = 0;
            return true;
        }
        return false;
    }

    /**
     * Calculates a Damage value which ranges from (Attacker's power to inflict damage (atk) - Defender's power to reduce damage (def)) (<- DMGBASE)
     * to (DMGBASE + DMGRANGE)
     * Example: atk = 20, def = 3, DMGRANGE = 10 => DMGBASE = 17 => Possible damage values range from 17 - 27.
     *
     * @param atk Attacker's power to inflict damage.
     * @param def Defender's power to reduce damage.
     * @return
     */
    private int calcDamage(int atk, int def)
    {
        int DMG;      // Math.random() * DMGRANGE + DMGBASE. Actual damage value that is being returned.
        int DMGRANGE = 1; // UNDEFINED, meaning that as of now it is completely mutable. Though it shouldn't stray away too far from DMGBASE, since that is the actual effect of Character attributes. Suggestion: 5 to 15.
        int DMGBASE = atk - def;  // Attacker's strength (or whatever is adequate to that) - Defender's defense (or whatever is adequate to that).

        DMG = (int)(Math.random() * DMGRANGE + DMGBASE);
        System.out.println(DMG);
        return DMG;
    }

    private void registerItem(Item item, ICallback callback)
    {
        items.put(item, callback);
    }
    private void registerMagic(Magic magic, ICallback callback)
    {
        magics.put(magic, callback);
    }
    private void registerSkill(Skill skill, ICallback callback)
    {
        skills.put(skill, callback);
    }
    private void registerRevengeMove(RevengeMove revengeMove, ICallback callback)
    {
        revengeMoves.put(revengeMove, callback);
    }

    private void setupItems()
    {
        registerItem(Item.POTION, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                target.hp += 50; // TODO: target mustn't be healed above it's MAXHP. Maybe attribute MAXHP ?
            }
        });
    }
    private void setupMagics()
    {
        registerMagic(Magic.FIRE, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                target.hp -= 50; // TODO: Damage lasts several turns. Damage dependant on monster type. Damage dependant on magic resistance, maybe fire resistance.
            }
        });
    }
    private void setupSkills()
    {
        registerSkill(Skill.HEAVY_SWING, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                Character.this.strength += 5;
                if (Character.this.strength > target.defence)
                    target.hp -= calcDamage(Character.this.strength, target.defence);
                Character.this.strength -= 5;
            }
        });

        registerSkill(Skill.QUICK_SWING, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                Character.this.strength += 2;
                if (Character.this.strength > target.defence)
                    target.hp -= calcDamage(Character.this.strength, target.defence);
                Character.this.strength -= 2;
            }
        });

        registerSkill(Skill.BLOODLETTER, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                Character.this.strength += 200;
                if (Character.this.strength > target.defence)
                    target.hp -= calcDamage(Character.this.strength, target.defence);
                Character.this.strength -= 200;
                Character.this.hp -= 200;
            }
        });
    }
    private void setupRevengeMoves()
    {
        registerRevengeMove(RevengeMove.A, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                target.strength += 10;
                if (target.strength > Character.this.defence)
                    Character.this.hp -= calcDamage(target.strength, Character.this.defence);
                target.strength -= 10;
            }
        });
    }

    public void action_attack(Character target)
    {
        target.hp -= (this.strength - target.defence);
        target.updateRevengeValue(1);
        target.reachedRevengeThreshold(this);
    }
    public void action_defend()
    {
        // TODO: Subtract defence boost after a certain amount of turns.
        this.defence += 10;
    }
    public void action_skill(Character target, Skill skill)
    {
        if (skills.containsKey(skill))
        {
            skills.get(skill).action(target);
            target.updateRevengeValue(skill.revengeModifier);
            target.reachedRevengeThreshold(this);
            return;
        }
        try
        {
            throw new SkillNotRegisteredException();
        } catch (SkillNotRegisteredException e)
        {
            e.printStackTrace();
        }
    }
    public void action_magic(Character target, Magic magic)
    {
        if (magics.containsKey(magic))
        {
            magics.get(magic).action(target);
            target.updateRevengeValue(magic.revengeModifier);
            target.reachedRevengeThreshold(this);
            return;
        }
        try
        {
            throw new MagicNotRegisteredException();
        } catch (MagicNotRegisteredException e)
        {
            e.printStackTrace();
        }
    }
    public void action_item(Character target, Item item)
    {
        if (items.containsKey(item))
        {
            items.get(item).action(target);
            return;
        }
        try
        {
            throw new ItemNotRegisteredException();
        } catch (ItemNotRegisteredException e)
        {
            e.printStackTrace();
        }
    }
    public void action_endturn()
    {

    }
    private void action_revengeMove(Character target, RevengeMove revengeMove)
    {
        if (revengeMoves.containsKey(revengeMove))
        {
            revengeMoves.get(revengeMove).action(target);
            return;
        }
        try
        {
            throw new RevengeMoveNotRegisteredException();
        } catch (RevengeMoveNotRegisteredException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isAlive()
    {
        return hp > 0;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", HP: " + hp + ", MP: " + mp + ", Strength: " + strength + ", Defence: " + defence + ", Wit: " + wit + ", Agility: " + agility + ", Speed: " + speed;
    }

    public String getName()
    {
        return name;
    }

    public int getHp()
    {
        return hp;
    }

    public int getMp()
    {
        return mp;
    }

    public int getStrength()
    {
        return strength;
    }

    public int getDefence()
    {
        return defence;
    }

    public int getWit()
    {
        return wit;
    }

    public int getAgility()
    {
        return agility;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}