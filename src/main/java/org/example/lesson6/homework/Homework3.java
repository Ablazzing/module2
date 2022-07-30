package org.example.lesson6.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Homework3 {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        //Задача №1
        //1. Создать класс User, с двумя атрибутами - int age, String name
        //2. Создать экземпляр класса HashSet<User>
        //3. Переопределить hashcode и equals у User так, чтобы при записи в HashSet, были только уникальные name юзера
        HashSet<User> users = new HashSet<>();
        User petr = new User(20, "Petr");
        User petr2 = new User(21, "Petr");
        User yuri = new User(21, "Yuri");
        users.add(petr);
        users.add(petr2);
        users.add(yuri);

        System.out.println(users);

        //Задача №2
        //1. Создать класс UserBroken, с двумя атрибутами - int age, String name
        //2. Создать экземпляр класса HashSet<UserBroken>
        //3. Переопределить hashcode и equals так, чтобы при вставке любого юзера в HashSet,
        // в hashSet всегда оставался один юзер, который последний был вставлен

        HashSet<UserBroken> users2 = new HashSet<>();
        UserBroken userBroken = new UserBroken(20, "Yuri");
        UserBroken userBroken2 = new UserBroken(20, "Petr");
        users2.add(userBroken);
        users2.add(userBroken2);
        System.out.println(users2);

        //Задача №3
        //1. Создать класс CarBroken
        //2. Создать экземпляра класса HashMap<CarBroken, Integer>
        //3. Переопределить hashcode и equals так, чтобы при вставке любого CarBroken в HashMap,
        //всегда CarBroken попадал в один бакет.
        HashMap<CarBroken, Integer> map = new HashMap<>();
        CarBroken carBroken = new CarBroken();
        CarBroken carBroken2 = new CarBroken();
        map.put(carBroken, 1);
        map.put(carBroken2, 2);


        //Задача №4
        //1. Создать аннотацию GetMetaData, которая может быть над атрибутом, методом, классом
        //2. Создать класс UserMeta, с атрибутом String name, int count, методом GetSuperName(String prefix)
        //3. Создать обработчик аннотации GetMetaData, так чтобы:
        //3.1 При нахождении @GetMetaData над классом, то печатаем все атрибуты класса
        //3.2 При нахождении @GetMetaData над атрибутом, чтобы печаталось значение атрибута этого поля
        //3.3 При нахождении @GetMetaData над методом, печатаем сколько времени выполнялся метод.
        //4. Создаем класс Runner, запускаем
        UserMeta userMeta = new UserMeta("Petr", 20);
        getMetaDataPreProccessor(userMeta);
    }

    public static void getMetaDataPreProccessor(UserMeta userMeta) throws IllegalAccessException, InvocationTargetException {
        if (userMeta.getClass().isAnnotationPresent(GetMetaData.class)) {
            Arrays.asList(userMeta.getClass().getDeclaredFields()).stream()
                    .peek(e -> e.setAccessible(true))
                    .forEach(System.out::println);
        }

        for (Field declaredField : userMeta.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(GetMetaData.class)) {
                System.out.println(declaredField.get(userMeta));
            }
        }

        for (Method declaredMethod : userMeta.getClass().getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(GetMetaData.class)) {
                long timeMillis = System.currentTimeMillis();
                Object arg = null;
                for (Class<?> parameterType : declaredMethod.getParameterTypes()) {
                    if (parameterType == String.class) {
                        arg = "prefix";
                    } else if (parameterType == Integer.class) {
                        arg = 2;
                    }
                }

                declaredMethod.invoke(userMeta, arg);
                long timeInvoked = System.currentTimeMillis() - timeMillis;
                System.out.println(timeInvoked);
            }
        }
    }
}
