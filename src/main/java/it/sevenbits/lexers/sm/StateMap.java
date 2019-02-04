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

        states.put(new Pair<>(defaultState, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(defaultState, "END_BLOCK"), endBlock);

        states.put(new Pair<>(openBlock, "END_BLOCK"), endBlock);
        states.put(new Pair<>(openBlock, "OPEN_BLOCK"), openBlock);

        states.put(new Pair<>(endBlock, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(endBlock, "END_BLOCK"), endBlock);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), defaultState);
    }
}