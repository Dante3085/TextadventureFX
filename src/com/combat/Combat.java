package com.combat;

import com.Main.Main;
import com.lists.List;
import com.character.Character;

import java.util.ArrayList;
import java.util.Comparator;

public class Combat
{
    ArrayList<Character> party1 = new ArrayList<Character>();
    ArrayList<Character> party2 = new ArrayList<Character>();

    public Combat(ArrayList<Character> party1, ArrayList<Character> party2)
    {
        this.party1 = party1;
        this.party2 = party2;
    }

    public void startCombat()
    {
        while (partyHealth(party1) > 0 && partyHealth(party2) > 0)
        {

        }
        Main.console.writeToConsole("Combat finished");
    }

    public ArrayList<Character> turnOrder()
    {
        ArrayList<Character> turnOrder = new ArrayList<Character>();
        turnOrder.addAll(party1);
        turnOrder.addAll(party2);

        turnOrder.sort(new Comparator<Character>()
        {
            @Override
            public int compare(Character o1, Character o2)
            {
                if (o1.getSpeed() < o2.getSpeed())
                    return 1;
                if (o1.getSpeed() > o2.getSpeed())
                    return -1;
                return 0;
            }
        });
        return turnOrder;
    }

    public int partyHealth(ArrayList<Character> party)
    {
        int health = 0;
        for (Character c : party)
            health += c.getHp();
        return health;
    }
}
