package it.sevenbits.lexers.sm;

import java.util.HashMap;
import java.util.Map;

class StateMap {
    private final State defaultState = new State("CLONE");


    private final Map<Pair<State, String>, State> states;

    StateMap() {
        states = new HashMap<>();
        //State start = new State("START");
        //TODO fucking state map
        //states.put(new Pair<>(defaultState, "MESSAGE_START"), start);

        State openBlock = new State("OPEN_BLOCK");
        State endBlock = new State("END_BLOCK");
        State spaceUno = new State("SPACE");
        State ignore = new State("IGNORE");
        State stringLiteral = new State("STRING_LITERAL");
        State endStringLiteral = new State("END_STRING_LITERAL");

        states.put(new Pair<>(defaultState, "OPEN_BLOC"), openBlock);
        states.put(new Pair<>(defaultState, "END_BLOCK"), endBlock);
        states.put(new Pair<>(defaultState, "SPACE"), spaceUno);
        states.put(new Pair<>(defaultState, "QUOTE"), stringLiteral);

        states.put(new Pair<>(stringLiteral, "OPEN_BLOC"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "END_BLOCK"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "SPACE"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "CUSTOM"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "QUOTE"), endStringLiteral);

        states.put(new Pair<>(endStringLiteral, "QUOTE"), stringLiteral);
        states.put(new Pair<>(endStringLiteral, "OPEN_BLOC"), openBlock);
        states.put(new Pair<>(endStringLiteral, "END_BLOCK"), endBlock);
        states.put(new Pair<>(endStringLiteral, "SPACE"), spaceUno);
        states.put(new Pair<>(endStringLiteral, "CUSTOM"), defaultState);

        states.put(new Pair<>(spaceUno, "SPACE"), ignore);
        states.put(new Pair<>(spaceUno, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(spaceUno, "END_BLOCK"), endBlock);
        states.put(new Pair<>(spaceUno, "QUOTE"), stringLiteral);

        states.put(new Pair<>(ignore, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(ignore, "END_BLOCK"), endBlock);
        states.put(new Pair<>(ignore, "SPACE"), ignore);
        states.put(new Pair<>(ignore, "QUOTE"), stringLiteral);

        states.put(new Pair<>(openBlock, "END_BLOCK"), endBlock);
        states.put(new Pair<>(openBlock, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(openBlock, "SPACE"), ignore);
        states.put(new Pair<>(openBlock, "QUOTE"), stringLiteral);

        states.put(new Pair<>(endBlock, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(endBlock, "END_BLOCK"), endBlock);
        states.put(new Pair<>(endBlock, "SPACE"), ignore);
        states.put(new Pair<>(endBlock, "QUOTE"), stringLiteral);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), defaultState);
    }
}