package it.sevenbits.lexers.sm;

import it.sevenbits.IO.IReader;
import it.sevenbits.lexers.ILexer;
import it.sevenbits.lexers.LexerException;
import it.sevenbits.lexers.lexems.*;
import it.sevenbits.lexers.sm.State;
import it.sevenbits.lexers.tokens.CharTokenMap;
import it.sevenbits.lexers.tokens.IToken;
import it.sevenbits.lexers.tokens.Token;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LexerStateMachine  implements ILexer {
    private int tokenNumber;
    private List<IToken> tokens;
    private CharTokenMap charsMap;
    private List<IToken> charsTokens;
    private List<IToken> buff;
    private State currentState;
    private StateMap stateMap = new StateMap();
    private Map<Pair<String, String>, ILexemCombiner> combs;

    public LexerStateMachine(IReader in) throws IOException {
        tokenNumber = 0;
        tokens = new LinkedList<>();
        charsTokens = new LinkedList<>();
        charsMap = new CharTokenMap();
        combs = new HashMap<>();
        buff = new LinkedList<>();
        currentState = stateMap.getStartState();
        while (in.hasNext()) {
            charsTokens.add(charsMap.getToken(in.read()));
        }
        //TODO combs add
        combs.put(new Pair<>("CLONE", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "SPACE"), new SpaceCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "QUOTE"), new StringLiteralCombiner(tokens, buff));

        combs.put(new Pair<>("IGNORE", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "SPACE"), new IgnoreCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "QUOTE"), new StringLiteralCombiner(tokens, buff));

        combs.put(new Pair<>("SPACE", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "SPACE"), new SpaceCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "QUOTE"), new StringLiteralCombiner(tokens, buff));

        combs.put(new Pair<>("STRING_LITERAL", "CUSTOM"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "OPEN_BLOCK"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "END_BLOCK"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "SPACE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "QUOTE"), new StringLiteralCombiner(tokens, buff));

        combs.put(new Pair<>("END_STRING_LITERAL", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "SPACE"), new SpaceCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "QUOTE"), new StringLiteralCombiner(tokens, buff));

        combs.put(new Pair<>("OPEN_BLOCK", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("OPEN_BLOCK", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("OPEN_BLOCK", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("OPEN_BLOCK", "SPACE"), new IgnoreCombiner(tokens, buff));
        combs.put(new Pair<>("OPEN_BLOCK", "QUOTE"), new StringLiteralCombiner(tokens, buff));

        combs.put(new Pair<>("END_BLOCK", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("END_BLOCK", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("END_BLOCK", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("END_BLOCK", "SPACE"), new IgnoreCombiner(tokens, buff));
        combs.put(new Pair<>("END_BLOCK", "QUOTE"), new StringLiteralCombiner(tokens, buff));




        for (IToken charToken : charsTokens) {
            buff.add(charToken);
            currentState = stateMap.getNextState(currentState, charToken.getType());
            System.out.println(currentState);
            System.out.println(buff);
            combs.get(new Pair<>(currentState.toString(), charToken.getType())).execute();
        }

        System.out.println(tokens.size());
        for (IToken t : tokens) {
            System.out.println(t.toString());
        }


    }


    @Override
    public IToken nextToken() throws LexerException {
        if (tokenNumber < tokenNumber) {
            return tokens.get(tokenNumber++);
        } else {
            throw new LexerException("End of tokens");
        }
    }

    @Override
    public boolean hasNextToken() {
        return tokenNumber < tokenNumber;
    }
}
