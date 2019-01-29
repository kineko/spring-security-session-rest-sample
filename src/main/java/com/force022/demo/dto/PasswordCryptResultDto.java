package com.force022.demo.dto;

public class PasswordCryptResultDto {

    private String password;
    private String crypt;

    @java.beans.ConstructorProperties({"password", "crypt"})
    public PasswordCryptResultDto(String password, String crypt) {
        this.password = password;
        this.crypt = crypt;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCrypt() {
        return this.crypt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCrypt(String crypt) {
        this.crypt = crypt;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PasswordCryptResultDto)) return false;
        final PasswordCryptResultDto other = (PasswordCryptResultDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$crypt = this.getCrypt();
        final Object other$crypt = other.getCrypt();
        if (this$crypt == null ? other$crypt != null : !this$crypt.equals(other$crypt)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PasswordCryptResultDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $crypt = this.getCrypt();
        result = result * PRIME + ($crypt == null ? 43 : $crypt.hashCode());
        return result;
    }

    public String toString() {
        return "PasswordCryptResultDto(password=" + this.getPassword() + ", crypt=" + this.getCrypt() + ")";
    }
}
