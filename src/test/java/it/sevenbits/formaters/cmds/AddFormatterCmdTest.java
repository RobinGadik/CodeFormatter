package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddFormatterCmdTest {
    @Test
    public void execute() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        AddFormatterCmd cmd = new AddFormatterCmd(out, buff, params);
        buff.add(new Token("Test", "USER_DEFINE"));
        cmd.execute();
        assertEquals("Test", out.toString());
    }
    @Test
    public void executeSpecial() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        AddFormatterCmd cmd = new AddFormatterCmd(out, buff, params);
        buff.add(new Token("Test", "USER_DEFINE"));
        buff.add(new Token(" ", "SPACE"));
        cmd.execute();
        cmd.execute();
        assertEquals("Test ", out.toString());
    }

}