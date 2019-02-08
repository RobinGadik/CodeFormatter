package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EndBlockFormatterCmdTest {
    @Test
    public void execute() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        EndBlockFormatterCmd cmd = new EndBlockFormatterCmd(out, buff, params);
        buff.add(new Token("}", "END_BLOCK"));
        cmd.execute();
        assertEquals("\n}", out.toString());
    }
    @Test
    public void executeSpecial() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        params.setTabCount(2);
        EndBlockFormatterCmd cmd = new EndBlockFormatterCmd(out, buff, params);
        buff.add(new Token("}", "END_BLOCK"));
        cmd.execute();
        assertEquals("\n    }", out.toString());
    }


}