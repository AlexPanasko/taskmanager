package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenSearchingForNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1,"Купить хлеб"));
        todos.add(new Epic(2, new String[]{"Сходить в банк"}));
        Task[] expected = {};
        Task[] actual = todos.search("Убрать в комнате");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayWithOneTaskWhenSearchingWithOneMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1,"Купить хлеб"));
        todos.add(new Epic(2, new String[]{"Сходить в банк"}));
        Task[] expected = { new SimpleTask(1, "Купить хлеб") };
        Task[] actual = todos.search("хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayWithAllTasksWhenSearchingWithEmptyQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1,"Купить хлеб"));
        todos.add(new Epic(2, new String[]{"Сходить в банк"}));
        Task[] expected = { new SimpleTask(1, "Купить хлеб"), new Epic(2, new String[]{"Сходить в банк"}) };
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }
}
