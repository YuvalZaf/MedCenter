import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GUIFinal extends JFrame 
{
	private JPanel contentPane;
	private JTextField nursetextField;
	private JTextField closeTimeField_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFinal frame = new GUIFinal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIFinal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int nurseNum = Integer.parseInt(nursetextField.getText());
				double closeTime = Double.parseDouble(closeTimeField_1.getText());
				
				if( nurseNum < 1 || nurseNum > 4)
				{
					JOptionPane.showMessageDialog(null, "The number of nurses should be between 1 to 4","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else if(closeTime<0 || closeTime > 24 )
				{
					JOptionPane.showMessageDialog(null, "The closing hour should be between 0 to 24","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					MedicalCenter newMed = new MedicalCenter(nurseNum, closeTime);
					
				}
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBounds(96, 192, 89, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_1.setBounds(225, 192, 89, 33);
		contentPane.add(btnNewButton_1);
		
		JLabel WecomelNewLabel = new JLabel("Welcome to Y&S  Emergency Medical Center!");
		WecomelNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		WecomelNewLabel.setBounds(21, 21, 405, 51);
		contentPane.add(WecomelNewLabel);
		
		JLabel NurseLabel = new JLabel("Please enter the num of nurses:");
		NurseLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		NurseLabel.setBounds(21, 91, 253, 14);
		contentPane.add(NurseLabel);
		
		JLabel lblNewLabel = new JLabel("PLease enter closing time:");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(21, 144, 191, 14);
		contentPane.add(lblNewLabel);
		
		nursetextField = new JTextField();
		nursetextField.setBounds(218, 86, 27, 20);
		contentPane.add(nursetextField);
		nursetextField.setColumns(10);
		
		closeTimeField_1 = new JTextField();
		closeTimeField_1.setText("8");
		closeTimeField_1.setColumns(10);
		closeTimeField_1.setBounds(218, 139, 27, 20);
		contentPane.add(closeTimeField_1);
	}
}
