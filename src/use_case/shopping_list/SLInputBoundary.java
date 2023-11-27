package use_case.shopping_list;

import java.io.IOException;

public interface SLInputBoundary {
    void execute (SLInputData slInputData) throws IOException;
}
