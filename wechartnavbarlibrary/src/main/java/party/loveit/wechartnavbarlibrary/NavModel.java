package party.loveit.wechartnavbarlibrary;

public class NavModel {
    private String textValue;
    private int iconNormalRes;
    private int iconSelectRes;

    public NavModel(Builder builder) {
        this.textValue = builder.textValue;
        this.iconNormalRes = builder.iconNormalRes;
        this.iconSelectRes = builder.iconSelectRes;
        this.iconSelectRes = builder.iconSelectRes;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public int getIconNormalRes() {
        return iconNormalRes;
    }

    public void setIconNormalRes(int iconNormalRes) {
        this.iconNormalRes = iconNormalRes;
    }

    public int getIconSelectRes() {
        return iconSelectRes;
    }

    public void setIconSelectRes(int iconSelectRes) {
        this.iconSelectRes = iconSelectRes;
    }

    public static class Builder{
        private String textValue;
        private int iconNormalRes;
        private int iconSelectRes;

        public Builder textValue(String text){
            this.textValue = text;
            return this;
        }
        public Builder iconNormalRes(int resId){
            this.iconNormalRes = resId;
            return this;
        }
        public Builder iconSelectRes(int resId){
            this.iconSelectRes = resId;
            return this;
        }

        public NavModel bulid(){
            return new NavModel(this);
        }
    }
}
