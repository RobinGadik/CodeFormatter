package it.sevenbits.formaters.sm;

import java.util.HashMap;
import java.util.Map;

/**
 * SMF state map
 */
class StateMap {
    private final State defaultState = new State("NEW_LINE");
    private final Map<Pair<State, String>, State> states;

    /**
     * default map
     */
    StateMap() {
        states = new HashMap<>();

        State add = new State("ADD");
        State fullLine = new State("FULL_LINE");
        State openBlock = new State("OPEN_BLOCK");
        State openMonoBlock = new State("OPEN_MONO_BLOCK");
        State endBlock = new State("END_BLOCK");

        states.put(new Pair<>(defaultState, "USER_DEFINE"), add);
        states.put(new Pair<>(defaultState, "INLINE_COMMENT"), fullLine);
        states.put(new Pair<>(defaultState, "MULTILINE_COMMENT"), fullLine);
        states.put(new Pair<>(defaultState, "OPEN_BLOCK"), openMonoBlock);
        states.put(new Pair<>(defaultState, "END_BLOCK"), endBlock);

        states.put(new Pair<>(add, "USER_DEFINE"), add);
        states.put(new Pair<>(add, "INLINE_COMMENT"), add);
        states.put(new Pair<>(add, "MULTILINE_COMMENT"), fullLine);
        states.put(new Pair<>(add, "OPEN_BLOCK"), openMonoBlock);
        states.put(new Pair<>(add, "END_BLOCK"), endBlock);

        states.put(new Pair<>(fullLine, "MULTILINE_COMMENT"), fullLine);
        states.put(new Pair<>(fullLine, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(fullLine, "END_BLOCK"), endBlock);

        states.put(new Pair<>(openMonoBlock, "MULTILINE_COMMENT"), fullLine);
        states.put(new Pair<>(openMonoBlock, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(openMonoBlock, "END_BLOCK"), endBlock);

        states.put(new Pair<>(openBlock, "MULTILINE_COMMENT"), fullLine);
        states.put(new Pair<>(openBlock, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(openBlock, "END_BLOCK"), endBlock);

        states.put(new Pair<>(endBlock, "MULTILINE_COMMENT"), fullLine);
        states.put(new Pair<>(endBlock, "OPEN_BLOCK"), openMonoBlock);
        states.put(new Pair<>(endBlock, "END_BLOCK"), endBlock);
    }

    /**
     * @return start state
     */
    State getStartState() {
        return defaultState;
    }

    /**
     * @param state now state
     * @param signal input lexeme type
     * @return state for that lexem
     */
    State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), defaultState);
    }
}