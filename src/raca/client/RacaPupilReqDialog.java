/*
 * Instituto de Matematica Pura e Aplicada - IMPA
 * Departamento de Dinamica dos Fluidos
 *
 */

package raca.client;


import java.awt.*;
import javax.swing.*;
import javax.swing.JDialog;
import java.awt.event.*;

/**
 *
 * <p>The dialog used to grant permission to a Slave user to take part on a RPN SESSION </p>
 */
public class RacaPupilReqDialog extends JDialog {

	private String clientID;

	JPanel mainPanel = new JPanel();    
	JPanel infoPanel = new JPanel();    
	JPanel buttonsPanel = new JPanel();    

	JComboBox colorCombo = new JComboBox();

	JButton allowButton = new JButton("Allow");
	JButton denyButton = new JButton("Deny");

	BorderLayout gridLayout = new BorderLayout();

	String colorChosen = new String("Blue");

	public JLabel infoLabel = new JLabel("A request for joining the session has arrived from : ");


	public RacaPupilReqDialog(String reqClientID) {

		try {

			init(reqClientID);

			setLocationRelativeTo(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void init(String reqClientID) throws Exception {


		clientID = new String(reqClientID);

		setResizable(false);
		setTitle("RPn Session Access Grant");

		//MKPCommandModule.PEN_COLOR_MAP.put(clientID,"Blue");

		infoLabel.setText(infoLabel.getText() + clientID);
		infoPanel.add(infoLabel);

		colorCombo.addItem("Blue");
		colorCombo.addItem("Yellow");

		colorCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorChosen = (String)((JComboBox) e.getSource()).getSelectedItem();

				//MKPCommandModule.PEN_COLOR_MAP.put(clientID,colorChosen);

			}
		});

		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(denyButton,BorderLayout.WEST);
		buttonsPanel.add(allowButton,BorderLayout.CENTER);

		JPanel leftPanel = new JPanel();
		leftPanel.add(buttonsPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.add(colorCombo);

		JPanel fullPanel = new JPanel();
		fullPanel.setLayout(new FlowLayout());
		fullPanel.add(leftPanel);
		fullPanel.add(rightPanel);

		mainPanel.setLayout(gridLayout);
		mainPanel.add(infoPanel,BorderLayout.NORTH);
		mainPanel.add(fullPanel,BorderLayout.SOUTH);

		getContentPane().add(mainPanel);

		allowButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				allowButton_actionPerformed(e);
			}
		});

		denyButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				denyButton_actionPerformed(e);
			}
		});


		pack();


	}

	void allowButton_actionPerformed(ActionEvent e) {

		RacaHttpPublisher publisher = new RacaHttpPublisher(RacaNetworkProxy.PUPIL_ACK_TOPIC_NAME, "sessionID");

		//publisher.publish(RacaNetworkProxy.PUPIL_ACK_LOG_MSG + '|' + clientID + '|' + RacaNetworkProxy.instance().aspectRatio() + '|' + colorChosen);

		publisher.close();

		dispose();

	}

	void denyButton_actionPerformed(ActionEvent e) {

		dispose();
	}

}