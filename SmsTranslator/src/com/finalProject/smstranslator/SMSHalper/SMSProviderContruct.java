package com.finalProject.smstranslator.SMSHalper;

public class SMSProviderContruct {
	public static final int MESSAGE_TYPE_ALL    = 0;
    public static final int MESSAGE_TYPE_INBOX  = 1;
    public static final int MESSAGE_TYPE_SENT   = 2;
    public static final int MESSAGE_TYPE_DRAFT  = 3;
    public static final int MESSAGE_TYPE_OUTBOX = 4;
    public static final int MESSAGE_TYPE_FAILED = 5; // for failed outgoing messages
    public static final int MESSAGE_TYPE_QUEUED = 6; // for messages to send later
	
	
	public static final String ID = "_id";
	public static final String TYPE = "type";
	public static final String BODY = "body";
	public static final String ADDRESS = "address";
	public static final String PERSON_ID = "person";
	public static final String DATE = "date";
    public static final String DATE_SENT = "date_sent";
    public static final String READ = "read";
	
}