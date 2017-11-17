package com.github.dreambrother.examples;

import com.github.dreambrother.examples.subject.EventsGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import rx.subjects.PublishSubject;

@Slf4j
public class SubjectTest {

    @Test
    public void testSubject() throws InterruptedException {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(val -> log.info("First subscriber got: {}", val));
        subject.subscribe(val -> log.info("Second subscriber got: {}", val));

        new EventsGenerator(subject).start();

        Thread.sleep(5000L);
    }
}
