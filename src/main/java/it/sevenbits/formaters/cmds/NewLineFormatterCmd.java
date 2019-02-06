package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import tokens.IToken;

import java.util.List;

/**
 *
 */
public class NewLineFormatterCmd implements IFormatterCmd {

    private StringBuilder outSb;
    private List<IToken> buff;
    private SMFDictParams dic;

    /**
     * @param outSb where write
     * @param buff lexems buffer
     * @param dic params
     */
    public NewLineFormatterCmd(final StringBuilder outSb, final List<IToken> buff, final SMFDictParams dic) {
        this.outSb = outSb;
        this.buff = buff;
        this.dic = dic;
    }

    @Override
    public void execute() {
        outSb.append(dic.getEndLine());
        for (int i = 0 ; i < dic.getTabCount() * dic.getTabSize() ; i++) {
            outSb.append(dic.getTabString());
        }
        outSb.append(buff.get(0).getText());
        buff.remove(0);
    }
}