package ru.job4j.question;

import java.util.*;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Set<Integer> ids = new HashSet<>();
        for (User userCurrent : current) {
            ids.add(userCurrent.getId());
        }

        for (User userPrevious : previous) {
            if (!ids.contains(userPrevious.getId())) {
                info.setDeleted(info.getDeleted() + 1);
            } else if (!current.contains(userPrevious)) {
                info.setChanged(info.getChanged() + 1);
            }
        }
        info.setAdded(current.size() - (previous.size() - info.getDeleted()));
        return info;
    }
}