package com.test.models;

import com.test.data.services.Filterer;

import java.util.*;

public class ApplicationList {
    private Map<String, Application> applications;

    public ApplicationList() {
        applications = new TreeMap<>();
    }

    public void addApplication(Application application) {
        if (find(application.getFullName()) == null) {
            applications.put(application.getFullName(), application);
        }
    }

    public void delAccount(Application application) {
        String fullName = application.getFullName();
        if (find(fullName) != null) {
            applications.remove(fullName);
        }
    }

    public ArrayList<Application> getAllApplications() {
        ArrayList list = new ArrayList();
        for (String key : applications.keySet()) {
            list.add(applications.get(key));
        }
        return list;
    }

    public ArrayList<Application> getAllApplications(Comparator comparator) {
        ArrayList list = new ArrayList();
        for (String key : applications.keySet()) {
            list.add(applications.get(key));
        }
        Collections.sort(list,comparator);
        return list;
    }

    public Application find(String key) {
        Application found = applications.get(key);
        return found;
    }

    public ApplicationList filterBy(Filterer<Application> filterer) {
        ApplicationList newList = new ApplicationList();
        for (String key : applications.keySet()) {
            Application application = applications.get(key);
            if (filterer.filter(application)) {
                newList.addApplication(application);
            }
        }
        return newList;
    }

}