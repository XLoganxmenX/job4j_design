package ru.job4j.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitExample {
    List<String> gitHub = new ArrayList<>();
    Map<Integer, String> gitLab = new HashMap<>();
    String commit;

    public GitExample(String commit) {
        this.commit = commit;
    }

    public void push() {
        gitHub.add(commit);
    }
}
