package com.skillbox.redisdemo;

import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
    private static final int DELETE_SECONDS_AGO = 2;

    // Допустим пользователи делают 500 запросов к сайту в секунду
    private static final int RPS = 500;

    // И всего на сайт заходило 1000 различных пользователей
    private static final int USERS = 1000;

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1; // 1 миллисекунда

    private static void log(int Id) {
        out.println("- На главной странице показываем пользователя " + Id);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        Random random = new Random();
        boolean payedService = false;
        redis.init();

        while (true) {
            // Эмулируем 20 пользователей
            for (int uniqueUsers = 1; uniqueUsers < 21; uniqueUsers++) {
                // Выполним 500 запросов
                for (int request = 0; request <= RPS; request++) {
                    int user_id = new Random().nextInt(USERS);
                    redis.logPageVisit(user_id);
                    Thread.sleep(SLEEP);
                }
                redis.deleteOldEntries(DELETE_SECONDS_AGO);

                int randomNum = random.nextInt((21 - 1) + 1) + 1;
                if (uniqueUsers % 10 == 0) {
                    payedService = true;
                    out.println("> Пользователь " + randomNum + " оплатил платную услугу");
                    log(randomNum);
                }

                int usersOnline = uniqueUsers;
                log(usersOnline);

                Thread.sleep(1000);
            }
        }
    }
}