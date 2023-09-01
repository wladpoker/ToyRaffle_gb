package UI.Core;

import java.util.List;

public interface IView {
    int getToyId();
    String getToyNaming();
    int getToyWeight();
    void showAll(List<Toy> toys);
    boolean clearAllDecision();
    void savedAll();
    void savedItem();
    void saveError();
    void emptyListMessage();
    void showGetToy(Toy toy);
    void loadMessage();
    int getRaffleTimes();
}
