package com.combat;

import com.Main.Main;
import com.character.Character;
import com.ui.combat.turnOrderView.TurnOrderView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Handles Combat in the Textadventure.
 * @author mjsch
 */
public class Combat implements Runnable
{
    private static final Logger m_log = Logger.getLogger(Combat.class.getName());

    ArrayList<Character> party1 = new ArrayList<Character>();
    ArrayList<Character> party2 = new ArrayList<Character>();
    TurnOrderView turnOrderView;

    public  Combat(TurnOrderView turnOrderView)
    {
        this.turnOrderView = turnOrderView;
        for (int i = 0; i < 4; i++)
        {
            party1.add(new Character("Party1[" + i + "]", 1000, 100, 20, 5, 9, 3, 2));
            party2.add(new Character("Party2[" + i + "]", 200, 100, 32, 40, 4555, 32, 25));
        }
        m_log.info("Combat initialisiert.");
    }

    public Combat(ArrayList<Character> party1, ArrayList<Character> party2)
    {
        this.party1 = party1;
        this.party2 = party2;
        m_log.info("Combat initialisiert.");
    }

    @Override
    public void run()
    {
        startCombat();
    }

    public void startCombat()
    {
        m_log.info("Combat started.");
        turnOrderView.init(2000);
        int turn = 1;
        while (/*partyHealth(party1) > 0 && partyHealth(party2) > 0*/true)
        {
            try
            {
                Thread.sleep(1500);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            turnOrderView.turn();
            Main.console.writeToConsole("TURN: " + turn++);
        }
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
