package ru.job4j.ood.lsp;

public class Shop extends AbstractStore {
    private static final double ACCEPT_EXPIRY_DATE_BEG = 0.25;

    @Override
    public boolean isAcceptByExpiryDate(double expiryDatePercent) {
        return expiryDatePercent >= ACCEPT_EXPIRY_DATE_BEG
                && expiryDatePercent < 1;
    }
}
