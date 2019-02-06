package it.sevenbits.formaters.cmds;

import it.sevenbits.formaters.sm.SMFDictParams;
import tokens.IToken;

import java.io.IOException;
import java.util.List;

public class EndBlockFormatterCmd implements IFormatterCmd {

    private StringBuilder outSb;
    private List<IToken> buff;
    private SMFDictParams dic;

    public EndBlockFormatterCmd(StringBuilder outSb, List<IToken> buff, SMFDictParams dic) {
        this.outSb = outSb;
        this.buff = buff;
        this.dic = dic;
    }


    @Override
    public void execute() throws IOException {
        outSb.append(dic.getEndLine());
        dic.setTabCount(dic.getTabCount() - 1 >= 0 ? dic.getTabCount() - 1 : 0);
        for (int i = 0 ; i < dic.getTabCount()*dic.getTabSize() ; i++) {
            outSb.append(dic.getTabString());
        }
        outSb.append(buff.get(0).getText());
        buff.remove(0);

    }
}