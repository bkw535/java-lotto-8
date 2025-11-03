package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final LottoRank[] RANK_ORDER = {
            LottoRank.FIFTH,
            LottoRank.FOURTH,
            LottoRank.THIRD,
            LottoRank.SECOND,
            LottoRank.FIRST
    };

    public void showLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void showStatistics(Map<LottoRank, Integer> result) {
        for (LottoRank rank : RANK_ORDER) {
            System.out.println(formatRankLine(rank, result.getOrDefault(rank, 0)));
        }
    }

    private String formatRankLine(LottoRank rank, int count) {
        if (rank == LottoRank.FIRST) return "6개 일치 (" + formatPrize(rank.getPrize()) + ") - " + count + "개";
        if (rank == LottoRank.SECOND) return "5개 일치, 보너스 볼 일치 (" + formatPrize(rank.getPrize()) + ") - " + count + "개";
        return rank.getMatchCount() + "개 일치 (" + formatPrize(rank.getPrize()) + ") - " + count + "개";
    }

    private String formatPrize(int prize) {
        return String.format("%,d원", prize);
    }

    public void showEarningRate(double rate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }
}
