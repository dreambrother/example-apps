package com.blogspot.nikcode.jms;

import javax.ejb.MessageDriven;

/**
 *
 * @author nik
 */
@MessageDriven(mappedName = "queueDestination")
public class QueueMessageConsumer2 extends AbstractMessageConsumer {
}
