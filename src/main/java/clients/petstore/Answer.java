package clients.petstore;

import java.util.Objects;

public final class Answer {
    private Answer answer;
    private int code;
    private String type;
    private String message;

    public void setCode(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Answer getAnswer() {
        if (answer == null) {
            answer = new Answer();
            return answer;
        } else {
            return answer;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        Answer answer1 = (Answer) o;
        return code == answer1.code
                && Objects.equals(answer, answer1.answer)
                && Objects.equals(type, answer1.type)
                && Objects.equals(message, answer1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, code, type, message);
    }
}
