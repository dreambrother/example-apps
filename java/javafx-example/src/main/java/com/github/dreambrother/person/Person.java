package com.github.dreambrother.person;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

public class Person {

    private final StringProperty name = new SimpleStringProperty(this, "name", "");
    private final StringProperty notes = new SimpleStringProperty(this, "notes", "sample notes");

    public Person() {
    }

    public Person(String name, String notes) {
        this.name.set(name);
        this.notes.set(notes);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNotes() {
        return notes.get();
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    @Override
    public String toString() {
        return name.get();
    }

    public static Callback<Person, Observable[]> extractor = p -> new Observable[] { p.nameProperty() };
}
