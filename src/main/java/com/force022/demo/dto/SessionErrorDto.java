package com.force022.demo.dto;

import java.util.List;

public class SessionErrorDto {
    List<ErrorMessageDto> errors;

    @java.beans.ConstructorProperties({"errors"})
    public SessionErrorDto(List<ErrorMessageDto> errors) {
        this.errors = errors;
    }

    public List<ErrorMessageDto> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ErrorMessageDto> errors) {
        this.errors = errors;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SessionErrorDto)) return false;
        final SessionErrorDto other = (SessionErrorDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$errors = this.getErrors();
        final Object other$errors = other.getErrors();
        if (this$errors == null ? other$errors != null : !this$errors.equals(other$errors)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SessionErrorDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $errors = this.getErrors();
        result = result * PRIME + ($errors == null ? 43 : $errors.hashCode());
        return result;
    }

    public String toString() {
        return "SessionErrorDto(errors=" + this.getErrors() + ")";
    }
}
