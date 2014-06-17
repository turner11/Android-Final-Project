package com.finalProject.smstranslator.SMSHalper;

// TODO: Auto-generated Javadoc
/**
 * The Class SMSProviderContruct.
 */
public class SMSProviderContruct {
	
	/** The Constant MESSAGE_TYPE_ALL. */
	public static final int MESSAGE_TYPE_ALL    = 0;
    
    /** The Constant MESSAGE_TYPE_INBOX. */
    public static final int MESSAGE_TYPE_INBOX  = 1;
    
    /** The Constant MESSAGE_TYPE_SENT. */
    public static final int MESSAGE_TYPE_SENT   = 2;
    
    /** The Constant MESSAGE_TYPE_DRAFT. */
    public static final int MESSAGE_TYPE_DRAFT  = 3;
    
    /** The Constant MESSAGE_TYPE_OUTBOX. */
    public static final int MESSAGE_TYPE_OUTBOX = 4;
    
    /** The Constant MESSAGE_TYPE_FAILED. */
    public static final int MESSAGE_TYPE_FAILED = 5; // for failed outgoing messages
    
    /** The Constant MESSAGE_TYPE_QUEUED. */
    public static final int MESSAGE_TYPE_QUEUED = 6; // for messages to send later
	
	
	/** The Constant ID. */
	public static final String ID = "_id";
	
	/** The Constant TYPE. */
	public static final String TYPE = "type";
	
	/** The Constant BODY. */
	public static final String BODY = "body";
	
	/** The Constant ADDRESS. */
	public static final String ADDRESS = "address";
	
	/** The Constant PERSON_ID. */
	public static final String PERSON_ID = "person";
	
	/** The Constant DATE. */
	public static final String DATE = "date";
    
    /** The Constant DATE_SENT. */
    public static final String DATE_SENT = "date_sent";
    
    /** The Constant READ. */
    public static final String READ = "read";
	
}