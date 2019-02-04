package it.sevenbits.lexers.sm;

import java.util.Objects;

public final class Pair<T, U> {

    private final T first;
    private final U second;

    Pair(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}