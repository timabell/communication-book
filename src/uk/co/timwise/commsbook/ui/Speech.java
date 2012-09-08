package uk.co.timwise.commsbook.ui;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speech {
	public static void speakSentence(String sentence) {
		Voice voice = VoiceManager.getInstance().getVoice("kevin16");
		if (voice == null) {
			System.err.println("Voice error. Couldn't load voice kevin16.");
			System.exit(1);
		}
		voice.allocate();
		voice.speak(sentence);
		voice.deallocate();
	}
}
