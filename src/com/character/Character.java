package com.character;

import com.Main.Main;

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

    HashMap<Skill, ICallback> skills = new HashMap<Skill, ICallback>();

    /**
     *
     */
    // Standard Constructor
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

//        try
//        {
//            Main.console.writeToConsole("NEW CHARACTER \n\t" + this.toString());
//        } catch (NullPointerException e)
//        {
//            System.out.println("@com.character.Character Character(): CONSOLE_NOT_INITIALIZED");
//            e.printStackTrace();
//        }
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
    }

    public void registerSkill(Skill skill, ICallback callback)
    {
        skills.put(skill, callback);
    }

    public void setupSkills()
    {
        registerSkill(Skill.HEAVY_SWING, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                if (target.isAlive() == false)
                {
                    Main.console.writeToConsole("isAlive(" + target.name + ") = false");
                    return;
                }

                Main.console.writeToConsole(Character.this.name + "(HEAVY_SWING) -> " + target.name + "\n\t" + target.hp);

                Character.this.strength += 5;
                if (Character.this.strength <= target.defence) {}
                else
                    target.hp -= (Character.this.strength - target.defence);
                Character.this.strength -= 5;

                Main.console.writeToConsole("\t" + target.hp);
            }
        });

        registerSkill(Skill.QUICK_SWING, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                if (target.isAlive() == false)
                {
                    Main.console.writeToConsole("isAlive(" + target.name + ") = false");
                    return;
                }

                Main.console.writeToConsole(Character.this.name + "(QUICK_SWING) -> " + target.name + "\n\t" + target.hp);

                Character.this.strength += 2;
                if (Character.this.strength <= target.defence) {}
                else
                    target.hp -= (Character.this.strength - target.defence);
                Character.this.strength -= 2;

                Main.console.writeToConsole("\t" + target.hp);
            }
        });

        registerSkill(Skill.BLOODLETTER, new ICallback()
        {
            @Override
            public void action(Character target)
            {
                if (target.isAlive() == false)
                {
                    Main.console.writeToConsole("isAlive(" + target.name + ") = false");
                    return;
                }

                Main.console.writeToConsole(Character.this.name + "(BLOODLETTER) -> " + target.name + "\n\t" + target.hp);

                Character.this.strength += 200;
                if (Character.this.strength <= target.defence) {}
                else
                    target.hp -= (Character.this.strength - target.defence);
                Character.this.strength -= 200;
                Character.this.hp -= 200;

                Main.console.writeToConsole("\t" + target.hp);
            }
        });
    }

    public void action_skill(Character target, Skill skill)
    {
        if (skills.containsKey(skill))
            skills.get(skill).action(target);
    }

    public void action_attack(Character target)
    {
        target.hp -= (this.strength - target.defence);
    }

    public void action_defend()
    {
        // TODO: Subtract defence boost after a certain amount of turns.
        this.defence += 10;
    }

    public void action_magic(Character target, Magic magic)
    {
        switch (magic)
        {
            default:
            {

                break;
            }
        }
    }

    public void action_item(Character target, Item item)
    {
        switch (item)
        {
            default:
            {

                break;
            }
        }
    }

    public void action_endturn()
    {

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