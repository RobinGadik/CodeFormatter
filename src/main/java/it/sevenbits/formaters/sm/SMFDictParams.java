package it.sevenbits.formaters.sm;

import java.util.HashSet;
import java.util.Set;

/**
 * base params fo SMF
 */
public class SMFDictParams {
    private int tabSize;
    private String tabString;
    private int tabCount;
    private String endLine;
    private Set<String> userDefine;
    private Set<String> ignore;

    public Set<String> getIgnore() {
        return ignore;
    }

    public void setTabCount(final int tabCount) {
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

    /**
     * @param tabSize how mash tabString need to one tab
     * @param tabString which use for tab
     * @param tabCount now tabs
     * @param endLine endline string
     * @param userDefine Set of lexemes type's, used as user_define lexem
     * @param ignore set of ignore lexemes
     */
    public SMFDictParams(final int tabSize, final String tabString, final int tabCount,
                         final String endLine, final Set<String> userDefine, final Set<String> ignore) {
        this.tabSize = tabSize;
        this.tabString = tabString;
        this.tabCount = tabCount;
        this.endLine = endLine;
        this.userDefine = userDefine;
        this.ignore = ignore;
    }

    /**
     * default start paramets
     */
    public SMFDictParams() {
        tabSize = 2 * 2;
        tabString = " ";
        tabCount = 0;
        endLine = "\n";
        userDefine = new HashSet<>();
        userDefine.add("SLASH");
        userDefine.add("SPACE");
        userDefine.add("STRING_LITERAL");
        userDefine.add("INLINE_COMMENT");

        ignore = new HashSet<>();
        ignore.add("END_LINE");

    }


}
