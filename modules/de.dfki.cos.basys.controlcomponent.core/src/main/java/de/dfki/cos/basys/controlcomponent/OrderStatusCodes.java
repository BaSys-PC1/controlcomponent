package de.dfki.cos.basys.controlcomponent;

public class OrderStatusCodes {

	public static final int OK = 0;						//Good
	public static final int ACCEPTED = 1;				//Good_CompletesAsynchronously
	

	public static final int NothingToDo = -1;			//Bad_NothingToDo
	public static final int NotSupported = -2; 			//Bad_NotSupported
	public static final int InvalidState = -3;			//Bad_InvalidState
	public static final int RequestNotAllowed = -4;		//Bad_RequestNotAllowed
	public static final int IdentityTokenInvalid = -5;	//Bad_IdentityTokenInvalid
	public static final int IdentityTokenRejected = -6;	//Bad_IdentityTokenRejected
	public static final int NotImplemented = -7; 		//Bad_NotImplemented
	public static final int NotFound = -8; 				//Bad_NotFound
	public static final int EntryExists = -9; 			//Bad_EntryExists
	public static final int NoEntryExists = -10;		//Bad_NoEntryExists

	

	//Bad_UserAccessDenied

	
	
}
