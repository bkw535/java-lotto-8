package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;

import java.util.*;

public class LottoService {
    public List<Lotto> getLottos(Integer purchaseAmount) {
        int count = purchaseAmount / 1000;
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < count; i++){
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    public Map<LottoRank, Integer> calculateResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = new HashMap<>();

        for (Lotto lotto : lottos) {
            int matchCount = (int) lotto.getNumbers().stream()
                    .filter(winningLotto.getWinningNumbers()::contains)
                    .count();
            boolean bonusMatch = lotto.getNumbers().contains(winningLotto.getBonusNumber());

            LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }

        return result;
    }
}
