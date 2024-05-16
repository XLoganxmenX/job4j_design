package ru.job4j.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitExample {
    private List<String> gitHub = new ArrayList<>();
    private Map<Integer, String> gitLab = new HashMap<>();
    private String commit;

    public GitExample(String commit) {
        this.commit = commit;
    }

    public void push() {
        gitHub.add(commit);
    }

    public List<String> getAllCommits() {
        return gitHub;
    }
}
