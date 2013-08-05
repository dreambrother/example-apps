package com.blogspot.nikcode.jms;

import javax.ejb.MessageDriven;

/**
 *
 * @author nik
 */
@MessageDriven(mappedName = "topicDestination")
public class TopicMessageConsumer1 extends AbstractMessageConsumer {
}
