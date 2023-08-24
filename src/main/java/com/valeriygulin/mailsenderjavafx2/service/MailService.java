package com.valeriygulin.mailsenderjavafx2.service;

import com.valeriygulin.mailsenderjavafx2.model.Mail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MailService {

    private BlockingQueue<Mail> queue = new LinkedBlockingQueue<>();
    private CopyOnWriteArrayList<Mail> processedList = new CopyOnWriteArrayList<>();

    private Thread myThread;

    private List<String> listTo;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MailService(List<String> listTo) {
        this.listTo = listTo;
    }

    /**
     * производим добавление нового письма в очередь на отправку
     * отправку из начала очереди письма заданному списку адресов
     * ожидание нового письма в очереди
     */
    public MailService() {
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Mail take = queue.take();
                        List<String> addresses = take.getListAddresses();
                        String text = take.getText();
                        for (String address : addresses) {
                            try (ExecutorService executorService1 = Executors.newSingleThreadExecutor()) {
                                executorService1.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        sendMail(new Mail(address, text));
                                        System.out.println("Mail sent to address: " + address);
                                    }
                                });
                                Thread.sleep(500L);
                                //TODO тут создать потоки для отправки письма нескольким адресатам параллельно
                            }
                        }
                        Thread.sleep(5000L);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });
    }

    public void addMail(Mail mail) {
        this.queue.add(mail);
        System.out.println(Arrays.toString(this.queue.toArray()));
        //App.showAlert("Info!", "Massage added to queue!", Alert.AlertType.INFORMATION);
    }

    public void sendMail(Mail mail) {
        boolean remove = this.queue.remove(mail);
        if (remove) {
            this.processedList.add(mail);
        }
    }


}
