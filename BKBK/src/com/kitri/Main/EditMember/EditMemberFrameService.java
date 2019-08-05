package com.kitri.Main.EditMember;

public class EditMemberFrameService {
	
	EditMemberFrameController emfc;
	public EditMemberFrameService(EditMemberFrameController emfc) {
		this.emfc = emfc;
	}
	
	public void clear() {
		emfc.emf.tfName.setText("");
		emfc.emf.tfEAdd.setText("");
		emfc.emf.tfEP1.setText("");
		emfc.emf.tfEP2.setText("");
		emfc.emf.tfEP3.setText("");
	}
}
