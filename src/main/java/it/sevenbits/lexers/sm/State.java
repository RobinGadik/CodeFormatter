package it.sevenbits.lexers.sm;

import java.util.Objects;

/**
 *SM state for lexerSM
 */
class State {
    private final String currentState;

    /**
     * @param currentState state Name
     */
    State(final String currentState) {
        this.currentState = currentState;
    }

    /**
     * @return current state name
     */
    public String toString() {
        return currentState;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(currentState, state.currentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState);
    }
}