package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    void showMyLottos() {
        int purchaseAmount = 8000;

        List<Lotto> lottos = lottoService.getLottos(purchaseAmount);

        assertThat(lottos).hasSize(8);
        lottos.forEach(lotto -> assertThat(lotto.getNumbers()).hasSize(6));
    }

    @Test
    void calculateResult_returnsCorrectRankCounts() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        Map<LottoRank, Integer> result = lottoService.calculateResult(lottos, winning);

        assertThat(result.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.get(LottoRank.MISS)).isEqualTo(1);
    }
}
