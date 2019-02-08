package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NewLineFormatterCmdTest {
    @Test
    public void execute() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        params.setTabCount(1);
        NewLineFormatterCmd cmd = new NewLineFormatterCmd(out, buff, params);
        buff.add(new Token("1231();", "USER_DEFINE"));
        cmd.execute();
        assertEquals("\n    1231();", out.toString());
    }
    @Test
    public void executeNoTab() throws Exception {
        StringBuilder out = new StringBuilder();
        List<IToken> buff = new ArrayList<>();
        SMFDictParams params = new SMFDictParams();
        NewLineFormatterCmd cmd = new NewLineFormatterCmd(out, buff, params);
        buff.add(new Token("1231();", "USER_DEFINE"));
        cmd.execute();
        assertEquals("\n1231();", out.toString());
    }

}