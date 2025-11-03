package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void showLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void showStatistics(Map<LottoRank, Integer> result) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.MISS) continue;
            System.out.printf("%d개 일치%s (%d원) - %d개%n",
                    rank.getMatchCount(),
                    rank == LottoRank.SECOND ? ", 보너스 볼 일치" : "",
                    rank.getPrize(),
                    result.getOrDefault(rank, 0));
        }
    }
}
