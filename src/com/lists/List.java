package com.lists;

public class List<T> implements IList<T>, Iterable<T>
{
    private String name;
    private Link<T> start;
    private Link<T> end;

    public List(String name)
    {
        this.name = name;
        start = end = null;
    }

    public List(String name, Link<T> start, Link<T> end)
    {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public int size()
    {
        Link<T> l = start;
        int size = 0;
        while (l != null)
        {
            size++;
            l = l.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        if (start == null)
            return true;
        return false;
    }

    public void addStart(T data)
    {
        if (start == null)
        {
            start = end = new Link<T>(data, null);
            return;
        }
        start = new Link<T>(data, start);
    }

    @Override
    public void add(T data)
    {
        if (start == null)
        {
            start = end = new Link<T>(data, null);
            return;
        }
        end.next = new Link<T>(data, null);
        end = end.next;
    }

    @Override
    public void insert(int index, T data)
    {
        // Negative Indizes gibt es natürlich nicht. Bis auf den Start kann immer nur eingefügt werden, wenn es einen direkten Vorgänger gibt.
        // Ich kann zum Beispiel nicht am Index 3 einfügen, wenn es nur die Indizes 0 und 1 gibt. Ich müsste sonst am Index 2 eine Lücke mit einem zufälligen Wert füllen.
        if (index < 0 || index > size())
        {
            System.out.println("@insert(" + index + ", " + data.toString() + "): " + "The index " + index + " doesn't exist.");
            return;
        }

        // Bei index = 0 ist es so, als ob man einen neuen Start hinzufügt.
        if (index == 0)
        {
            addStart(data);
            return;
        }

        Link<T> runner = start;
        int c = 0;
        while (runner != null)
        {
            if (c == index - 1)
            {
                Link<T> n = new Link<T>(data, runner.next);
                runner.next = n;
                if (n.next == null)
                    end = n;
                return;
            }
            runner = runner.next;
            c++;
        }
    }

    @Override
    public void remove(int index)
    {
        if (index < 0 || index >= size())
        {
            System.out.println("@remove(" + index + "): " + "The index " + index + " doesn't exist.");
            return;
        }

        // Start braucht eine Sonderbehandlung
        if (index == 0)
        {
            start = start.next;
            return;
        }

        Link<T> runner = start;
        int c = 0;
        while (runner != null)
        {
            if (c == index - 1)
            {
                if (runner.next == end)
                    end = runner;
                runner.next = runner.next.next; // Zu löschendes Element wird jetzt übergangen.
                return;
            }
            runner = runner.next;
            c++;
        }
    }

    @Override
    public void remove(T data)
    {
        if (contains(data) == false)
        {
            System.out.println("@remove(" + data.toString() + "): The com.lists.List doesn't contain '" + data.toString() + "'");
            return;
        }

        if (start.data == data)
        {
            start = start.next;
            return;
        }

        Link<T> runner = start;
        while (runner != null)
        {
            if (runner.next.data == data)
            {
                runner.next = runner.next.next;
                return;
            }
            runner = runner.next;
        }
    }

    /**
     * Removes all occurrences of data.
     * @param data
     */
    public void removeAll(T data)
    {
        while (contains(data) == true)
            remove(data);
    }

    public void flush()
    {
        start = end = null;
    }

    @Override
    public T get(int index)
    {
        if (index < 0 || index >= size())
        {
            System.out.println("@get(" + index + "): Der index '" + index + "' existiert nicht.");
            return  null;
        }

        if (index == 0)
            return start.data;

        Link<T> runner = start;
        int c = 0;
        while (runner != null)
        {
            runner = runner.next; // Ein Element weitersetzen.
            c++;
            if (c == index)
                return runner.data;
        }
        return null;
    }

    @Override
    public boolean contains(T data)
    {
        Link<T> runner = start;
        while (runner != null)
        {
            if (runner.data.equals(data))
                return true;
            runner = runner.next;
        }
        return false;
    }

    /**
     * Splits the list
     * This list contains the first 'n' elements, the returned (new) list the remaining elements.
     *
     * @param n number of elements to retain in this list
     * @return new list with remaining size() - n elements
     */
    @Override
    public List<T> split(int n)
    {
        List<T> remaining = new List<T>("remaining"); // Liste, die alle Elmente von n+1 bis ende annimmt.
        // Wenn n = 0 ist bzw. die "this" Liste keine Elemente behalten soll, kriegt die remaining Liste alle Elemente der this Liste. Dann wird die this Liste mit flush geleert und die remaining Liste zurückgegeben.
        if (n == 0)
        {
            Iterator<T> it = iterator();
            while (it.hasNext())
                remaining.add(it.next());
            flush();
            return remaining;
        }


        // Füge übrige Werte der remaining Liste hinzu.
        Link<T> runner = start;
        int c = 0;

        List<Integer> victims = new List<Integer>("victims");
        while (runner != null)
        {
            c++;
            if (c > n)
            {
                remaining.add(runner.data);
                victims.add(c - 1); // Fülle victims Liste mit Werten, die in remaining gepackt wurden und aus this raus müssen.
            }
            runner = runner.next;
        }

        // Lösche Werte aus this-Liste mithilfe der victims Liste.
        for (int i = victims.size() - 1; i >= 0 ; i--)
            remove(victims.get(i));

        return remaining;
    }

    public void chain(List<T> secondList)
    {
        this.end.next = secondList.start;
        this.end = secondList.end;
    }

    public void printList()
    {
        if (start == null)
        {
            System.out.println("size(" + name + "): " + size() + "\nstart -> null");
            return;
        }

        Link<T> runner = start;
        int c = 0;

        System.out.println("size(" + name + "): " + size());
        while (runner != null)
        {
            System.out.println(name + "[" + c++ + "]: " + runner.data.toString());
            runner = runner.next;
        }
    }

    public void printListSymbolically()
    {
        if (start == null)
        {
            System.out.println("size(" + name + "): " + size() + "\nstart -> null");
            return;
        }
        Link<T> runner = start;

        System.out.println("size(" + name + "): " + size());
        while (runner.next != null)
        {
            System.out.print(runner.data.toString() + " -> ");
            runner = runner.next;
        }
        System.out.println(runner.data.toString());
    }

    public Iterator<T> iterator()
    {
        return new Iterator<T>(start, end);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Link<T> getStart()
    {
        return start;
    }

    public Link<T> getEnd()
    {
        return end;
    }
}
