package com.example.cw_draft5;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class DisplayClubs {
    private final StringProperty clubNameProperty;
    private final StringProperty clubDescriptionProperty;
    private final StringProperty clubBenefitsProperty;
    private final IntegerProperty clubIDProperty;



    public DisplayClubs() {
        this.clubNameProperty = new SimpleStringProperty();
        this.clubDescriptionProperty = new SimpleStringProperty();
        this.clubBenefitsProperty = new SimpleStringProperty();
        this.clubIDProperty = new SimpleIntegerProperty();
    }

    public StringProperty getClubNameProperty() {
        return clubNameProperty;
    }

    public StringProperty getClubDescriptionProperty() {
        return clubDescriptionProperty;
    }

    public StringProperty getClubBenefitsProperty() { return clubBenefitsProperty;
    }

    public IntegerProperty getClubIDProperty() {
        return clubIDProperty;
    }

}
