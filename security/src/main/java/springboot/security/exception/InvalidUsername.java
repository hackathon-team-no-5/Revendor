package springboot.security.exception;

public class InvalidUsername extends RuntimeException {
    public InvalidUsername() {
        super("일치하는 ID가 없습니다. 다시 입력해주세요.");
    }
}