package com.combat;

import com.Main.Main;
import com.lists.List;
import com.character.Character;
import com.ui.TurnOrderView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Handles Combat in the Textadventure.
 * @author mjsch
 */
public class Combat
{
    ArrayList<Character> party1 = new ArrayList<Character>();
    ArrayList<Character> party2 = new ArrayList<Character>();
    TurnOrderView turnOrderView = new TurnOrderView();

    public  Combat()
    {
        for (int i = 0; i < 4; i++)
        {
            party1.add(new Character("Party1[" + i + "]", 1000, 100, 20, 5, 9, 3, 2));
            party2.add(new Character("Party2[" + i + "]", 200, 100, 32, 40, 4555, 32, 25));
        }
    }

    public Combat(ArrayList<Character> party1, ArrayList<Character> party2)
    {
        this.party1 = party1;
        this.party2 = party2;
    }

    public void startCombat()
    {
        turnOrderView.init(2000);
        int turn = 1;
        while (/*partyHealth(party1) > 0 && partyHealth(party2) > 0*/turn <= 10)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            turnOrderView.turn();
            Main.console.writeToConsole("TURN: " + turn++);
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

    public  TurnOrderView getTurnOrderView()
    {
        return turnOrderView;
    }
}
