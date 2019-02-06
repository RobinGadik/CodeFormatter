package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import tokens.IToken;

import java.util.List;

/**
 *
 */
public class IgnoreFormatterCmd implements IFormatterCmd {

    private StringBuilder outSb;
    private List<IToken> buff;
    private SMFDictParams dic;

    /**
     * @param outSb where write
     * @param buff lexems buffer
     * @param dic params
     */
    public IgnoreFormatterCmd(final StringBuilder outSb, final List<IToken> buff, final SMFDictParams dic) {
        this.outSb = outSb;
        this.buff = buff;
        this.dic = dic;
    }


    @Override
    public void execute() {
        buff.remove(0);
    }
}