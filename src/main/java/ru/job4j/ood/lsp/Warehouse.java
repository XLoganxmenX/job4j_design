package ru.job4j.ood.lsp;

public class Warehouse extends AbstractStore {
    private static final double ACCEPT_EXPIRY_DATE = 0.25;

    @Override
    public boolean isAcceptByExpiryDate(double expiryDatePercent) {
        return expiryDatePercent < ACCEPT_EXPIRY_DATE;
    }
}
