package it.sevenbits.formaters.sm;

import it.sevenbits.IO.IReader;
import it.sevenbits.IO.IWritter;
import it.sevenbits.formaters.IFormatter;
import it.sevenbits.formaters.cmds.*;
import it.sevenbits.lexers.ILexer;
import it.sevenbits.lexers.ILexerFactory;
import it.sevenbits.lexers.LexerException;
import tokens.IToken;
import tokens.Token;

import java.io.IOException;
import java.util.*;

public class StateMachineFormatter  implements IFormatter {

    private ILexerFactory lexerFactory;
    private ILexer lexer;
    private StateMap stateMap;
    private SMFDictParams parameters;
    private State currentState;
    private Map<State, IFormatterCmd> cmds;
    private StringBuilder outSb;
    private List<IToken> buff;

    private void setCmds() {
        cmds = new HashMap<>();

        cmds.put(new State("ADD"), new AddFormatterCmd(outSb, buff, parameters));
        cmds.put(new State("NEW_LINE"), new NewLineFormatterCmd(outSb, buff, parameters));
        cmds.put(new State("OPEN_BLOCK"), new OpenBlockFormatterCmd(outSb, buff, parameters));
        cmds.put(new State("OPEN_MONO_BLOCK"), new OpenMonoBlockFormatterCmd(outSb, buff, parameters));
        cmds.put(new State("END_BLOCK"), new EndBlockFormatterCmd(outSb, buff, parameters));
        cmds.put(new State("FULL_LINE"), new NewLineFormatterCmd(outSb, buff, parameters));
    }

    public StateMachineFormatter(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
        stateMap = new StateMap();
        parameters = new SMFDictParams();
        outSb = new StringBuilder();
        buff = new ArrayList<>();
        setCmds();
    }

    public StateMachineFormatter(ILexerFactory lexerFactory, SMFDictParams parameters) {
        this.lexerFactory = lexerFactory;
        this.parameters = parameters;
        stateMap = new StateMap();
        outSb = new StringBuilder();
        buff = new ArrayList<>();
        setCmds();
    }

    @Override
    public void format(final IReader in, final IWritter out) throws IOException, LexerException {
        lexer = lexerFactory.getLexer(in);
        currentState = stateMap.getStartState();
        while (lexer.hasNextToken()) {
            IToken lexeme = lexer.nextToken();
            if (parameters.getIgnore().contains(lexeme.getType())) {
                continue;
            }
            if (parameters.getUserDefine().contains(lexeme.getType())) {
                lexeme = new Token(lexeme.getText(), "USER_DEFINE");
            }

            buff.add(lexeme);
            currentState = stateMap.getNextState(currentState, lexeme.getType());
            cmds.get(currentState).execute();
            for (int i = 0 ; i < outSb.length() ; i++) {
                out.write(outSb.charAt(i));
            }
            outSb.delete(0, outSb.length());
        }


    }
}
