package lotto.Controller;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();

        List<Lotto> lottos = lottoService.getLottos(purchaseAmount);
        outputView.showLottos(lottos);

        List<Integer> winningNumbers = getValidWinningNumbers();
        int bonusNumber = getValidBonusNumber(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winningLotto);
        outputView.showStatistics(result);
    }

    // 사용자 입력 + 재입력 처리 메서드
    private int getValidPurchaseAmount() {
        try{
            int amount = inputView.purchase();
            InputValidator.validatePurchase(amount);
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력해야 합니다.");
            return getValidPurchaseAmount();
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getValidPurchaseAmount();
        }
    }

    private List<Integer> getValidWinningNumbers() {
        try {
            List<Integer> numbers = Arrays.stream(inputView.winningNumber().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();

            InputValidator.validateWinningNumber(numbers);
            return numbers;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력해야 합니다.");
            return getValidWinningNumbers();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getValidWinningNumbers();
        }
    }

    private int getValidBonusNumber(List<Integer> winningNumbers) {
        try{
            int amount = inputView.bonusNumber();
            InputValidator.validateBonusNumber(amount, winningNumbers);
            return amount;
        }  catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력해야 합니다.");
            return getValidBonusNumber(winningNumbers);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getValidBonusNumber(winningNumbers);
        }
    }
}
