package UI.Core;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Presenter {
    private final Model model;
    private final IView view;

    public Presenter(ConsoleView view, String pathDb) {
        this.view = view;
        model = new Model(pathDb);
    }

    public void loadFromFile() {
        model.load();
        view.loadMessage();
    }

    public void putForRaffle() {
        if (model.currentRaffleService().putForRaffle(
                new Toy(view.getToyId(), view.getToyNaming(), view.getToyWeight())))
            view.savedItem();
        else
            view.saveError();
    }

    public void deleteFromRaffle() {
        if (model.currentRaffleService.getToys().size() == 0)
            view.emptyListMessage();
        else
            model.currentRaffleService().remove(view.getToyId());
    }

    public void saveToFile() {
        model.save();
        view.savedAll();
    }

    public void showAll() {
        if (model.currentRaffleService.getToys().size() == 0)
            view.emptyListMessage();
        else
            view.showAll(model.currentRaffleService.getToys());
    }

    public void clearAll() {
        if (model.currentRaffleService.getToys().size() == 0)
            view.emptyListMessage();
        else {
            if (view.clearAllDecision()) {
                model.currentRaffleService.getToys().clear();
                System.out.println("Все записи очищены!");
            }
        }
    }

    public void getRaffle() {
        PriorityQueue<Toy> priorityQueue = new PriorityQueue<>();
        Toy RafflenToy;
        List<Toy> RafflenToys = new ArrayList<>();
        if (model.currentRaffleService.getToys().size() != 0) {
            int times = view.getRaffleTimes();
            priorityQueue.addAll(model.currentRaffleService().getToys());
            while (priorityQueue.size() < times) {
                priorityQueue.addAll(model.currentRaffleService().getToys());
            }
            for (int i = 0; i < times; i++) {
                RafflenToy = priorityQueue.remove();
                view.showGetToy(RafflenToy);
                RafflenToys.add(RafflenToy);
            }
            model.saveResult(Config.pathResult, RafflenToys);
        } else
            view.emptyListMessage();
    }
}
