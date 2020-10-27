package com.codecool.simpleSQLApp.controllers;

import com.codecool.simpleSQLApp.dao.ApplicantDAO;
import com.codecool.simpleSQLApp.dao.MentorDAO;
import com.codecool.simpleSQLApp.io.InputProvider;
import com.codecool.simpleSQLApp.io.View;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private final Map<String, Runnable> mainMenuMap;
    private final MentorDAO mentorDao;
    private final ApplicantDAO applicantDao;
    private final InputProvider inputProvider;
    private static final View view = new View();

    public Controller() {
        this.inputProvider = new InputProvider();
        this.mentorDao = new MentorDAO();
        this.applicantDao = new ApplicantDAO();
        mainMenuMap = new HashMap<>();


        handleMenu(mainMenuMap, this::createMainMenuMap);

    }


    public void handleMenu(Map<String, Runnable> menuMap, Runnable uiMenu) {
        boolean isRunning = true;
        do {
            uiMenu.run();
            view.printMenu();
            String input = inputProvider.takeUserInput("Choose option ");
            if (input.equals("0")) {
                isRunning = false;
                continue;
            }
            try {
                menuMap.get(input).run();
            } catch (NullPointerException e) {
                System.out.println("No such option");
            }
        } while (isRunning);
    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", mentorDao::getNameAndSurname);
        mainMenuMap.put("2", mentorDao::getMiskolcMentorsNicknames);
        mainMenuMap.put("3", applicantDao::getCarol);
        mainMenuMap.put("4", applicantDao::getAdipiscingenimmiSuffixUser);
        mainMenuMap.put("5", applicantDao::getByApplicationCode);
        mainMenuMap.put("6", applicantDao::updateJemina);
        //mainMenuMap.put("7", () -> applicantDao.getUserByName("Jemina"));
        mainMenuMap.put("8", applicantDao::removeMauriseuSuffixUsers);
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }
}
