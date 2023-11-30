package interface_adapter;

import entity.Recipe;
import use_case.ShowCollectionInteractor;
import view.ShowCollectionView;

import java.util.List;

public class ShowCollectionController {

    private final ShowCollectionInteractor showCollectionInteractor;
    private ShowCollectionView view;

    public ShowCollectionController(ShowCollectionInteractor showCollectionInteractor, ShowCollectionView view) {
        this.showCollectionInteractor = showCollectionInteractor;
        this.view = view;
    }

    public void execute() {
        List<Recipe> recipes = showCollectionInteractor.execute();
        view.displayRecipes(recipes);
    }

    public void setView(ShowCollectionView view) {
        this.view = view;
    }
}
