package de.erethon.commons.misc;

public class SecuredStringBuilder {

    private final String key;
    private final StringBuilder sb;
    private boolean accessible;

    public SecuredStringBuilder(String key) {
        this.key = key;
        this.sb = new StringBuilder();
        this.accessible = true;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean b) {
        this.accessible = b;
    }

    public boolean isEmpty() {
        return sb.length() == 0;
    }

    public StringBuilder builder() {
        if (!accessible) {
            throw new IllegalArgumentException("Access denied, found duplicated key: " + key);
        }
        return sb;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
