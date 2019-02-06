package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import tokens.IToken;

import java.io.IOException;
import java.util.List;

public class IgnoreFormatterCmd implements IFormatterCmd {

    private StringBuilder outSb;
    private List<IToken> buff;
    private SMFDictParams dic;

    public IgnoreFormatterCmd(final StringBuilder outSb, final List<IToken> buff, final SMFDictParams dic) {
        this.outSb = outSb;
        this.buff = buff;
        this.dic = dic;
    }


    @Override
    public void execute() throws IOException {
        buff.remove(0);
    }
}