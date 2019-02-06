package it.sevenbits.formaters.sm;

import java.util.HashSet;
import java.util.Set;

public class SMFDictParams {
    private int tabSize = 4;
    private String tabString = " ";
    private int tabCount = 0;
    private String endLine = "\n";
    private Set<String> userDefine;
    private Set<String> ignore;

    public Set<String> getIgnore() {
        return ignore;
    }

    public void setTabCount(int tabCount) {
        this.tabCount = tabCount;
    }

    public int getTabSize() {
        return tabSize;
    }

    public String getTabString() {
        return tabString;
    }

    public int getTabCount() {
        return tabCount;
    }

    public String getEndLine() {
        return endLine;
    }

    public Set<String> getUserDefine() {
        return userDefine;
    }

    public SMFDictParams(int tabSize, String tabString, int tabCount, String endLine, Set<String> userDefine, Set<String> ignore) {
        this.tabSize = tabSize;
        this.tabString = tabString;
        this.tabCount = tabCount;
        this.endLine = endLine;
        this.userDefine = userDefine;
        this.ignore = ignore;
    }

    public SMFDictParams() {
        userDefine = new HashSet<>();
        userDefine.add("SLASH");
        userDefine.add("SPACE");
        userDefine.add("STRING_LITERAL");
        userDefine.add("INLINE_COMMENT");

        ignore = new HashSet<>();
        ignore.add("END_LINE");

    }


}
