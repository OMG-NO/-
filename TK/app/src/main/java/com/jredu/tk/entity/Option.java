package com.jredu.tk.entity;

public class Option {
    private String optionContent;

    private boolean isSelec=false;

    public Option(String optionContent) {
        this.optionContent = optionContent;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public boolean isSelec() {
        return isSelec;
    }

    public void setSelec(boolean selec) {
        isSelec = selec;
    }
}
