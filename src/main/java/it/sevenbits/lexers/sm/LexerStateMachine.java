package it.sevenbits.lexers.sm;

import it.sevenbits.IO.IReader;
import it.sevenbits.lexers.ILexer;
import it.sevenbits.lexers.LexerException;
import it.sevenbits.lexers.lexems.CloneCombiner;
import it.sevenbits.lexers.lexems.CommentStartCombiner;
import it.sevenbits.lexers.lexems.EndBlockCombiner;
import it.sevenbits.lexers.lexems.EndLineCombiner;
import it.sevenbits.lexers.lexems.ILexemCombiner;
import it.sevenbits.lexers.lexems.IgnoreCombiner;
import it.sevenbits.lexers.lexems.InlineCommentCombiner;
import it.sevenbits.lexers.lexems.MultilineCommentCombiner;
import it.sevenbits.lexers.lexems.OpenBlockCombiner;
import it.sevenbits.lexers.lexems.SpaceCombiner;
import it.sevenbits.lexers.lexems.StringLiteralCombiner;
import tokens.CharTokenMap;
import tokens.IToken;

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

    /**
     * @param in IReader input
     * @throws IOException IReader fail
     */
    public LexerStateMachine(final IReader in) throws IOException {
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
        combs.put(new Pair<>("CLONE", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "SPACE"), new SpaceCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "END_LINE"), new EndLineCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "STAR"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("CLONE", "SLASH"), new CloneCombiner(tokens, buff));

        combs.put(new Pair<>("SLASH", "SLASH"), new CommentStartCombiner(tokens, buff));

        combs.put(new Pair<>("SUSPECT_COMMENT", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("SUSPECT_COMMENT", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("SUSPECT_COMMENT", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("SUSPECT_COMMENT", "SPACE"), new SpaceCombiner(tokens, buff));
        combs.put(new Pair<>("SUSPECT_COMMENT", "QUOTE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("SUSPECT_COMMENT", "END_LINE"), new EndLineCombiner(tokens, buff));
        combs.put(new Pair<>("SUSPECT_COMMENT", "STAR"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("SUSPECT_COMMENT", "SLASH"), new InlineCommentCombiner(tokens, buff));


        combs.put(new Pair<>("INLINE_COMMENT", "CUSTOM"), new InlineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("INLINE_COMMENT", "OPEN_BLOCK"), new InlineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("INLINE_COMMENT", "END_BLOCK"), new InlineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("INLINE_COMMENT", "SPACE"), new InlineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("INLINE_COMMENT", "QUOTE"), new InlineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("INLINE_COMMENT", "END_LINE"), new EndLineCombiner(tokens, buff));
        combs.put(new Pair<>("INLINE_COMMENT", "STAR"), new InlineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("INLINE_COMMENT", "SLASH"), new InlineCommentCombiner(tokens, buff));

        combs.put(new Pair<>("MULTILINE_COMMENT", "CUSTOM"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT", "OPEN_BLOCK"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT", "END_BLOCK"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT", "SPACE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT", "QUOTE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT", "END_LINE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT", "STAR"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT", "SLASH"), new MultilineCommentCombiner(tokens, buff));

        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "CUSTOM"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "OPEN_BLOCK"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "END_BLOCK"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "SPACE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "QUOTE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "END_LINE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "STAR"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_SUSPECT_END", "SLASH"), new MultilineCommentCombiner(tokens, buff));

        combs.put(new Pair<>("MULTILINE_COMMENT_END", "CUSTOM"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_END", "OPEN_BLOCK"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_END", "END_BLOCK"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_END", "SPACE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_END", "QUOTE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_END", "END_LINE"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_END", "STAR"), new MultilineCommentCombiner(tokens, buff));
        combs.put(new Pair<>("MULTILINE_COMMENT_END", "SLASH"), new MultilineCommentCombiner(tokens, buff));

        combs.put(new Pair<>("END_LINE", "END_LINE"), new IgnoreCombiner(tokens, buff));

        combs.put(new Pair<>("IGNORE", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "SPACE"), new IgnoreCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "QUOTE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "END_LINE"), new EndLineCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "STAR"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("IGNORE", "SLASH"), new CommentStartCombiner(tokens, buff));

        combs.put(new Pair<>("SPACE", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "SPACE"), new SpaceCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "QUOTE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "END_LINE"), new EndLineCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "STAR"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("SPACE", "SLASH"), new CommentStartCombiner(tokens, buff));

        combs.put(new Pair<>("STRING_LITERAL", "CUSTOM"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "OPEN_BLOCK"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "END_BLOCK"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "SPACE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "QUOTE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "END_LINE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "STAR"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("STRING_LITERAL", "SLASH"), new StringLiteralCombiner(tokens, buff));

        combs.put(new Pair<>("END_STRING_LITERAL", "CUSTOM"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "END_BLOCK"), new EndBlockCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "SPACE"), new SpaceCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "QUOTE"), new StringLiteralCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "END_LINE"), new EndLineCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "STAR"), new CloneCombiner(tokens, buff));
        combs.put(new Pair<>("END_STRING_LITERAL", "SLASH"), new CommentStartCombiner(tokens, buff));

        combs.put(new Pair<>("OPEN_BLOCK", "OPEN_BLOCK"), new OpenBlockCombiner(tokens, buff));

        combs.put(new Pair<>("END_BLOCK", "END_BLOCK"), new EndBlockCombiner(tokens, buff));

        for (IToken charToken : charsTokens) {
            buff.add(charToken);
            currentState = stateMap.getNextState(currentState, charToken.getType());
            //System.out.println(currentState);
            //System.out.println(buff);
            combs.get(new Pair<>(currentState.toString(), charToken.getType())).execute();
            //System.out.println("FIUUUUU uuu uu u u u u u u ");
            //System.out.println(tokens);
        }

        /*System.out.println(tokens.size());
        for (IToken t : tokens) {
            //System.out.println(t.toString());
        }*/


    }


    @Override
    public IToken nextToken() throws LexerException {
        if (tokenNumber < tokens.size()) {
            return tokens.get(tokenNumber++);
        } else {
            throw new LexerException("End of tokens");
        }
    }

    @Override
    public boolean hasNextToken() {
        return tokenNumber < tokens.size();
    }
}
