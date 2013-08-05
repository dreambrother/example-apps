package com.blogspot.nikcode.jms;

import javax.ejb.MessageDriven;

/**
 *
 * @author nik
 */
@MessageDriven(mappedName = "topicDestination")
public class TopicMessageConsumer2 extends AbstractMessageConsumer {
}
