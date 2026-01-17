package com.projectasmag.asmag.exceptiohandler.exception;

import java.util.Set;
import java.util.UUID;

public class InvalidLoanOwnershipException extends RuntimeException {
    private final UUID loanId;
    private final Set<UUID> loanDetailIds;

    public InvalidLoanOwnershipException(String message, UUID loanId, Set<UUID> loanDetailIds) {
        super(message);
        this.loanId = loanId;
        this.loanDetailIds = loanDetailIds;
    }

    public UUID getLoanId() {
        return loanId;
    }

    public Set<UUID> getLoanDetailIds() {
        return loanDetailIds;
    }
}
