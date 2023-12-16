package com.example.lab7;

import Models.Bank;
import Models.Client;
import ViewModel.BankViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BankSimulationController {

    // UI elements
    @FXML
    private ComboBox<Thread> ThreadsComboBox;
    @FXML
    private TextField NumberTextField;
    @FXML
    private TextField MoneyTextField;
    @FXML
    private HBox MainHBox;
    @FXML
    private HBox ThreadInfo;
    @FXML
    private Button StartButton;
    @FXML
    private Button StopButton;
    @FXML
    private Button ResumeButton;
    // -------------

    private final List<Client> clients = new LinkedList<>();
    private final ObservableList<Thread> threads = FXCollections.observableArrayList();
    private final List<Label> threadsInfo = new LinkedList<>();

    // UI events
    @FXML
    protected void onStartClick() {
        int threadsCount;
        try {
            threadsCount = Integer.parseInt(NumberTextField.getText());
        }
        catch (NumberFormatException ex) {
            showMessage(ex.getMessage(), Alert.AlertType.ERROR);
            return;
        }

        int bankMoney;
        try {
            bankMoney = 900;
            this.StartButton.setDisable(true);
        }
        catch (NumberFormatException ex) {
            showMessage(ex.getMessage(), Alert.AlertType.ERROR);
            return;
        }

        BankViewModel bankViewModel = new BankViewModel(new Bank(bankMoney));

        for (int i = 0; i < threadsCount; i++) {

            Label threadsText = new Label();
            threadsText.setWrapText(true);
            threadsText.setPadding(new Insets(20.0));
            threadsText.maxHeight(300);
            MainHBox.getChildren().add(threadsText);

            Label threadInfo = new Label();
            threadInfo.setWrapText(true);
            threadInfo.setPadding(new Insets(20));
            threadInfo.maxHeight(150);
            ThreadInfo.getChildren().add(threadInfo);

            var client = new Client(
                    bankViewModel, threadsText, threadInfo
            );

            var thread = new Thread(client);

            try {
                thread.setPriority(new Random().nextInt(Thread.MIN_PRIORITY, Thread.MAX_PRIORITY + 1));
            }
            catch (Exception ex) {
                thread = new Thread(client);
                thread.setPriority(5);
            }

            clients.add(client);
            threads.add(thread);
            threadsInfo.add(threadInfo);
        }

        startThreads();

        StopButton.setDisable(false);
        ThreadsComboBox.setDisable(false);
        ThreadsComboBox.getItems().clear();
        ThreadsComboBox.getItems().addAll(threads);
    }

    @FXML
    protected void onStopClick() {
        Thread selectedThread = ThreadsComboBox.getSelectionModel().getSelectedItem();
        int index = threads.indexOf(selectedThread);
        if(index != -1 && threads.get(index).isAlive()) {
            clients.get(index).manuallyStop();
            System.out.println("Stopped"+clients.get(index).getName());
        } else {
            showMessage("This thread can't be stopped", Alert.AlertType.ERROR);
            return;
        }

        ResumeButton.setDisable(false);
        // Update UI information
        var infos = threadsInfo.get(threads.indexOf(selectedThread)).getText().split("\n");
        infos[infos.length - 1] = "Time last change (stopped): " +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            threadsInfo.get(threads.indexOf(selectedThread)).setText(String.join("\n", infos));
    }



    @FXML
    protected void onResumeClick() {
        Thread selectedThread = ThreadsComboBox.getSelectionModel().getSelectedItem();
        int index = threads.indexOf(selectedThread);
        if(index != -1 && threads.get(index).isAlive()) {
            clients.get(index).manuallyResume();
        } else {
            showMessage("This thread can't be resumed", Alert.AlertType.WARNING);
            return;
        }

        StopButton.setDisable(true);
        // Update UI information
        var infos = threadsInfo.get(threads.indexOf(selectedThread)).getText().split("\n");
        infos[infos.length - 1] = "Time last change (resumed): " +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        threadsInfo.get(threads.indexOf(selectedThread)).setText(String.join("\n", infos));
    }

    private void startThreads() {
        for (Thread thread : threads)
        {
            thread.start();
        }

        try {
            for (Thread guest : clients)
            {
                guest.join();
            }
        } catch (InterruptedException e) {
            showMessage(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showMessage (String errorMessage, Alert.AlertType alertType) {
        var alert = new Alert(alertType);
        alert.setTitle("Error!");
        alert.setContentText(errorMessage);
        alert.show();
    }
}