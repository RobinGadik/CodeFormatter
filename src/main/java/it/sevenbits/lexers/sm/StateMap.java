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
        State slash = new State("SLASH");
        State commentSuspection = new State("SUSPECT_COMMENT");
        State inlineComment = new State("INLINE_COMMENT");
        State multilineComment = new State("MULTILINE_COMMENT");
        State multilineCommentSuspectEnd = new State("MULTILINE_COMMENT_SUSPECT_END");
        State multilineCommentEnd = new State("MULTILINE_COMMENT_END");
        State endLine = new State("END_LINE");

        states.put(new Pair<>(defaultState, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(defaultState, "END_BLOCK"), endBlock);
        states.put(new Pair<>(defaultState, "SPACE"), spaceUno);
        states.put(new Pair<>(defaultState, "QUOTE"), stringLiteral);
        states.put(new Pair<>(defaultState, "SLASH"), slash);;
        states.put(new Pair<>(defaultState, "END_LINE"), endLine);

        states.put(new Pair<>(stringLiteral, "OPEN_BLOCK"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "END_BLOCK"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "SPACE"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "CUSTOM"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "SLASH"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "STAR"), stringLiteral);
        states.put(new Pair<>(stringLiteral, "QUOTE"), endStringLiteral);

        states.put(new Pair<>(endStringLiteral, "QUOTE"), stringLiteral);
        states.put(new Pair<>(endStringLiteral, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(endStringLiteral, "END_BLOCK"), endBlock);
        states.put(new Pair<>(endStringLiteral, "SPACE"), spaceUno);
        states.put(new Pair<>(endStringLiteral, "SLASH"), slash);
        states.put(new Pair<>(endStringLiteral, "END_LINE"), endLine);

        states.put(new Pair<>(slash, "QUOTE"), stringLiteral);
        states.put(new Pair<>(slash, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(slash, "END_BLOCK"), endBlock);
        states.put(new Pair<>(slash, "SPACE"), spaceUno);
        states.put(new Pair<>(slash, "END_LINE"), endLine);
        states.put(new Pair<>(slash, "SLASH"), inlineComment);
        states.put(new Pair<>(slash, "STAR"), multilineComment);

        states.put(new Pair<>(commentSuspection, "QUOTE"), stringLiteral);
        states.put(new Pair<>(commentSuspection, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(commentSuspection, "END_BLOCK"), endBlock);
        states.put(new Pair<>(commentSuspection, "SPACE"), spaceUno);
        states.put(new Pair<>(commentSuspection, "END_LINE"), endLine);
        states.put(new Pair<>(commentSuspection, "SLASH"), inlineComment);
        states.put(new Pair<>(commentSuspection, "STAR"), multilineComment);

        states.put(new Pair<>(inlineComment, "QUOTE"), inlineComment);
        states.put(new Pair<>(inlineComment, "OPEN_BLOCK"), inlineComment);
        states.put(new Pair<>(inlineComment, "END_BLOCK"), inlineComment);
        states.put(new Pair<>(inlineComment, "SPACE"), inlineComment);
        states.put(new Pair<>(inlineComment, "CUSTOM"), inlineComment);
        states.put(new Pair<>(inlineComment, "SLASH"), inlineComment);
        states.put(new Pair<>(inlineComment, "STAR"), inlineComment);
        states.put(new Pair<>(inlineComment, "END_LINE"), endLine);

        states.put(new Pair<>(multilineComment, "QUOTE"), multilineComment);
        states.put(new Pair<>(multilineComment, "OPEN_BLOCK"), multilineComment);
        states.put(new Pair<>(multilineComment, "END_BLOCK"), multilineComment);
        states.put(new Pair<>(multilineComment, "SPACE"), multilineComment);
        states.put(new Pair<>(multilineComment, "CUSTOM"), multilineComment);
        states.put(new Pair<>(multilineComment, "SLASH"), multilineComment);
        states.put(new Pair<>(multilineComment, "END_LINE"), multilineComment);
        states.put(new Pair<>(multilineComment, "STAR"), multilineCommentSuspectEnd);

        states.put(new Pair<>(multilineCommentSuspectEnd, "QUOTE"), multilineComment);
        states.put(new Pair<>(multilineCommentSuspectEnd, "OPEN_BLOCK"), multilineComment);
        states.put(new Pair<>(multilineCommentSuspectEnd, "END_BLOCK"), multilineComment);
        states.put(new Pair<>(multilineCommentSuspectEnd, "SPACE"), multilineComment);
        states.put(new Pair<>(multilineCommentSuspectEnd, "CUSTOM"), multilineComment);
        states.put(new Pair<>(multilineCommentSuspectEnd, "SLASH"), multilineCommentEnd);
        states.put(new Pair<>(multilineCommentSuspectEnd, "END_LINE"), multilineComment);
        states.put(new Pair<>(multilineCommentSuspectEnd, "STAR"), multilineCommentSuspectEnd);

        states.put(new Pair<>(spaceUno, "SPACE"), ignore);
        states.put(new Pair<>(spaceUno, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(spaceUno, "END_BLOCK"), endBlock);
        states.put(new Pair<>(spaceUno, "QUOTE"), stringLiteral);
        states.put(new Pair<>(spaceUno, "SLASH"), slash);
        states.put(new Pair<>(spaceUno, "END_LINE"), endLine);

        states.put(new Pair<>(ignore, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(ignore, "END_BLOCK"), endBlock);
        states.put(new Pair<>(ignore, "SPACE"), ignore);
        states.put(new Pair<>(ignore, "QUOTE"), stringLiteral);
        states.put(new Pair<>(ignore, "SLASH"), slash);
        states.put(new Pair<>(ignore, "END_LINE"), endLine);

        states.put(new Pair<>(openBlock, "END_BLOCK"), endBlock);
        states.put(new Pair<>(openBlock, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(openBlock, "SPACE"), ignore);
        states.put(new Pair<>(openBlock, "QUOTE"), stringLiteral);
        states.put(new Pair<>(openBlock, "SLASH"), slash);
        states.put(new Pair<>(openBlock, "END_LINE"), endLine);

        states.put(new Pair<>(endBlock, "OPEN_BLOCK"), openBlock);
        states.put(new Pair<>(endBlock, "END_BLOCK"), endBlock);
        states.put(new Pair<>(endBlock, "SPACE"), ignore);
        states.put(new Pair<>(endBlock, "QUOTE"), stringLiteral);
        states.put(new Pair<>(endBlock, "SLASH"), slash);
        states.put(new Pair<>(endBlock, "END_LINE"), endLine);
    }

    public State getStartState() {
        return defaultState;
    }

    public State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), defaultState);
    }
}