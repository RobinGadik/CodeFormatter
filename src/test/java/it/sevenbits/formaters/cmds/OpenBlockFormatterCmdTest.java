package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OpenBlockFormatterCmdTest {
    @Test
    public void execute() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        params.setTabCount(1);
        OpenBlockFormatterCmd cmd = new OpenBlockFormatterCmd(out, buff, params);
        buff.add(new Token("{", "USER_DEFINE"));
        cmd.execute();
        assertEquals("\n    {", out.toString());
    }
    @Test
    public void executeNoTab() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        OpenBlockFormatterCmd cmd = new OpenBlockFormatterCmd(out, buff, params);
        buff.add(new Token("{", "USER_DEFINE"));
        cmd.execute();
        assertEquals("\n{", out.toString());
    }

}