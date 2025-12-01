package org.pm.api;

import org.pm.game.Board;

import java.util.*;
import java.util.function.Consumer;

public class RuleSet implements Iterable<Rule> {
//    Set<Rule<T>> ruleList = new HashSet<>();
    List<Rule> ruleList = new ArrayList<>();

    public void add(Rule boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return ruleList.iterator();
    }

    @Override
    public void forEach(Consumer<? super Rule> action) {
        ruleList.forEach(action);
    }

    @Override
    public Spliterator<Rule> spliterator() {
        return  ruleList.spliterator();
    }

    public List<Rule> getRules() {
        return ruleList;
    }

}
