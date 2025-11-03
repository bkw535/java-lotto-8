package lotto.validator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {
    @Test
    void validatePurchase_InvalidAmount() {
        assertThatThrownBy(() -> InputValidator.validatePurchase(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위로 입력해야 합니다.");

        assertThatThrownBy(() -> InputValidator.validatePurchase(1250))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateWinningNumber_InvalidNumber() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 50);
        assertThatThrownBy(() -> InputValidator.validateWinningNumber(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");

        List<Integer> shortNumbers = List.of(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> InputValidator.validateWinningNumber(shortNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    void validateBonusNumber_duplicatedOrRange() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateBonusNumber(0, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");

        assertThatThrownBy(() -> InputValidator.validateBonusNumber(3, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

}
