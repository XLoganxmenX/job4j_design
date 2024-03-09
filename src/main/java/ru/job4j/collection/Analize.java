package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);

        if (!previous.equals(current)) {
            Map<Integer, User> currentMap = new HashMap<>();
            current.forEach(user -> currentMap.put(user.getId(), user));

            for (User user : previous) {
                User userVal = currentMap.get(user.getId());

                if (userVal == null) {
                    info.setDeleted(info.getDeleted() + 1);
                } else if (!user.equals(userVal)) {
                    info.setChanged(info.getChanged() + 1);
                }

                if (userVal != null) {
                    currentMap.remove(userVal.getId());
                }
            }

            info.setAdded(currentMap.size());
        }
        return info;
    }

}
