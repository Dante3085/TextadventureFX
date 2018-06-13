package com.job;

public class Job
{
    private enum Reward
    {
        GIL,
        EXP,
        JOBPOINTS,
        ITEM,
        EQUIPMENT,
        SKILL,
        MAGIC;
    }

    String m_description;
    Reward m_reward; // Geld, Ausrüstung, Fähigkeiten, EXP, Jobpunkte, Items
    String m_entryBarrier;
    String m_connectedCharacters;
    String m_completionRequirment;

    public Job(String description, Reward reward)
    {

    }
}
