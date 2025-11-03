package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
