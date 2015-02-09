package raca.client;

import raca.util.client.RacaStringUtil;

/**
*
* @author ANTENA DIGITAL
*/
public class RacaPupilRequestParser implements RacaMessageParser {

	private String topicName_ = RacaNetworkProxy.PUPIL_REQ_QUEUE_NAME;      //PUPIL_REQ_LOG_MSG
	private String sessionID_;
	private RacaAttendee attendee_ = null;
	
	public RacaPupilRequestParser(String sessionID, RacaAttendee attendee) {

		sessionID_ = sessionID;
		attendee_ = attendee;		
	}
	
	@Override
	public void parse(Object obj) {
			
		if (obj instanceof String){
			String text = obj.toString();
		
			if (attendee_.isMaster(sessionID_)== true) {

                RacaPupilReqDialog reqDialog = new RacaPupilReqDialog(RacaStringUtil.filterClientID(text), attendee_, sessionID_);
                reqDialog.setVisible(true);
            }		
		}		
	}

	@Override
	public String topicName() {
		
		return topicName_ + sessionID_;
	}

	@Override
	public String hitURL() {
		
		return topicName_ + sessionID_;
	}
}
