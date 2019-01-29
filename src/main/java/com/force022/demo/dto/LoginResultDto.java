package com.force022.demo.dto;

public class LoginResultDto {

    private String access_token;
    private String token_type;
    private Integer expires_in;

    @java.beans.ConstructorProperties({"access_token", "token_type", "expires_in"})
    public LoginResultDto(String access_token, String token_type, Integer expires_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public Integer getExpires_in() {
        return this.expires_in;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LoginResultDto)) return false;
        final LoginResultDto other = (LoginResultDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$access_token = this.getAccess_token();
        final Object other$access_token = other.getAccess_token();
        if (this$access_token == null ? other$access_token != null : !this$access_token.equals(other$access_token))
            return false;
        final Object this$token_type = this.getToken_type();
        final Object other$token_type = other.getToken_type();
        if (this$token_type == null ? other$token_type != null : !this$token_type.equals(other$token_type))
            return false;
        final Object this$expires_in = this.getExpires_in();
        final Object other$expires_in = other.getExpires_in();
        if (this$expires_in == null ? other$expires_in != null : !this$expires_in.equals(other$expires_in))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoginResultDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $access_token = this.getAccess_token();
        result = result * PRIME + ($access_token == null ? 43 : $access_token.hashCode());
        final Object $token_type = this.getToken_type();
        result = result * PRIME + ($token_type == null ? 43 : $token_type.hashCode());
        final Object $expires_in = this.getExpires_in();
        result = result * PRIME + ($expires_in == null ? 43 : $expires_in.hashCode());
        return result;
    }

    public String toString() {
        return "LoginResultDto(access_token=" + this.getAccess_token() + ", token_type=" + this.getToken_type() + ", expires_in=" + this.getExpires_in() + ")";
    }
}
