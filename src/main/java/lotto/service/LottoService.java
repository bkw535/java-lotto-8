package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
