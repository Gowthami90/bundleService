package com.cerner.bundleService.model;

public enum Status {
    DRAFT, PUBLISHED, HISTORY,INVALID;

    public String getStatus() {

        switch(this) {
            case DRAFT:
                return "Draft";

            case PUBLISHED:
                return "Published";

            case HISTORY:
                return "History";

            case INVALID:
                return "Invalid";

            default:
                return null;
        }
    }
}
