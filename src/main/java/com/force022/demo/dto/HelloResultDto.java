package com.force022.demo.dto;

public class HelloResultDto {
    String helloSay;

    @java.beans.ConstructorProperties({"helloSay"})
    public HelloResultDto(String helloSay) {
        this.helloSay = helloSay;
    }

    public String getHelloSay() {
        return this.helloSay;
    }

    public void setHelloSay(String helloSay) {
        this.helloSay = helloSay;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof HelloResultDto)) return false;
        final HelloResultDto other = (HelloResultDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$helloSay = this.getHelloSay();
        final Object other$helloSay = other.getHelloSay();
        if (this$helloSay == null ? other$helloSay != null : !this$helloSay.equals(other$helloSay)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HelloResultDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $helloSay = this.getHelloSay();
        result = result * PRIME + ($helloSay == null ? 43 : $helloSay.hashCode());
        return result;
    }

    public String toString() {
        return "HelloResultDto(helloSay=" + this.getHelloSay() + ")";
    }
}
